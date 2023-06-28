package com.qos.fakebnb.web.rest;

import com.qos.fakebnb.repository.LivingQuartersRepository;
import com.qos.fakebnb.service.LivingQuartersService;
import com.qos.fakebnb.service.dto.LivingQuartersDTO;
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
 * REST controller for managing {@link com.qos.fakebnb.domain.LivingQuarters}.
 */
@RestController
@RequestMapping("/api")
public class LivingQuartersResource {

    private final Logger log = LoggerFactory.getLogger(LivingQuartersResource.class);

    private static final String ENTITY_NAME = "livingQuarters";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LivingQuartersService livingQuartersService;

    private final LivingQuartersRepository livingQuartersRepository;

    public LivingQuartersResource(LivingQuartersService livingQuartersService, LivingQuartersRepository livingQuartersRepository) {
        this.livingQuartersService = livingQuartersService;
        this.livingQuartersRepository = livingQuartersRepository;
    }

    /**
     * {@code POST  /living-quarters} : Create a new livingQuarters.
     *
     * @param livingQuartersDTO the livingQuartersDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new livingQuartersDTO, or with status {@code 400 (Bad Request)} if the livingQuarters has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/living-quarters")
    public ResponseEntity<LivingQuartersDTO> createLivingQuarters(@RequestBody LivingQuartersDTO livingQuartersDTO)
        throws URISyntaxException {
        log.debug("REST request to save LivingQuarters : {}", livingQuartersDTO);
        if (livingQuartersDTO.getId() != null) {
            throw new BadRequestAlertException("A new livingQuarters cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LivingQuartersDTO result = livingQuartersService.save(livingQuartersDTO);
        return ResponseEntity
            .created(new URI("/api/living-quarters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /living-quarters/:id} : Updates an existing livingQuarters.
     *
     * @param id the id of the livingQuartersDTO to save.
     * @param livingQuartersDTO the livingQuartersDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated livingQuartersDTO,
     * or with status {@code 400 (Bad Request)} if the livingQuartersDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the livingQuartersDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/living-quarters/{id}")
    public ResponseEntity<LivingQuartersDTO> updateLivingQuarters(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody LivingQuartersDTO livingQuartersDTO
    ) throws URISyntaxException {
        log.debug("REST request to update LivingQuarters : {}, {}", id, livingQuartersDTO);
        if (livingQuartersDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, livingQuartersDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!livingQuartersRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        LivingQuartersDTO result = livingQuartersService.save(livingQuartersDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, livingQuartersDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /living-quarters/:id} : Partial updates given fields of an existing livingQuarters, field will ignore if it is null
     *
     * @param id the id of the livingQuartersDTO to save.
     * @param livingQuartersDTO the livingQuartersDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated livingQuartersDTO,
     * or with status {@code 400 (Bad Request)} if the livingQuartersDTO is not valid,
     * or with status {@code 404 (Not Found)} if the livingQuartersDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the livingQuartersDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/living-quarters/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<LivingQuartersDTO> partialUpdateLivingQuarters(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody LivingQuartersDTO livingQuartersDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update LivingQuarters partially : {}, {}", id, livingQuartersDTO);
        if (livingQuartersDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, livingQuartersDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!livingQuartersRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<LivingQuartersDTO> result = livingQuartersService.partialUpdate(livingQuartersDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, livingQuartersDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /living-quarters} : get all the livingQuarters.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of livingQuarters in body.
     */
    @GetMapping("/living-quarters")
    public List<LivingQuartersDTO> getAllLivingQuarters() {
        log.debug("REST request to get all LivingQuarters");
        return livingQuartersService.findAll();
    }

    /**
     * {@code GET  /living-quarters/:id} : get the "id" livingQuarters.
     *
     * @param id the id of the livingQuartersDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the livingQuartersDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/living-quarters/{id}")
    public ResponseEntity<LivingQuartersDTO> getLivingQuarters(@PathVariable Long id) {
        log.debug("REST request to get LivingQuarters : {}", id);
        Optional<LivingQuartersDTO> livingQuartersDTO = livingQuartersService.findOne(id);
        return ResponseUtil.wrapOrNotFound(livingQuartersDTO);
    }

    /**
     * {@code DELETE  /living-quarters/:id} : delete the "id" livingQuarters.
     *
     * @param id the id of the livingQuartersDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/living-quarters/{id}")
    public ResponseEntity<Void> deleteLivingQuarters(@PathVariable Long id) {
        log.debug("REST request to delete LivingQuarters : {}", id);
        livingQuartersService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
