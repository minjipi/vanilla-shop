package com.minji.vanillashop.domain.product.service;

import com.minji.vanillashop.domain.product.dto.PageRequestDTO;
import com.minji.vanillashop.domain.product.dto.PageResultDTO;
import com.minji.vanillashop.domain.product.dto.ProductDTO;
import com.minji.vanillashop.domain.product.entity.Product;
import com.minji.vanillashop.domain.product.entity.ProductImage;
import com.minji.vanillashop.domain.product.repository.ProductImageRepository;
import com.minji.vanillashop.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository; //final

    private final ProductImageRepository imageRepository;


    @Transactional
    @Override
    public Long register(ProductDTO productDTO) {

        Map<String, Object> entityMap = dtoToEntity(productDTO);
        Product product = (Product) entityMap.get("product");
        List<ProductImage> productImageList = (List<ProductImage>) entityMap.get("imgList");

        productRepository.save(product);

        productImageList.forEach(productImage -> {
            imageRepository.save(productImage);
        });

        return product.getPno();
    }

    @Transactional
    @Override
    public Long update(ProductDTO productDTO) {

        Optional<Product> result = productRepository.findById(productDTO.getPno());

        if (result.isPresent()){
            Product product = result.get();
            product.changeName(productDTO.getTitle());
            product.changeStock(productDTO.getStockQuantity());
            productRepository.save(product);

        }


        return productDTO.getPno();
    }

    @Override
    public List<Product> findProducts(){
        return productRepository.findAll();
    }

    @Override
    public PageResultDTO<ProductDTO, Object[]> getList(PageRequestDTO requestDTO) {

        Pageable pageable = requestDTO.getPageable(Sort.by("pno").descending());

        Page<Object[]> result = productRepository.getListPage(pageable);

        log.info("==============================================");

        Function<Object[], ProductDTO> fn = (arr -> entitiesToDTO(
                (Product) arr[0],
                (List<ProductImage>) (Arrays.asList((ProductImage) arr[1])),
                (Double) arr[2],
                (Long) arr[3])
        );

        return new PageResultDTO<>(result, fn);
    }

    @Override
    public ProductDTO getProduct(Long pno) {

        List<Object[]> result = productRepository.getProductWithAll(pno);

        Product product = (Product) result.get(0)[0];

        List<ProductImage> productImageList = new ArrayList<>();

        result.forEach(arr -> {
            ProductImage productImage = (ProductImage) arr[1];
            productImageList.add(productImage);
        });

        Double avg = (Double) result.get(0)[2];
        Long reviewCnt = (Long) result.get(0)[3];

        return entitiesToDTO(product, productImageList, avg, reviewCnt);
    }

}


















