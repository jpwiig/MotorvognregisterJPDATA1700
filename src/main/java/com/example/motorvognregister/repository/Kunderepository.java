package com.example.motorvognregister.repository;

import com.example.motorvognregister.model.Newreg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.swing.tree.ExpandVetoException;
import java.util.List;

@Repository
public class Kunderepository {
    @Autowired
    private JdbcTemplate db;
    private Logger logger = LoggerFactory.getLogger(Kunderepository.class);
    public boolean lagreKunde(Newreg nyreg){
        String sql="INSERT INTO Reg (skiltNr, bilmerke, biltype, personnr, navn, adresse) VALUES(?,?,?,?,?,?)--";
        try {
            db.update(sql, nyreg.getSkiltNr(), nyreg.getBilmerke(), nyreg.getBiltype(), nyreg.getPersonnr(), nyreg.getNavn(), nyreg.getAdresse());
            return true;
        } catch (Exception e){
            logger.error("feil i lagring" + e);
            return false;
        }
    }

    public List<Newreg> hentall(){
        try {
            String sql = "SELECT * FROM Reg";
            return db.query(sql, new BeanPropertyRowMapper<>(Newreg.class));
        } catch (Exception e){
            logger.error("feil i henting av kuner" + e);
            return null;
        }
    }
    public void deleteall(){
        try {
            String sql = "DELETE FROM Reg";
            db.update(sql);
        }catch (Exception e){
            logger.error("feil i sletting av regisering " + e);
        }
    }

    public void deleteone(){

        try {
            String sql="";
            db.update(sql);
        } catch (Exception e){
            logger.error("feil enkelt sletting" + e);
        }

    }
}
