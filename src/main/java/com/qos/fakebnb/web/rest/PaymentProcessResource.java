package com.qos.fakebnb.web.rest;

import com.qos.fakebnb.service.PaymentProcessService;
import com.qos.fakebnb.service.dto.PaymentProcessDTO;
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
 * REST controller for managing {@link com.qos.fakebnb.domain.PaymentProcess}.
 */
@RestController
@RequestMapping("/api")
public class PaymentProcessResource {

    private final Logger log = LoggerFactory.getLogger(PaymentProcessResource.class);

    private static final String ENTITY_NAME = "paymentProcess";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PaymentProcessService paymentProcessService;

    public PaymentProcessResource(PaymentProcessService paymentProcessService) {
        this.paymentProcessService = paymentProcessService;
    }

    /**
     * {@code POST  /payment-processes} : Create a new paymentProcess.
     *
     * @param paymentProcessDTO the paymentProcessDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new paymentProcessDTO, or with status {@code 400 (Bad Request)} if the paymentProcess has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/payment-processes")
    public ResponseEntity<PaymentProcessDTO> create(@RequestBody PaymentProcessDTO paymentProcessDTO) throws URISyntaxException {
        log.debug("REST request to save PaymentProcess : {}", paymentProcessDTO);
        if (paymentProcessDTO.getId() != null) {
            throw new BadRequestAlertException("A new paymentProcess cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PaymentProcessDTO result = paymentProcessService.create(paymentProcessDTO);
        return ResponseEntity
            .created(new URI("/api/payment-processes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /payment-processes} : get all the paymentProcesss.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of paymentProcesss in body.
     */
    @GetMapping("/payment-processes")
    public List<PaymentProcessDTO> getAllPaymentProcesss() {
        log.debug("REST request to get all PaymentProcesss");
        return paymentProcessService.findAll();
    }

    /**
     * {@code GET  /payment-processes/:id} : get the "id" paymentProcess.
     *
     * @param processInstanceId the id of the paymentProcessDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the paymentProcessDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/payment-processes/{processInstanceId}")
    public ResponseEntity<PaymentProcessDTO> getByProcessInstanceId(@PathVariable Long processInstanceId) {
        log.debug("REST request to get PaymentProcess by processInstanceId : {}", processInstanceId);
        Optional<PaymentProcessDTO> paymentProcessDTO = paymentProcessService.findByProcessInstanceId(processInstanceId);
        return ResponseUtil.wrapOrNotFound(paymentProcessDTO);
    }
}
