package com.example.motorvognregister;


import com.example.motorvognregister.model.Account;
import com.example.motorvognregister.model.Newreg;
import com.example.motorvognregister.model.Biler;
import com.example.motorvognregister.repository.BIlrepository;
import com.example.motorvognregister.repository.Kunderepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class Motorvogncontroller {
    @Autowired
    Kunderepository Repo;
    @Autowired
    BIlrepository Rep;

    @Autowired

    private Logger log = LoggerFactory.getLogger(Motorvogncontroller.class);

    @PostMapping("/api/logginn")
    public void loggin(Account logginn, HttpServletResponse response) throws IOException{
        try{

        } catch (Exception e){
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "lmao feil");
        }

    }
    @GetMapping("/api/hentBiler")
    public List<Biler> hentbiler(HttpServletResponse response) throws IOException {
        List<Biler> biler = Rep.hentbil();
        if (biler == null) {
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Feil i databasen, prøv igjen senere");
        }
        return biler;
    }

    @PostMapping("/api/registere")
    public void newcar(Newreg nyreg, HttpServletResponse response) throws IOException {

        if (valdiernewreg(nyreg)) {
            if (!Repo.lagreKunde(nyreg)) {
                response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Feil lagringen av kunde til DB");
            }
        }
    }

    @GetMapping("/api/show")
    public List<Newreg> showcars() {
        return Repo.hentall();
    }

    @PostMapping("/api/remove")
    public void delete() {
        Repo.deleteall();
    }

    @PostMapping("/api/deleteone")
    public void deleteone(Newreg nyreg, HttpServletResponse response) throws IOException {
        try {
            Repo.deleteone(nyreg);
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "feil i db, prøv igjen senere");
        }

    }

    public boolean valdiernewreg(Newreg newreg) {
        String regexppersonnr = "[0-9]{11}";
        String regexpnavn = "[a-zA-ZæøåÆØÅ. //-]{2,30}";
        String regexpadresse = "[0-9a-zA-ZæøåÆØÅ. //-]{2,30}";
        String regexpKjennetegn = "[A-Z]{2}[0-9]{6}";
        String regexpmerke = "[A-Za-zæøåÆØÅ. //-]{2,10}";
        String regexptype = "[0-9a-zA-ZæøåÆØÅ. //-]{2,10}";

        boolean OKperson = newreg.getPersonnr().matches(regexppersonnr);
        boolean OKnavn = newreg.getNavn().matches(regexpnavn);
        boolean OKadresse = newreg.getAdresse().matches(regexpadresse);
        boolean OKkjennetegn = newreg.getSkiltNr().matches(regexpKjennetegn);
        boolean OKmerke = newreg.getBilmerke().matches(regexpmerke);
        boolean OKtype = newreg.getBiltype().matches(regexptype);

        if (OKperson && OKnavn && OKadresse && OKkjennetegn && OKmerke && OKtype) {
            log.info("OK DEN FUNKER");
            return true;
        } else {
            log.error("valideringen er FUCKED");
            return false;
        }
        public
    }
}