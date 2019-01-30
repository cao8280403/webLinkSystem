package com.webLinkSystem.houtai.service;

import com.webLinkSystem.houtai.bean.WebLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ServiceOfWebLink extends JpaRepository<WebLink, String> {

    @Query(value = "SELECT * FROM web_link WHERE product_name LIKE ?1 ORDER BY state DESC,create_time DESC limit ?2,?3",nativeQuery = true)
    List<WebLink> findAllOrderByCreateTimeDesc(String productName,int page,int size);

    @Query(value = "SELECT COUNT(*) FROM web_link WHERE product_name LIKE ?1 ",nativeQuery = true)
    int findCount(String productName);


    List<WebLink> findByProductName(String prudoctName);

    List<WebLink> findByGuid(Integer guid);
}
