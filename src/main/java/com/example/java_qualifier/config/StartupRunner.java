package com.example.javaqualifier.config;

import com.example.javaqualifier.service.QualifierService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupRunner implements CommandLineRunner {

    private final QualifierService qualifierService;

    public StartupRunner(QualifierService qualifierService) {
        this.qualifierService = qualifierService;
    }

    @Override
    public void run(String... args) {
        qualifierService.executeWorkflow();
    }
}
