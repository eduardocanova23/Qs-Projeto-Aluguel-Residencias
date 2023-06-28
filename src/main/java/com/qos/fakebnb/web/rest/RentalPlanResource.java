package com.qos.fakebnb.web.rest;

import com.qos.fakebnb.repository.RentalPlanRepository;
import com.qos.fakebnb.service.RentalPlanService;
import com.qos.fakebnb.service.dto.RentalPlanDTO;
import com.qos.fakebnb.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.qos.fakebnb.domain.RentalPlan}.
 */
@RestController
@RequestMapping("/api")
public class RentalPlanResource {

    private final Logger log = LoggerFactory.getLogger(RentalPlanResource.class);

    private final RentalPlanService rentalPlanService;

    private final RentalPlanRepository rentalPlanRepository;

    public RentalPlanResource(RentalPlanService rentalPlanService, RentalPlanRepository rentalPlanRepository) {
        this.rentalPlanService = rentalPlanService;
        this.rentalPlanRepository = rentalPlanRepository;
    }

    /**
     * {@code GET  /rental-plans} : get all the rentalPlans.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of rentalPlans in body.
     */
    @GetMapping("/rental-plans")
    public List<RentalPlanDTO> getAllRentalPlans() {
        log.debug("REST request to get all RentalPlans");
        return rentalPlanService.findAll();
    }

    /**
     * {@code GET  /rental-plans/:id} : get the "id" rentalPlan.
     *
     * @param id the id of the rentalPlanDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the rentalPlanDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/rental-plans/{id}")
    public ResponseEntity<RentalPlanDTO> getRentalPlan(@PathVariable Long id) {
        log.debug("REST request to get RentalPlan : {}", id);
        Optional<RentalPlanDTO> rentalPlanDTO = rentalPlanService.findOne(id);
        return ResponseUtil.wrapOrNotFound(rentalPlanDTO);
    }
}
