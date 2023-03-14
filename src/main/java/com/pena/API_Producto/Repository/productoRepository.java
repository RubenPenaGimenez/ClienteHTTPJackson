package com.pena.API_Producto.Repository;

import com.pena.API_Producto.Modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface productoRepository  extends JpaRepository<Producto, Integer>{
    
}
