package com.qos.fakebnb.process.rentalPlanProcess;

import com.qos.fakebnb.repository.RentalPlanProcessRepository;
import com.qos.fakebnb.service.RentalPlanService;
import com.qos.fakebnb.service.dto.RentalPlanDTO;
import com.qos.fakebnb.service.dto.RentalPlanProcessDTO;
import com.qos.fakebnb.service.mapper.RentalPlanProcessMapper;
import org.akip.repository.TaskInstanceRepository;
import org.akip.service.TaskInstanceService;
import org.akip.service.dto.TaskInstanceDTO;
import org.akip.service.mapper.TaskInstanceMapper;
import org.springframework.stereotype.Component;

@Component
public class TaskBrowseResidencesService {

    private final TaskInstanceService taskInstanceService;

    private final RentalPlanService rentalPlanService;

    private final TaskInstanceRepository taskInstanceRepository;

    private final RentalPlanProcessRepository rentalPlanProcessRepository;

    private final TaskInstanceMapper taskInstanceMapper;

    private final TaskBrowseResidencesMapper taskBrowseResidencesMapper;

    private final RentalPlanProcessMapper rentalPlanProcessMapper;

    public TaskBrowseResidencesService(
        TaskInstanceService taskInstanceService,
        RentalPlanService rentalPlanService,
        TaskInstanceRepository taskInstanceRepository,
        RentalPlanProcessRepository rentalPlanProcessRepository,
        TaskInstanceMapper taskInstanceMapper,
        TaskBrowseResidencesMapper taskBrowseResidencesMapper,
        RentalPlanProcessMapper rentalPlanProcessMapper
    ) {
        this.taskInstanceService = taskInstanceService;
        this.rentalPlanService = rentalPlanService;
        this.taskInstanceRepository = taskInstanceRepository;
        this.rentalPlanProcessRepository = rentalPlanProcessRepository;
        this.taskInstanceMapper = taskInstanceMapper;
        this.taskBrowseResidencesMapper = taskBrowseResidencesMapper;
        this.rentalPlanProcessMapper = rentalPlanProcessMapper;
    }

    public TaskBrowseResidencesContextDTO loadContext(Long taskInstanceId) {
        TaskInstanceDTO taskInstanceDTO = taskInstanceRepository
            .findById(taskInstanceId)
            .map(taskInstanceMapper::toDTOLoadTaskContext)
            .orElseThrow();

        RentalPlanProcessDTO rentalPlanProcess = rentalPlanProcessRepository
            .findByProcessInstanceId(taskInstanceDTO.getProcessInstance().getId())
            .map(taskBrowseResidencesMapper::toRentalPlanProcessDTO)
            .orElseThrow();

        TaskBrowseResidencesContextDTO taskBrowseResidencesContext = new TaskBrowseResidencesContextDTO();
        taskBrowseResidencesContext.setTaskInstance(taskInstanceDTO);
        taskBrowseResidencesContext.setRentalPlanProcess(rentalPlanProcess);

        return taskBrowseResidencesContext;
    }

    public TaskBrowseResidencesContextDTO claim(Long taskInstanceId) {
        taskInstanceService.claim(taskInstanceId);
        return loadContext(taskInstanceId);
    }

    public void save(TaskBrowseResidencesContextDTO taskBrowseResidencesContext) {
        RentalPlanDTO rentalPlanDTO = rentalPlanService
            .findOne(taskBrowseResidencesContext.getRentalPlanProcess().getRentalPlan().getId())
            .orElseThrow();
        rentalPlanDTO.setCity(taskBrowseResidencesContext.getRentalPlanProcess().getRentalPlan().getCity());
        rentalPlanDTO.setNeighborhood(taskBrowseResidencesContext.getRentalPlanProcess().getRentalPlan().getNeighborhood());
        rentalPlanDTO.setUserName(taskBrowseResidencesContext.getRentalPlanProcess().getRentalPlan().getUserName());
        rentalPlanDTO.setUserEmail(taskBrowseResidencesContext.getRentalPlanProcess().getRentalPlan().getUserEmail());
        rentalPlanDTO.setStartDate(taskBrowseResidencesContext.getRentalPlanProcess().getRentalPlan().getStartDate());
        rentalPlanDTO.setEndDate(taskBrowseResidencesContext.getRentalPlanProcess().getRentalPlan().getEndDate());
        rentalPlanDTO.setRentalConfirmationNumber(
            taskBrowseResidencesContext.getRentalPlanProcess().getRentalPlan().getRentalConfirmationNumber()
        );
        rentalPlanDTO.setLivingQuarters(taskBrowseResidencesContext.getRentalPlanProcess().getRentalPlan().getLivingQuarters());
        rentalPlanService.save(rentalPlanDTO);
    }

    public void complete(TaskBrowseResidencesContextDTO taskBrowseResidencesContext) {
        save(taskBrowseResidencesContext);
        RentalPlanProcessDTO rentalPlanProcess = rentalPlanProcessRepository
            .findByProcessInstanceId(taskBrowseResidencesContext.getRentalPlanProcess().getProcessInstance().getId())
            .map(rentalPlanProcessMapper::toDto)
            .orElseThrow();
        taskInstanceService.complete(taskBrowseResidencesContext.getTaskInstance(), rentalPlanProcess);
    }
}
