package com.lavv.spring.webscrapper.services;

import com.lavv.spring.webscrapper.models.WebPage;
import com.lavv.spring.webscrapper.repository.WebPageRepository;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class WebPageServiceImpl implements WebPageService {

    @Autowired
    private WebPageRepository webPageRepository;

    public List<WebPage> search(String query) {
        return new ArrayList<>(webPageRepository.findByText(query));
    }

    public void scrapeAndSave(String url) throws IOException {

        Document document = Jsoup.connect(url).get();

        String title = document.title();
        String description = document.select("meta[name=description]").attr("content");
        String picture = document.select("meta[property=og:image]").attr("content");

        WebPage webPage = new WebPage();
        webPage.setDomain(getDomainFromUrl(url));
        webPage.setUrl(url);
        webPage.setTitle(title);
        webPage.setDescription(description);
        webPage.setPicture(picture);
        webPage.setRanking(6);

        webPageRepository.save(webPage);
    }

    private String getDomainFromUrl(String url) {

        String domain = url.replaceFirst("^(https?://)?(www\\.)?", "");
        int index = domain.indexOf('/');
        if (index != -1) {
            domain = domain.substring(0, index);
        }

        return domain;
    }

    public List<String> getAllLinks(String url) {

        List<String> result = new ArrayList<>();

        try {
            Document document = Jsoup.connect(url).get();
            Elements links = document.select("a[href]");
            for (Element link: links) {
                String linkHref = link.attr("href");
                result.add(linkHref);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return result;
    }
}
