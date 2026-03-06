package com.telecom.ims.controller;

import com.telecom.ims.model.Subscriber;
import com.telecom.ims.service.SubscriberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller for IMS Subscriber operations with explicit HTTP Statuses.
 */
@RestController
@RequestMapping("/ims/subscriber")
@RequiredArgsConstructor
@Log4j2
public class SubscriberController {

    private final SubscriberService subscriberService;

    /**
     * Retrieve the subscriber. Returns 200 OK or 404 Not Found.
     */
    @GetMapping("/{phoneNumber}")
    public ResponseEntity<Subscriber> getSubscriber(@PathVariable String phoneNumber) {
        log.info("Received GET request for subscriber: {}", phoneNumber);

        return subscriberService.getSubscriber(phoneNumber)
                .map(subscriber -> ResponseEntity.status(HttpStatus.OK).body(subscriber))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    /**
     * Add or update a subscriber.
     * Returns 201 Created for new entries, or 200 OK for updates.
     */
    @PutMapping("/{phoneNumber}")
    public ResponseEntity<Subscriber> addOrUpdateSubscriber(
            @PathVariable String phoneNumber,
            @RequestBody Subscriber subscriber) {
        log.info("Received PUT request for subscriber: {}", phoneNumber);

        // Check if exists to determine the correct status code
        boolean exists = subscriberService.getSubscriber(phoneNumber).isPresent();
        Subscriber savedSubscriber = subscriberService.addOrUpdateSubscriber(phoneNumber, subscriber);

        HttpStatus status = exists ? HttpStatus.OK : HttpStatus.CREATED;

        return ResponseEntity.status(status).body(savedSubscriber);
    }

    /**
     * Remove the subscriber. Returns 204 No Content or 404 Not Found.
     */
    @DeleteMapping("/{phoneNumber}")
    public ResponseEntity<Void> deleteSubscriber(@PathVariable String phoneNumber) {
        log.info("Received DELETE request for subscriber: {}", phoneNumber);

        boolean isDeleted = subscriberService.deleteSubscriber(phoneNumber);

        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}