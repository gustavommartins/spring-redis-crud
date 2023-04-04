package br.com.gustavom.crudredis.service;

import br.com.gustavom.crudredis.domain.Usuario;
import br.com.gustavom.crudredis.mock.IdIncremente;
import br.com.gustavom.crudredis.repository.RedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RedisService {

    @Autowired
    private RedisRepository repository;

    @Autowired
    private IdIncremente mock;

    public void gravaUsuario(Usuario usuario){
        usuario.setId(mock.incrementaId());
        repository.save(usuario);
    }

    public Usuario getUsuario(int id){
        return repository.findByid(id);
    }

    public List<Usuario> findAll() {
        return repository.findAll();
    }

    public void deleteById(int id) {
        repository.deleteById(id);
    }

    public boolean updateByid(int id, Usuario usuario){
        return repository.updateById(id,usuario);
    }

}
