package vn.hoidanit.laptopshop.services;

import org.springframework.stereotype.Service;

import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.repository.ProductDetailRepository;
import vn.hoidanit.laptopshop.repository.ProductRepository;
import java.util.List;

@Service
public class ProductService {
  private final ProductRepository productRepository;
  private final ProductDetailRepository productDetailRepository;

  public ProductService(ProductRepository productRepository, ProductDetailRepository productDetailRepository) {
    this.productRepository = productRepository;
    this.productDetailRepository = productDetailRepository;
  }

  public void handleSaveProduct(Product product) {
    this.productRepository.save(product);
    this.productDetailRepository.save(product.getProductDetail());
  }

  public List<Product> findAll() {
    return this.productRepository.findAll();
  }
}
