package com.lavv.spring.webscrapper.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class SpyderServiceImpl implements SpyderService {

    @Autowired
    private WebPageServiceImpl webPageService;

    private final String initialLink = "https://elpais.com";

    public void start() {
        ScrapeLinksAndSave(initialLink);
    }

    public void ScrapeLinksAndSave(String url) {
        List<String> links = webPageService.getAllLinks(initialLink);
        links.stream().parallel().forEach(link -> {
            try {
                webPageService.scrapeAndSave(link);
                ScrapeLinksAndSave(url);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        });
    }
}
