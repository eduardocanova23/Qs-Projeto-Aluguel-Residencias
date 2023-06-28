package com.qos.fakebnb.web.rest;

import com.qos.fakebnb.service.RentalPlanProcessService;
import com.qos.fakebnb.service.dto.RentalPlanProcessDTO;
import com.qos.fakebnb.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.qos.fakebnb.domain.RentalPlanProcess}.
 */
@RestController
@RequestMapping("/api")
public class RentalPlanProcessResource {

    private final Logger log = LoggerFactory.getLogger(RentalPlanProcessResource.class);

    private static final String ENTITY_NAME = "rentalPlanProcess";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RentalPlanProcessService rentalPlanProcessService;

    public RentalPlanProcessResource(RentalPlanProcessService rentalPlanProcessService) {
        this.rentalPlanProcessService = rentalPlanProcessService;
    }

    /**
     * {@code POST  /rental-plan-processes} : Create a new rentalPlanProcess.
     *
     * @param rentalPlanProcessDTO the rentalPlanProcessDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new rentalPlanProcessDTO, or with status {@code 400 (Bad Request)} if the rentalPlanProcess has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/rental-plan-processes")
    public ResponseEntity<RentalPlanProcessDTO> create(@RequestBody RentalPlanProcessDTO rentalPlanProcessDTO) throws URISyntaxException {
        log.debug("REST request to save RentalPlanProcess : {}", rentalPlanProcessDTO);
        if (rentalPlanProcessDTO.getId() != null) {
            throw new BadRequestAlertException("A new rentalPlanProcess cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RentalPlanProcessDTO result = rentalPlanProcessService.create(rentalPlanProcessDTO);
        return ResponseEntity
            .created(new URI("/api/rental-plan-processes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /rental-plan-processes} : get all the rentalPlanProcesss.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of rentalPlanProcesss in body.
     */
    @GetMapping("/rental-plan-processes")
    public List<RentalPlanProcessDTO> getAllRentalPlanProcesss() {
        log.debug("REST request to get all RentalPlanProcesss");
        return rentalPlanProcessService.findAll();
    }

    /**
     * {@code GET  /rental-plan-processes/:id} : get the "id" rentalPlanProcess.
     *
     * @param processInstanceId the id of the rentalPlanProcessDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the rentalPlanProcessDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/rental-plan-processes/{processInstanceId}")
    public ResponseEntity<RentalPlanProcessDTO> getByProcessInstanceId(@PathVariable Long processInstanceId) {
        log.debug("REST request to get RentalPlanProcess by processInstanceId : {}", processInstanceId);
        Optional<RentalPlanProcessDTO> rentalPlanProcessDTO = rentalPlanProcessService.findByProcessInstanceId(processInstanceId);
        return ResponseUtil.wrapOrNotFound(rentalPlanProcessDTO);
    }
}
