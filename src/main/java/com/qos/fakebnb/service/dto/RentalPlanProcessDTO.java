package com.qos.fakebnb.service.dto;

import java.io.Serializable;
import java.util.Objects;
import org.akip.service.dto.ProcessInstanceDTO;

/**
 * A DTO for the {@link com.qos.fakebnb.domain.RentalPlanProcess} entity.
 */
public class RentalPlanProcessDTO implements Serializable {

    private Long id;

    private ProcessInstanceDTO processInstance;

    private RentalPlanDTO rentalPlan;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProcessInstanceDTO getProcessInstance() {
        return processInstance;
    }

    public void setProcessInstance(ProcessInstanceDTO processInstance) {
        this.processInstance = processInstance;
    }

    public RentalPlanDTO getRentalPlan() {
        return rentalPlan;
    }

    public void setRentalPlan(RentalPlanDTO rentalPlan) {
        this.rentalPlan = rentalPlan;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RentalPlanProcessDTO)) {
            return false;
        }

        RentalPlanProcessDTO rentalPlanProcessDTO = (RentalPlanProcessDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, rentalPlanProcessDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RentalPlanProcessDTO{" +
            "id=" + getId() +
            ", processInstance=" + getProcessInstance() +
            ", rentalPlan=" + getRentalPlan() +
            "}";
    }
}
