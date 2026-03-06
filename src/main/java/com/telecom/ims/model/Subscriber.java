package com.telecom.ims.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Represents an active home phone subscriber on the IMS platform.
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Subscriber {

    @Id
    private String phoneNumber;
    private String username;
    private String password;
    private String domain;
    private String status;

    @Embedded
    private Features features;
}