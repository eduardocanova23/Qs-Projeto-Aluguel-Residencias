package com.qos.fakebnb.process.rentalPlanProcess;

import com.qos.fakebnb.service.dto.RentalPlanProcessDTO;
import org.akip.service.dto.TaskInstanceDTO;

public class PaymentDetailsTaskContextDTO {

    private RentalPlanProcessDTO rentalPlanProcess;
    private TaskInstanceDTO taskInstance;

    public RentalPlanProcessDTO getRentalPlanProcess() {
        return rentalPlanProcess;
    }

    public void setRentalPlanProcess(RentalPlanProcessDTO rentalPlanProcess) {
        this.rentalPlanProcess = rentalPlanProcess;
    }

    public TaskInstanceDTO getTaskInstance() {
        return taskInstance;
    }

    public void setTaskInstance(TaskInstanceDTO taskInstance) {
        this.taskInstance = taskInstance;
    }
}
