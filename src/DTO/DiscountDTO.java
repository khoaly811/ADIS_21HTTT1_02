package DTO;

public class DiscountDTO {
    private Integer discountID;
    private String description;
    private double discountPercentage;
    private Integer companyID;

    public DiscountDTO() {
    }

    public DiscountDTO(Integer discountID, String description, double discountPercentage, Integer companyID) {
        this.discountID = discountID;
        this.description = description;
        this.discountPercentage = discountPercentage;
        this.companyID = companyID;
    }

    public Integer getDiscountID() {
        return discountID;
    }

    public void setDiscountID(Integer discountID) {
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

    public Integer getCompanyID() {
        return companyID;
    }

    public void setCompanyID(Integer companyID) {
        this.companyID = companyID;
    }
}
