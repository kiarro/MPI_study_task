package com.mpi.alienresearch.model.enums;

/**
 * Status of Application
 */
public enum AppStatus {
    /**
     *  Waiting for director decision
     */
    Created,
    /**
     * Approved by director. Waited for acceptance
     */
    Approved,
    /**
     * Declined by director
     */
    Declined,
    /**
     * Accepted by executor
     */
    Accepted,
    /**
     * Executed
     */
    Closed
}
