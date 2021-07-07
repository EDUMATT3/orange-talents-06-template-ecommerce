package com.zupacademy.eduardo.meli.cliente;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("clientes")
public class NovoClienteController {

    @PostMapping
    public ResponseEntity<?> cadastrar(){
        return ResponseEntity.ok("ok");
    }

}
