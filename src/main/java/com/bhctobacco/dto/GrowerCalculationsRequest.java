package com.bhctobacco.dto;

import com.bhctobacco.model.Bale;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GrowerCalculationsRequest {
    private ArrayList<Bale> baleList ;
    private String growerNumber ;

}
