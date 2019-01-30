package com.webLinkSystem.houtai.service;

import com.webLinkSystem.houtai.bean.VisitHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ServiceOfVisitHistory extends JpaRepository<VisitHistory, String> {
    @Query(value = "SELECT T.D,COUNT(1) FROM (SELECT *,YEAR(create_time) Y,MONTH(create_time) M,DAY(create_time) D FROM visit_history where product_id=?1) T WHERE T.Y =?2 AND T.M = ?3 GROUP BY T.D",nativeQuery = true)
    List<Object> findPV(Integer guid, String year, String month);

    @Query(value = "SELECT\n" +
            "\tU.D,\n" +
            "\tCOUNT( 1 ) \n" +
            "FROM\n" +
            "\t(\n" +
            "SELECT\n" +
            "\tT.* \n" +
            "FROM\n" +
            "\t( SELECT *, YEAR ( create_time ) Y, MONTH ( create_time ) M, DAY ( create_time ) D FROM visit_history WHERE product_id = ?1 ) T \n" +
            "WHERE\n" +
            "\tT.Y = ?2 \n" +
            "\tAND T.M = ?3 \n" +
            "GROUP BY\n" +
            "\tT.D,\n" +
            "\tT.request_id \n" +
            "\t) U \n" +
            "GROUP BY\n" +
            "\tU.D",nativeQuery = true)
    List<Object> findUV(Integer guid, String year, String month);

//    @Query(value = "SELECT * FROM web_link WHERE product_name LIKE ?1 ORDER BY create_time DESC ",nativeQuery = true)
//    List<VisitHistory> findAllOrderByCreateTimeDesc(String productName);
//
//    List<VisitHistory> findByProductName(String prudoctName);
//
//    List<VisitHistory> findByGuid(Integer guid);
}
