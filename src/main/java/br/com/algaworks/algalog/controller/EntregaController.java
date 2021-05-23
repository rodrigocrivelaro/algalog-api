package br.com.algaworks.algalog.controller;

import br.com.algaworks.algalog.assembler.EntregaAssembler;
import br.com.algaworks.algalog.domain.model.Entrega;
import br.com.algaworks.algalog.domain.repositories.EntregaRepository;
import br.com.algaworks.algalog.domain.services.EntregaService;
import br.com.algaworks.algalog.domain.services.FinalizacaoEntregaService;
import br.com.algaworks.algalog.model.EntregaModel;
import br.com.algaworks.algalog.model.input.EntregaInput;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {

    private EntregaRepository entregaRepository;
    private EntregaService entregaService;
    private EntregaAssembler entregaAssembler;
    private FinalizacaoEntregaService finalizacaoEntregaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntregaModel solicitar(@Valid @RequestBody EntregaInput entregaInput) {
        Entrega novaEntrega = entregaAssembler.toEntrega(entregaInput);
        Entrega ent = entregaService.solicitar(novaEntrega);
        return entregaAssembler.toModel(ent);
    }

    @PutMapping("/{id}/finalizacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void finalizar(@PathVariable Long id) {
        finalizacaoEntregaService.finalizar(id);
    }

    @GetMapping
    public List<EntregaModel> listar() {
        List<Entrega> entregas = entregaRepository.findAll();
        return entregaAssembler.toCollectionModel(entregas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntregaModel> buscarEntregaPorID(@PathVariable Long id) {
        return entregaRepository.findById(id)
                .map(entrega -> ResponseEntity.ok(entregaAssembler.toModel(entrega)))
                .orElse(ResponseEntity.notFound().build());
    }
}
