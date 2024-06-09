package vn.hoidanit.laptopshop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.ProductDetail;

@Controller
public class ProductController {

  @GetMapping("/admin/product")
  public String getProductPage() {
    return "/admin/product/show";
  }

  @GetMapping("/admin/product/create")
  public String getCreateProductPage(Model model) {
    model.addAttribute("newProduct", new Product());
    return "/admin/product/create";
  }

  @PostMapping("/admin/product/create")
  public String postMethodName(@ModelAttribute("newProduct") Product newProduct) {
    System.out.println(newProduct);
    return "/admin/product";
  }

}
