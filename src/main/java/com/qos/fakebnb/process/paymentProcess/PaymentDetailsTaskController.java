package com.qos.fakebnb.process.paymentProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment-process/payment-details-task")
public class PaymentDetailsTaskController {

    private final Logger log = LoggerFactory.getLogger(PaymentDetailsTaskController.class);

    private final PaymentDetailsTaskService paymentDetailsTaskService;

    public PaymentDetailsTaskController(PaymentDetailsTaskService paymentDetailsTaskService) {
        this.paymentDetailsTaskService = paymentDetailsTaskService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDetailsTaskContextDTO> loadContext(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        PaymentDetailsTaskContextDTO paymentDetailsTaskContext = paymentDetailsTaskService.loadContext(id);
        return ResponseEntity.ok(paymentDetailsTaskContext);
    }

    @GetMapping("/{id}/claim")
    public ResponseEntity<PaymentDetailsTaskContextDTO> claim(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        PaymentDetailsTaskContextDTO paymentDetailsTaskContext = paymentDetailsTaskService.claim(id);
        return ResponseEntity.ok(paymentDetailsTaskContext);
    }

    @PostMapping("/complete")
    public ResponseEntity<Void> complete(@RequestBody PaymentDetailsTaskContextDTO paymentDetailsTaskContext) {
        log.debug("REST request to complete PaymentProcess.PaymentDetailsTask {}", paymentDetailsTaskContext.getTaskInstance().getId());
        paymentDetailsTaskService.complete(paymentDetailsTaskContext);
        return ResponseEntity.noContent().build();
    }
}
