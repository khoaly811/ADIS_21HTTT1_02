package dto;

import java.math.BigInteger;
import java.util.Date;

public class RecruitmentDTO {
    private BigInteger recruitmentId;
    private int companyId;
    private String position;
    private int numberOfPosition;
    private int length;
    private Date startDate;
    private String requirement;
    private int recruitmentStatus;
    private int adsForm;

    public RecruitmentDTO() {
    }

    public RecruitmentDTO(BigInteger recruitmentId, String position,
            int numberOfPosition, Date startDate, Date endDate, String requirement) {
        this.recruitmentId = recruitmentId;
        this.position = position;
        this.numberOfPosition = numberOfPosition;
        this.startDate = startDate;
        this.requirement = requirement;
    }

    public RecruitmentDTO(String position, int numberOfPosition, Date startDate, Date endDate, String requirement) {
        this.position = position;
        this.numberOfPosition = numberOfPosition;
        this.startDate = startDate;
        this.requirement = requirement;
    }

    public RecruitmentDTO(String string, int i, int j, java.sql.Date valueOf, java.sql.Date valueOf2, String string2) {
        // TODO Auto-generated constructor stub
    }

    public RecruitmentDTO(int recruitmentId, int companyId, String position, int quantity, java.sql.Date startDate,
            String requirements, int recruimentStatus, int length, int adsForm) {
        // TODO Auto-generated constructor stub
        this.recruitmentId = BigInteger.valueOf(recruitmentId);
        this.companyId = companyId;
        this.position = position;
        this.numberOfPosition = quantity;
        this.startDate = startDate;
        this.requirement = requirements;
        this.recruitmentStatus = recruimentStatus;
        this.length = length;
        this.adsForm = adsForm;
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

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getRecruitmentStatus() {
        return recruitmentStatus;
    }

    public void setRecruitmentStatus(int recruitmentStatus) {
        this.recruitmentStatus = recruitmentStatus;
    }

    public void setStartDate(java.sql.Date startDate) {
        this.startDate = startDate;
    }

    public void setRecruitmentId(int recruitmentId) {
        this.recruitmentId = BigInteger.valueOf(recruitmentId);
    }

    public int getAdsForm() {
        return adsForm;
    }

    public void setAdsForm(int adsForm) {
        this.adsForm = adsForm;
    }
}
