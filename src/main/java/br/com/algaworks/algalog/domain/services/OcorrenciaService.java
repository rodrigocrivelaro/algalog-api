package br.com.algaworks.algalog.domain.services;

import br.com.algaworks.algalog.domain.exceptions.NegocioException;
import br.com.algaworks.algalog.domain.model.Entrega;
import br.com.algaworks.algalog.domain.model.Ocorrencia;
import br.com.algaworks.algalog.domain.repositories.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class OcorrenciaService {

    EntregaRepository entregaRepository;
    BuscarEntregaService buscarEntregaService;

    @Transactional
    public Ocorrencia registrar(Long id, String descricao) {
        Entrega entrega = buscarEntregaService.buscar(id);

        return entrega.adicionarOcorrencia(descricao);
    }
}
