package service;

import dao.AppointmentDAO;
import dao.BillDAO;
import dao.PatientDAO;
import dao.StaffDAO;
import java.util.List;
import javax.swing.JEditorPane;
import model.Appointment;
import model.Bill;
import model.Patient;
import model.Staff;
import patterns.visitor.reports.BillNode;
import patterns.visitor.reports.BillReportVisitor;
import patterns.visitor.reports.PatientMedicalReportVisitor;
import patterns.visitor.reports.PatientNode;
import patterns.visitor.reports.PatientTreatmentSummaryVisitor;
import patterns.visitor.reports.ReportContext;
import patterns.visitor.reports.ReportDTO;
import reporting.renderer.HtmlReportRenderer;

public class ReportService {

    private final PatientDAO patientDAO;
    private final AppointmentDAO appointmentDAO;
    private final BillDAO billDAO;

    public ReportService() {
        this.patientDAO = new PatientDAO();
        this.appointmentDAO = new AppointmentDAO();
        this.billDAO = new BillDAO();
    }

    public String generateTreatmentSummery(int patientId) throws Exception {
        Patient patient = patientDAO.findById(patientId);
        List<Appointment> appointmentList = appointmentDAO.findByPatient(patientId);
        List<Bill> billList = billDAO.findBillsByPatient(patientId);
        PatientNode patientNode = new PatientNode(patient, appointmentList, billList);

        PatientTreatmentSummaryVisitor visitor = new PatientTreatmentSummaryVisitor();
        patientNode.accept(visitor);

        ReportDTO dto = visitor.getReport();
        HtmlReportRenderer renderer = new HtmlReportRenderer();
        String htmlReport = renderer.render(dto);
        
        return htmlReport;
        
    }
    
    public String generateMedicalReport(int patientId,Staff staff) throws Exception {
        
        Patient patient = patientDAO.findById(patientId);
        List<Appointment> appointmentList = appointmentDAO.findByPatient(patientId);
        List<Bill> billList = billDAO.findBillsByPatient(patientId);
        PatientNode patientNode = new PatientNode(patient, appointmentList, billList);
        ReportContext reportContext;
        if(staff.getRole().equals("ADMIN")|| staff.getRole().equals("DOCTOR")){
            reportContext = new ReportContext(staff,false);        
        }else{
            reportContext = new ReportContext(staff,true);        
            
        }

        PatientMedicalReportVisitor visitor = new PatientMedicalReportVisitor();
        visitor.setContext(reportContext);
        patientNode.accept(visitor);

        ReportDTO dto = visitor.getReport();
        HtmlReportRenderer renderer = new HtmlReportRenderer();
        String htmlReport = renderer.render(dto);
        
        return htmlReport;
        
    }
    
    public String generateBillReport(int billId) throws Exception {
       Bill bill = billDAO.findById(billId);
        BillNode billNode = new BillNode(bill);

        BillReportVisitor visitor = new BillReportVisitor();
        billNode.accept(visitor);

        ReportDTO dto = visitor.getReport();
        HtmlReportRenderer renderer = new HtmlReportRenderer();
        String htmlReport = renderer.render(dto);
        
        return htmlReport;
        
    }
    
    

}
