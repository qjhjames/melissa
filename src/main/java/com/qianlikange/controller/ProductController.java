package com.qianlikange.controller;

import com.qianlikange.ProductDao.ProductRepository;
import com.qianlikange.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by qiujunhong on 2017/5/29.
 */
@RestController
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping(value = "/getproducts")
    public List<Product> productList(){
        return  productRepository.findAll();
    }

    @GetMapping(value = "/getproduct")
    public Product productAdd(@RequestParam("name") String name,@RequestParam("age") String age,
                             @RequestParam("imgUrl") String imgUrl,
                              @RequestParam("price") String price){
      Product product=new Product();
      product.setName(name);
      product.setAge(age);
      product.setImgUrl(imgUrl);
      product.setPrice(price);
      return productRepository.save(product);
    }

    @GetMapping(value="/getoneproduct")
    public Product getOneProduct(@RequestParam("id") Integer id){
         return productRepository.findOne(id);
    }
    @GetMapping(value="/deleteproduct")
    public String deleteProduct(@RequestParam("id") Integer id){
                 productRepository.delete(id);
                 return "success";
    }


}
