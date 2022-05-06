package com.example.motorvognregister.repository;

import com.example.motorvognregister.model.Biler;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class BIlrepository {
    @Autowired
    private JdbcTemplate db;
    private Logger logger = LoggerFactory.getLogger(BIlrepository.class);
public List<Biler>hentbil(){
    //sett inn logger
    String sql = "SELECT * FROM Biler";
  try  {
        return db.query(sql, new BeanPropertyRowMapper<>(Biler.class));
    } catch (Exception e){
      logger.error("feil du er så feil i biler" + e);
      return null;
  }
}}
