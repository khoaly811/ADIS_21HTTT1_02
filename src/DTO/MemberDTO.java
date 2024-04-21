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
