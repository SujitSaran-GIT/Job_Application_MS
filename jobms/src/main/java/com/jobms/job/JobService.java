package com.jobms.job;

import com.jobms.job.dto.JobDTO;

import java.util.List;

public interface JobService {
    List<JobDTO> findAll();
    void createJob(Job job);

    JobDTO getJobById(Long id);
    boolean deleteJobById(Long id);

    JobDTO updateJob(Long id, Job updatedJob);
}
