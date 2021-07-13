package com.zupacademy.eduardo.meli.produto;

import com.zupacademy.eduardo.meli.opniao.Opiniao;

import java.util.OptionalDouble;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Opinioes {
    private Set<Opiniao> opinioes;

    public Opinioes(Set<Opiniao> opinioes) {
        this.opinioes = opinioes;
    }

    public <T> Set<T> mapeiaOpinioes(Function<Opiniao, T> funcao){
        return this.opinioes.stream().map(funcao).collect(Collectors.toSet());
    }

    public double getMedia(){
        Set<Integer> notas = mapeiaOpinioes(Opiniao::getNota);
        OptionalDouble possivelMedia = notas.stream().mapToInt(n->n).average();

        return  possivelMedia.orElse(0.0);
    }

    public int getTotal(){
        return this.opinioes.size();
    }
}
