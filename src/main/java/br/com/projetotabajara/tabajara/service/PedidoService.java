package br.com.projetotabajara.tabajara.service;

import br.com.projetotabajara.tabajara.repository.ItemDoPedidoRepository;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.projetotabajara.tabajara.entity.Pedido;
import br.com.projetotabajara.tabajara.entity.Produto;
import br.com.projetotabajara.tabajara.entity.ItemDoPedido;
import br.com.projetotabajara.tabajara.repository.PedidoRepository;
import br.com.projetotabajara.tabajara.repository.ProdutoRepository;

@Service
public class PedidoService {

    private final ItemDoPedidoRepository itemDoPedidoRepository;

    @Autowired
    private PedidoRepository repositoryPedido;

    @Autowired
    private ProdutoRepository repositoryProduto;

    @Autowired
    public PedidoService(ItemDoPedidoRepository itemDoPedidoRepository) {
        this.itemDoPedidoRepository = itemDoPedidoRepository;
    }

    // método para salvar um pedido
    public Pedido salvarPedido(Pedido pedido){

        if (pedido.getUsuario() == null || pedido.getUsuario().getIdUsuario() == null) {
            throw new RuntimeException("Usuário não informado!");
        }

        if (pedido.getItens() == null || pedido.getItens().isEmpty()) {
            throw new RuntimeException("Pedido sem itens!");
        }

        pedido.setDataPedido(LocalDate.now());

        for (ItemDoPedido item : pedido.getItens()) {

            Produto produto = repositoryProduto
                    .findById(item.getProduto().getIdProduto())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));

            item.setProduto(produto);
            item.setPreco(produto.getValorProduto());
            item.atualizarSubtotal();
            item.setPedido(pedido);
        }

        pedido.atualizarTotal();
        return repositoryPedido.save(pedido);
    }

    public List<Pedido> findAll(){
        return repositoryPedido.findAll();
    }
}
