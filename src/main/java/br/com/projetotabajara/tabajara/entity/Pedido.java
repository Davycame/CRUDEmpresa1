package br.com.projetotabajara.tabajara.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private LocalDate dataPedido;

    private double totalPedido;

    @ManyToOne
    @JoinColumn(name = "idUsuario_fk")
    private Usuario usuario;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemDoPedido> itens;

    // Método para calcular o total
    public double calcularTotal() {
        double total = 0.0;

        if (itens != null) {
            for (ItemDoPedido item : itens) {
                total += item.getSubtotal();
            }
        }

        return total;
    }

    // atualizar o totalPedido automaticamente
    public void atualizarTotal() {
        this.totalPedido = calcularTotal();
    }
}