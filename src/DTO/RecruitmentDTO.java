package dto;

import java.math.BigInteger;
import java.util.Date;

public class RecruitmentDTO {
    private BigInteger recruitmentId;
    private String position;
    private int numberOfPosition;
    private Date startDate;
    private Date endDate;
    private String requirement;

    public RecruitmentDTO() {}

    public RecruitmentDTO(BigInteger recruitmentId, String position,
                          int numberOfPosition, Date startDate, Date endDate, String requirement) {
        this.recruitmentId = recruitmentId;
        this.position = position;
        this.numberOfPosition = numberOfPosition;
        this.startDate = startDate;
        this.endDate = endDate;
        this.requirement = requirement;
    }

    public RecruitmentDTO(String position, int numberOfPosition, Date startDate, Date endDate, String requirement) {
        this.position = position;
        this.numberOfPosition = numberOfPosition;
        this.startDate = startDate;
        this.endDate = endDate;
        this.requirement = requirement;
    }


    public BigInteger getRecruitmentId() {
        return recruitmentId;
    }

    public void setRecruitmentId(BigInteger recruitmentId) {
        this.recruitmentId = recruitmentId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getNumberOfPosition() {
        return numberOfPosition;
    }

    public void setNumberOfPosition(int numberOfPosition) {
        this.numberOfPosition = numberOfPosition;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }
}

