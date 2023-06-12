package com.bhctobacco.model;

import lombok.Data;

import java.util.List;

@Data
public class Pallet {
    private String palletNumber;
    private List<Bale> bales;
    private String warehouse;
    private List<String> grades;
    private int baleCount;

}
