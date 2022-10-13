package com.infybuzz.service;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class JobService {

    @Autowired
    private JobLauncher jobLauncher;

    @Qualifier("firstJob")
    @Autowired
    private Job firstJob;
    @Qualifier("SecondJob")
    @Autowired
    private Job secondJob;

    @Async
    public void startJob(String jobName) throws Exception {
        Map<String, JobParameter> params = new HashMap<>();
        params.put("currentTime", new JobParameter((System.currentTimeMillis())));
        JobParameters jobParameters = new JobParameters(params);

        JobExecution jobExecution = null;
        if(jobName.equals("First Job")){
            jobExecution = jobLauncher.run(firstJob,jobParameters);
        } else if(jobName.equals("Second Job")){
            jobExecution = jobLauncher.run(secondJob,jobParameters);
        }
        System.out.println("JobExecution ID = "+jobExecution.getId());
    }
}
