package com.webLinkSystem.houtai.service;

import com.webLinkSystem.houtai.bean.WebLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ServiceOfWebLink extends JpaRepository<WebLink, String> {

    @Query(value = "SELECT * FROM web_link WHERE product_name LIKE ?1 ORDER BY create_time DESC ",nativeQuery = true)
    List<WebLink> findAllOrderByCreateTimeDesc(String productName);

    List<WebLink> findByProductName(String prudoctName);

    List<WebLink> findByGuid(String guid);
}
