package com.marcoserp.erpmarcos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marcoserp.erpmarcos.model.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
