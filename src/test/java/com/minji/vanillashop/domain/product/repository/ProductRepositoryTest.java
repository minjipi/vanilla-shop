package com.minji.vanillashop.domain.product.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;


    @Test
    public void testGetMovieWithAll() {

        List<Object[]> result = productRepository.getProductWithAll(1L);

        System.out.println(result);

        for (Object[] arr : result) {
            System.out.println(Arrays.toString(arr));
        }
    }

}