package br.com.algaworks.algalog.domain.services;

import br.com.algaworks.algalog.domain.exceptions.NegocioException;
import br.com.algaworks.algalog.domain.model.Cliente;
import br.com.algaworks.algalog.domain.repositories.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class ClienteService {

    private ClienteRepository clienteRepository;

    public Cliente getCliente(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new NegocioException("Cliente não encontrado"));
    }

    @Transactional
    public Cliente salvar(Cliente cliente) {
        Boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail()).stream().anyMatch(clienteEncontrado -> !clienteEncontrado.equals(cliente));

        if (emailEmUso) {
            throw new NegocioException("Já existe um cliente cadastrado com esse e-mail");
        }
        return clienteRepository.save(cliente);
    }

    @Transactional
    public void excluir(Long id) {
        clienteRepository.deleteById(id);
    }
}
