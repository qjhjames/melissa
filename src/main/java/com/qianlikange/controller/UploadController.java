package com.qianlikange.controller;

import com.qianlikange.ProductDao.ProductRepository;
import com.qianlikange.data.ConfigData;
import com.qianlikange.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

/**
 * Created by qiujunhong on 2017/9/22.
 */
@Controller
public class UploadController {
    @Value("${SAVEIMAGEURL}")
    private  String SAVEIMAGEURL;

    @Value("${GETIMAGEURL}")
    private  String GETIMAGEURL;
    private String userPageSize;


    @Autowired
    private ProductRepository productRepository;
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(Model model, @RequestParam("file") MultipartFile file,@RequestParam("file2") MultipartFile file2, @RequestParam("productName") String productName,
                         @RequestParam("age") String age, @RequestParam("price") String price, @RequestParam("level") String level,
                         @RequestParam("place") String place, @RequestParam("code") String code, @RequestParam("description") String description){
        if (!file.isEmpty()||!file2.isEmpty()) {
            try {
                UUID uuid = UUID.randomUUID();
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(new File(SAVEIMAGEURL+uuid.toString()+file.getOriginalFilename())));
                out.write(file.getBytes());
                out.flush();
                out.close();
                UUID uuid2 = UUID.randomUUID();
                BufferedOutputStream out2 = new BufferedOutputStream(
                        new FileOutputStream(new File(SAVEIMAGEURL+uuid2.toString()+file2.getOriginalFilename())));
                out2.write(file.getBytes());
                out2.flush();
                out2.close();
                Product product=new Product();
                product.setName(productName);
                product.setAge(age);
                product.setPrice(price);
                product.setCode(code);
                product.setDescription(description);
                product.setLevel(level);
                product.setPlace(place);
                product.setImgUrl(GETIMAGEURL+uuid.toString()+file.getOriginalFilename());
                product.setImgUrl2(GETIMAGEURL+uuid2.toString()+file2.getOriginalFilename());
                productRepository.save(product);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return "redirect:myerror";
            } catch (IOException e) {
                e.printStackTrace();
                return "redirect:myerror";
            }
            return "redirect:mysuccess";
        } else {
            return "redirect:myerror";
        }
    }
}
