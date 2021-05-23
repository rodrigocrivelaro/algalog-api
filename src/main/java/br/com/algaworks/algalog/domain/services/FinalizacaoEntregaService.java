package br.com.algaworks.algalog.domain.services;

import br.com.algaworks.algalog.domain.model.Entrega;
import br.com.algaworks.algalog.domain.repositories.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class FinalizacaoEntregaService {

    private EntregaRepository entregaRepository;
    private BuscarEntregaService buscarEntregaService;

    @Transactional
    public void finalizar(Long id) {
        Entrega entrega = buscarEntregaService.buscar(id);
        entrega.finalizar();
        entregaRepository.save(entrega);
    }
}
