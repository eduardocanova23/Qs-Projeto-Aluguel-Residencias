package com.qos.fakebnb.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.qos.fakebnb.IntegrationTest;
import com.qos.fakebnb.domain.LivingQuarters;
import com.qos.fakebnb.repository.LivingQuartersRepository;
import com.qos.fakebnb.service.dto.LivingQuartersDTO;
import com.qos.fakebnb.service.mapper.LivingQuartersMapper;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link LivingQuartersResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class LivingQuartersResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CITY = "AAAAAAAAAA";
    private static final String UPDATED_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_NEIGHBORHOOD = "AAAAAAAAAA";
    private static final String UPDATED_NEIGHBORHOOD = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_IMAGE = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/living-quarters";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private LivingQuartersRepository livingQuartersRepository;

    @Autowired
    private LivingQuartersMapper livingQuartersMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLivingQuartersMockMvc;

    private LivingQuarters livingQuarters;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LivingQuarters createEntity(EntityManager em) {
        LivingQuarters livingQuarters = new LivingQuarters()
            .name(DEFAULT_NAME)
            .city(DEFAULT_CITY)
            .neighborhood(DEFAULT_NEIGHBORHOOD)
            .description(DEFAULT_DESCRIPTION)
            .image(DEFAULT_IMAGE);
        return livingQuarters;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LivingQuarters createUpdatedEntity(EntityManager em) {
        LivingQuarters livingQuarters = new LivingQuarters()
            .name(UPDATED_NAME)
            .city(UPDATED_CITY)
            .neighborhood(UPDATED_NEIGHBORHOOD)
            .description(UPDATED_DESCRIPTION)
            .image(UPDATED_IMAGE);
        return livingQuarters;
    }

    @BeforeEach
    public void initTest() {
        livingQuarters = createEntity(em);
    }

    @Test
    @Transactional
    void createLivingQuarters() throws Exception {
        int databaseSizeBeforeCreate = livingQuartersRepository.findAll().size();
        // Create the LivingQuarters
        LivingQuartersDTO livingQuartersDTO = livingQuartersMapper.toDto(livingQuarters);
        restLivingQuartersMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(livingQuartersDTO))
            )
            .andExpect(status().isCreated());

        // Validate the LivingQuarters in the database
        List<LivingQuarters> livingQuartersList = livingQuartersRepository.findAll();
        assertThat(livingQuartersList).hasSize(databaseSizeBeforeCreate + 1);
        LivingQuarters testLivingQuarters = livingQuartersList.get(livingQuartersList.size() - 1);
        assertThat(testLivingQuarters.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testLivingQuarters.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testLivingQuarters.getNeighborhood()).isEqualTo(DEFAULT_NEIGHBORHOOD);
        assertThat(testLivingQuarters.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testLivingQuarters.getImage()).isEqualTo(DEFAULT_IMAGE);
    }

    @Test
    @Transactional
    void createLivingQuartersWithExistingId() throws Exception {
        // Create the LivingQuarters with an existing ID
        livingQuarters.setId(1L);
        LivingQuartersDTO livingQuartersDTO = livingQuartersMapper.toDto(livingQuarters);

        int databaseSizeBeforeCreate = livingQuartersRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restLivingQuartersMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(livingQuartersDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the LivingQuarters in the database
        List<LivingQuarters> livingQuartersList = livingQuartersRepository.findAll();
        assertThat(livingQuartersList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllLivingQuarters() throws Exception {
        // Initialize the database
        livingQuartersRepository.saveAndFlush(livingQuarters);

        // Get all the livingQuartersList
        restLivingQuartersMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(livingQuarters.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY)))
            .andExpect(jsonPath("$.[*].neighborhood").value(hasItem(DEFAULT_NEIGHBORHOOD)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].image").value(hasItem(DEFAULT_IMAGE)));
    }

    @Test
    @Transactional
    void getLivingQuarters() throws Exception {
        // Initialize the database
        livingQuartersRepository.saveAndFlush(livingQuarters);

        // Get the livingQuarters
        restLivingQuartersMockMvc
            .perform(get(ENTITY_API_URL_ID, livingQuarters.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(livingQuarters.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.city").value(DEFAULT_CITY))
            .andExpect(jsonPath("$.neighborhood").value(DEFAULT_NEIGHBORHOOD))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.image").value(DEFAULT_IMAGE));
    }

    @Test
    @Transactional
    void getNonExistingLivingQuarters() throws Exception {
        // Get the livingQuarters
        restLivingQuartersMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewLivingQuarters() throws Exception {
        // Initialize the database
        livingQuartersRepository.saveAndFlush(livingQuarters);

        int databaseSizeBeforeUpdate = livingQuartersRepository.findAll().size();

        // Update the livingQuarters
        LivingQuarters updatedLivingQuarters = livingQuartersRepository.findById(livingQuarters.getId()).get();
        // Disconnect from session so that the updates on updatedLivingQuarters are not directly saved in db
        em.detach(updatedLivingQuarters);
        updatedLivingQuarters
            .name(UPDATED_NAME)
            .city(UPDATED_CITY)
            .neighborhood(UPDATED_NEIGHBORHOOD)
            .description(UPDATED_DESCRIPTION)
            .image(UPDATED_IMAGE);
        LivingQuartersDTO livingQuartersDTO = livingQuartersMapper.toDto(updatedLivingQuarters);

        restLivingQuartersMockMvc
            .perform(
                put(ENTITY_API_URL_ID, livingQuartersDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(livingQuartersDTO))
            )
            .andExpect(status().isOk());

        // Validate the LivingQuarters in the database
        List<LivingQuarters> livingQuartersList = livingQuartersRepository.findAll();
        assertThat(livingQuartersList).hasSize(databaseSizeBeforeUpdate);
        LivingQuarters testLivingQuarters = livingQuartersList.get(livingQuartersList.size() - 1);
        assertThat(testLivingQuarters.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testLivingQuarters.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testLivingQuarters.getNeighborhood()).isEqualTo(UPDATED_NEIGHBORHOOD);
        assertThat(testLivingQuarters.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testLivingQuarters.getImage()).isEqualTo(UPDATED_IMAGE);
    }

    @Test
    @Transactional
    void putNonExistingLivingQuarters() throws Exception {
        int databaseSizeBeforeUpdate = livingQuartersRepository.findAll().size();
        livingQuarters.setId(count.incrementAndGet());

        // Create the LivingQuarters
        LivingQuartersDTO livingQuartersDTO = livingQuartersMapper.toDto(livingQuarters);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLivingQuartersMockMvc
            .perform(
                put(ENTITY_API_URL_ID, livingQuartersDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(livingQuartersDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the LivingQuarters in the database
        List<LivingQuarters> livingQuartersList = livingQuartersRepository.findAll();
        assertThat(livingQuartersList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchLivingQuarters() throws Exception {
        int databaseSizeBeforeUpdate = livingQuartersRepository.findAll().size();
        livingQuarters.setId(count.incrementAndGet());

        // Create the LivingQuarters
        LivingQuartersDTO livingQuartersDTO = livingQuartersMapper.toDto(livingQuarters);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLivingQuartersMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(livingQuartersDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the LivingQuarters in the database
        List<LivingQuarters> livingQuartersList = livingQuartersRepository.findAll();
        assertThat(livingQuartersList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamLivingQuarters() throws Exception {
        int databaseSizeBeforeUpdate = livingQuartersRepository.findAll().size();
        livingQuarters.setId(count.incrementAndGet());

        // Create the LivingQuarters
        LivingQuartersDTO livingQuartersDTO = livingQuartersMapper.toDto(livingQuarters);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLivingQuartersMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(livingQuartersDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the LivingQuarters in the database
        List<LivingQuarters> livingQuartersList = livingQuartersRepository.findAll();
        assertThat(livingQuartersList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateLivingQuartersWithPatch() throws Exception {
        // Initialize the database
        livingQuartersRepository.saveAndFlush(livingQuarters);

        int databaseSizeBeforeUpdate = livingQuartersRepository.findAll().size();

        // Update the livingQuarters using partial update
        LivingQuarters partialUpdatedLivingQuarters = new LivingQuarters();
        partialUpdatedLivingQuarters.setId(livingQuarters.getId());

        partialUpdatedLivingQuarters
            .name(UPDATED_NAME)
            .city(UPDATED_CITY)
            .neighborhood(UPDATED_NEIGHBORHOOD)
            .description(UPDATED_DESCRIPTION)
            .image(UPDATED_IMAGE);

        restLivingQuartersMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLivingQuarters.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedLivingQuarters))
            )
            .andExpect(status().isOk());

        // Validate the LivingQuarters in the database
        List<LivingQuarters> livingQuartersList = livingQuartersRepository.findAll();
        assertThat(livingQuartersList).hasSize(databaseSizeBeforeUpdate);
        LivingQuarters testLivingQuarters = livingQuartersList.get(livingQuartersList.size() - 1);
        assertThat(testLivingQuarters.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testLivingQuarters.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testLivingQuarters.getNeighborhood()).isEqualTo(UPDATED_NEIGHBORHOOD);
        assertThat(testLivingQuarters.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testLivingQuarters.getImage()).isEqualTo(UPDATED_IMAGE);
    }

    @Test
    @Transactional
    void fullUpdateLivingQuartersWithPatch() throws Exception {
        // Initialize the database
        livingQuartersRepository.saveAndFlush(livingQuarters);

        int databaseSizeBeforeUpdate = livingQuartersRepository.findAll().size();

        // Update the livingQuarters using partial update
        LivingQuarters partialUpdatedLivingQuarters = new LivingQuarters();
        partialUpdatedLivingQuarters.setId(livingQuarters.getId());

        partialUpdatedLivingQuarters
            .name(UPDATED_NAME)
            .city(UPDATED_CITY)
            .neighborhood(UPDATED_NEIGHBORHOOD)
            .description(UPDATED_DESCRIPTION)
            .image(UPDATED_IMAGE);

        restLivingQuartersMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLivingQuarters.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedLivingQuarters))
            )
            .andExpect(status().isOk());

        // Validate the LivingQuarters in the database
        List<LivingQuarters> livingQuartersList = livingQuartersRepository.findAll();
        assertThat(livingQuartersList).hasSize(databaseSizeBeforeUpdate);
        LivingQuarters testLivingQuarters = livingQuartersList.get(livingQuartersList.size() - 1);
        assertThat(testLivingQuarters.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testLivingQuarters.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testLivingQuarters.getNeighborhood()).isEqualTo(UPDATED_NEIGHBORHOOD);
        assertThat(testLivingQuarters.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testLivingQuarters.getImage()).isEqualTo(UPDATED_IMAGE);
    }

    @Test
    @Transactional
    void patchNonExistingLivingQuarters() throws Exception {
        int databaseSizeBeforeUpdate = livingQuartersRepository.findAll().size();
        livingQuarters.setId(count.incrementAndGet());

        // Create the LivingQuarters
        LivingQuartersDTO livingQuartersDTO = livingQuartersMapper.toDto(livingQuarters);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLivingQuartersMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, livingQuartersDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(livingQuartersDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the LivingQuarters in the database
        List<LivingQuarters> livingQuartersList = livingQuartersRepository.findAll();
        assertThat(livingQuartersList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchLivingQuarters() throws Exception {
        int databaseSizeBeforeUpdate = livingQuartersRepository.findAll().size();
        livingQuarters.setId(count.incrementAndGet());

        // Create the LivingQuarters
        LivingQuartersDTO livingQuartersDTO = livingQuartersMapper.toDto(livingQuarters);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLivingQuartersMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(livingQuartersDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the LivingQuarters in the database
        List<LivingQuarters> livingQuartersList = livingQuartersRepository.findAll();
        assertThat(livingQuartersList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamLivingQuarters() throws Exception {
        int databaseSizeBeforeUpdate = livingQuartersRepository.findAll().size();
        livingQuarters.setId(count.incrementAndGet());

        // Create the LivingQuarters
        LivingQuartersDTO livingQuartersDTO = livingQuartersMapper.toDto(livingQuarters);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLivingQuartersMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(livingQuartersDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the LivingQuarters in the database
        List<LivingQuarters> livingQuartersList = livingQuartersRepository.findAll();
        assertThat(livingQuartersList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteLivingQuarters() throws Exception {
        // Initialize the database
        livingQuartersRepository.saveAndFlush(livingQuarters);

        int databaseSizeBeforeDelete = livingQuartersRepository.findAll().size();

        // Delete the livingQuarters
        restLivingQuartersMockMvc
            .perform(delete(ENTITY_API_URL_ID, livingQuarters.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<LivingQuarters> livingQuartersList = livingQuartersRepository.findAll();
        assertThat(livingQuartersList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
