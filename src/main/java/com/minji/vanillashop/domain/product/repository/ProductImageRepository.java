package com.minji.vanillashop.domain.product.repository;

import com.minji.vanillashop.domain.product.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
}
