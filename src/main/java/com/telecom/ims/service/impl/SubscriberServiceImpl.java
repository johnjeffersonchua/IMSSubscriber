package com.telecom.ims.service.impl;

import com.telecom.ims.model.Subscriber;
import com.telecom.ims.repository.SubscriberRepository;
import com.telecom.ims.service.SubscriberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service class handling business logic for IMS Subscribers.
 */
@Service
@Log4j2
@RequiredArgsConstructor
public class SubscriberServiceImpl implements SubscriberService {

    private final SubscriberRepository subscriberRepository;

    /**
     * Retrieves a subscriber by their phone number.
     *
     * @param phoneNumber the unique phone number
     * @return an Optional containing the subscriber if found
     */
    @Override
    public Optional<Subscriber> getSubscriber(String phoneNumber) {
        log.info("Fetching subscriber with phone number: {}", phoneNumber);
        return subscriberRepository.findById(phoneNumber);
    }

    /**
     * Adds a new subscriber or updates an existing one.
     *
     * @param phoneNumber the phone number identifier
     * @param subscriber the subscriber data to save
     * @return the saved subscriber
     */
    @Override
    public Subscriber addOrUpdateSubscriber(String phoneNumber, Subscriber subscriber) {
        log.info("Adding/Updating subscriber with phone number: {}", phoneNumber);

        // Ensure the ID in the path is applied to the entity
        subscriber.setPhoneNumber(phoneNumber);

        return subscriberRepository.findById(phoneNumber)
                .map(existingSub -> {
                    log.debug("Subscriber exists. Updating fields.");
                    existingSub.setUsername(subscriber.getUsername());
                    existingSub.setPassword(subscriber.getPassword());
                    existingSub.setDomain(subscriber.getDomain());
                    existingSub.setStatus(subscriber.getStatus());
                    existingSub.setFeatures(subscriber.getFeatures());
                    return subscriberRepository.save(existingSub);
                })
                .orElseGet(() -> {
                    log.debug("Subscriber does not exist. Creating new record.");
                    return subscriberRepository.save(subscriber);
                });
    }

    /**
     * Deletes a subscriber by their phone number.
     *
     * @param phoneNumber the unique phone number
     * @return true if deleted, false if the subscriber didn't exist
     */
    @Override
    public boolean deleteSubscriber(String phoneNumber) {
        log.info("Attempting to delete subscriber with phone number: {}", phoneNumber);
        return subscriberRepository.findById(phoneNumber)
                .map(sub -> {
                    subscriberRepository.delete(sub);
                    log.debug("Successfully deleted subscriber: {}", phoneNumber);
                    return true;
                })
                .orElseGet(() -> {
                    log.warn("Cannot delete. Subscriber not found: {}", phoneNumber);
                    return false;
                });
    }
}