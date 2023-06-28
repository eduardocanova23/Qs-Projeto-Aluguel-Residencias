package com.qos.fakebnb.repository;

import com.qos.fakebnb.domain.RentalPlan;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the RentalPlan entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RentalPlanRepository extends JpaRepository<RentalPlan, Long> {}
