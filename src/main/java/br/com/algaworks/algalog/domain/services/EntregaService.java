package br.com.algaworks.algalog.domain.services;

import br.com.algaworks.algalog.domain.model.Cliente;
import br.com.algaworks.algalog.domain.model.Entrega;
import br.com.algaworks.algalog.domain.model.StatusEntrega;
import br.com.algaworks.algalog.domain.repositories.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@AllArgsConstructor
@Service
public class EntregaService {

    private EntregaRepository entregaRepository;
    private ClienteService clienteService;

    @Transactional
    public Entrega solicitar(Entrega entrega) {
        Cliente cliente = clienteService.getCliente(entrega.getCliente().getId());

        entrega.setCliente(cliente);
        entrega.setStatus(StatusEntrega.PENDENTE);
        entrega.setDataPedido(OffsetDateTime.now());

        return entregaRepository.save(entrega);
    }

}
