package com.lavv.spring.webscrapper.services;

import com.lavv.spring.webscrapper.models.WebPage;

import java.io.IOException;
import java.util.List;

public interface WebPageService {

    List<WebPage> search(String query);
    void scrapeAndSave(String url) throws IOException;
    List<String> getAllLinks(String url);
}
