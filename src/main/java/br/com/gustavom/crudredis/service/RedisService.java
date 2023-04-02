package br.com.gustavom.crudredis.service;

import br.com.gustavom.crudredis.domain.Usuario;
import br.com.gustavom.crudredis.repository.RedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RedisService {

    @Autowired
    private RedisRepository repository;

    private static Integer id = 1;

    public void gravaUsuario(Usuario usuario){
        usuario.setId(id);
        repository.save(usuario);
        id++;
    }

    public Usuario getUsuario(int id){
        return repository.get(id);
    }

    public List<Usuario> findAll() {
        return repository.findAll();
    }

    public void deleteById(int id) {
        repository.deleteById(id);
    }
}
