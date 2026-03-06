package com.telecom.ims.controller;

import com.telecom.ims.model.Subscriber;
import com.telecom.ims.service.SubscriberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller for IMS Subscriber operations.
 */
@RestController
@RequestMapping("/ims/subscriber")
@RequiredArgsConstructor
@Log4j2
public class SubscriberController {

    private final SubscriberService subscriberService;

    /**
     * Retrieve the subscriber identified by the provided phone number.
     */
    @GetMapping("/{phoneNumber}")
    public ResponseEntity<Subscriber> getSubscriber(@PathVariable String phoneNumber) {
        log.info("Received GET request for subscriber: {}", phoneNumber);
        return subscriberService.getSubscriber(phoneNumber)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Add or update a subscriber identified by the provided phone number.
     */
    @PutMapping("/{phoneNumber}")
    public ResponseEntity<Subscriber> addOrUpdateSubscriber(
            @PathVariable String phoneNumber,
            @RequestBody Subscriber subscriber) {
        log.info("Received PUT request for subscriber: {}", phoneNumber);
        Subscriber savedSubscriber = subscriberService.addOrUpdateSubscriber(phoneNumber, subscriber);
        return ResponseEntity.ok(savedSubscriber);
    }

    /**
     * Remove the subscriber identified by the phone number.
     */
    @DeleteMapping("/{phoneNumber}")
    public ResponseEntity<Void> deleteSubscriber(@PathVariable String phoneNumber) {
        log.info("Received DELETE request for subscriber: {}", phoneNumber);
        boolean isDeleted = subscriberService.deleteSubscriber(phoneNumber);

        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}