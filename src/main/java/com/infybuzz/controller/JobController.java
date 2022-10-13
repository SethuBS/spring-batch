package com.infybuzz.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("api/job")
public class JobController {

    @Autowired
    private JobLauncher jobLauncher;

    @Qualifier("firstJob")
    @Autowired
    private Job firstJob;
    @Qualifier("secondJob")
    @Autowired
    private Job secondJob;


    @GetMapping("/start/{jobName}")
    public String startJob(@PathVariable String jobName) throws Exception{

        Map<String, JobParameter> params = new HashMap<>();
        params.put("currentTime", new JobParameter((System.currentTimeMillis())));
        JobParameters jobParameters = new JobParameters(params);

        System.out.println("Starting the Job.......");
        if(jobName.equals("First Job")){
            jobLauncher.run(firstJob,jobParameters);
        } else if(jobName.equals("Second Job")){
            jobLauncher.run(secondJob,jobParameters);
        }
        return "Job started.......";
    }
}
