package com.zupacademy.eduardo.meli.cliente;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("usuarios")
public class NovoUsuarioController {

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid NovoUsuarioRequest request){
        return ResponseEntity.ok("okkkkk");
    }

}
