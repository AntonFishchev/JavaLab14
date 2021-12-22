package com.vyatsu.task14.controllers;

import com.vyatsu.task14.entities.Product;
import com.vyatsu.task14.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductsController {
    private ProductsService productsService;

    @Autowired
    public void setProductsService(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping
    public String showProductsList(@RequestParam(value = "Min",required = false) Integer min,
                                   @RequestParam(value = "Max",required = false) Integer max,
                                   @RequestParam(value = "Substring",required = false) String subsrting,
                                   Model model) {
        Product product = new Product();
        model.addAttribute("products", productsService.getAllProducts(min, max, subsrting));
        model.addAttribute("product", product);
        model.addAttribute("Min", min);
        model.addAttribute("Max", max);
        model.addAttribute("Substring", subsrting);

        String minStr = min == null ? "" : min.toString();
        String maxStr = max == null ? "" : min.toString();

        currentString = "?Min=" + minStr + "&Max=" + maxStr + "&Substring=" + subsrting;
        return "products";
    }

    String currentString = "?Min=&Max=&Substring=";

    @GetMapping("/add")
    public String addProduct(@ModelAttribute(value = "product")Product product) {
        productsService.add(product);
        return "redirect:/products" + currentString;
    }

    @GetMapping("/show/{id}")
    public String showOneProduct(Model model, @PathVariable(value = "id") Long id) {
        Product product = productsService.getById(id);
        model.addAttribute("product", product);
        return "product-page";
    }

    @GetMapping("/delete")
    public String deleteProduct(@RequestParam(value = "id")Long id) {
        productsService.deleteById(id);
        return "redirect:/products" + currentString;
    }

    @GetMapping("/edit")
    public String editProduct(@ModelAttribute(value = "product")Product product) {
        productsService.editProduct(product);
        return "redirect:/products" + currentString;
    }
}
