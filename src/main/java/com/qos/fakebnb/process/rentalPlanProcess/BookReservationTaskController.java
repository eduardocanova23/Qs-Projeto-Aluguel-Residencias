package com.qos.fakebnb.process.rentalPlanProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rental-plan-process/book-reservation-task")
public class BookReservationTaskController {

    private final Logger log = LoggerFactory.getLogger(BookReservationTaskController.class);

    private final BookReservationTaskService bookReservationTaskService;

    public BookReservationTaskController(BookReservationTaskService bookReservationTaskService) {
        this.bookReservationTaskService = bookReservationTaskService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookReservationTaskContextDTO> loadContext(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        BookReservationTaskContextDTO bookReservationTaskContext = bookReservationTaskService.loadContext(id);
        return ResponseEntity.ok(bookReservationTaskContext);
    }

    @GetMapping("/{id}/claim")
    public ResponseEntity<BookReservationTaskContextDTO> claim(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        BookReservationTaskContextDTO bookReservationTaskContext = bookReservationTaskService.claim(id);
        return ResponseEntity.ok(bookReservationTaskContext);
    }

    @PostMapping("/complete")
    public ResponseEntity<Void> complete(@RequestBody BookReservationTaskContextDTO bookReservationTaskContext) {
        log.debug(
            "REST request to complete RentalPlanProcess.BookReservationTask {}",
            bookReservationTaskContext.getTaskInstance().getId()
        );
        bookReservationTaskService.complete(bookReservationTaskContext);
        return ResponseEntity.noContent().build();
    }
}
