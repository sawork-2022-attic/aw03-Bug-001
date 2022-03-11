package com.example.webpos.biz;

import com.example.webpos.db.PosDB;
import com.example.webpos.model.Cart;
import com.example.webpos.model.Item;
import com.example.webpos.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PosServiceImp implements PosService {

    private PosDB posDB;

    @Autowired
    public void setPosDB(PosDB posDB) {
        this.posDB = posDB;
    }

    @Override
    public Cart getCart() {

        Cart cart = posDB.getCart();
        if (cart == null){
            cart = this.newCart();
        }
        return cart;
    }

    @Override
    public Cart newCart() {
        return posDB.saveCart(new Cart());
    }

    @Override
    public double checkout(Cart cart) {
        if (cart == null) return -1;
        double payment = this.total(cart);
        cart.clear();
        return payment;
    }

    @Override
    public double total(Cart cart) {
        if (cart == null) return -1;
        return cart.total();
    }

    @Override
    public boolean add(Product product, int amount) {
        return false;
    }

    @Override
    public boolean add(String productId, int amount) {

        Product product = posDB.getProduct(productId);
        if (product == null) return false;

        return this.getCart().addItem(new Item(product, amount));
    }

    @Override
    public boolean del(int index) {
        return this.getCart().delItem(index);
    }

    @Override
    public List<Product> products() {
        return posDB.getProducts();
    }
}
