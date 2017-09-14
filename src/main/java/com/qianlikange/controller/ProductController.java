package com.qianlikange.controller;

import com.qianlikange.ProductDao.ProductRepository;
import com.qianlikange.data.ConfigData;
import com.qianlikange.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;
import java.util.UUID;

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

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(@RequestParam("file") MultipartFile file,@RequestParam("productName") String productName,
                 @RequestParam("age") String age,@RequestParam("price") String price,@RequestParam("level") String level,
                         @RequestParam("place") String place,@RequestParam("code") String code,@RequestParam("description") String description){
        if (!file.isEmpty()) {
            try {
                UUID uuid = UUID.randomUUID();
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(new File(ConfigData.SAVEIMAGEURL+uuid.toString()+file.getOriginalFilename())));
                out.write(file.getBytes());
                out.flush();
                out.close();
                Product product=new Product();
                product.setName(productName);
                product.setAge(age);
                product.setPrice(price);
                product.setCode(code);
                product.setDescription(description);
                product.setLevel(level);
                product.setPlace(place);
                product.setImgUrl(ConfigData.GETIMAGEURL+uuid.toString()+file.getOriginalFilename());
                productRepository.save(product);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return "上传失败," + e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "上传失败," + e.getMessage();
            }
            return "上传成功";
        } else {
            return "上传失败，因为文件是空的.";
        }
    }
}
