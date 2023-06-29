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
public class BookReservationTaskService {

    private final TaskInstanceService taskInstanceService;

    private final RentalPlanService rentalPlanService;

    private final TaskInstanceRepository taskInstanceRepository;

    private final RentalPlanProcessRepository rentalPlanProcessRepository;

    private final TaskInstanceMapper taskInstanceMapper;

    private final BookReservationTaskMapper bookReservationTaskMapper;

    private final RentalPlanProcessMapper rentalPlanProcessMapper;

    public BookReservationTaskService(
        TaskInstanceService taskInstanceService,
        RentalPlanService rentalPlanService,
        TaskInstanceRepository taskInstanceRepository,
        RentalPlanProcessRepository rentalPlanProcessRepository,
        TaskInstanceMapper taskInstanceMapper,
        BookReservationTaskMapper bookReservationTaskMapper,
        RentalPlanProcessMapper rentalPlanProcessMapper
    ) {
        this.taskInstanceService = taskInstanceService;
        this.rentalPlanService = rentalPlanService;
        this.taskInstanceRepository = taskInstanceRepository;
        this.rentalPlanProcessRepository = rentalPlanProcessRepository;
        this.taskInstanceMapper = taskInstanceMapper;
        this.bookReservationTaskMapper = bookReservationTaskMapper;
        this.rentalPlanProcessMapper = rentalPlanProcessMapper;
    }

    public BookReservationTaskContextDTO loadContext(Long taskInstanceId) {
        TaskInstanceDTO taskInstanceDTO = taskInstanceRepository
            .findById(taskInstanceId)
            .map(taskInstanceMapper::toDTOLoadTaskContext)
            .orElseThrow();

        RentalPlanProcessDTO rentalPlanProcess = rentalPlanProcessRepository
            .findByProcessInstanceId(taskInstanceDTO.getProcessInstance().getId())
            .map(bookReservationTaskMapper::toRentalPlanProcessDTO)
            .orElseThrow();

        BookReservationTaskContextDTO bookReservationTaskContext = new BookReservationTaskContextDTO();
        bookReservationTaskContext.setTaskInstance(taskInstanceDTO);
        bookReservationTaskContext.setRentalPlanProcess(rentalPlanProcess);

        return bookReservationTaskContext;
    }

    public BookReservationTaskContextDTO claim(Long taskInstanceId) {
        taskInstanceService.claim(taskInstanceId);
        return loadContext(taskInstanceId);
    }

    public void save(BookReservationTaskContextDTO bookReservationTaskContext) {
        RentalPlanDTO rentalPlanDTO = rentalPlanService
            .findOne(bookReservationTaskContext.getRentalPlanProcess().getRentalPlan().getId())
            .orElseThrow();
        rentalPlanDTO.setCity(bookReservationTaskContext.getRentalPlanProcess().getRentalPlan().getCity());
        rentalPlanDTO.setNeighborhood(bookReservationTaskContext.getRentalPlanProcess().getRentalPlan().getNeighborhood());
        rentalPlanDTO.setStartDate(bookReservationTaskContext.getRentalPlanProcess().getRentalPlan().getStartDate());
        rentalPlanDTO.setEndDate(bookReservationTaskContext.getRentalPlanProcess().getRentalPlan().getEndDate());
        rentalPlanDTO.setConfirmation(bookReservationTaskContext.getRentalPlanProcess().getRentalPlan().getConfirmation());
        rentalPlanService.save(rentalPlanDTO);
    }

    public void complete(BookReservationTaskContextDTO bookReservationTaskContext) {
        save(bookReservationTaskContext);
        RentalPlanProcessDTO rentalPlanProcess = rentalPlanProcessRepository
            .findByProcessInstanceId(bookReservationTaskContext.getRentalPlanProcess().getProcessInstance().getId())
            .map(rentalPlanProcessMapper::toDto)
            .orElseThrow();
        taskInstanceService.complete(bookReservationTaskContext.getTaskInstance(), rentalPlanProcess);
    }
}
