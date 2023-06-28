package com.qos.fakebnb.service.dto;

import java.io.Serializable;
import java.util.Objects;
import org.akip.service.dto.ProcessInstanceDTO;

/**
 * A DTO for the {@link com.qos.fakebnb.domain.PaymentProcess} entity.
 */
public class PaymentProcessDTO implements Serializable {

    private Long id;

    private ProcessInstanceDTO processInstance;

    private PaymentDTO payment;

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

    public PaymentDTO getPayment() {
        return payment;
    }

    public void setPayment(PaymentDTO payment) {
        this.payment = payment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PaymentProcessDTO)) {
            return false;
        }

        PaymentProcessDTO paymentProcessDTO = (PaymentProcessDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, paymentProcessDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PaymentProcessDTO{" +
            "id=" + getId() +
            ", processInstance=" + getProcessInstance() +
            ", payment=" + getPayment() +
            "}";
    }
}
