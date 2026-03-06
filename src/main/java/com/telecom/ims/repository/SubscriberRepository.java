package com.telecom.ims.repository;

import com.telecom.ims.model.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for managing Subscriber persistence in H2.
 */
@Repository
public interface SubscriberRepository extends JpaRepository<Subscriber, String> {
}