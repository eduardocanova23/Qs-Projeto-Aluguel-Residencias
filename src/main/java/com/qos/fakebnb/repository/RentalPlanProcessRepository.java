package com.qos.fakebnb.repository;

import com.qos.fakebnb.domain.RentalPlanProcess;
import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the RentalPlanProcess entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RentalPlanProcessRepository extends JpaRepository<RentalPlanProcess, Long> {
    Optional<RentalPlanProcess> findByProcessInstanceId(Long processInstanceId);
}
