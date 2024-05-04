package com.kidwiz.web.DTO;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;


@Data
public class ResultData {
	private String title;
    private String description;
    private List<String> recommendedJobs;
    private List<String> personalTraits;
    
    
    public ResultData() {
        this.personalTraits = new ArrayList<>();
        this.recommendedJobs = new ArrayList<>();
    }

}
