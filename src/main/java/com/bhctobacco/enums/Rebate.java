package com.bhctobacco.enums;

public enum Rebate {
    REBATE_1(0.05),REBATE_2(10.00);
    private double pricePerKg;

    Rebate(double pricePerKg) {
        this.pricePerKg = pricePerKg;
    }
}
