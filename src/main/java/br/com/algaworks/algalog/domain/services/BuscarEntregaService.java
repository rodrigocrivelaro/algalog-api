package br.com.algaworks.algalog.domain.services;

import br.com.algaworks.algalog.domain.exceptions.EntidadeNaoEncontradaException;
import br.com.algaworks.algalog.domain.model.Entrega;
import br.com.algaworks.algalog.domain.repositories.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BuscarEntregaService {

    EntregaRepository entregaRepository;

    public Entrega buscar(Long id) {
        return entregaRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Entrega não encontrada"));
    }
}
