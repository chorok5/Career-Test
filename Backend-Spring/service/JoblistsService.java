package com.kidwiz.web.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.kidwiz.web.DTO.JoblistsDTO;

@Service
public class JoblistsService {
    public List<JoblistsDTO> getJobs() throws IOException {
        String url = "https://job.incruit.com/jobdb_list/searchjob.asp?occ2=574&occ1=120&rgn2=11";
        Document document = Jsoup.connect(url).get();

        List<JoblistsDTO> jobs = new ArrayList<>();
        Elements jobElements = document.select(".c_row"); // 공고 리스트 선택

        for (Element jobElement : jobElements) {
            String title = jobElement.select(".cell_mid .cl_top a").text();  // 채용 제목
            String company = jobElement.select(".cell_first .cpname").text();  // 회사 이름
            String location = jobElement.select(".cell_mid .cl_md span:nth-of-type(3)").text();  // 위치
            String date = jobElement.select(".cell_last .cl_btm span:nth-of-type(1)").text();  // 마감 날짜
            String detailLink = jobElement.select(".cell_mid .cl_top a").attr("href");  // 자세히 보기 링크

            // Debug logging
//            System.out.println("Title: " + title);
//            System.out.println("Company: " + company);
//            System.out.println("Location: " + location);
//            System.out.println("Date: " + date);
//            System.out.println("Detail Link: " + detailLink);

            JoblistsDTO job = new JoblistsDTO(title, company, location, date, detailLink);
            jobs.add(job);
        }

        return filterAndCleanJobs(jobs);
    }
    
    private List<JoblistsDTO> filterAndCleanJobs(List<JoblistsDTO> jobs) { // 중복 제거 (detailLink 기준)
        
        Map<String, JoblistsDTO> uniqueJobs = new HashMap<>();
        for (JoblistsDTO job : jobs) {
            uniqueJobs.put(job.getDetailLink(), job);
        }
        // 필드 형식 통일 및 빈 데이터 제거
        List<JoblistsDTO> filteredJobs = uniqueJobs.values().stream()
                .filter(this::isValidJob)
                .collect(Collectors.toList());

        
        // 접수 기간 기준으로 정렬
        filteredJobs.sort(Comparator.comparing(JoblistsDTO::getDate));
        return filteredJobs;
    }
    
    private boolean isValidJob(JoblistsDTO job) {
        // 필수 필드 확인
        return job.getTitle() != null && !job.getTitle().isEmpty()
                && job.getCompany() != null && !job.getCompany().isEmpty()
                && job.getLocation() != null && !job.getLocation().isEmpty()
                && job.getDate() != null && !job.getDate().isEmpty();
    }
}
