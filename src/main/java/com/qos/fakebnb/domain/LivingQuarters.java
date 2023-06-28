package com.qos.fakebnb.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A LivingQuarters.
 */
@Entity
@Table(name = "living_quarters")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class LivingQuarters implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "city")
    private String city;

    @Column(name = "neighborhood")
    private String neighborhood;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LivingQuarters id(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public LivingQuarters name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return this.city;
    }

    public LivingQuarters city(String city) {
        this.city = city;
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNeighborhood() {
        return this.neighborhood;
    }

    public LivingQuarters neighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
        return this;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getDescription() {
        return this.description;
    }

    public LivingQuarters description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return this.image;
    }

    public LivingQuarters image(String image) {
        this.image = image;
        return this;
    }

    public void setImage(String image) {
        this.image = image;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LivingQuarters)) {
            return false;
        }
        return id != null && id.equals(((LivingQuarters) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LivingQuarters{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", city='" + getCity() + "'" +
            ", neighborhood='" + getNeighborhood() + "'" +
            ", description='" + getDescription() + "'" +
            ", image='" + getImage() + "'" +
            "}";
    }
}
