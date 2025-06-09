//package Utils.HelperClasses;
////
////import org.slf4j.Logger;
////import org.slf4j.LoggerFactory;
////import org.testng.*;
////import org.testng.xml.XmlSuite;
////
////import java.io.*;
////import java.nio.file.Files;
////import java.nio.file.Paths;
////import java.util.List;
////import java.util.Map;
////import java.util.Set;
////import java.util.function.Function;
////import java.util.stream.Collectors;
////import java.util.stream.Stream;
////
////import static java.util.stream.Collectors.toList;
////
////public class CustomisedReports implements IReporter {
////    private static final Logger LOGGER = LoggerFactory.getLogger(CustomisedReports.class);
////
////    private static final String ROW_TEMPLATE = "<tr class=\"%s\"><td>%s</td><td>%s</td><td>%s</td><td>%s</td></tr>";
////
////    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
////        String reportTemplate = initReportTemplate();
////        outputDirectory = "Reports/CustomizedReports/";
////
////        final String body = suites
////                .stream()
////                .flatMap(suiteToResults())
////                .collect(Collectors.joining());
////
////        saveReportTemplate(outputDirectory,
////                reportTemplate.replaceFirst("</tbody>", String.format("%s</tbody>", body)));
////    }
////
////    private Function<ISuite, Stream<? extends String>> suiteToResults() {
////        return suite -> suite.getResults().entrySet()
////                .stream()
////                .flatMap(resultsToRows(suite));
////    }
////
////
////    private Function<Map.Entry<String, ISuiteResult>, Stream<? extends String>> resultsToRows(ISuite suite) {
////        return e -> {
////            ITestContext testContext = e.getValue().getTestContext();
////
////            Set<ITestResult> failedTests = testContext
////                    .getFailedTests()
////                    .getAllResults();
////            Set<ITestResult> passedTests = testContext
////                    .getPassedTests()
////                    .getAllResults();
////            Set<ITestResult> skippedTests = testContext
////                    .getSkippedTests()
////                    .getAllResults();
////
////            String suiteName = suite.getName();
////
////            return Stream
////                    .of(failedTests, passedTests, skippedTests)
////                    .flatMap(results -> generateReportRows(e.getKey(), suiteName, results).stream());
////        };
////    }
////
////    private List<String> generateReportRows(String testName, String suiteName, Set<ITestResult> allTestResults) {
////        return allTestResults.stream()
////                .map(testResultToResultRow(testName, suiteName))
////                .collect(toList());
////    }
////
////
////    private Function<ITestResult, String> testResultToResultRow(String testName, String suiteName) {
////        return testResult -> {
////            switch (testResult.getStatus()) {
////                case ITestResult.FAILURE:
////                    return String.format(ROW_TEMPLATE, "danger", suiteName, testName, testResult.getName(), "FAILED", "");
////
////                case ITestResult.SUCCESS:
////                    return String.format(ROW_TEMPLATE, "success", suiteName, testName, testResult.getName(), "PASSED", "");//(testResult.getEndMillis() - testResult.getStartMillis()) / 1000
////
////                case ITestResult.SKIP:
////                    return String.format(ROW_TEMPLATE, "warning", suiteName, testName, testResult.getName(), "SKIPPED", "");
////
////                default:
////                    return "";
////            }
////        };
////    }
////
////    private String initReportTemplate() {
////        String template = null;
////        byte[] reportTemplate;
////        try {
////            reportTemplate = Files.readAllBytes(Paths.get("src/test/java/Resources/reportTemplate.html"));
////            template = new String(reportTemplate, "UTF-8");
////        } catch (IOException e) {
////            LOGGER.error("Problem initializing template", e);
////        }
////        return template;
////    }
////
////    private void saveReportTemplate(String outputDirectory, String reportTemplate) {
////        new File(outputDirectory).mkdirs();
////        try {
////            PrintWriter reportWriter = new PrintWriter(new BufferedWriter(new FileWriter(new File(outputDirectory, "SimpleReport.html"))));
////            reportWriter.println(reportTemplate);
////            reportWriter.flush();
////            reportWriter.close();
////        } catch (IOException e) {
////            LOGGER.error("Problem saving template", e);
////        }
////    }
////}
//
//import org.testng.IReporter;
//import org.testng.ISuite;
//import org.testng.Reporter;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.nio.charset.StandardCharsets;
//import java.util.List;
//import java.util.function.Function;
//import java.util.stream.Stream;
//;
//
//public class CustomisedReports implements IReporter {
//
//    public void generateReport(List<org.testng.xml.XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
//        String reportTemplate = readTemplateFile();
//        outputDirectory = "Reports/CustomizedReports/";
//
//        // Assuming suiteToResults() is defined elsewhere to extract results from suites
//        final String body = suites
//                .stream()
//                .flatMap(suiteToResults())
//                .collect(Collectors.joining());
//
//        // Ensure the output directory exists
//
//        // Save the generated report to the specified output directory
//        saveReportTemplate(outputDirectory,
//                reportTemplate.replaceFirst("</tbody>", String.format("%s</tbody>", body)));
//    }
//
//    public String readTemplateFile() {
//        String template = null;
//        try {
//            byte[] reportTemplate = Files.readAllBytes(Paths.get("src/test/java/Resources/htmlReportTemplate.html"));
//            template = new String(reportTemplate, StandardCharsets.UTF_8);
//        } catch (IOException e) {
//            System.err.println("Problem initializing template: " + e.getMessage());
//        }
//        return template;
//    }
//
//    private void saveReportTemplate(String outputDirectory, String content) {
//        new java.io.File(outputDirectory).mkdirs();
//
//        try {
//            Files.write(Paths.get(outputDirectory + "test-results.html"), content.getBytes(StandardCharsets.UTF_8));
//        } catch (IOException e) {
//            System.err.println("Problem writing report: " + e.getMessage());
//        }
//    }
//
////
////    private java.util.function.Function<ISuite, java.util.stream.Stream<String>> suiteToResults() {
////        return suite -> {
////            return java.util.stream.Stream.of(
////                    "<tr><td>Example Suite</td><td>Example Test</td><td>Example Use Case</td><td>passed</td></tr>"
////            );
////        };
////    }
//
//    private Function<ISuite, Stream<? extends String>> suiteToResults() {
//        return suite -> suite.getResults().entrySet()
//                .stream()
//                .flatMap(resultsToRows(suite));
//    }
//
//    private String formatTestResultRow(ITestResult result) {
//        String status;
//        String statusClass;
//
//        switch (result.getStatus()) {
//            case ITestResult.FAILURE:
//                status = "FAILED";
//                statusClass = "failed";
//                break;
//            case ITestResult.SUCCESS:
//                status = "PASSED";
//                statusClass = "passed";
//                break;
//            case ITestResult.SKIP:
//                status = "SKIPPED";
//                statusClass = "skipped";
//                break;
//            default:
//                status = "UNKNOWN";
//                statusClass = "unknown"; // Optional: handle unknown status if needed
//                break;
//        }
//
//        return String.format("<tr class=\"%s\"><td>%s</td><td>%s</td><td>%s</td><td>%s</td></tr>",
//                statusClass,
//                result.getTestContext().getSuite().getName(),
//                result.getMethod().getMethodName(),
//                (String) result.getMethod().getDescription(),
//                status);
//    }
//
//}