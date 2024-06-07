package vn.hoidanit.laptopshop.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_detail")
public class ProductDetail {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String detailDesc;
  private String shortDesc;

  @OneToOne(mappedBy = "productDetail")
  private Product product;

  public String getDetailDesc() {
    return detailDesc;
  }

  public void setDetailDesc(String detailDesc) {
    this.detailDesc = detailDesc;
  }

  public String getShortDesc() {
    return shortDesc;
  }

  public void setShortDesc(String shortDesc) {
    this.shortDesc = shortDesc;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "ProductDetail [id=" + id + ", detailDesc=" + detailDesc + ", shortDesc=" + shortDesc + "]";
  }
}
