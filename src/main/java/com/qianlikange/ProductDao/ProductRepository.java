package com.qianlikange.ProductDao;

import com.qianlikange.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by qiujunhong on 2017/5/29.
 */
public interface ProductRepository extends JpaRepository<Product,Integer> {
}
