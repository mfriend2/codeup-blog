package com.codeup.springblog.repositories;

import com.codeup.springblog.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product getProductByName(String name);
    @Query("from Product product where product.name like %:name%")
    List<Product> searchByNameLike(@Param("name") String name);
}
