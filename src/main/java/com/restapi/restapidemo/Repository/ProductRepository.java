package com.restapi.restapidemo.Repository;

import com.restapi.restapidemo.Entity.Category;
import com.restapi.restapidemo.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findByName(String name);
    @Query("select p from Product p join p.category c where p.name like ?1 or c.name like ?1")
    List<Product> search(String name);
}