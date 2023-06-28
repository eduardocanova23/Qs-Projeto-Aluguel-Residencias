package com.qos.fakebnb.service;

import com.qos.fakebnb.domain.LivingQuarters;
import com.qos.fakebnb.repository.LivingQuartersRepository;
import com.qos.fakebnb.service.dto.LivingQuartersDTO;
import com.qos.fakebnb.service.mapper.LivingQuartersMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link LivingQuarters}.
 */
@Service
@Transactional
public class LivingQuartersService {

    private final Logger log = LoggerFactory.getLogger(LivingQuartersService.class);

    private final LivingQuartersRepository livingQuartersRepository;

    private final LivingQuartersMapper livingQuartersMapper;

    public LivingQuartersService(LivingQuartersRepository livingQuartersRepository, LivingQuartersMapper livingQuartersMapper) {
        this.livingQuartersRepository = livingQuartersRepository;
        this.livingQuartersMapper = livingQuartersMapper;
    }

    /**
     * Save a livingQuarters.
     *
     * @param livingQuartersDTO the entity to save.
     * @return the persisted entity.
     */
    public LivingQuartersDTO save(LivingQuartersDTO livingQuartersDTO) {
        log.debug("Request to save LivingQuarters : {}", livingQuartersDTO);
        LivingQuarters livingQuarters = livingQuartersMapper.toEntity(livingQuartersDTO);
        livingQuarters = livingQuartersRepository.save(livingQuarters);
        return livingQuartersMapper.toDto(livingQuarters);
    }

    /**
     * Partially update a livingQuarters.
     *
     * @param livingQuartersDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<LivingQuartersDTO> partialUpdate(LivingQuartersDTO livingQuartersDTO) {
        log.debug("Request to partially update LivingQuarters : {}", livingQuartersDTO);

        return livingQuartersRepository
            .findById(livingQuartersDTO.getId())
            .map(
                existingLivingQuarters -> {
                    livingQuartersMapper.partialUpdate(existingLivingQuarters, livingQuartersDTO);
                    return existingLivingQuarters;
                }
            )
            .map(livingQuartersRepository::save)
            .map(livingQuartersMapper::toDto);
    }

    /**
     * Get all the livingQuarters.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<LivingQuartersDTO> findAll() {
        log.debug("Request to get all LivingQuarters");
        return livingQuartersRepository
            .findAll()
            .stream()
            .map(livingQuartersMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one livingQuarters by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<LivingQuartersDTO> findOne(Long id) {
        log.debug("Request to get LivingQuarters : {}", id);
        return livingQuartersRepository.findById(id).map(livingQuartersMapper::toDto);
    }

    /**
     * Delete the livingQuarters by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete LivingQuarters : {}", id);
        livingQuartersRepository.deleteById(id);
    }
}
