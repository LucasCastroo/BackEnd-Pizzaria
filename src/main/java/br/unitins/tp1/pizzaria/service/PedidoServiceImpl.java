package br.unitins.tp1.pizzaria.service;

import br.unitins.tp1.pizzaria.dto.PedidoDTO;
import br.unitins.tp1.pizzaria.dto.PedidoResponseDTO;
import br.unitins.tp1.pizzaria.dto.StatusPedidoDTO;
import br.unitins.tp1.pizzaria.model.*;
import br.unitins.tp1.pizzaria.repository.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class PedidoServiceImpl implements PedidoService{
    @Inject
    PedidoRepository repository;
    @Inject
    ClienteRepository clienteRepository;
    @Inject
    CupomRepository cupomRepository;
    @Inject
    EnderecoRepository enderecoRepository;
    @Inject
    PizzaRepository pizzaRepository;
    @Inject
    BebidaRepository bebidaRepository;


    @Override
    @Transactional
    public PedidoResponseDTO create(PedidoDTO dto, Long idCliente) {
        Pedido pedido = new Pedido();
        pedido.setCliente(clienteRepository.findById(idCliente));
        return setupPedido(dto, pedido);
    }

    private PedidoResponseDTO setupPedido(PedidoDTO dto, Pedido pedido) {
        try {
            if (dto.cupom() != null) pedido.setCupom(cupomRepository.findByCodigo(dto.cupom()));
            if (dto.idEndereco() != null) {
                EnderecoPedido ep = new EnderecoPedido();
                Endereco endereco = enderecoRepository.findById(dto.idEndereco());

                if (endereco == null) {
                    throw new RuntimeException("Endereço não encontrado para o ID fornecido: " + dto.idEndereco());
                }

                ep.setBairro(endereco.getBairro());
                ep.setEndereco(endereco);
                ep.setLogradouro(endereco.getLogradouro());
                ep.setCep(endereco.getCep());
                ep.setCidade(endereco.getCidade());
                pedido.setEndereco(ep);
            }
            if (dto.items() != null) {
                pedido.getItems().addAll(
                        dto.items().stream().map(i -> {
                            ItemPedido itemPedido = new ItemPedido();
                            itemPedido.setQuant(i.quantidade());
                            switch (i.tipo()) {
                                case PIZZA -> {
                                    Pizza pizza = pizzaRepository.findById(i.idItem());

                                    if (pizza == null) {
                                        throw new RuntimeException("Pizza não encontrada para o ID fornecido: " + i.idItem());
                                    }

                                    itemPedido.setItem(pizza);
                                    itemPedido.setTamanho(pizza.getTamanhoPizza().name().toLowerCase());
                                }
                                case BEBIDA -> {
                                    Bebida bebida = bebidaRepository.findById(i.idItem());

                                    if (bebida == null) {
                                        throw new RuntimeException("Bebida não encontrada para o ID fornecido: " + i.idItem());
                                    }

                                    itemPedido.setItem(bebida);
                                    itemPedido.setTamanho(bebida.getMl().toString() + "ML");
                                }
                            }
                            itemPedido.setPreco(itemPedido.getItem().getPreco());
                            return itemPedido;
                        }).toList()
                );
            }
            if (dto.formaPagamento() != null) pedido.setFormaPagamento(dto.formaPagamento());
            repository.persistAndFlush(pedido);
            return PedidoResponseDTO.from(pedido);
        } catch (Exception e) {
            throw new RuntimeException("Ocorreu um erro ao realizar o pedido: " + e);
        }
    }

    @Override
    @Transactional
    public PedidoResponseDTO update(PedidoDTO dto, Long id) {
        Pedido pedido = repository.findById(id);
        repository.flush();
        return setupPedido(dto, pedido);
    }

    @Override
    @Transactional
    public PedidoResponseDTO updateStatus(StatusPedidoDTO dto, Long id) {
        Pedido pedido = repository.findById(id);
        StatusPedido sp = new StatusPedido();
        sp.setHorario(LocalDateTime.now());
        sp.setStatus(dto.status());
        pedido.getStatus().add(sp);
        repository.persistAndFlush(pedido);
        return PedidoResponseDTO.from(pedido);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public PedidoResponseDTO findById(Long id) {
        return PedidoResponseDTO.from(repository.findById(id));
    }

    @Override
    public List<PedidoResponseDTO> findByClienteId(Long idCliente) {
        return repository.findByClienteId(idCliente).stream().map(PedidoResponseDTO::from).toList();

    }
}
