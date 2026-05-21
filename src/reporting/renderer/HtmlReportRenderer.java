package reporting.renderer;

import patterns.visitor.reports.ReportDTO;



public class HtmlReportRenderer {

    public String render(ReportDTO report) {
        StringBuilder html = new StringBuilder();

        
        html.append("<html>\n<head>\n");
        html.append("<title>").append(escape(report.getTitle())).append("</title>\n");
        html.append("<style>\n")
            .append("body { font-family: Arial, sans-serif; margin: 20px; }\n")
            .append("h1 { color: #2a4d69; }\n")
            .append("h2 { color: #4b86b4; margin-top: 20px; }\n")
            .append("pre { background: #f4f4f4; padding: 10px; border-radius: 5px; }\n")
            .append("</style>\n</head>\n<body>\n");

        
        html.append("<h1>").append(escape(report.getTitle())).append("</h1>\n");

        
        report.getSections().forEach((heading, content) -> {
            html.append("<h2>").append(escape(heading)).append("</h2>\n");
            html.append("<pre>").append(escape(content)).append("</pre>\n");
        });

        
        html.append("</body>\n</html>");

        return html.toString();
    }

    private String escape(String text) {
        if (text == null) return "";
        return text.replace("&", "&amp;")
                   .replace("<", "&lt;")
                   .replace(">", "&gt;")
                   .replace("\"", "&quot;")
                   .replace("'", "&#39;");
    }
}
