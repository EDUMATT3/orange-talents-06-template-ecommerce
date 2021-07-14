package com.zupacademy.eduardo.meli.pagamento;

import com.zupacademy.eduardo.meli.compra.Compra;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class Ranking implements EventoCompraSucesso {
    @Override
    public void processa(Compra compra){
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> request = Map.of("idCompra", compra.getId(), "idVendedor", compra.getIdVendedor());

        restTemplate.postForEntity("http://localhost:8080/meli/ranking", request, String.class);
    }
}
