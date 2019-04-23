package com.webLinkSystem.houtai.service;

import com.webLinkSystem.houtai.bean.RegisterHistory;
import com.webLinkSystem.houtai.bean.VisitHistory;
import com.webLinkSystem.houtai.bean.WebLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ServiceOfRegisterHistory extends JpaRepository<RegisterHistory, String> {
    List<RegisterHistory> findByProductNameAndIp(String productName, String ip);

    @Query(value = "select * from register_history where year(createtime) = ?2 and month(createtime) = ?3 and product_name like ?1",nativeQuery = true)
    List<RegisterHistory> getDaochuList(String guid, String year, String month);


//    @Query(value = "SELECT * FROM web_link WHERE product_name LIKE ?1 ORDER BY create_time DESC ",nativeQuery = true)
//    List<VisitHistory> findAllOrderByCreateTimeDesc(String productName);
//
//    List<VisitHistory> findByProductName(String prudoctName);
//
//    List<VisitHistory> findByGuid(Integer guid);
}
