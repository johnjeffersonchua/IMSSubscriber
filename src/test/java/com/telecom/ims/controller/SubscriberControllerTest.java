package com.telecom.ims.controller;

import com.telecom.ims.model.Subscriber;
import com.telecom.ims.service.SubscriberService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SubscriberControllerTest {

    @Mock
    private SubscriberService service;

    @InjectMocks
    private SubscriberController controller;

    private Subscriber mockSubscriber;

    @Before
    public void setup() {
        mockSubscriber = Subscriber.builder()
                .phoneNumber("18675181010")
                .username("16045906403")
                .build();
    }

    @Test
    public void testGetSubscriber_Ok() {
        when(service.getSubscriber("18675181010")).thenReturn(Optional.of(mockSubscriber));

        ResponseEntity<Subscriber> response = controller.getSubscriber("18675181010");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockSubscriber, response.getBody());
    }

    @Test
    public void testGetSubscriber_NotFound() {
        when(service.getSubscriber("unknown")).thenReturn(Optional.empty());

        ResponseEntity<Subscriber> response = controller.getSubscriber("unknown");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testAddOrUpdateSubscriber() {
        when(service.addOrUpdateSubscriber(eq("18675181010"), any(Subscriber.class)))
                .thenReturn(mockSubscriber);

        ResponseEntity<Subscriber> response = controller.addOrUpdateSubscriber("18675181010", mockSubscriber);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockSubscriber, response.getBody());
    }

    @Test
    public void testDeleteSubscriber_NoContent() {
        when(service.deleteSubscriber("18675181010")).thenReturn(true);

        ResponseEntity<Void> response = controller.deleteSubscriber("18675181010");

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}