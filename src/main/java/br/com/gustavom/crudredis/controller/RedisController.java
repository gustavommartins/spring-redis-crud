package br.com.gustavom.crudredis.controller;

import br.com.gustavom.crudredis.domain.Usuario;
import br.com.gustavom.crudredis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class RedisController {

    @Autowired
    private RedisService service;

    @PostMapping("")
    public ResponseEntity<String> gravaUsuario(@RequestBody Usuario usuario){
        service.gravaUsuario(usuario);
        return ResponseEntity.ok().body("Gravado com sucesso!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscaUsuario(@PathVariable int id){
        return ResponseEntity.ok().body(service.getUsuario(id));
    }

    @GetMapping("")
    public ResponseEntity<List<Usuario>> buscaTodosOsUsuarios(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletaUsuario(@PathVariable int id){
        service.deleteById(id);
        return ResponseEntity.ok().body("Usuario deletado com sucesso");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> alteraUsuario(@PathVariable int id, @RequestBody Usuario usuario){
        if(service.updateByid(id,usuario)){
            return ResponseEntity.ok().body("Usuario Alterado!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado!");
        }
    }

}
