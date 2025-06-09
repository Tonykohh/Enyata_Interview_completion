//package Utils.listeners;
//
//import BaseClasses.TestBase;
//import Utils.extentReports.extentManager;
//import com.aventstack.extentreports.ExtentReports;
//import org.testng.ISuite;
//import org.testng.ISuiteListener;
//import Utils.ReportUtils;
//
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.HashMap;
//import java.util.Map;
//
//import Utils.sendEmails.EmailUtils;
//import org.testng.ISuiteResult;
//import org.testng.ITestContext;
//
//public class CustomSuiteListener implements ISuiteListener {
//    private final TestBase testBase = new TestBase(); // Initialize TestBase properly
//    private final EmailUtils emailUtils = new EmailUtils(testBase);
//    private ExtentReports extent;
//    private Map<String, String> testResults; // Assuming you collect these results during test execution
//
//    @Override
//    public void onStart(ISuite suite) {
//        // Initialize the testResults map
//        testResults = new HashMap<>();
//        extent = extentManager.createExtentReports();
//
//        // Specify your report directory path
//    }
//
//
//    @Override
//    public void onFinish(ISuite suite) {
//        // Initialize the testResults map
//        extent.flush();
//
//        Map<String, String> testResults = new HashMap<>();
//
//        // Collect test results for each suite result
//        for (ISuiteResult suiteResult : suite.getResults().values()) {
//            ITestContext context = suiteResult.getTestContext();
//
//            context.getPassedTests().getAllResults().forEach(testResult -> {
//                testResults.put(testResult.getName(), "pass");
//            });
//
//            context.getFailedTests().getAllResults().forEach(testResult -> {
//                testResults.put(testResult.getName(), "fail");
//            });
//
//            context.getSkippedTests().getAllResults().forEach(testResult -> {
//                testResults.put(testResult.getName(), "skip");
//            });
//        }
//
//        // Generate the report using the collected test results and the suite
//        String body = ReportUtils.generateAndSendReport(testResults, suite);
//
////        String body = "Not Really";
//
//
//        // Specify the recipients and subject for the email
//        String to = "chukwudi.okoli@tmnxtsupport.com,Ramanjaneyulu@techmsupport.com";
//        String subject = "TestNG Test Suite Report";
//
//        // Path to the extent report
//        String reportPath = extentManager.getReportPath();
//        File reportFile = new File(reportPath);
//
//        // Add a delay to ensure the report file is fully created
//        try {
//            Thread.sleep(2000); // Delay for 2 seconds
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        if (!reportFile.exists()) {
//            System.out.println("Report file not found: " + reportPath);
//            return;
//        }
//
//        // Save the generated report body as an HTML file (optional)
//        try (PrintWriter out = new PrintWriter(new FileWriter("body.html"))) {
//            out.println(body);
//            System.out.println("body.html file has been created/updated with the report content.");
//        } catch (IOException e) {
//            System.out.println("An error occurred while writing to body.html: " + e.getMessage());
//            e.printStackTrace();
//        }
//
//        // Send the email with the report attachment (uncomment and implement email sending logic)
////        emailUtils.sendEmail(to, subject, body, reportPath);
//
//        System.out.println("Test suite has finished. Report generated and email sent.");
//
//        // Delete old reports after the current suite is finished
//        File reportDirectory = new File("Reports");
//
//        if (reportDirectory.exists() && reportDirectory.isDirectory()) {
//            File[] files = reportDirectory.listFiles((dir, name) -> name.endsWith(".html"));
//            if (files != null) {
//                for (File file : files) {
//                    if (file.isFile() && !file.getName().equals(new File(reportPath).getName())) {
//                        boolean deleted = file.delete();
//                        if (deleted) {
//                            System.out.println("Deleted file: " + file.getName());
//                        } else {
//                            System.out.println("Failed to delete file: " + file.getName());
//                        }
//                    }
//                }
//            }
//        }
//
//        System.out.println("Previous reports deleted. Suite is finished...");
//    }
//
//}
