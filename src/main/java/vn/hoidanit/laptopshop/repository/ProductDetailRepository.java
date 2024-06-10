package vn.hoidanit.laptopshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.hoidanit.laptopshop.domain.ProductDetail;

public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long> {
  ProductDetail save(ProductDetail productDetail);

  void deleteById(Long id);
}
