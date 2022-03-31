package com.SistasProject.Entities;

import javax.persistence.*;


@Entity
@Table(name="products")
public class Product {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long productId;

	 @Column(nullable = false, unique = true, length = 45)
	 private String productName;

	 @Column(nullable = false, length = 64)
	 private String productModel;

	 @Column(nullable = false, length = 20)
	 private String productCode;

	 @Column(nullable = false, length = 20)
	 private double productPrice;

	
	 
	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductModel() {
		return productModel;
	}

	public void setProductModel(String productModel) {
		this.productModel = productModel;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	
	 
	 
	
	
}
