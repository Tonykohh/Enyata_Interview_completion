//package Utils.sendEmails;
//
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//
//import java.io.File;
//import java.io.IOException;
//import  Utils.extentReports.extentManager;
//
//public class HtmlReportAnalyzer {
//
//    private String reportPath;
//
//    public HtmlReportAnalyzer(String reportPath) {
//        this.reportPath = reportPath;
//    }
//
//    public HtmlReportAnalyzer() {
//        this.reportPath = extentManager.getReportPath();
//    }
//
//    public String analyzeReport() throws IOException {
//        File input = new File(reportPath);
//        Document doc = Jsoup.parse(input, "UTF-8");
//
//        // Example to get the test names and their statuses
//        Elements testItems = doc.select(".test-item");
//        StringBuilder reportSummary = new StringBuilder();
//        int passed = 0;
//        int failed = 0;
//        int skipped = 0;
//
//        for (Element item : testItems) {
//            String testName = item.select(".test-name").text(); // Assuming this is the correct class
//            String status = item.attr("status");
//            switch (status) {
//                case "pass":
//                    passed++;
//                    break;
//                case "fail":
//                    failed++;
//                    break;
//                case "skip":
//                    skipped++;
//                    break;
//            }
//            // Append test name and status to the summary
//            reportSummary.append("Test Case: ").append(testName).append(" - Status: ").append(status).append("\n");
//        }
//
//        // Build the final summary string
//        return "Test Summary:\n" +
//                "Tests Passed: " + passed + "\n" +
//                "Tests Failed: " + failed + "\n" +
//                "Tests Skipped: " + skipped + "\n\n" +
//                "Detailed Test Results:\n" +
//                reportSummary;
//    }
//
//    public static void main(String[] args) {
//        try {
//            HtmlReportAnalyzer analyzer = new HtmlReportAnalyzer();
//            analyzer.analyzeReport();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
