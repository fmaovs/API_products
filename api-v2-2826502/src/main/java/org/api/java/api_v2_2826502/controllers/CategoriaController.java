package org.api.java.api_v2_2826502.controllers;


import org.api.java.api_v2_2826502.entities.Categoria;
import org.api.java.api_v2_2826502.entities.Product;
import org.api.java.api_v2_2826502.services.CategoriaService;
import org.api.java.api_v2_2826502.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService servicio;


    @GetMapping
    public List<Categoria> getCategorias(){
        return servicio.findAll();
    }


    @GetMapping("/{id}")
    public Categoria getCategoria(@PathVariable Long id){
        return servicio.findById(id);
    }
}
