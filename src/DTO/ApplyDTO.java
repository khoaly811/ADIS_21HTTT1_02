package DTO;

public class ApplyDTO {
    private int applyID;
    public ApplyDTO(){}
    public ApplyDTO(int apply_id){
        this.applyID=apply_id;
    }
    public int getApplyID() {
        return applyID;
    }
    public void setApplyID(int apply_id) {
        this.applyID = apply_id;
    }
    
}
