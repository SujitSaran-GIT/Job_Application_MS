package com.jobms.job.impl;


import com.jobms.job.Job;
import com.jobms.job.JobRepository;
import com.jobms.job.JobService;
import com.jobms.job.clients.CompanyClient;
import com.jobms.job.clients.ReviewClient;
import com.jobms.job.dto.JobDTO;
import com.jobms.job.external.Company;
import com.jobms.job.external.Review;
import com.jobms.job.mapper.JobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    RestTemplate restTemplate;

    private CompanyClient companyClient;
    private ReviewClient reviewClient;

    public JobServiceImpl(CompanyClient companyClient, JobRepository jobRepository, ReviewClient reviewClient) {
        this.companyClient = companyClient;
        this.jobRepository = jobRepository;
        this.reviewClient = reviewClient;
    }

    @Override
    public List<JobDTO> findAll() {
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
        List<JobDTO> jobCompanyDTOS = new ArrayList<>();



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

    private JobDTO convertToDTO(Job job){
//        JobCompanyDTO jobCompanyDTO = new JobCompanyDTO();
//        jobCompanyDTO.setJob(job);
//        RestTemplate restTemplate = new RestTemplate();

//        Company company = restTemplate.getForObject("http://COMPANYMS:8082/company/"+job.getCompanyId(),Company.class);
        Company company = companyClient.getCompany(job.getCompanyId());
//        ResponseEntity<List<Review>> responseEntity = restTemplate.exchange(
//                "http://REVIEWMS:8083/reviews?companyId=" + job.getCompanyId(),
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<List<Review>>() {
//        });

        List<Review> reviews = reviewClient.getReviews(job.getCompanyId());

//        List<Review> reviews = responseEntity.getBody();
        JobDTO jobCompanyDTO = JobMapper.mapToJobCompanyDTO(job,company,reviews);
//        jobCompanyDTO.setCompany(company);
        return jobCompanyDTO;
    }


    @Override
    public void createJob(Job job) {
//        job.setId(nextId++);
//        jobs.add(job);
        jobRepository.save(job);
    }

    @Override
    public JobDTO getJobById(Long id) {
//        for (Job job : jobs){
//            if (job.getId().equals(id)){
//                return job;
//            }
//        }
//        return null;

//        return jobRepository.findById(id).orElse(null);
        Job job = jobRepository.findById(id).orElse(null);
        return convertToDTO(job);
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
    public JobDTO updateJob(Long id, Job updatedJob) {
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
            return convertToDTO(job);
        }
        return null;
    }
}
