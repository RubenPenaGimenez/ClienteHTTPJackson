package com.pena.API_Producto.Controller;

import com.pena.API_Producto.Modelo.Producto;
import com.pena.API_Producto.Service.productoService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

@RestController
@RequiredArgsConstructor
class productoRestController {

    private final productoService productoService;

    @GetMapping("/producto")
    public List<Producto> ListProducts() {
        return productoService.listProducts();
    }
    
    @GetMapping("/producto/{id}")
    public ResponseEntity<Producto> obtainProduct(@PathVariable Integer id) {
        try{
            Producto producto = productoService.obtainProductoById(id);
            return ResponseEntity.ok(producto);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("/producto")
    public void newProduct (@RequestBody Producto producto) {
        productoService.saveProduct(producto);
    }
    
    @PutMapping("/producto/{id}")
    public ResponseEntity<?> editProduct(@RequestBody Producto producto, @PathVariable Integer id) {
        try{
            Producto productoExistente = productoService.obtainProductoById(id);
            productoExistente.setName(producto.getName());
            productoExistente.setPrice(producto.getPrice());
            productoService.saveProduct(productoExistente);
            return new ResponseEntity<Producto>(producto, HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/producto/{id}")
    public void deleteProduct(@PathVariable Integer id) {
        productoService.deleteProduct(id);
    }
    
    private static final String API_URL = "http://localhost:8080/producto";

    public static void main(String[] args) {
        List<User> users = new ArrayList<>();

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(API_URL);

            try (CloseableHttpResponse response = httpClient.execute(request)) {
                HttpEntity entity = response.getEntity();

                if (entity != null) {
                    try (InputStream inputStream = entity.getContent()) {
                        ObjectMapper objectMapper = new ObjectMapper();
                        users = objectMapper.readValue(inputStream, objectMapper.getTypeFactory().constructCollectionType(List.class, User.class));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(users);
    }
}


