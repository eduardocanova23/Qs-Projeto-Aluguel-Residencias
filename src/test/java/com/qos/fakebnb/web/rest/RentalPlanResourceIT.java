package com.qos.fakebnb.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.qos.fakebnb.IntegrationTest;
import com.qos.fakebnb.domain.RentalPlan;
import com.qos.fakebnb.repository.RentalPlanRepository;
import com.qos.fakebnb.service.dto.RentalPlanDTO;
import com.qos.fakebnb.service.mapper.RentalPlanMapper;
import java.time.LocalDate;
import java.time.ZoneId;
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
 * Integration tests for the {@link RentalPlanResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class RentalPlanResourceIT {

    private static final String DEFAULT_CITY = "AAAAAAAAAA";
    private static final String UPDATED_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_NEIGHBORHOOD = "AAAAAAAAAA";
    private static final String UPDATED_NEIGHBORHOOD = "BBBBBBBBBB";

    private static final String DEFAULT_USER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_USER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_USER_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_USER_EMAIL = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_START_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_START_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_END_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_END_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_RENTAL_CONFIRMATION_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_RENTAL_CONFIRMATION_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_CARD_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_CARD_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_CARD_VERIFICATION_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_CARD_VERIFICATION_VALUE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_EXPIRATION_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_EXPIRATION_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/rental-plans";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private RentalPlanRepository rentalPlanRepository;

    @Autowired
    private RentalPlanMapper rentalPlanMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRentalPlanMockMvc;

    private RentalPlan rentalPlan;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RentalPlan createEntity(EntityManager em) {
        RentalPlan rentalPlan = new RentalPlan()
            .city(DEFAULT_CITY)
            .neighborhood(DEFAULT_NEIGHBORHOOD)
            .userName(DEFAULT_USER_NAME)
            .userEmail(DEFAULT_USER_EMAIL)
            .startDate(DEFAULT_START_DATE)
            .endDate(DEFAULT_END_DATE)
            .rentalConfirmationNumber(DEFAULT_RENTAL_CONFIRMATION_NUMBER)
            .cardNumber(DEFAULT_CARD_NUMBER)
            .cardVerificationValue(DEFAULT_CARD_VERIFICATION_VALUE)
            .expirationDate(DEFAULT_EXPIRATION_DATE);
        return rentalPlan;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RentalPlan createUpdatedEntity(EntityManager em) {
        RentalPlan rentalPlan = new RentalPlan()
            .city(UPDATED_CITY)
            .neighborhood(UPDATED_NEIGHBORHOOD)
            .userName(UPDATED_USER_NAME)
            .userEmail(UPDATED_USER_EMAIL)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .rentalConfirmationNumber(UPDATED_RENTAL_CONFIRMATION_NUMBER)
            .cardNumber(UPDATED_CARD_NUMBER)
            .cardVerificationValue(UPDATED_CARD_VERIFICATION_VALUE)
            .expirationDate(UPDATED_EXPIRATION_DATE);
        return rentalPlan;
    }

    @BeforeEach
    public void initTest() {
        rentalPlan = createEntity(em);
    }

    @Test
    @Transactional
    void getAllRentalPlans() throws Exception {
        // Initialize the database
        rentalPlanRepository.saveAndFlush(rentalPlan);

        // Get all the rentalPlanList
        restRentalPlanMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(rentalPlan.getId().intValue())))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY)))
            .andExpect(jsonPath("$.[*].neighborhood").value(hasItem(DEFAULT_NEIGHBORHOOD)))
            .andExpect(jsonPath("$.[*].userName").value(hasItem(DEFAULT_USER_NAME)))
            .andExpect(jsonPath("$.[*].userEmail").value(hasItem(DEFAULT_USER_EMAIL)))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].rentalConfirmationNumber").value(hasItem(DEFAULT_RENTAL_CONFIRMATION_NUMBER)))
            .andExpect(jsonPath("$.[*].cardNumber").value(hasItem(DEFAULT_CARD_NUMBER)))
            .andExpect(jsonPath("$.[*].cardVerificationValue").value(hasItem(DEFAULT_CARD_VERIFICATION_VALUE)))
            .andExpect(jsonPath("$.[*].expirationDate").value(hasItem(DEFAULT_EXPIRATION_DATE.toString())));
    }

    @Test
    @Transactional
    void getRentalPlan() throws Exception {
        // Initialize the database
        rentalPlanRepository.saveAndFlush(rentalPlan);

        // Get the rentalPlan
        restRentalPlanMockMvc
            .perform(get(ENTITY_API_URL_ID, rentalPlan.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(rentalPlan.getId().intValue()))
            .andExpect(jsonPath("$.city").value(DEFAULT_CITY))
            .andExpect(jsonPath("$.neighborhood").value(DEFAULT_NEIGHBORHOOD))
            .andExpect(jsonPath("$.userName").value(DEFAULT_USER_NAME))
            .andExpect(jsonPath("$.userEmail").value(DEFAULT_USER_EMAIL))
            .andExpect(jsonPath("$.startDate").value(DEFAULT_START_DATE.toString()))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()))
            .andExpect(jsonPath("$.rentalConfirmationNumber").value(DEFAULT_RENTAL_CONFIRMATION_NUMBER))
            .andExpect(jsonPath("$.cardNumber").value(DEFAULT_CARD_NUMBER))
            .andExpect(jsonPath("$.cardVerificationValue").value(DEFAULT_CARD_VERIFICATION_VALUE))
            .andExpect(jsonPath("$.expirationDate").value(DEFAULT_EXPIRATION_DATE.toString()));
    }

    @Test
    @Transactional
    void getNonExistingRentalPlan() throws Exception {
        // Get the rentalPlan
        restRentalPlanMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
