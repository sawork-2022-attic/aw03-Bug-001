package com.example.webpos.biz;

import com.example.webpos.model.Cart;
import com.example.webpos.model.Item;
import com.example.webpos.model.Product;

import java.util.List;

public interface PosService {
    public Cart getCart();

    public Cart newCart();

    public Cart saveCart(Cart cart);

    public double checkout();

    public double total();

    public boolean add(String productId);

    public boolean inc(int itemIndex);

    public boolean dec(int itemIndex);

    public boolean del(int itemIndex);

    public List<Product> products();
}
