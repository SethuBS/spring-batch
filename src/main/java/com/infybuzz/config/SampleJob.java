package com.infybuzz.config;

import com.infybuzz.listener.FirstJobListener;
import com.infybuzz.listener.FirstStepListener;
import com.infybuzz.service.FirstTasklet;
import com.infybuzz.service.SecondTasklet;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SampleJob {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private FirstTasklet firstTasklet;
    @Autowired
    private SecondTasklet secondTasklet;
    @Autowired
    private FirstJobListener firstJobListener;
    @Autowired
    private FirstStepListener firstStepListener;

   // @Bean
    public Job firstJob(){
        return jobBuilderFactory.get("First Job")
                .incrementer(new RunIdIncrementer())
                .start(firstStep())
                .next(secondStep())
                .listener(firstJobListener)
                .build();
    }

    private Step firstStep(){
        return stepBuilderFactory.get("First Step")
                .tasklet(firstTasklet)
                .listener(firstStepListener)
                .build();
    }
    private Step secondStep(){
        return stepBuilderFactory.get("Second Step")
                .tasklet(secondTasklet)
                .build();
    }

}
