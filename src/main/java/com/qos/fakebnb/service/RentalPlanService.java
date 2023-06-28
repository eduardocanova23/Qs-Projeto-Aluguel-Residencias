package com.qos.fakebnb.service;

import com.qos.fakebnb.domain.RentalPlan;
import com.qos.fakebnb.repository.RentalPlanRepository;
import com.qos.fakebnb.service.dto.RentalPlanDTO;
import com.qos.fakebnb.service.mapper.RentalPlanMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link RentalPlan}.
 */
@Service
@Transactional
public class RentalPlanService {

    private final Logger log = LoggerFactory.getLogger(RentalPlanService.class);

    private final RentalPlanRepository rentalPlanRepository;

    private final RentalPlanMapper rentalPlanMapper;

    public RentalPlanService(RentalPlanRepository rentalPlanRepository, RentalPlanMapper rentalPlanMapper) {
        this.rentalPlanRepository = rentalPlanRepository;
        this.rentalPlanMapper = rentalPlanMapper;
    }

    /**
     * Save a rentalPlan.
     *
     * @param rentalPlanDTO the entity to save.
     * @return the persisted entity.
     */
    public RentalPlanDTO save(RentalPlanDTO rentalPlanDTO) {
        log.debug("Request to save RentalPlan : {}", rentalPlanDTO);
        RentalPlan rentalPlan = rentalPlanMapper.toEntity(rentalPlanDTO);
        rentalPlan = rentalPlanRepository.save(rentalPlan);
        return rentalPlanMapper.toDto(rentalPlan);
    }

    /**
     * Partially update a rentalPlan.
     *
     * @param rentalPlanDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<RentalPlanDTO> partialUpdate(RentalPlanDTO rentalPlanDTO) {
        log.debug("Request to partially update RentalPlan : {}", rentalPlanDTO);

        return rentalPlanRepository
            .findById(rentalPlanDTO.getId())
            .map(
                existingRentalPlan -> {
                    rentalPlanMapper.partialUpdate(existingRentalPlan, rentalPlanDTO);
                    return existingRentalPlan;
                }
            )
            .map(rentalPlanRepository::save)
            .map(rentalPlanMapper::toDto);
    }

    /**
     * Get all the rentalPlans.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<RentalPlanDTO> findAll() {
        log.debug("Request to get all RentalPlans");
        return rentalPlanRepository.findAll().stream().map(rentalPlanMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one rentalPlan by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<RentalPlanDTO> findOne(Long id) {
        log.debug("Request to get RentalPlan : {}", id);
        return rentalPlanRepository.findById(id).map(rentalPlanMapper::toDto);
    }

    /**
     * Delete the rentalPlan by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete RentalPlan : {}", id);
        rentalPlanRepository.deleteById(id);
    }
}
