package DTO;

import java.math.BigInteger;

public class MemberDTO {
    private BigInteger member_id;
    private int member_type;
    private String member_name;
    private String address;
    private String phone;
    private String representative;
    private String email;
    private String password;
    public MemberDTO(){}
    public MemberDTO(BigInteger member_id, int member_type, String member_name, String address, String phone, String representative, String email, String password){
        this.member_id=member_id;
        this.member_type=member_type;
        this.member_name=member_name;
        this.address=address;
        this.phone=phone;
        this.representative=representative;
        this.email=email;
        this.password=password;
    }
    public MemberDTO(int member_type, String member_name, String address, String phone, String representative, String email, String password){
        this.member_type=member_type;
        this.member_name=member_name;
        this.address=address;
        this.phone=phone;
        this.representative=representative;
        this.email=email;
        this.password=password;
    }
    public BigInteger getID(){
        return member_id;
    }
    public void setID(BigInteger member_id){
        this.member_id=member_id;
    }
    public int getType(){
        return member_type;
    }
    public void setType(int member_type){
        this.member_type=member_type;
    }
    public String getName(){
        return member_name;
    }
    public void setName(String member_name){
        this.member_name=member_name;
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
