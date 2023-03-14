package com.pena.API_Producto.Modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor

public class Producto { 
    @Id @GeneratedValue
    private Integer id;
    
    private String name;
    private float price;
}
