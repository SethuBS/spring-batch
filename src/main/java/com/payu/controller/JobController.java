package com.payu.controller;

import com.payu.model.JobParamsRequest;
import com.payu.service.JobService;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
