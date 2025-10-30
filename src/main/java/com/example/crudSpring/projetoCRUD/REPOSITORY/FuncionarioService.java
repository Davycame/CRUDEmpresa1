package com.example.crudSpring.projetoCRUD.REPOSITORY;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.crudSpring.projetoCRUD.ENTITY.Funcionario;

import jakarta.transaction.Transactional;

@Service
public class FuncionarioService {
    @Autowired
    private FuncionarioRepository ligacaoFuncionarioRepository;
    public List<Funcionario> listarTodosFuncionarios(){
    return ligacaoFuncionarioRepository.findAll();
    }

    public Funcionario cadastrarFuncionario(Funcionario dadosFuncionario) {
        return ligacaoFuncionarioRepository.save(dadosFuncionario);
    }

    public Optional<Funcionario> buscarFuncionarioPorId(Long Id) {
        return ligacaoFuncionarioRepository.findById(Id);
    }

    public void deletarFuncionario(Long Id) {
        ligacaoFuncionarioRepository.deleteById(Id);
    }

    @Transactional
    public void atualizarFuncionario(
        Long id, Funcionario dadosAtualizados){

            Funcionario objtFuncionario = buscarFuncionarioPorId(id).orElseThrow(() -> new IllegalArgumentException("Funcionario não encontrado"));
            objtFuncionario.setNome(dadosAtualizados.getNome());
            objtFuncionario.setSalario(dadosAtualizados.getSalario());
            objtFuncionario.setCargo(dadosAtualizados.getCargo());
            objtFuncionario.setIdentificadoEmpresa(dadosAtualizados.getIdentificadoEmpresa());
    }
}
