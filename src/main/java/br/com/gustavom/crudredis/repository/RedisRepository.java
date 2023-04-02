package br.com.gustavom.crudredis.repository;

import br.com.gustavom.crudredis.domain.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class RedisRepository {

    private static final String KEY_USUARIO = "usuarios";

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    private HashOperations<String, Integer, Usuario> hashOperations;
    private ObjectMapper mapper;

    @PostConstruct
    private void init(){
        hashOperations = redisTemplate.opsForHash();
        mapper = new ObjectMapper();
    }

    public void save(Usuario value) {
        hashOperations.put(KEY_USUARIO, value.getId(), value);
    }

    public Usuario findByid(int id){
        return mapper.convertValue(hashOperations.get(KEY_USUARIO, id), Usuario.class);
    }

    public List<Usuario> findAll() {
        return hashOperations.values(KEY_USUARIO);
    }

    public void deleteById(int id) {
        hashOperations.delete(KEY_USUARIO, id);
    }

    public boolean updateById(int id, Usuario user){
        Usuario usuarioRedis = this.findByid(id);
        if(Objects.nonNull(usuarioRedis)){
            user.setId(usuarioRedis.getId());
            hashOperations.put(KEY_USUARIO, usuarioRedis.getId(), user);
            return true;
        }
        return false;
    }

}
