package com.kidwiz.web.DTO;

import lombok.Data;

@Data
public class JoblistsDTO {

    private String title;
    private String company;
    private String location;
    private String date;
    private String detailLink;

    public JoblistsDTO(String title, String company, String location, String date, String detailLink) {
        this.title = title;
        this.company = company;
        this.location = location;
        this.date = date;
        this.detailLink = detailLink;
    }
}
