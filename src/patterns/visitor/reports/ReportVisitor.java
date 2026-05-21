package patterns.visitor.reports;


public interface ReportVisitor {

  
    void visit(PatientNode patientNode);

   
    void visit(AppointmentNode appointmentNode);

   
    void visit(BillNode billNode);

   
    ReportDTO getReport();

   
    void setContext(ReportContext context);
}
