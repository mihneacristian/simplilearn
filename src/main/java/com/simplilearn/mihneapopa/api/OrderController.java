package com.simplilearn.mihneapopa.api;

import com.simplilearn.mihneapopa.exceptions.OrderNotFoundException;
import com.simplilearn.mihneapopa.exceptions.ProductNotFoundException;
import com.simplilearn.mihneapopa.model.User;
import com.simplilearn.mihneapopa.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/getAll")
    public String getAll(Model model) {
        model.addAttribute("orders",orderService.getAll());
        return "product.html";
    }

    @GetMapping("/getById")
    @SneakyThrows
    public String getById(Integer id, Model model) throws OrderNotFoundException {
        model.addAttribute("orders", orderService.getOrderById(id));
        return "order.html";
    }

    @GetMapping("/admin/getAll")
    public String getAllAsAdmin(Model model) {
        model.addAttribute("orders", orderService.getAll());
        return "admin.html";
    }

    @PostMapping("/add")
    public String placeOrder(@RequestParam Integer productId, @RequestParam Integer userId) throws ProductNotFoundException {
        orderService.saveOrder(productId, userId);
        return "order.html";
    }
}
