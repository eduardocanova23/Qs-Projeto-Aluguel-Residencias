package com.qos.fakebnb.delegate;

import com.qos.fakebnb.service.MailService;
import com.qos.fakebnb.service.dto.RentalPlanDTO;
import com.qos.fakebnb.service.dto.RentalPlanProcessDTO;
import java.util.Locale;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Component
public class EmailRenterDelegate implements JavaDelegate {

    @Autowired
    MailService mailService;

    @Autowired
    SpringTemplateEngine templateEngine;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        RentalPlanProcessDTO rentalPlanProcess = (RentalPlanProcessDTO) delegateExecution.getVariable("processInstance");
        RentalPlanDTO rentalPlan = rentalPlanProcess.getRentalPlan();
        String to = rentalPlan.getUserEmail();
        String subject = "[FakeBnB] Summary of your reservation";
        Context context = new Context(Locale.getDefault());
        context.setVariable("rentalPlan", rentalPlan);
        String content = templateEngine.process("rentalPlanProcess/rentalPlanSummaryEmail", context);
        mailService.sendEmail(to, subject, content, false, true);
    }
}
