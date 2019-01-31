package com.webLinkSystem.houtai.service;

import com.webLinkSystem.houtai.bean.WebLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ServiceOfWebLink extends JpaRepository<WebLink, String> {

    @Query(value = "SELECT\n" +
            "\tw.*,\n" +
            "\tb.create_time AS visit_time \n" +
            "FROM\n" +
            "\tweb_link w\n" +
            "\tLEFT JOIN ( SELECT a.* FROM ( SELECT * FROM visit_history ORDER BY create_time DESC ) a GROUP BY a.product_id ) b ON w.guid = b.product_id \n" +
            " WHERE w.product_name LIKE ?1 \n"+
            "ORDER BY\n" +
            "\tw.state DESC,\n" +
            "\tw.create_time DESC limit ?2,?3",nativeQuery = true)
    List<Object> findAllOrderByCreateTimeDesc(String productName,int page,int size);

    @Query(value = "SELECT COUNT(*) FROM web_link WHERE product_name LIKE ?1 ",nativeQuery = true)
    int findCount(String productName);


    List<WebLink> findByProductName(String prudoctName);

    List<WebLink> findByGuid(Integer guid);
}
