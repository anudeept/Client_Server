package com.newrelic.codingchallenge.domain;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author : anudeep on 1/30/18
 * @project : Application_Server
 */
public class ReportDTO {

    private AtomicLong totalUnique;
    private AtomicLong unique;
    private AtomicLong duplicate;

    public ReportDTO() {
        totalUnique= new AtomicLong(0);
        unique= new AtomicLong(0);
        duplicate= new AtomicLong(0);

    }

    public void flush() {
        this.unique.set(0);
        this.duplicate.set(0);
    }

    public void incUnique() {
        this.unique.incrementAndGet();
        this.totalUnique.incrementAndGet();
    }

    public void incDuplice() {
        this.duplicate.incrementAndGet();
    }

    public AtomicLong getTotalUnique() {
        return totalUnique;
    }

    public AtomicLong getUnique() {
        return unique;
    }

    public AtomicLong getDuplicate() {
        return duplicate;
    }

    @Override
    public String toString() {

        return new StringBuilder().append("Received ").append(unique).append(" unique numbers, ")
                .append(duplicate).append(" duplicates.")
                .append(" Unique Total:").append(totalUnique).toString();

    }
}
