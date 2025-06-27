package com.jobms.job;

import com.jobms.job.dto.JobCompanyDTO;

import java.util.List;

public interface JobService {
    List<JobCompanyDTO> findAll();
    void createJob(Job job);

    JobCompanyDTO getJobById(Long id);
    boolean deleteJobById(Long id);

    JobCompanyDTO updateJob(Long id, Job updatedJob);
}
