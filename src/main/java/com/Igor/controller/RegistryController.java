package com.Igor.controller;

import com.Igor.dto.UserDTO;
import com.Igor.exception.ResourceNotFoundException;
import com.Igor.model.Registry;
import com.Igor.repository.RegistryRepository;

import com.Igor.services.Imple.DataProcessingService;
import com.Igor.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.persistence.*;
import java.util.List;

@RestController
@RequestMapping("/api/regController/")
public class RegistryController {
    @PersistenceContext
    private EntityManager em;
    @Autowired
    TestService one;

    @Autowired
    private RegistryRepository registryRepository;

    private final DataProcessingService dataProcessingService;

    public RegistryController(DataProcessingService one) {
        this.dataProcessingService = one;
    }

//    @GetMapping("products")
//    public List<Registry> getAllProducts(){
//        QRegistry qRegistry = QRegistry.registry;
//        JPAQuery<Registry> query = new JPAQuery<>(em);
//        return query.select(qRegistry).from(qRegistry).fetch();
//    }

    @PostMapping("getProductsByProductTypeCountryCompanyOnlineInstallment")
    public List<Registry> getSpecifiedProducts(@RequestBody UserDTO userData){
        return dataProcessingService.searchByParametrs(userData);
    }

    @PostMapping("fillterProducts")
    public List<Registry> getFilteredProducts(){


        return null;
    }

    // ---------------------Not needed---------------------

    @Query
    @GetMapping("products/{id}")
    public ResponseEntity<Registry> getProductsById(@PathVariable(value = "id") Long productId)
                throws ResourceNotFoundException {
            Registry registryEntry = registryRepository.findById(productId).
                    orElseThrow(() -> new ResourceNotFoundException("Product with such id not found." + productId));
            return ResponseEntity.ok().body(registryEntry);
    }

    @PostMapping("product")
    public Registry createProduct(@RequestBody Registry product){
        return this.registryRepository.save(product);
    }


}
