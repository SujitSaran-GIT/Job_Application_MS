package com.jobms.job;

import com.jobms.job.dto.JobDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {
//    Find all jobs
//    private List<Job> jobs = new ArrayList<>();

    @Autowired
    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<JobDTO>> findAll(){
        return ResponseEntity.ok(jobService.findAll());
    }

//    Instead of using PostMapping, GetMapping and PutMapping We can use RequestMapping
//    @RequestMapping(value = "/jobs/{id}, method = RequestMethod.PUT)
    @RequestMapping(method = RequestMethod.POST)
//    @PostMapping("/jobs")
    public ResponseEntity<Job> createJob(@RequestBody Job job){
        jobService.createJob(job);
        return new ResponseEntity<>(job,HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobDTO> getJobById(@PathVariable Long id){
        JobDTO jobCompanyDTO = jobService.getJobById(id);
        if (jobCompanyDTO != null){
            return new ResponseEntity<>(jobCompanyDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id){
        boolean deleted = jobService.deleteJobById(id);
        if (deleted){
            return new ResponseEntity<>("Job deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobDTO> updateJob(@PathVariable Long id, @RequestBody Job updatedJob){
        JobDTO jobCompanyDTO = jobService.updateJob(id,updatedJob);

        if (jobCompanyDTO != null){
            return new ResponseEntity<>(jobCompanyDTO,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
