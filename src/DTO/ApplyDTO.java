package DTO;

public class ApplyDTO {
    private int applyID;
    private int resumeID;
    private int recruitmentID;

    public ApplyDTO() {
    }

    public ApplyDTO(int applyID, int resumeID, int recruitmentID) {
        this.applyID = applyID;
        this.resumeID = resumeID;
        this.recruitmentID = recruitmentID;
    }

    public int getApplyID() {
        return applyID;
    }

    public void setApplyID(int applyID) {
        this.applyID = applyID;
    }

    public int getResumeID() {
        return resumeID;
    }

    public void setResumeID(int resumeID) {
        this.resumeID = resumeID;
    }

    public int getRecruitmentID() {
        return recruitmentID;
    }

    public void setRecruitmentID(int recruitmentID) {
        this.recruitmentID = recruitmentID;
    }
}
