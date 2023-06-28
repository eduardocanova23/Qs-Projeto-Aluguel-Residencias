package com.qos.fakebnb.repository;

import com.qos.fakebnb.domain.LivingQuarters;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the LivingQuarters entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LivingQuartersRepository extends JpaRepository<LivingQuarters, Long> {}
