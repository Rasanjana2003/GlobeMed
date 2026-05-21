package patterns.visitor.reports;


public interface ReportNode {
    void accept(ReportVisitor visitor);
}
