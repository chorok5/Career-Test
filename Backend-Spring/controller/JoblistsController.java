package com.kidwiz.web.controller;

import com.kidwiz.web.DTO.JoblistsDTO;
import com.kidwiz.web.service.JoblistsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value="/api/jobslists")
public class JoblistsController {

    @Autowired
    private JoblistsService joblistsService;

    @GetMapping
    public List<JoblistsDTO> getJobs() {
        try {
            return joblistsService.getJobs();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
