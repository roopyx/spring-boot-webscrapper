package com.lavv.spring.webscrapper.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "webpage")
public class WebPage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String domain;
    private String url;
    private String title;
    @Column(columnDefinition = "LONGTEXT")
    private String description;
    private String picture;
    private Integer ranking;

}
