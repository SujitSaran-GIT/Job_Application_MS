package com.jobms.job;

import com.jobms.job.dto.JobCompanyDTO;

import java.util.List;

public interface JobService {
    List<JobCompanyDTO> findAll();
    void createJob(Job job);

    Job getJobById(Long id);
    boolean deleteJobById(Long id);

    boolean updateJob(Long id, Job updatedJob);
}
