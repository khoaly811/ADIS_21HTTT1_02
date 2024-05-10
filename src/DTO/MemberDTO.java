package dto;

import java.math.BigInteger;

public class MemberDTO {
    private BigInteger memberID;
    private int memberType;
    private String memberName;
    private String memberAddress;
    private String memberPhone;
    private String memberRepresentative;
    private String memberEmail;
    private String memberPassword;
    private String taxNumber;
    private Integer discount;
    private String description;

    public MemberDTO() {
    }

    // Company account constructor
    public MemberDTO(int i, String comName, String address, String phone, String representative, String email,
            String taxNum, String password) {
        this.memberType = i;
        this.memberName = comName;
        this.memberAddress = address;
        this.memberPhone = phone;
        this.memberRepresentative = representative;
        this.memberEmail = email;
        this.taxNumber = taxNum;
        this.memberPassword = password;
    }

    // Company account constructor
    public MemberDTO(BigInteger member_id, String member_name, String email, String address, String phone,
            String representative, String tax_number) {
        this.memberID = member_id;
        this.memberName = member_name;
        this.memberEmail = email;
        this.memberAddress = address;
        this.memberPhone = phone;
        this.memberRepresentative = representative;
        this.taxNumber = tax_number;
    }

    public MemberDTO(BigInteger member_id, int member_type, String member_name, String address, String phone,
            String representative, String email, String password, String tax_number) {
        this.memberID = member_id;
        this.memberType = member_type;
        this.memberName = member_name;
        this.memberAddress = address;
        this.memberPhone = phone;
        this.memberRepresentative = representative;
        this.memberEmail = email;
        this.memberPassword = password;
        this.taxNumber = tax_number;
    }

    public MemberDTO(BigInteger member_id, String member_name, String email, String address,
            String phone, String representative, String tax_number, String password, Integer discount) {
        this.memberID = member_id;
        this.memberName = member_name;
        this.memberAddress = address;
        this.memberPhone = phone;
        this.memberRepresentative = representative;
        this.memberEmail = email;
        this.memberPassword = password;
        this.taxNumber = tax_number;
        this.discount = discount;
    }

    public MemberDTO(BigInteger member_id, int member_type, String member_name, String email, String address,
            String phone, String representative, String tax_number, String password, Integer discount,
            String description) {
        this.memberID = member_id;
        this.memberType = member_type;
        this.memberName = member_name;
        this.memberAddress = address;
        this.memberPhone = phone;
        this.memberRepresentative = representative;
        this.memberEmail = email;
        this.memberPassword = password;
        this.taxNumber = tax_number;
        this.discount = discount;
        this.description = description;
    }

    public MemberDTO(BigInteger memberID, String memberName, String email, String phone, String tax_number,
            String description, Integer discount) {
        this.memberID = memberID;
        this.memberName = memberName;
        this.memberEmail = email;
        this.memberPhone = phone;
        this.taxNumber = tax_number;
        this.description = description;
        this.discount = discount;
    }

    public BigInteger getMemberID() {
        return memberID;
    }

    public void setMemberID(BigInteger member_id) {
        this.memberID = member_id;
    }

    public int getMemberType() {
        return memberType;
    }

    public void setMemberType(int member_type) {
        this.memberType = member_type;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String member_name) {
        this.memberName = member_name;
    }

    public String getMemberAddress() {
        return memberAddress;
    }

    public void setMemberAddress(String address) {
        this.memberAddress = address;
    }

    public String getMemberPhone() {
        return memberPhone;
    }

    public void setMemberPhone(String phone) {
        this.memberPhone = phone;
    }

    public String getMemberRepresentative() {
        return memberRepresentative;
    }

    public void setMemberRepresentative(String representative) {
        this.memberRepresentative = representative;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String email) {
        this.memberEmail = email;
    }

    public String getMemberPassword() {
        return memberPassword;
    }

    public void setMemberPassword(String password) {
        this.memberPassword = password;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String tax_number) {
        this.taxNumber = tax_number;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
