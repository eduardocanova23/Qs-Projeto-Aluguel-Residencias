package com.qos.fakebnb.process.paymentProcess;

import com.qos.fakebnb.repository.PaymentProcessRepository;
import com.qos.fakebnb.service.PaymentService;
import com.qos.fakebnb.service.dto.PaymentDTO;
import com.qos.fakebnb.service.dto.PaymentProcessDTO;
import com.qos.fakebnb.service.mapper.PaymentProcessMapper;
import org.akip.repository.TaskInstanceRepository;
import org.akip.service.TaskInstanceService;
import org.akip.service.dto.TaskInstanceDTO;
import org.akip.service.mapper.TaskInstanceMapper;
import org.springframework.stereotype.Component;

@Component
public class PaymentDetailsTaskService {

    private final TaskInstanceService taskInstanceService;

    private final PaymentService paymentService;

    private final TaskInstanceRepository taskInstanceRepository;

    private final PaymentProcessRepository paymentProcessRepository;

    private final TaskInstanceMapper taskInstanceMapper;

    private final PaymentDetailsTaskMapper paymentDetailsTaskMapper;

    private final PaymentProcessMapper paymentProcessMapper;

    public PaymentDetailsTaskService(
        TaskInstanceService taskInstanceService,
        PaymentService paymentService,
        TaskInstanceRepository taskInstanceRepository,
        PaymentProcessRepository paymentProcessRepository,
        TaskInstanceMapper taskInstanceMapper,
        PaymentDetailsTaskMapper paymentDetailsTaskMapper,
        PaymentProcessMapper paymentProcessMapper
    ) {
        this.taskInstanceService = taskInstanceService;
        this.paymentService = paymentService;
        this.taskInstanceRepository = taskInstanceRepository;
        this.paymentProcessRepository = paymentProcessRepository;
        this.taskInstanceMapper = taskInstanceMapper;
        this.paymentDetailsTaskMapper = paymentDetailsTaskMapper;
        this.paymentProcessMapper = paymentProcessMapper;
    }

    public PaymentDetailsTaskContextDTO loadContext(Long taskInstanceId) {
        TaskInstanceDTO taskInstanceDTO = taskInstanceRepository
            .findById(taskInstanceId)
            .map(taskInstanceMapper::toDTOLoadTaskContext)
            .orElseThrow();

        PaymentProcessDTO paymentProcess = paymentProcessRepository
            .findByProcessInstanceId(taskInstanceDTO.getProcessInstance().getId())
            .map(paymentDetailsTaskMapper::toPaymentProcessDTO)
            .orElseThrow();

        PaymentDetailsTaskContextDTO paymentDetailsTaskContext = new PaymentDetailsTaskContextDTO();
        paymentDetailsTaskContext.setTaskInstance(taskInstanceDTO);
        paymentDetailsTaskContext.setPaymentProcess(paymentProcess);

        return paymentDetailsTaskContext;
    }

    public PaymentDetailsTaskContextDTO claim(Long taskInstanceId) {
        taskInstanceService.claim(taskInstanceId);
        return loadContext(taskInstanceId);
    }

    public void save(PaymentDetailsTaskContextDTO paymentDetailsTaskContext) {
        PaymentDTO paymentDTO = paymentService.findOne(paymentDetailsTaskContext.getPaymentProcess().getPayment().getId()).orElseThrow();
        paymentDTO.setCardNumber(paymentDetailsTaskContext.getPaymentProcess().getPayment().getCardNumber());
        paymentDTO.setCardVerificationValue(paymentDetailsTaskContext.getPaymentProcess().getPayment().getCardVerificationValue());
        paymentDTO.setExpirationDate(paymentDetailsTaskContext.getPaymentProcess().getPayment().getExpirationDate());
        paymentDTO.setPhoneNumber(paymentDetailsTaskContext.getPaymentProcess().getPayment().getPhoneNumber());
        paymentService.save(paymentDTO);
    }

    public void complete(PaymentDetailsTaskContextDTO paymentDetailsTaskContext) {
        save(paymentDetailsTaskContext);
        PaymentProcessDTO paymentProcess = paymentProcessRepository
            .findByProcessInstanceId(paymentDetailsTaskContext.getPaymentProcess().getProcessInstance().getId())
            .map(paymentProcessMapper::toDto)
            .orElseThrow();
        taskInstanceService.complete(paymentDetailsTaskContext.getTaskInstance(), paymentProcess);
    }
}
