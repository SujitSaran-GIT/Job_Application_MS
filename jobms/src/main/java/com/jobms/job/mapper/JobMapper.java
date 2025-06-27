package com.jobms.job.mapper;

import com.jobms.job.Job;
import com.jobms.job.dto.JobDTO;
import com.jobms.job.external.Company;
import com.jobms.job.external.Review;

import java.util.List;

public class JobMapper {
    public static JobDTO mapToJobCompanyDTO(Job job, Company company, List<Review> review){
        JobDTO jobCompanyDTO = new JobDTO();
        jobCompanyDTO.setId(job.getId());
        jobCompanyDTO.setTitle(job.getTitle());
        jobCompanyDTO.setDescription(job.getDescription());
        jobCompanyDTO.setLocation(job.getLocation());
        jobCompanyDTO.setMaxSalary(job.getMaxSalary());
        jobCompanyDTO.setMinSalary(job.getMinSalary());
        jobCompanyDTO.setCompany(company);

        jobCompanyDTO.setReview(review);
        return jobCompanyDTO;
    }
}
