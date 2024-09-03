package org.api.java.api_v2_2826502.controllers;

import org.api.java.api_v2_2826502.entities.Product;
import org.api.java.api_v2_2826502.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.naming.Binding;


@RestController
@RequestMapping("/api")
public class ProductController {
    //inyectar el servicio
    @Autowired
    private ProductService servicio;

    //Endpoint
    //listar todos los productos
    @GetMapping("/products")
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    public ResponseEntity<List<Product>> getProducts(){
        return ResponseEntity.ok(servicio.findAll());
    }

    //endpoint
    //listar un producto por ID
    @GetMapping("/products/{id}")
    public ResponseEntity<?> getProduct(@PathVariable Long id){
        //verificar si existe el producto por id
        Optional<Product> optProduct = servicio.findById(id);
        
        if(optProduct.isPresent()){
            //se encontro el producto
            return ResponseEntity.status(HttpStatus.OK).body(servicio.findById(id));
        }else{
            return ResponseEntity.badRequest().body("product whith id"+ id +"product not found");
        }
    }

    //endpoint
    //crar un producto
    @PostMapping("/products")
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    public ResponseEntity<?> crearproduct (@Valid @RequestBody Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            //si existen errores de validacion en los datos del body
            List<String> erroresValidacion = new ArrayList<>();
            for(FieldError error : bindingResult.getFieldErrors()){
                erroresValidacion.add(error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body("errores de validacion");
        }
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body( servicio.save(product));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("error creting product");
        }
    }

    //endpoint para actualizar 
    @PutMapping("/products/{id}")
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    public ResponseEntity<?> actualizarProducto (@RequestBody Product product, @PathVariable Long id){
        Optional<Product> prodAct = servicio.actualizarProduct(product, id);
        if(prodAct.isPresent()){
            //si el producto a actualizar existe
            return ResponseEntity.ok().body(prodAct.get());
        }else{
            return ResponseEntity.badRequest().body("product with  id " + id + " not found");
        }
    
 
    }

    //endpoint para eliminar
    @DeleteMapping("/products/{id}")    
    public ResponseEntity<?> elimProduct(@PathVariable Long id){
        Optional<Product> prodAct = servicio.elimProdcut(id);
        if(prodAct.isPresent()){
            //si el producto a actualizar existe
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.badRequest().body("product with  id " + id + " not found");
        }
    }

}
