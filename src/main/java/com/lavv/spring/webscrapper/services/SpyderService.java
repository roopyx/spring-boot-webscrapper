package com.lavv.spring.webscrapper.services;

public interface SpyderService {

    void start();
    void ScrapeLinksAndSave(String url);
}
