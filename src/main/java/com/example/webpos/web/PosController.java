package com.example.webpos.web;

import com.example.webpos.biz.PosService;
import com.example.webpos.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PosController {

    private PosService posService;

    @Autowired
    public void setPosService(PosService posService) {
        this.posService = posService;
    }

    private String home(Model model) {
        model.addAttribute("products", posService.products());
        model.addAttribute("cart", posService.getCart());
        model.addAttribute("tax", 0.12);
        model.addAttribute("discount", 0);
        return "redirect:/";
    }

    @GetMapping("/")
    public String pos(Model model) {
        model.addAttribute("products", posService.products());
        model.addAttribute("cart", posService.getCart());
        model.addAttribute("tax", 0.12);
        model.addAttribute("discount", 0);
        return "index";
    }

    @GetMapping("/add/{id}")
    public String addItem(@PathVariable("id") String id, Model model) {
        posService.add(id);
        return home(model);
    }

    @PostMapping("/plus/{index}")
    public String quantityInc(@PathVariable("index") int index, Model model) {
        posService.inc(index);
        return home(model);
    }

    @PostMapping("/minus/{index}")
    public String quantityDec(@PathVariable("index") int index, Model model) {
        posService.dec(index);
        return home(model);
    }

    @GetMapping("/del/{index}")
    public String delItem(@PathVariable("index") int index, Model model) {
        posService.del(index);
        return home(model);
    }

    @PostMapping("/cancel")
    public String cancel(Model model) {
        posService.newCart();
        return home(model);
    }

    @PostMapping("/charge")
    public String checkout(Model model) {
        posService.checkout();
        return home(model);
    }
}
