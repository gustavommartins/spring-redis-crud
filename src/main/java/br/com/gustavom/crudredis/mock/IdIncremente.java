package br.com.gustavom.crudredis.mock;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class IdIncremente {

    private static Integer id = 0;

    public Integer incrementaId(){
        id++;
        return id;
    }
}
