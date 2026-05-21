package patterns.visitor.reports;

import model.Staff;

import java.time.LocalDate;

public class ReportContext {

    private LocalDate startDate;
    private LocalDate endDate;
    private Staff requestedBy;
    private boolean maskSensitiveData;

    public ReportContext() {
    }

    public ReportContext(LocalDate startDate, LocalDate endDate, Staff requestedBy, boolean maskSensitiveData) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.requestedBy = requestedBy;
        this.maskSensitiveData = maskSensitiveData;
    }

    public ReportContext(Staff requestedBy, boolean maskSensitiveData) {
        this.requestedBy = requestedBy;
        this.maskSensitiveData = maskSensitiveData;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Staff getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(Staff requestedBy) {
        this.requestedBy = requestedBy;
    }

    public boolean isMaskSensitiveData() {
        return maskSensitiveData;
    }

    public void setMaskSensitiveData(boolean maskSensitiveData) {
        this.maskSensitiveData = maskSensitiveData;
    }
}
