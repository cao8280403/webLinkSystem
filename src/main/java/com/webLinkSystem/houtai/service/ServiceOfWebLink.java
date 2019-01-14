package com.webLinkSystem.houtai.service;

import com.webLinkSystem.houtai.bean.WebLink;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceOfWebLink extends JpaRepository<WebLink, String> {
    @Override
    List<WebLink> findAll();

    List<WebLink> findByProductName(String prudoctName);

    List<WebLink> findByGuid(String guid);
}
