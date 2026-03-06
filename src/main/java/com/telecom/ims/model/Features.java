package com.telecom.ims.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

/**
 * Encapsulates the features of an IMS subscriber.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Features {
    @Embedded
    private CallForwardNoReply callForwardNoReply;
}