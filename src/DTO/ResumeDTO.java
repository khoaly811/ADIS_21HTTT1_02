package DTO;

import java.math.BigInteger;
import java.util.Date;

public class ResumeDTO {
    private BigInteger resumeID;
    private String certificateName;
    private Date issueDate;
    private String description;

    public ResumeDTO() {
        // Default constructor
    }

    public ResumeDTO(BigInteger resumeID, String certificateName, Date issueDate, String description) {
        this.resumeID = resumeID;
        this.certificateName = certificateName;
        this.issueDate = issueDate;
        this.description = description;
    }

    public BigInteger getResumeID() {
        return resumeID;
    }

    public void setResumeID(BigInteger resumeID) {
        this.resumeID = resumeID;
    }

    public String getCertificateName() {
        return certificateName;
    }

    public void setCertificateName(String certificateName) {
        this.certificateName = certificateName;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
