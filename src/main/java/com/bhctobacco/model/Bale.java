package com.bhctobacco.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bale {
    private String barcode;
    private String grade;
    private double price;
    private double mass;
    private  Grower grower;
}
