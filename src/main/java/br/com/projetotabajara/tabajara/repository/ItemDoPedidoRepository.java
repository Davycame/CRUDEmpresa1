package br.com.projetotabajara.tabajara.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projetotabajara.tabajara.entity.ItemDoPedido;

public interface ItemDoPedidoRepository extends JpaRepository<ItemDoPedido, Integer> {
    
}
