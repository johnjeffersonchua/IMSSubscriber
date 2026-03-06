package com.telecom.ims.service;

import com.telecom.ims.model.Subscriber;

import java.util.Optional;

public interface SubscriberService {


    public Optional<Subscriber> getSubscriber(String phoneNumber);
    public Subscriber addOrUpdateSubscriber(String phoneNumber, Subscriber subscriber);
    public boolean deleteSubscriber(String phoneNumber);

}
