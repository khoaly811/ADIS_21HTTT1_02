package DTO;

public class AdvertisementDTO {
    private int adID;
    private String content;
    private int method;

    public AdvertisementDTO() {
    }

    public AdvertisementDTO(int adID, String content, int method) {
        this.adID = adID;
        this.content = content;
        this.method = method;
    }

    public int getAdID() {
        return adID;
    }

    public void setAdID(int adID) {
        this.adID = adID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getMethod() {
        return method;
    }

    public void setMethod(int method) {
        this.method = method;
    }
}
