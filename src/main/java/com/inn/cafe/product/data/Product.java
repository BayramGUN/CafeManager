package com.inn.cafe.product.data;

import java.io.Serializable;
import java.math.BigDecimal;

import com.inn.cafe.category.data.Category;
import com.inn.cafe.product.data.enums.ProductStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product implements Serializable {
  private static final Long serialVersionUID = 123456L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;
  
  @Column(name = "name")
  private String name;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(
    name = "category_foreign_key",
    nullable = false
  )
  private Category category;

  @Column(name = "description")
  private String description;

  @Column(name = "price")
  private BigDecimal price;

  @Column(name = "status")
  @Enumerated(EnumType.STRING)
  private ProductStatus status;

}
