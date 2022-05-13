package com.example.motorvognregister.repository;

import com.example.motorvognregister.model.Account;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@Repository
public class innloggrepository {
    private Logger logger= (Logger) LoggerFactory.getLogger(Account.class);

public void logginn(Account user, HttpServletResponse) throws IOException{

}

    public String krypter(Account user){
        return BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10));
    }
}
