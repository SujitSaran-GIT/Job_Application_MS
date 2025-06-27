package com.jobms.job.mapper;

import com.jobms.job.Job;
import com.jobms.job.dto.JobCompanyDTO;
import com.jobms.job.external.Company;

public class JobMapper {
    public static JobCompanyDTO mapToJobCompanyDTO(Job job, Company company){
        JobCompanyDTO jobCompanyDTO = new JobCompanyDTO();
        jobCompanyDTO.setId(job.getId());
        jobCompanyDTO.setTitle(job.getTitle());
        jobCompanyDTO.setDescription(job.getDescription());
        jobCompanyDTO.setLocation(job.getLocation());
        jobCompanyDTO.setMaxSalary(job.getMaxSalary());
        jobCompanyDTO.setMinSalary(job.getMinSalary());
        jobCompanyDTO.setCompany(company);

        return jobCompanyDTO;
    }
}
