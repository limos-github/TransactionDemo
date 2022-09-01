package com.limo.transactiondemo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author libiao
 */
@RestController
@RequestMapping("/oracledb")
public class OracleController {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @RequestMapping("/getUsers")
  public List<Map<String, Object>> getUsers() {

    String sql = "select * from WEB_USER";
    List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
    return list;
  }

}