package br.com.projetotabajara.tabajara.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class ItemDoPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer quantidade;

    private double preco;

    private double subtotal;

    @ManyToOne
    @JoinColumn(name = "idProduto_fk")
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "idPedido_fk")
    private Pedido pedido;

    //método para calcular subtotal;

    public double calcularSubtotal(){
        return quantidade * preco;
    }

    public void atualizarSubtotal(){
        this.subtotal = calcularSubtotal();
    }
}
