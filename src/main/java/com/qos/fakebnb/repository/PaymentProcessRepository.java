package com.qos.fakebnb.repository;

import com.qos.fakebnb.domain.PaymentProcess;
import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the PaymentProcess entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PaymentProcessRepository extends JpaRepository<PaymentProcess, Long> {
    Optional<PaymentProcess> findByProcessInstanceId(Long processInstanceId);
}
