package DTO;

import java.math.BigInteger;

public class MemberDTO {
    private BigInteger memberId;
    private int memberType;
    private String memberName;
    private String address;
    private String phone;
    private String representative;
    private String email;
    private String password;
    public MemberDTO(){}
    public MemberDTO(BigInteger memberId, int memberType, String memberName, String address, String phone, String representative, String email, String password){
        this.memberId=memberId;
        this.memberType=memberType;
        this.memberName=memberName;
        this.address=address;
        this.phone=phone;
        this.representative=representative;
        this.email=email;
        this.password=password;
    }
    public MemberDTO(int memberType, String memberName, String address, String phone, String representative, String email, String password){
        this.memberType=memberType;
        this.memberName=memberName;
        this.address=address;
        this.phone=phone;
        this.representative=representative;
        this.email=email;
        this.password=password;
    }
    public BigInteger getID(){
        return memberId;
    }
    public void setID(BigInteger memberId){
        this.memberId=memberId;
    }
    public int getType(){
        return memberType;
    }
    public void setType(int memberType){
        this.memberType=memberType;
    }
    public String getName(){
        return memberName;
    }
    public void setName(String memberName){
        this.memberName=memberName;
    }
    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address=address;
    }
    public String getPhone(){
        return phone;
    }
    public void setPhone(String phone){
        this.phone=phone;
    }
    public String getRepresentative(){
        return representative;
    }
    public void setRepresentative(String representative){
        this.representative=representative;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password=password;
    }

}
