package com.telecom.ims.service;

import com.telecom.ims.model.Subscriber;
import com.telecom.ims.repository.SubscriberRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SubscriberServiceTest {

    @Mock
    private SubscriberRepository repository;

    @InjectMocks
    private SubscriberService service;

    private Subscriber mockSubscriber;

    @Before
    public void setup() {
        mockSubscriber = Subscriber.builder()
                .phoneNumber("18675181010")
                .username("16045906403")
                .status("ACTIVE")
                .build();
    }

    @Test
    public void testGetSubscriber_Found() {
        when(repository.findById("18675181010")).thenReturn(Optional.of(mockSubscriber));

        Optional<Subscriber> result = service.getSubscriber("18675181010");

        assertTrue(result.isPresent());
        assertEquals("16045906403", result.get().getUsername());
        verify(repository, times(1)).findById("18675181010");
    }

    @Test
    public void testAddOrUpdateSubscriber_NewRecord() {
        when(repository.findById("18675181010")).thenReturn(Optional.empty());
        when(repository.save(any(Subscriber.class))).thenReturn(mockSubscriber);

        Subscriber result = service.addOrUpdateSubscriber("18675181010", mockSubscriber);

        assertNotNull(result);
        assertEquals("ACTIVE", result.getStatus());
        verify(repository, times(1)).save(mockSubscriber);
    }

    @Test
    public void testDeleteSubscriber_Success() {
        when(repository.findById("18675181010")).thenReturn(Optional.of(mockSubscriber));

        boolean isDeleted = service.deleteSubscriber("18675181010");

        assertTrue(isDeleted);
        verify(repository, times(1)).delete(mockSubscriber);
    }
}