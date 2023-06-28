package com.qos.fakebnb.service;

import com.qos.fakebnb.domain.PaymentProcess;
import com.qos.fakebnb.repository.PaymentProcessRepository;
import com.qos.fakebnb.repository.PaymentRepository;
import com.qos.fakebnb.service.dto.PaymentProcessDTO;
import com.qos.fakebnb.service.mapper.PaymentProcessMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.akip.domain.ProcessInstance;
import org.akip.service.ProcessInstanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link PaymentProcess}.
 */
@Service
@Transactional
public class PaymentProcessService {

    public static final String BPMN_PROCESS_DEFINITION_ID = "FakeAirBnBProcess";

    private final Logger log = LoggerFactory.getLogger(PaymentProcessService.class);

    private final ProcessInstanceService processInstanceService;

    private final PaymentRepository paymentRepository;

    private final PaymentProcessRepository paymentProcessRepository;

    private final PaymentProcessMapper paymentProcessMapper;

    public PaymentProcessService(
        ProcessInstanceService processInstanceService,
        PaymentRepository paymentRepository,
        PaymentProcessRepository paymentProcessRepository,
        PaymentProcessMapper paymentProcessMapper
    ) {
        this.processInstanceService = processInstanceService;
        this.paymentRepository = paymentRepository;
        this.paymentProcessRepository = paymentProcessRepository;
        this.paymentProcessMapper = paymentProcessMapper;
    }

    /**
     * Save a paymentProcess.
     *
     * @param paymentProcessDTO the entity to save.
     * @return the persisted entity.
     */
    public PaymentProcessDTO create(PaymentProcessDTO paymentProcessDTO) {
        log.debug("Request to save PaymentProcess : {}", paymentProcessDTO);

        PaymentProcess paymentProcess = paymentProcessMapper.toEntity(paymentProcessDTO);

        //Saving the domainEntity
        paymentRepository.save(paymentProcess.getPayment());

        //Creating the process instance in the Camunda and setting it in the process entity
        ProcessInstance processInstance = processInstanceService.create(
            BPMN_PROCESS_DEFINITION_ID,
            "Payment#" + paymentProcess.getPayment().getId(),
            paymentProcess
        );
        paymentProcess.setProcessInstance(processInstance);

        //Saving the process entity
        paymentProcess = paymentProcessRepository.save(paymentProcess);
        return paymentProcessMapper.toDto(paymentProcess);
    }

    /**
     * Get all the paymentProcesss.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<PaymentProcessDTO> findAll() {
        log.debug("Request to get all PaymentProcesss");
        return paymentProcessRepository
            .findAll()
            .stream()
            .map(paymentProcessMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one paymentProcess by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PaymentProcessDTO> findOne(Long id) {
        log.debug("Request to get PaymentProcess : {}", id);
        return paymentProcessRepository.findById(id).map(paymentProcessMapper::toDto);
    }

    /**
     * Get one paymentProcess by id.
     *
     * @param processInstanceId the id of the processInstance associated to the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PaymentProcessDTO> findByProcessInstanceId(Long processInstanceId) {
        log.debug("Request to get PaymentProcess by  processInstanceId: {}", processInstanceId);
        return paymentProcessRepository.findByProcessInstanceId(processInstanceId).map(paymentProcessMapper::toDto);
    }
}
