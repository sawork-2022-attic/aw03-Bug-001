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
    public Cart saveCart(Cart cart) {
        return posDB.saveCart(cart);
    }

    @Override
    public double checkout() {
        double payment = this.total();
        Cart cart = this.getCart();
        this.getCart().clear();
        return payment;
    }

    @Override
    public double total() {
        return this.getCart().total();
    }

    @Override
    public boolean add(String productId) {

        Product product = posDB.getProduct(productId);
        if (product == null) return false;

        return this.getCart().addItem(new Item(product, 1));
    }

    @Override
    public boolean inc(int itemIndex) {
        Cart cart = this.getCart();
        boolean ret = cart.itemInc(itemIndex);
        this.saveCart(cart);
        return ret;
    }

    @Override
    public boolean dec(int itemIndex) {
        Cart cart = this.getCart();
        boolean ret = cart.itemDec(itemIndex);
        this.saveCart(cart);
        return ret;
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
