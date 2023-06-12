package com.bhctobacco.dto;

import com.bhctobacco.model.Bale;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TaxCalculationsRequest {
    private List<Bale> baleList;
    private String growerNumber;
}
