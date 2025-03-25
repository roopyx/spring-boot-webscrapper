package com.lavv.spring.webscrapper.repository;

import com.lavv.spring.webscrapper.models.WebPage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WebPageRepository extends CrudRepository<WebPage, Integer> {

    @Query(
        "SELECT w " +
        "FROM WebPage w " +
        "WHERE w.domain Like %:text% " +
        "OR w.description LIKE %:text% " +
        "OR w.title LIKE %:text% " +
        "OR w.url LIKE %:text%"
    )
    List<WebPage> findByText(@Param("text") String text);

    WebPage findByUrl(String url);
}
