package com.smelgert.carlease.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class LeaseRate {

    // Constructor
    public LeaseRate() {
        // Default constructor can be left empty or removed if not needed
    }

    // Method to calculate the lease rate with parameters
    public BigDecimal calculateLeaseRate(int mileagePerYear, int durationInMonths, BigDecimal interestRate, BigDecimal nettPrice) {

        BigDecimal partOneOfCalculation = BigDecimal.valueOf(mileagePerYear).divide(BigDecimal.valueOf(12), 10, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(durationInMonths))
                .divide(nettPrice, 10, RoundingMode.HALF_UP);
        BigDecimal partTwoOfCalculation = interestRate.divide(BigDecimal.valueOf(100), 10, RoundingMode.HALF_UP)
                .multiply(nettPrice)
                .divide(BigDecimal.valueOf(12), 10, RoundingMode.HALF_UP);

        // Lease rate per month is the sum of both components
        return partOneOfCalculation.add(partTwoOfCalculation).setScale(2, RoundingMode.HALF_UP); // Final rounding to 2 decimal places
    }
}
