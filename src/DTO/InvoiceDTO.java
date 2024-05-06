package DTO;
import java.time.LocalDate;

public class InvoiceDTO {
    private int invoiceID;
    private LocalDate  paymentDate;
    private Float paidAmount;
    private Float totalPrice;
    public InvoiceDTO(){}
    public InvoiceDTO(int invoiceID, LocalDate paymentDate, Float totalPrice, Float paidAmount){
        this.invoiceID=invoiceID;
        this.paymentDate=paymentDate;
        this.paidAmount=paidAmount;
        this.totalPrice=totalPrice;

    }
    public int getInvoiceID() {
        return invoiceID;
    }
    public void setInvoiceID(int invoiceID) {
        this.invoiceID = invoiceID;
    }
    public LocalDate getPaymentDate() {
        return paymentDate;
    }
    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }
    public Float getPaidAmount() {
        return paidAmount;
    }
    public void setPaidAmount(Float paidAmount) {
        this.paidAmount = paidAmount;
    }
    public Float getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }
}
