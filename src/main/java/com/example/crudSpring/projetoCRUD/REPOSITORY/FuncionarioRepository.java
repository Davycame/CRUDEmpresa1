package com.example.crudSpring.projetoCRUD.REPOSITORY;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.crudSpring.projetoCRUD.ENTITY.Funcionario;

interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{
    List<Funcionario> findByNomeContainingIgnoreCase(String nome_funcionario);
}
