package br.com.algaworks.algalog.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ClienteIdInput {

    @NotNull
    private Long id;
}
