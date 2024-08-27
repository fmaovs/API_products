package org.api.java.api_v2_2826502.services;

import org.api.java.api_v2_2826502.entities.Categoria;
import org.api.java.api_v2_2826502.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoriaService {


    @Autowired
    private CategoriaRepository repositorio;


    public List<Categoria> findAll(){
        return (List<Categoria>)
                repositorio.findAll();
    }

    public Categoria findById(Long id){
        return repositorio.findById(id).get();
    }
}
