package org.api.java.api_v2_2826502.services;

import org.api.java.api_v2_2826502.entities.Product;
import org.api.java.api_v2_2826502.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    /*dependecia inyectada
    * bjeto necesario para cumplir}operaciones en la clase*/
    @Autowired
    private ProductRepository repositorio;

    //CRUD
    /*seleccionar todos los producctos*/
    public List<Product> findAll(){

        //seleccionar todos los productos
        return (List<Product>)
                repositorio.findAll();

    }

    //seleccionar producctos por id
    //SQL select from where id =
    public Optional<Product> findById(Long id){
        return repositorio.findById(id);
    }

    //insertar nuevo producto

    @Transactional
    public Product save (Product product){

        //save es el metodo que guarda un nuevo producto en bd
        return repositorio.save(product);

    }


    @Transient
    public Optional<Product> actualizarProduct(Product product, long id){

        //1. obtener el producto por id
        //verificar que si el prodcuto existe
        Optional<Product> prodUP = this.findById(id);
        if(prodUP.isPresent()){
            /**si existe el producto obtener en una variable prodcuto
             * optional 
             */
            Product actualizacion = prodUP.get();
            //3. actualizar el producto obtenido
            actualizacion.setName(product.getName());
            actualizacion.setDescription(product.getDescription());
            actualizacion.setPrice(product.getPrice());
            //4. grabar cambios
            repositorio.save(actualizacion);
            //5. convertir el producto  optionl
            return Optional.of(actualizacion);
        }else{
            return prodUP;
        }
        //guardar los cambio en bd
        
    }
 
    @Transient
    public Optional<Product> elimProdcut(Long id){

        //1. obtener el producto por id
        Optional<Product> elimProduct = this.findById(id);
        if (elimProduct.isPresent()) {
            // Eliminar el producto utilizando el repositorio
            this.repositorio.deleteById(id);
             // Indica que el producto fue eliminado exitosamente
        } else {
            return elimProduct; // Indica que el producto no fue encontrado
        }
        return elimProduct;
        
        
    }
}
