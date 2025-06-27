package com.jobms.job.impl;


import com.jobms.job.Job;
import com.jobms.job.JobRepository;
import com.jobms.job.JobService;
import com.jobms.job.dto.JobCompanyDTO;
import com.jobms.job.external.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {

//    private List<Job> jobs = new ArrayList<>();
    @Autowired
JobRepository jobRepository;
//    private Long nextId = 1l;

    @Override
    public List<JobCompanyDTO> findAll() {
//        RestTemplate restTemplate = new RestTemplate();
//
//        try {
//            String company = restTemplate.getForObject("http://localhost:8082/company/1", String.class);
//
//            if (company != null) {
//                System.out.println("Company ID: " + company);
//            } else {
//                System.out.println("Company is null. The external service might be down or returned no content.");
//            }
//        } catch (Exception e) {
//            System.out.println("Failed to fetch company: " + e.getMessage());
//        }

        List<Job> jobs = jobRepository.findAll();
        List<JobCompanyDTO> jobCompanyDTOS = new ArrayList<>();



//        for(Job job: jobs){
//            JobCompanyDTO jobCompanyDTO = new JobCompanyDTO();
//            jobCompanyDTO.setJob(job);
//            Company company = restTemplate.getForObject("http://localhost:8082/company/"+job.getCompanyId(),Company.class);
//
//            jobCompanyDTO.setCompany(company);
//            jobCompanyDTOS.add(jobCompanyDTO);
//        }

        return jobs.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private JobCompanyDTO convertToDTO(Job job){
        JobCompanyDTO jobCompanyDTO = new JobCompanyDTO();
        jobCompanyDTO.setJob(job);
        RestTemplate restTemplate = new RestTemplate();
        Company company = restTemplate.getForObject("http://localhost:8082/company/"+job.getCompanyId(),Company.class);

        jobCompanyDTO.setCompany(company);
        return jobCompanyDTO;
    }


    @Override
    public void createJob(Job job) {
//        job.setId(nextId++);
//        jobs.add(job);
        jobRepository.save(job);
    }

    @Override
    public Job getJobById(Long id) {
//        for (Job job : jobs){
//            if (job.getId().equals(id)){
//                return job;
//            }
//        }
//        return null;

        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteJobById(Long id) {
//        Iterator<Job> iterator = jobs.iterator();
//        while (iterator.hasNext()){
//            Job job = iterator.next();
//            if (job.getId().equals(id)){
//                iterator.remove();
//                return true;
//            }
//        }
//        return false;
        try {
            jobRepository.deleteById(id);
            return true;
        } catch(Exception e){
            return false;
        }
    }

    @Override
    public boolean updateJob(Long id, Job updatedJob) {
//        Iterator<Job> iterator = jobs.iterator();
//        while(iterator.hasNext()){
//            Job job = iterator.next();
//            if (job.getId().equals(id)){
//                job.setTitle(updatedJob.getTitle());
//                job.setDescription(updatedJob.getDescription());
//                job.setMaxSalary(updatedJob.getMaxSalary());
//                job.setMinSalary(updatedJob.getMinSalary());
//                job.setLocation(updatedJob.getLocation());
//
//                return true;
//            }
//        }
//        return false;

        Optional<Job> jobOptional = jobRepository.findById(id);
        if (jobOptional.isPresent()){
            Job job = jobOptional.get();
            job.setTitle(updatedJob.getTitle());
            job.setDescription(updatedJob.getDescription());
            job.setMinSalary(updatedJob.getMinSalary());
            job.setMaxSalary(updatedJob.getMaxSalary());
            job.setLocation(updatedJob.getLocation());

            jobRepository.save(job);
            return true;
        }
        return false;
    }
}
