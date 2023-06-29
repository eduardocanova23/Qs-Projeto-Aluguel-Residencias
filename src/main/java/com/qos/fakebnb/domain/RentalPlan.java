package com.qos.fakebnb.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A RentalPlan.
 */
@Entity
@Table(name = "rental_plan")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RentalPlan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "city")
    private String city;

    @Column(name = "neighborhood")
    private String neighborhood;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "rental_confirmation_number")
    private String rentalConfirmationNumber;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "card_verification_value")
    private String cardVerificationValue;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @ManyToOne
    private LivingQuarters livingQuarters;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RentalPlan id(Long id) {
        this.id = id;
        return this;
    }

    public String getCity() {
        return this.city;
    }

    public RentalPlan city(String city) {
        this.city = city;
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNeighborhood() {
        return this.neighborhood;
    }

    public RentalPlan neighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
        return this;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getUserName() {
        return this.userName;
    }

    public RentalPlan userName(String userName) {
        this.userName = userName;
        return this;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return this.userEmail;
    }

    public RentalPlan userEmail(String userEmail) {
        this.userEmail = userEmail;
        return this;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public RentalPlan startDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }

    public RentalPlan endDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getRentalConfirmationNumber() {
        return this.rentalConfirmationNumber;
    }

    public RentalPlan rentalConfirmationNumber(String rentalConfirmationNumber) {
        this.rentalConfirmationNumber = rentalConfirmationNumber;
        return this;
    }

    public void setRentalConfirmationNumber(String rentalConfirmationNumber) {
        this.rentalConfirmationNumber = rentalConfirmationNumber;
    }

    public String getCardNumber() {
        return this.cardNumber;
    }

    public RentalPlan cardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
        return this;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardVerificationValue() {
        return this.cardVerificationValue;
    }

    public RentalPlan cardVerificationValue(String cardVerificationValue) {
        this.cardVerificationValue = cardVerificationValue;
        return this;
    }

    public void setCardVerificationValue(String cardVerificationValue) {
        this.cardVerificationValue = cardVerificationValue;
    }

    public LocalDate getExpirationDate() {
        return this.expirationDate;
    }

    public RentalPlan expirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
        return this;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public LivingQuarters getLivingQuarters() {
        return this.livingQuarters;
    }

    public RentalPlan livingQuarters(LivingQuarters livingQuarters) {
        this.setLivingQuarters(livingQuarters);
        return this;
    }

    public void setLivingQuarters(LivingQuarters livingQuarters) {
        this.livingQuarters = livingQuarters;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RentalPlan)) {
            return false;
        }
        return id != null && id.equals(((RentalPlan) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RentalPlan{" +
            "id=" + getId() +
            ", city='" + getCity() + "'" +
            ", neighborhood='" + getNeighborhood() + "'" +
            ", userName='" + getUserName() + "'" +
            ", userEmail='" + getUserEmail() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", rentalConfirmationNumber='" + getRentalConfirmationNumber() + "'" +
            ", cardNumber='" + getCardNumber() + "'" +
            ", cardVerificationValue='" + getCardVerificationValue() + "'" +
            ", expirationDate='" + getExpirationDate() + "'" +
            "}";
    }
}
