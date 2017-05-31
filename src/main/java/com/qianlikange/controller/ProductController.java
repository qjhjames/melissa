package com.qianlikange.controller;

import com.qianlikange.ProductDao.ProductRepository;
import com.qianlikange.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by qiujunhong on 2017/5/29.
 */
@RestController
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping(value = "/products")
    public List<Product> productList(){
        return  productRepository.findAll();
    }

    @PostMapping(value = "/product")
    public Product productAdd(@RequestParam("name") String name,@RequestParam("age") Integer age,
                             @RequestParam("imgUrl") String imgUrl,
                              @RequestParam("price") Double price){
      Product product=new Product();
      product.setName(name);
      product.setAge(age);
      product.setImgUrl(imgUrl);
      product.setPrice(price);
      return productRepository.save(product);
    }
}
