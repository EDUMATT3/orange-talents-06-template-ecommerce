package com.zupacademy.eduardo.meli.outrossistemas;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class OutrosSistemasController {
    @PostMapping("notas-fiscais")
    public void criaNota(@RequestBody @Valid NotaFiscalRequest request) throws InterruptedException {
        System.out.println("Criando nota para "+request.getIdCompra()+" do comprador "+request.getIdComprador());
        Thread.sleep(150);
    }

    @PostMapping("ranking")
    public void ranking(@RequestBody @Valid RankingRequest request) throws InterruptedException {
        System.out.println("Criando ranking para "+request.getIdCompra()+" do comprador "+request.getIdVendedor());
        Thread.sleep(150);
    }
}
