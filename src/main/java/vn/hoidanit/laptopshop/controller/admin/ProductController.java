package vn.hoidanit.laptopshop.controller.admin;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.ProductDetail;
import vn.hoidanit.laptopshop.services.ProductService;
import vn.hoidanit.laptopshop.services.UploadFileService;
import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ProductController {
  private final UploadFileService uploadFileService;
  private final ProductService productService;

  public ProductController(UploadFileService uploadFileService, ProductService productService) {
    this.uploadFileService = uploadFileService;
    this.productService = productService;
  }

  @GetMapping("/admin/product")
  public String getProductPage(Model model) {
    List<Product> aProducts = this.productService.findAll();
    model.addAttribute("listProduct", aProducts);
    return "/admin/product/show";
  }

  @GetMapping("/admin/product/create")
  public String getCreateProductPage(Model model) {
    model.addAttribute("newProduct", new Product());
    return "/admin/product/create";
  }

  @PostMapping("/admin/product/create")
  public String postMethodName(@ModelAttribute("newProduct") @Valid Product newProduct,
      BindingResult newProductBindingResult,
      @RequestParam("productFile") MultipartFile file) {

    // validate
    if (newProductBindingResult.hasErrors()) {
      return "/admin/product/create";
    }

    newProduct.setImage(this.uploadFileService.handleUploadFile(file, "product"));
    ProductDetail productDetail = new ProductDetail();
    productDetail.setDetailDesc(newProduct.getProductDetail().getDetailDesc());
    productDetail.setShortDesc(newProduct.getProductDetail().getShortDesc());
    newProduct.setProductDetail(productDetail);
    this.productService.handleSaveProduct(newProduct);
    return "redirect:/admin/product";
  }

  @GetMapping("/admin/product/{id}")
  public String getProductDetailPage(Model model, @PathVariable Long id) {
    model.addAttribute("product", this.productService.findById(id));
    return "/admin/product/detail";
  }

  @GetMapping("/admin/product/update/{id}")
  public String getUpdateProductPage(Model model, @PathVariable Long id) {
    model.addAttribute("updateProduct", this.productService.findById(id));
    return "/admin/product/update";
  }

  @PostMapping("/admin/product/update/{id}")
  public String updateProduct(@ModelAttribute("updateProduct") @Valid Product newProduct,
      BindingResult newProductBindingResult,
      @RequestParam("productFile") MultipartFile file) {

    // validate
    if (newProductBindingResult.hasErrors()) {
      return "/admin/product/update";
    }
    //
    Product product = this.productService.findById(newProduct.getId());

    if (!file.isEmpty()) {
      String imgString = this.uploadFileService.handleUploadFile(file, "product");
      product.setImage(imgString);
    }

    product.setFactory(newProduct.getFactory());
    product.setName(newProduct.getName());
    product.setPrice(newProduct.getPrice());
    product.setProductDetail(newProduct.getProductDetail());
    product.setQuantity(newProduct.getQuantity());
    product.setSold(newProduct.getSold());
    product.setTarget(newProduct.getTarget());

    this.productService.handleSaveProduct(product);

    return "redirect:/admin/product";
  }

  @GetMapping("/admin/product/delete/{id}")
  public String getDeleteProductPage(Model model, @PathVariable Long id) {
    model.addAttribute("id", id);
    model.addAttribute("product", this.productService.findById(id));
    return "/admin/product/delete";
  }

  @PostMapping("/admin/product/delete")
  public String deleteProduct(@ModelAttribute("product") Product product) {
    this.productService.deleteById(product.getId());
    return "redirect:/admin/product";
  }

}
