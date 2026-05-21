package patterns.visitor.reports;

import java.time.LocalTime;
import model.Appointment;
import model.Bill;
import model.Patient;

import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import service.PatientService;
import service.StaffService;
import session.SessionManager;


public class BillReportVisitor implements ReportVisitor {

    private final PatientService patientService = new PatientService();
    private final ReportDTO reportDTO = new ReportDTO();
    private ReportContext context;

    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("dd MMM yyyy");

    @Override
    public void visit(PatientNode patientNode) {
      
    }

    @Override
    public void visit(AppointmentNode appointmentNode) {
        
    }

    @Override
    public void visit(BillNode billNode) {
        
        Bill b = billNode.getBill();
        String details = String.format("Bill ID: %s\nPatient Name: %s\nDate: %s\nAmount: Rs.%.2f\nPayment Method: %s\nService Type: %s\nStatus: %s",
                b.getId(),
                getPatient(b.getPatientId()),
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
    
   private String getPatient(int patientId){
        
        try {
            return patientService.getPatientById(patientId,SessionManager.getInstance().getCurrentStaff()).getName();
        } catch (Exception ex) {
           ex.printStackTrace();
        }
        
        return null;
    }
    private String maskName(String name) {
        if (name == null || name.length() < 2) {
            return "****";
        }
        return name.charAt(0) + "***";
    }
}
