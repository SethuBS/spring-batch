package com.infybuzz.service;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SecondJobScheduler {

    @Autowired
    private JobLauncher jobLauncher;

    @Qualifier("SecondJob")
    @Autowired
    private Job secondJob;

    @Scheduled(cron = "0 0/1 * 1/1 * ?")
    public void secondJobStater() throws Exception{

        Map<String, JobParameter> params = new HashMap<>();
        params.put("currentTime", new JobParameter((System.currentTimeMillis())));

        JobParameters jobParameters = new JobParameters(params);

        JobExecution jobExecution = jobLauncher.run(secondJob,jobParameters);

        System.out.println("JobExecution ID = "+jobExecution.getId());
    }
}
