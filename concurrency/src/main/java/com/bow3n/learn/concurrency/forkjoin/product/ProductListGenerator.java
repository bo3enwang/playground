package com.bow3n.learn.concurrency.forkjoin.product;

import java.util.ArrayList;
import java.util.List;

public class ProductListGenerator {

    List<Product> ret = new ArrayList<Product>();

    public List<Product> generate(int size) {
        for (int i = 0; i < size; i++) {
            Product product = new Product();
            product.setName("Product" + i);
            product.setPrice(10);
            ret.add(product);
        }
        return ret;
    }
}