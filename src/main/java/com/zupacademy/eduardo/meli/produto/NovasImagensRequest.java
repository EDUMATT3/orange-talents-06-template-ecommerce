package com.zupacademy.eduardo.meli.produto;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class NovasImagensRequest {

    @Size(min = 1)
    @NotNull
    private List<MultipartFile> imagens;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public NovasImagensRequest(List<MultipartFile> imagens) {
        this.imagens = imagens;
    }

    public List<MultipartFile> getImagens() {
        return this.imagens;
    }
}
