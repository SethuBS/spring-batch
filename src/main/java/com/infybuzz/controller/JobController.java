package com.infybuzz.controller;

import com.infybuzz.model.JobParamsRequest;
import com.infybuzz.service.JobService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("api/job")
public class JobController {

    @Autowired
    private JobService jobService;

    @Autowired
    private JobOperator jobOperator;


    @GetMapping("/start/{jobName}")
    public String startJob(@PathVariable String jobName, @RequestBody List<JobParamsRequest> jobParamsRequestList) throws Exception{
        jobService.startJob(jobName,jobParamsRequestList);
        return "Job started.......";
    }

    @GetMapping("/stop/{executionId}")
    public String stopJob(@PathVariable long executionId) throws Exception{
        jobOperator.stop(executionId);
        return "Jop Stopped.......";
    }
}
