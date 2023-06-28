package com.qos.fakebnb.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.qos.fakebnb.domain.LivingQuarters} entity.
 */
public class LivingQuartersDTO implements Serializable {

    private Long id;

    private String name;

    private String city;

    private String neighborhood;

    private String description;

    private String image;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LivingQuartersDTO)) {
            return false;
        }

        LivingQuartersDTO livingQuartersDTO = (LivingQuartersDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, livingQuartersDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LivingQuartersDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", city='" + getCity() + "'" +
            ", neighborhood='" + getNeighborhood() + "'" +
            ", description='" + getDescription() + "'" +
            ", image='" + getImage() + "'" +
            "}";
    }
}
