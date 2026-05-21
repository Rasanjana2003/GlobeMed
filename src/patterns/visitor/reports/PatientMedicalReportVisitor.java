package patterns.visitor.reports;

import java.time.LocalTime;
import model.Appointment;
import model.Bill;
import model.Patient;

import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import service.StaffService;

public class PatientMedicalReportVisitor implements ReportVisitor {

    private final ReportDTO reportDTO = new ReportDTO();
    private ReportContext context;

    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("dd MMM yyyy");

    @Override
    public void visit(PatientNode patientNode) {
        Patient patient = patientNode.getPatient();

        reportDTO.setTitle("Patient Medical Report");

        StringBuilder patientInfo = new StringBuilder();
        patientInfo.append("Name: ").append(patient.getName())
                .append("\nID: ").append(patient.getId())
                .append("\nDOB: ").append(patient.getDob() != null ? patient.getDob() : "N/A")
                .append("\nGender: ").append(patient.getGender())
                .append("\nMedical History: ").append(context != null && context.isMaskSensitiveData()
                ? maskName(patient.getMedicalHistory())
                : patient.getMedicalHistory())
                .append("\nTreatment Plan: ").append(context != null && context.isMaskSensitiveData()
                ? maskName(patient.getTreatmentPlans())
                : patient.getTreatmentPlans());
        reportDTO.addSection("Patient Information", patientInfo.toString());

    }

    @Override
    public void visit(AppointmentNode appointmentNode) {

        Appointment a = appointmentNode.getAppointment();
        String details = String.format("%s - %s with Dr. %s (%s)",
                a.getStartTime().format(DATE_FMT),
                a.getType(),
                a.getStaffId(),
                a.getStatus());
        reportDTO.addSection("Appointment Detail", details);
    }

    @Override
    public void visit(BillNode billNode) {

        Bill b = billNode.getBill();
        String details = String.format("Bill ID: %s\nDate: %s\nAmount: $%.2f\nStatus: %s",
                b.getId(),
                b.getCreatedAt(),
                b.getAmount(),
                b.getStatus());
        reportDTO.addSection("Bill Detail", details);
    }

    @Override
    public ReportDTO getReport() {
        return reportDTO;
    }

    @Override
    public void setContext(ReportContext context) {
        this.context = context;
    }

    private String maskName(String name) {
        if (name == null || name.length() < 2) {
            return "****";
        }
        return name.charAt(0) + "***";
    }
}
