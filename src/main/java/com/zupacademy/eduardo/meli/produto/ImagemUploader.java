package com.zupacademy.eduardo.meli.produto;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

public interface ImagemUploader {
    public Set<String> envia(List<MultipartFile> imagens);
}
