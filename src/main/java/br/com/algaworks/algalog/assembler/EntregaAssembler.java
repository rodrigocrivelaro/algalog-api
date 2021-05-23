package br.com.algaworks.algalog.assembler;

import br.com.algaworks.algalog.domain.model.Entrega;
import br.com.algaworks.algalog.model.EntregaModel;
import br.com.algaworks.algalog.model.input.EntregaInput;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class EntregaAssembler {

    private ModelMapper modelMapper;

    public EntregaModel toModel(Entrega entrega) {
        return modelMapper.map(entrega, EntregaModel.class);
    }

    public List<EntregaModel> toCollectionModel(List<Entrega> entregas) {
        return entregas.stream().map(this::toModel).collect(Collectors.toList());
    }

    public Entrega toEntrega(EntregaInput entragaInput) {
        return modelMapper.map(entragaInput, Entrega.class);
    }
}
