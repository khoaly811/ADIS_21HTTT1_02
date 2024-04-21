package DTO;

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
    public MemberDTO(){}
    public MemberDTO(BigInteger member_id, int member_type, String member_name, String address, String phone, String representative, String email, String password){
        this.memberID=member_id;
        this.memberType=member_type;
        this.memberName=member_name;
        this.memberAddress=address;
        this.memberPhone=phone;
        this.memberRepresentative=representative;
        this.memberEmail=email;
        this.memberPassword=password;
    }
    public MemberDTO(int member_type, String member_name, String address, String phone, String representative, String email, String password){
        this.memberType=member_type;
        this.memberName=member_name;
        this.memberAddress=address;
        this.memberPhone=phone;
        this.memberRepresentative=representative;
        this.memberEmail=email;
        this.memberPassword=password;
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
    

}
