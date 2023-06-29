package com.qos.fakebnb.delegate;

import java.time.LocalDateTime;  
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import com.qos.fakebnb.service.dto.RentalPlanProcessDTO;

@Component
public class ProcessPaymentDelegate implements JavaDelegate{
    
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        RentalPlanProcessDTO rentalPlanProcess = (RentalPlanProcessDTO) delegateExecution.getVariable("processInstance");
        Boolean paymentAccepted = rentalPlanProcess.getRentalPlan().getExpirationDate().isBefore(java.time.LocalDate.now());
        delegateExecution.setVariable("paymentAccepted", paymentAccepted);
    }
}
