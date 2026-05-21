package patterns.visitor.reports;

import java.util.LinkedHashMap;
import java.util.Map;

public class ReportDTO {

    private String title;
    private final Map<String, String> sections = new LinkedHashMap<>();

    public ReportDTO() {
    }

    public ReportDTO(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void addSection(String heading, String body) {
        sections.put(heading, body);
    }

    public Map<String, String> getSections() {
        return sections;
    }

    public String toPlainText() {
        StringBuilder sb = new StringBuilder();
        if (title != null) {
            sb.append(title).append("\n\n");
        }
        for (Map.Entry<String, String> e : sections.entrySet()) {
            sb.append(e.getKey()).append("\n");
            sb.append(e.getValue()).append("\n\n");
        }
        return sb.toString();
    }
}
