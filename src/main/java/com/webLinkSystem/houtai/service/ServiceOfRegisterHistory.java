package com.webLinkSystem.houtai.service;

import com.webLinkSystem.houtai.bean.RegisterHistory;
import com.webLinkSystem.houtai.bean.VisitHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ServiceOfRegisterHistory extends JpaRepository<RegisterHistory, String> {


//    @Query(value = "SELECT * FROM web_link WHERE product_name LIKE ?1 ORDER BY create_time DESC ",nativeQuery = true)
//    List<VisitHistory> findAllOrderByCreateTimeDesc(String productName);
//
//    List<VisitHistory> findByProductName(String prudoctName);
//
//    List<VisitHistory> findByGuid(Integer guid);
}
