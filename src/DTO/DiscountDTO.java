package DTO;

import java.math.BigInteger;

public class DiscountDTO {
    private BigInteger discountID;
    private String description;
    private double discountPercentage;

    public DiscountDTO() {}

    public DiscountDTO(BigInteger discountID, String description, double discountPercentage) {
        this.discountID = discountID;
        this.description = description;
        this.discountPercentage = discountPercentage;
    }

    public BigInteger getDiscountID() {
        return discountID;
    }

    public void setDiscountID(BigInteger discountID) {
        this.discountID = discountID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }
}
