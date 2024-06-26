package vn.hoidanit.laptopshop.controller.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrderController {

    private final ProductService productService;

    public OrderController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/admin/order")
    public String getDashboard(Model model, @RequestParam("page") Optional<String> pageOptional) {
        int page = 1;
        try {
            if (pageOptional.isPresent()) {
                // convert from String to int
                page = Integer.parseInt(pageOptional.get());
            } else {
                // page = 1
            }
        } catch (Exception e) {
            // page = 1
            // TODO: handle exception
        }
        Pageable pageable = PageRequest.of(page - 1, 3);
        Page<Order> od = this.productService.fetchOrders(pageable);
        List<Order> lOrders = od.getContent();
        model.addAttribute("orders", lOrders);
        model.addAttribute("totalPages", od.getTotalPages());
        model.addAttribute("currentPage", page);
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
