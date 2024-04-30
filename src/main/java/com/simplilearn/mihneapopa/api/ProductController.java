package com.simplilearn.mihneapopa.api;

import com.simplilearn.mihneapopa.exceptions.ProductNotFoundException;
import com.simplilearn.mihneapopa.service.OrderService;
import com.simplilearn.mihneapopa.service.ProductService;
import com.simplilearn.mihneapopa.service.UserService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @GetMapping("/getAll")
    public String getAll(Model model) {
        model.addAttribute("products",productService.getAll());
        return "product.html";
    }

    @GetMapping("/getById")
    @SneakyThrows
    public String getById(Integer id, Model model) throws ProductNotFoundException {
        model.addAttribute("product", productService.getProductById(id));
        return "product.html";
    }

    @GetMapping("/admin/getAll")
    public String getAllAsAdmin(Model model) {
        model.addAttribute("products",productService.getAll());
        model.addAttribute("orders", orderService.getAll());
        model.addAttribute("users", userService.getAll());
        return "admin.html";
    }

}
