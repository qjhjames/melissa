package com.qianlikange.controller;

import com.qianlikange.ProductDao.ProductRepository;
import com.qianlikange.data.ConfigData;
import com.qianlikange.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private ProductRepository productRepository;
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(Model model, @RequestParam("file") MultipartFile file, @RequestParam("productName") String productName,
                         @RequestParam("age") String age, @RequestParam("price") String price, @RequestParam("level") String level,
                         @RequestParam("place") String place, @RequestParam("code") String code, @RequestParam("description") String description){
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
