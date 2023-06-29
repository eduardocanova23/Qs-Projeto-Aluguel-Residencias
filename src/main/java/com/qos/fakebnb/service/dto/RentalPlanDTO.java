package com.qos.fakebnb.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link com.qos.fakebnb.domain.RentalPlan} entity.
 */
public class RentalPlanDTO implements Serializable {

    private Long id;

    private String targetAddress;

    private String userName;

    private String userEmail;

    private LocalDate startDate;

    private LocalDate endDate;

    private String rentalConfirmationNumber;

    private LivingQuartersDTO livingQuarters;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTargetAddress() {
        return targetAddress;
    }

    public void setTargetAddress(String targetAddress) {
        this.targetAddress = targetAddress;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getRentalConfirmationNumber() {
        return rentalConfirmationNumber;
    }

    public void setRentalConfirmationNumber(String rentalConfirmationNumber) {
        this.rentalConfirmationNumber = rentalConfirmationNumber;
    }

    public LivingQuartersDTO getLivingQuarters() {
        return livingQuarters;
    }

    public void setLivingQuarters(LivingQuartersDTO livingQuarters) {
        this.livingQuarters = livingQuarters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RentalPlanDTO)) {
            return false;
        }

        RentalPlanDTO rentalPlanDTO = (RentalPlanDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, rentalPlanDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RentalPlanDTO{" +
            "id=" + getId() +
            ", targetAddress='" + getTargetAddress() + "'" +
            ", userName='" + getUserName() + "'" +
            ", userEmail='" + getUserEmail() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", rentalConfirmationNumber='" + getRentalConfirmationNumber() + "'" +
            ", livingQuarters=" + getLivingQuarters() +
            "}";
    }
}
