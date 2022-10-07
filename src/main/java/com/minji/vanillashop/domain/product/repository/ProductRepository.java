package com.minji.vanillashop.domain.product.repository;

import com.minji.vanillashop.domain.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p, pi, avg(coalesce(r.grade,0)),  count(distinct r) from Product p " +
            "left outer join ProductImage pi on pi.product = p " +
            "left outer join Review  r on r.product = p group by p ")
    Page<Object[]> getListPage(Pageable pageable);


    @Query("select p, pi, avg(coalesce(r.grade,0)), count (r)" +
            " from Product p left outer join ProductImage pi on pi.product = p " +
            " left outer join Review  r on r.product = p " +
            " where p.pno = :pno group by pi")
    List<Object[]> getProductWithAll(Long pno);


}
