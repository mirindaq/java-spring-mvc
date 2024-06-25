package vn.hoidanit.laptopshop.controller.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import vn.hoidanit.laptopshop.domain.Order;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.service.ProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class OrderController {

    private final ProductService productService;

    public OrderController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/admin/order")
    public String getDashboard(Model model) {
        List<Order> od = this.productService.fetchOrders();
        model.addAttribute("orders", od);
        return "admin/order/show";
    }

    @GetMapping("/admin/order/{id}")
    public String getDetailPage(@PathVariable Long id, Model model) {
        Order order = this.productService.findOrderById(id);
        model.addAttribute("orderDetails", order.getOrderDetails());
        model.addAttribute("id", id);
        return "admin/order/detail";
    }

    @GetMapping("/admin/order/update/{id}")
    public String getUpdatePage(@PathVariable Long id, Model model) {
        Order order = this.productService.findOrderById(id);
        model.addAttribute("newOrder", order);
        model.addAttribute("id", id);
        return "admin/order/update";
    }

    @PostMapping("/admin/order/update")
    public String updateOrder(@ModelAttribute("newOrder") Order newOrder) {
        Order order = this.productService.findOrderById(newOrder.getId());
        order.setStatus(newOrder.getStatus());
        this.productService.createOrder(order);
        return "redirect:/admin/order";
    }

    @GetMapping("/admin/order/delete/{id}")
    public String getDeletePage(@PathVariable Long id, Model model) {
        Order order = this.productService.findOrderById(id);
        model.addAttribute("newOrder", order);
        model.addAttribute("id", id);
        return "admin/order/delete";
    }

    @PostMapping("/admin/order/delete")
    public String deleteOrder(@ModelAttribute("newOrder") Order newOrder) {
        this.productService.handleDeleteOrder(newOrder.getId());
        return "redirect:/admin/order";
    }

}
