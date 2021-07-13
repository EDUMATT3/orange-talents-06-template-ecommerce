package com.zupacademy.eduardo.meli.pergunta;

public interface Mailer {
    void send(String body, String subject, String nameFrom, String from, String to);
}
