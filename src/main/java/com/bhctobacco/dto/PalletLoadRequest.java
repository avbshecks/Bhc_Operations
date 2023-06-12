package com.bhctobacco.dto;

import com.bhctobacco.model.Bale;

import java.util.List;
import lombok.Data;
@Data
public class PalletLoadRequest {
    private List<Bale> bales;
    //private List<String> grades;
}
