package com.lavv.spring.webscrapper.jobs;

import com.lavv.spring.webscrapper.services.SpyderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class WebScrapperJob {

    @Autowired
    private SpyderService spyderService;

    @Scheduled(cron = "0 0 17 * * MON")
    public void executeJob() {
        spyderService.start();
    }

}
