package com.pena.API_Producto.Service;

import com.pena.API_Producto.Modelo.Producto;
import com.pena.API_Producto.Repository.productoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class productoService {

    @Autowired

    private productoRepository repository;

    public List<Producto> listProducts() {
        return repository.findAll();
    }

    public void saveProduct(Producto producto) {
        repository.save(producto);
    }

    public Producto obtainProductoById(Integer id) {
        return repository.findById(id).get();
    }

    public void deleteProduct(Integer id) {
        repository.deleteById(id);
    }
}
