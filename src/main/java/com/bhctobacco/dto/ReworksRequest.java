package com.bhctobacco.dto;

import com.bhctobacco.model.Bale;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class ReworksRequest {
    private List<Bale> baleList;

}
