package com.qos.fakebnb.process.paymentProcess;

import com.qos.fakebnb.service.dto.PaymentProcessDTO;
import org.akip.service.dto.TaskInstanceDTO;

public class PaymentDetailsTaskContextDTO {

    private PaymentProcessDTO paymentProcess;
    private TaskInstanceDTO taskInstance;

    public PaymentProcessDTO getPaymentProcess() {
        return paymentProcess;
    }

    public void setPaymentProcess(PaymentProcessDTO paymentProcess) {
        this.paymentProcess = paymentProcess;
    }

    public TaskInstanceDTO getTaskInstance() {
        return taskInstance;
    }

    public void setTaskInstance(TaskInstanceDTO taskInstance) {
        this.taskInstance = taskInstance;
    }
}
