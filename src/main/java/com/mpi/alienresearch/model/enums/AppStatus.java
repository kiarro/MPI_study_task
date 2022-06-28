package com.mpi.alienresearch.model.enums;

/**
 * Status of Application
 */
public enum AppStatus {
    /**
     *  Waiting for director decision
     */
    CREATED,
    /**
     * Approved by director. Waited for acceptance
     */
    APPROVED,
    /**
     * Declined by director
     */
    DECLINED,
    /**
     * Accepted by executor
     */
    ACCEPTED,
    /**
     * Executed
     */
    CLOSED
}
