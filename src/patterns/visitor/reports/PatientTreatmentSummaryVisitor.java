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

public class PatientTreatmentSummaryVisitor implements ReportVisitor {

    private final StaffService staffService = new StaffService();
    private final ReportDTO reportDTO = new ReportDTO();
    private ReportContext context;

    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("dd MMM yyyy");

    @Override
    public void visit(PatientNode patientNode) {
        Patient patient = patientNode.getPatient();

        reportDTO.setTitle("Patient Treatment Summary");

        StringBuilder patientInfo = new StringBuilder();
        patientInfo.append("Name: ")
                .append(context != null && context.isMaskSensitiveData()
                        ? maskName(patient.getName())
                        : patient.getName())
                .append("\nID: ").append(patient.getId())
                .append("\nDOB: ").append(patient.getDob() != null ? patient.getDob() : "N/A")
                .append("\nGender: ").append(patient.getGender());
        reportDTO.addSection("Patient Information", patientInfo.toString());

        String appointmentsSummary = patientNode.getAppointments().stream()
                .filter(a -> withinRange(a))
                .map(a -> String.format("%s - %s with Dr. %s (%s)",
                a.getStartTime().format(DATE_FMT),
                a.getType(),
                getDoctor(a.getStaffId()),
                a.getStatus()))
                .collect(Collectors.joining("\n"));
        if (appointmentsSummary.isEmpty()) {
            appointmentsSummary = "No appointments in selected period.";
        }
        reportDTO.addSection("Appointments", appointmentsSummary);

        double total = patientNode.getBills().stream()
                .mapToDouble(Bill::getAmount)
                .sum();
        StringBuilder billInfo = new StringBuilder();
        billInfo.append("Number of bills: ").append(patientNode.getBills().size()).append("\n");
        billInfo.append("Total amount: Rs.").append(String.format("%.2f", total));
        reportDTO.addSection("Financial Summary", billInfo.toString());
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
        String details = String.format("Bill ID: %s\nDate: %s\nAmount: Rs.%.2f\nPayment Method: %s\nService Type: %s\nStatus: %s",
                b.getId(),
                b.getCreatedAt(),
                b.getAmount(),
                b.getPaymentMethod().toString(),
                b.getServiceType(),
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

    private String getDoctor(int doctorId) {

        try {
            return staffService.getStaffById(doctorId).getName();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    private boolean withinRange(Appointment a) {
        if (context == null) {
            return true;
        }
        if (context.getStartDate() != null && a.getStartTime().isBefore(context.getStartDate().atStartOfDay())) {
            return false;
        }
        if (context.getEndDate() != null && a.getEndTime().isAfter(context.getEndDate().atTime(LocalTime.MAX))) {
            return false;
        }
        return true;
    }

    private String maskName(String name) {
        if (name == null || name.length() < 2) {
            return "****";
        }
        return name.charAt(0) + "***";
    }
}
