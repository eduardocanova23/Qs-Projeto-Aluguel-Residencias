package com.qos.fakebnb.service;

import com.qos.fakebnb.domain.RentalPlanProcess;
import com.qos.fakebnb.repository.RentalPlanProcessRepository;
import com.qos.fakebnb.repository.RentalPlanRepository;
import com.qos.fakebnb.service.dto.RentalPlanProcessDTO;
import com.qos.fakebnb.service.mapper.RentalPlanProcessMapper;
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
 * Service Implementation for managing {@link RentalPlanProcess}.
 */
@Service
@Transactional
public class RentalPlanProcessService {

    public static final String BPMN_PROCESS_DEFINITION_ID = "RentalPlanProcess";

    private final Logger log = LoggerFactory.getLogger(RentalPlanProcessService.class);

    private final ProcessInstanceService processInstanceService;

    private final RentalPlanRepository rentalPlanRepository;

    private final RentalPlanProcessRepository rentalPlanProcessRepository;

    private final RentalPlanProcessMapper rentalPlanProcessMapper;

    public RentalPlanProcessService(
        ProcessInstanceService processInstanceService,
        RentalPlanRepository rentalPlanRepository,
        RentalPlanProcessRepository rentalPlanProcessRepository,
        RentalPlanProcessMapper rentalPlanProcessMapper
    ) {
        this.processInstanceService = processInstanceService;
        this.rentalPlanRepository = rentalPlanRepository;
        this.rentalPlanProcessRepository = rentalPlanProcessRepository;
        this.rentalPlanProcessMapper = rentalPlanProcessMapper;
    }

    /**
     * Save a rentalPlanProcess.
     *
     * @param rentalPlanProcessDTO the entity to save.
     * @return the persisted entity.
     */
    public RentalPlanProcessDTO create(RentalPlanProcessDTO rentalPlanProcessDTO) {
        log.debug("Request to save RentalPlanProcess : {}", rentalPlanProcessDTO);

        RentalPlanProcess rentalPlanProcess = rentalPlanProcessMapper.toEntity(rentalPlanProcessDTO);

        //Saving the domainEntity
        rentalPlanRepository.save(rentalPlanProcess.getRentalPlan());

        //Creating the process instance in the Camunda and setting it in the process entity
        ProcessInstance processInstance = processInstanceService.create(
            BPMN_PROCESS_DEFINITION_ID,
            "RentalPlan#" + rentalPlanProcess.getRentalPlan().getId(),
            rentalPlanProcess
        );
        rentalPlanProcess.setProcessInstance(processInstance);

        //Saving the process entity
        rentalPlanProcess = rentalPlanProcessRepository.save(rentalPlanProcess);
        return rentalPlanProcessMapper.toDto(rentalPlanProcess);
    }

    /**
     * Get all the rentalPlanProcesss.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<RentalPlanProcessDTO> findAll() {
        log.debug("Request to get all RentalPlanProcesss");
        return rentalPlanProcessRepository
            .findAll()
            .stream()
            .map(rentalPlanProcessMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one rentalPlanProcess by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<RentalPlanProcessDTO> findOne(Long id) {
        log.debug("Request to get RentalPlanProcess : {}", id);
        return rentalPlanProcessRepository.findById(id).map(rentalPlanProcessMapper::toDto);
    }

    /**
     * Get one rentalPlanProcess by id.
     *
     * @param processInstanceId the id of the processInstance associated to the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<RentalPlanProcessDTO> findByProcessInstanceId(Long processInstanceId) {
        log.debug("Request to get RentalPlanProcess by  processInstanceId: {}", processInstanceId);
        return rentalPlanProcessRepository.findByProcessInstanceId(processInstanceId).map(rentalPlanProcessMapper::toDto);
    }
}
