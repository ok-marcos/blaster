package com.marcoserp.erpmarcos.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcoserp.erpmarcos.exception.ResourceNotFoundException;
import com.marcoserp.erpmarcos.model.Product;
import com.marcoserp.erpmarcos.repository.ProductRepository;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class ProductController {
	
	@Autowired
	public ProductRepository productRepository;
	
	@GetMapping("/products")
	public List<Product> getAllProducts(){
		return productRepository.findAll();
	}
	
	@GetMapping("/products/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable(value = "id") Long idLong) throws ResourceNotFoundException{	
		Product product = productRepository.findById(idLong)
				.orElseThrow(() -> new ResourceNotFoundException("ID de produto não existe " + idLong));
		return ResponseEntity.ok(product);
	}
	
	@PostMapping("/products")
	public Product createProduct(@RequestBody Product product) {
		return productRepository.save(product);
	}
	
	@PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable(value = "id") Long idLong,
          @RequestBody Product productDetails) throws ResourceNotFoundException {
        Product product = productRepository.findById(idLong)
        .orElseThrow(() -> new ResourceNotFoundException("ID de produto não encontrado :: " + idLong));

        
        product.setName(productDetails.getName());
        product.setPrecoCompra(productDetails.getPrecoCompra());
        product.setPrecoVenda(productDetails.getPrecoVenda());
        product.setDescricao(productDetails.getDescricao());
        
        Product updatedProduct = productRepository.save(product);
        return ResponseEntity.ok(updatedProduct);
    }
	
	@DeleteMapping("/products/{id}")
    public ResponseEntity <Map<String, Boolean>> deleteProduct(@PathVariable Long id)
         throws ResourceNotFoundException {
        Product product = productRepository.findById(id)
       .orElseThrow(() -> new ResourceNotFoundException("Produto não existe :: " + id));

        productRepository.delete(product);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
