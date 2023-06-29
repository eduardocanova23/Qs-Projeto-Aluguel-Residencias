package com.qos.fakebnb.process.rentalPlanProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rental-plan-process/task-browse-residences")
public class TaskBrowseResidencesController {

    private final Logger log = LoggerFactory.getLogger(TaskBrowseResidencesController.class);

    private final TaskBrowseResidencesService taskBrowseResidencesService;

    public TaskBrowseResidencesController(TaskBrowseResidencesService taskBrowseResidencesService) {
        this.taskBrowseResidencesService = taskBrowseResidencesService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskBrowseResidencesContextDTO> loadContext(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        TaskBrowseResidencesContextDTO taskBrowseResidencesContext = taskBrowseResidencesService.loadContext(id);
        return ResponseEntity.ok(taskBrowseResidencesContext);
    }

    @GetMapping("/{id}/claim")
    public ResponseEntity<TaskBrowseResidencesContextDTO> claim(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        TaskBrowseResidencesContextDTO taskBrowseResidencesContext = taskBrowseResidencesService.claim(id);
        return ResponseEntity.ok(taskBrowseResidencesContext);
    }

    @PostMapping("/complete")
    public ResponseEntity<Void> complete(@RequestBody TaskBrowseResidencesContextDTO taskBrowseResidencesContext) {
        log.debug(
            "REST request to complete RentalPlanProcess.TaskBrowseResidences {}",
            taskBrowseResidencesContext.getTaskInstance().getId()
        );
        taskBrowseResidencesService.complete(taskBrowseResidencesContext);
        return ResponseEntity.noContent().build();
    }
}
