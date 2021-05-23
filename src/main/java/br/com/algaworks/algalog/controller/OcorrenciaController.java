package br.com.algaworks.algalog.controller;

import br.com.algaworks.algalog.assembler.OcorrenciaAssembler;
import br.com.algaworks.algalog.domain.model.Entrega;
import br.com.algaworks.algalog.domain.model.Ocorrencia;
import br.com.algaworks.algalog.domain.services.BuscarEntregaService;
import br.com.algaworks.algalog.domain.services.OcorrenciaService;
import br.com.algaworks.algalog.model.OcorrenciaModel;
import br.com.algaworks.algalog.model.input.OcorrenciaInput;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas/{id}/ocorrencias")
public class OcorrenciaController {

    private OcorrenciaService ocorrenciaService;
    private OcorrenciaAssembler ocorrenciaAssembler;
    private BuscarEntregaService buscarEntregaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OcorrenciaModel registrar(@PathVariable Long id, @Valid @RequestBody OcorrenciaInput ocorrenciaInput) {
        Ocorrencia novaOcorrencia = ocorrenciaService.registrar(id, ocorrenciaInput.getDescricao());
        return ocorrenciaAssembler.toModel(novaOcorrencia);
    }

    @GetMapping
    public List<OcorrenciaModel> listar(@PathVariable Long id) {
        Entrega entrega = buscarEntregaService.buscar(id);
        return ocorrenciaAssembler.toCollectionModel(entrega.getOcorrencias());
    }
}
