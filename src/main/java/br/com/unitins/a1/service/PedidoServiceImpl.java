package br.com.unitins.a1.service;

import br.com.unitins.a1.dto.PedidoDTO;
import br.com.unitins.a1.dto.PedidoResponseDTO;
import br.com.unitins.a1.dto.StatusPedidoDTO;
import br.com.unitins.a1.model.*;
import br.com.unitins.a1.repository.*;
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
        pedido.setCupom(cupomRepository.findByCodigo(dto.cupom()));
        EnderecoPedido ep = new EnderecoPedido();
        Endereco endereco = enderecoRepository.findById(dto.idEndereco());
        ep.setBairro(endereco.getBairro());
        ep.setEndereco(endereco);
        ep.setLogradouro(endereco.getLogradouro());
        ep.setCep(endereco.getCep());
        ep.setCidade(endereco.getCidade());
        pedido.setEndereco(ep);
        pedido.getItems().addAll(
                dto.items().stream().map(i ->{
                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuant(i.quantidade());
                    switch (i.tipo()){
                        case PIZZA -> {
                            Pizza pizza = pizzaRepository.findById(i.idItem());
                            itemPedido.setItem(pizza);
                            itemPedido.setTamanho(pizza.getTamanhoPizza().name().toLowerCase());
                        }
                        case BEBIDA -> {
                            Bebida bebida = bebidaRepository.findById(i.idItem());
                            itemPedido.setItem(bebida);
                            itemPedido.setTamanho(bebida.getMl().toString() + "ML");
                        }
                    }
                    itemPedido.setPreco(itemPedido.getItem().getPreco());
                    return itemPedido;
                }).toList()
        );
        repository.persist(pedido);
        return PedidoResponseDTO.from(pedido);
    }

    @Override
    @Transactional
    public PedidoResponseDTO update(PedidoDTO dto, Long id) {
        Pedido pedido = repository.findById(id);
        pedido.setCupom(cupomRepository.findByCodigo(dto.cupom()));
        EnderecoPedido ep = new EnderecoPedido();
        Endereco endereco = enderecoRepository.findById(dto.idEndereco());
        ep.setBairro(endereco.getBairro());
        ep.setEndereco(endereco);
        ep.setLogradouro(endereco.getLogradouro());
        ep.setCep(endereco.getCep());
        ep.setCidade(endereco.getCidade());
        pedido.setEndereco(ep);
        pedido.getItems().addAll(
                dto.items().stream().map(i ->{
                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuant(i.quantidade());
                    switch (i.tipo()){
                        case PIZZA -> {
                            Pizza pizza = pizzaRepository.findById(i.idItem());
                            itemPedido.setItem(pizza);
                            itemPedido.setTamanho(pizza.getTamanhoPizza().name().toLowerCase());
                        }
                        case BEBIDA -> {
                            Bebida bebida = bebidaRepository.findById(i.idItem());
                            itemPedido.setItem(bebida);
                            itemPedido.setTamanho(bebida.getMl().toString() + "ML");
                        }
                    }
                    itemPedido.setPreco(itemPedido.getItem().getPreco());
                    return itemPedido;
                }).toList()
        );
        repository.persist(pedido);
        return PedidoResponseDTO.from(pedido);
    }

    @Override
    @Transactional
    public PedidoResponseDTO updateStatus(StatusPedidoDTO dto, Long id) {
        Pedido pedido = repository.findById(id);
        StatusPedido sp = new StatusPedido();
        sp.setHorario(LocalDateTime.now());
        sp.setStatus(dto.status());
        pedido.getStatus().add(sp);
        repository.persist(pedido);
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