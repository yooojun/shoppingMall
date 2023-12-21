package com.nhnacademy.shoppingmall.product.domain;

import java.util.Objects;
import lombok.Getter;

@Getter
public class Product {
    private Integer productId;
    private String modelNumber;
    private String modelName;
    private int unitCost;
    private String productImage;
    private String description;


    public Product(String modelNumber, String modelName, int unitCost , String description) {
        this(0, modelNumber, modelName, "resources/no-image.png", unitCost, description);
    }

    public Product(Integer productId, String modelNumber, String modelName, String productImage, int unitCost, String description) {
        this.productId = productId;
        this.modelNumber = modelNumber;
        this.modelName = modelName;
        this.productImage = productImage;
        this.unitCost = unitCost;
        this.description = description;
    }

    public Product(Integer id, String number, String name, int unitCost) {
        this(id, number, name, "resources/no-image.png", unitCost, "");
    }

    public Product(String modelNumber, String productName, Integer unitCost, String productImage, String description) {
        this(0, modelNumber, productName, productImage, unitCost, description);
    }


    public void setProductId(Integer productId) {
        this.productId = productId;
    }
    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public void setUnitCost(int unitCost) {
        this.unitCost = unitCost;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return getUnitCost() == product.getUnitCost() &&
                Objects.equals(getProductId(), product.getProductId()) &&
                Objects.equals(getModelNumber(), product.getModelNumber()) &&
                Objects.equals(getModelName(), product.getModelName()) &&
                Objects.equals(getProductImage(), product.getProductImage()) &&
                Objects.equals(getDescription(), product.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductId(), getModelNumber(), getModelName(), getProductImage(), getUnitCost(), getDescription());
    }

    @Override
    public String toString() {
        return "Products{" +
                "productId=" + productId +
                ", modelNumber='" + modelNumber + '\'' +
                ", modelName='" + modelName + '\'' +
                ", productImage='" + productImage + '\'' +
                ", unitCost=" + unitCost +
                ", description='" + description + '\'' +
                '}';
    }


}
