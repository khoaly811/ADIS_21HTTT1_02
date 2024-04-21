package DTO;

public class AdvertisementDTO {
    private int adID;
    private String adContent;
    private int adMethod;
    public AdvertisementDTO(){}
    public AdvertisementDTO(int advertisement_id, String content, int method){
        this.adID=advertisement_id;
        this.adContent=content;
        this.adMethod=method;
    }
    public int getAdID() {
        return adID;
    }
    public void setAdID(int advertisement_id) {
        this.adID = advertisement_id;
    }
    public String getAdContent() {
        return adContent;
    }
    public void setAdContent(String content) {
        this.adContent = content;
    }
    public int getAdMethod() {
        return adMethod;
    }
    public void setAdMethod(int method) {
        this.adMethod = method;
    }
}
