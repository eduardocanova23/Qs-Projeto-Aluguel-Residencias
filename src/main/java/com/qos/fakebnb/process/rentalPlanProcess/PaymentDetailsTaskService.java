package com.qos.fakebnb.process.rentalPlanProcess;

import com.qos.fakebnb.repository.RentalPlanProcessRepository;
import com.qos.fakebnb.service.RentalPlanService;
import com.qos.fakebnb.service.dto.RentalPlanDTO;
import com.qos.fakebnb.service.dto.RentalPlanProcessDTO;
import com.qos.fakebnb.service.mapper.RentalPlanProcessMapper;
import org.akip.repository.TaskInstanceRepository;
import org.akip.service.TaskInstanceService;
import org.akip.service.dto.TaskInstanceDTO;
import org.akip.service.mapper.TaskInstanceMapper;
import org.springframework.stereotype.Component;

@Component
public class PaymentDetailsTaskService {

    private final TaskInstanceService taskInstanceService;

    private final RentalPlanService rentalPlanService;

    private final TaskInstanceRepository taskInstanceRepository;

    private final RentalPlanProcessRepository rentalPlanProcessRepository;

    private final TaskInstanceMapper taskInstanceMapper;

    private final PaymentDetailsTaskMapper paymentDetailsTaskMapper;

    private final RentalPlanProcessMapper rentalPlanProcessMapper;

    public PaymentDetailsTaskService(
        TaskInstanceService taskInstanceService,
        RentalPlanService rentalPlanService,
        TaskInstanceRepository taskInstanceRepository,
        RentalPlanProcessRepository rentalPlanProcessRepository,
        TaskInstanceMapper taskInstanceMapper,
        PaymentDetailsTaskMapper paymentDetailsTaskMapper,
        RentalPlanProcessMapper rentalPlanProcessMapper
    ) {
        this.taskInstanceService = taskInstanceService;
        this.rentalPlanService = rentalPlanService;
        this.taskInstanceRepository = taskInstanceRepository;
        this.rentalPlanProcessRepository = rentalPlanProcessRepository;
        this.taskInstanceMapper = taskInstanceMapper;
        this.paymentDetailsTaskMapper = paymentDetailsTaskMapper;
        this.rentalPlanProcessMapper = rentalPlanProcessMapper;
    }

    public PaymentDetailsTaskContextDTO loadContext(Long taskInstanceId) {
        TaskInstanceDTO taskInstanceDTO = taskInstanceRepository
            .findById(taskInstanceId)
            .map(taskInstanceMapper::toDTOLoadTaskContext)
            .orElseThrow();

        RentalPlanProcessDTO rentalPlanProcess = rentalPlanProcessRepository
            .findByProcessInstanceId(taskInstanceDTO.getProcessInstance().getId())
            .map(paymentDetailsTaskMapper::toRentalPlanProcessDTO)
            .orElseThrow();

        PaymentDetailsTaskContextDTO paymentDetailsTaskContext = new PaymentDetailsTaskContextDTO();
        paymentDetailsTaskContext.setTaskInstance(taskInstanceDTO);
        paymentDetailsTaskContext.setRentalPlanProcess(rentalPlanProcess);

        return paymentDetailsTaskContext;
    }

    public PaymentDetailsTaskContextDTO claim(Long taskInstanceId) {
        taskInstanceService.claim(taskInstanceId);
        return loadContext(taskInstanceId);
    }

    public void save(PaymentDetailsTaskContextDTO paymentDetailsTaskContext) {
        RentalPlanDTO rentalPlanDTO = rentalPlanService
            .findOne(paymentDetailsTaskContext.getRentalPlanProcess().getRentalPlan().getId())
            .orElseThrow();
        rentalPlanDTO.setUserName(paymentDetailsTaskContext.getRentalPlanProcess().getRentalPlan().getUserName());
        rentalPlanDTO.setCardNumber(paymentDetailsTaskContext.getRentalPlanProcess().getRentalPlan().getCardNumber());
        rentalPlanDTO.setCardVerificationValue(paymentDetailsTaskContext.getRentalPlanProcess().getRentalPlan().getCardVerificationValue());
        rentalPlanDTO.setExpirationDate(paymentDetailsTaskContext.getRentalPlanProcess().getRentalPlan().getExpirationDate());
        rentalPlanService.save(rentalPlanDTO);
    }

    public void complete(PaymentDetailsTaskContextDTO paymentDetailsTaskContext) {
        save(paymentDetailsTaskContext);
        RentalPlanProcessDTO rentalPlanProcess = rentalPlanProcessRepository
            .findByProcessInstanceId(paymentDetailsTaskContext.getRentalPlanProcess().getProcessInstance().getId())
            .map(rentalPlanProcessMapper::toDto)
            .orElseThrow();
        taskInstanceService.complete(paymentDetailsTaskContext.getTaskInstance(), rentalPlanProcess);
    }
}
