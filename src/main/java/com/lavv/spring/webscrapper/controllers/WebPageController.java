package com.lavv.spring.webscrapper.controllers;

import com.lavv.spring.webscrapper.models.WebPage;
import com.lavv.spring.webscrapper.services.SpyderServiceImpl;
import com.lavv.spring.webscrapper.services.WebPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class WebPageController {

    @Autowired
    private WebPageService webPageService;

    @Autowired
    private SpyderServiceImpl spyderService;

    @GetMapping("/search")
    public List<WebPage> search(@RequestParam("query") String query) {
        return webPageService.search(query);
    }

    @GetMapping("/webscrapper")
    public void scrapeAndSave(@RequestParam("url") String url) throws IOException {
        webPageService.scrapeAndSave(url);
    }

    @GetMapping("/spyder")
    public void Spyder() {
        spyderService.start();
    }
}
