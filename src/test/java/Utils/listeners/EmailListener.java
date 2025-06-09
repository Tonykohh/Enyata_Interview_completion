//package Utils.listeners;
//
//import BaseClasses.TestBase;
//import Utils.sendEmails.EmailUtils;
//import Utils.extentReports.extentManager;
//import Utils.sendEmails.HtmlReportAnalyzer;
//import com.aventstack.extentreports.ExtentReports;
//import org.testng.IExecutionListener;
//
//import java.io.IOException;
//
//public class EmailListener implements IExecutionListener {
//    private ExtentReports extent;
//    private final TestBase testBase = new TestBase(); // Initialize TestBase properly
//    private final EmailUtils emailUtils = new EmailUtils(testBase);
//    private final HtmlReportAnalyzer reportAnalyzer = new HtmlReportAnalyzer();
//
//
//    @Override
//    public void onExecutionStart() {
//        // This method is called before the TestNG suite starts
//        System.out.println("Test suite is starting...");
//        System.out.println("Test suite is starting...");
//        extent = extentManager.createExtentReports();
//    }
//
//    @Override
//    public void onExecutionFinish() {
//        // This method is called after the TestNG suite finishes
//        System.out.println("Test suite has finished. Sending email...");
//
//        // Collect the report path, customize the details as per your setup
//
//        String reportPath = extentManager.getReportPath();
//        String summary = "";
//
//        try {
//            // Analyze the report and get the summary
//            HtmlReportAnalyzer analyzer = new HtmlReportAnalyzer(reportPath);
//            summary = analyzer.analyzeReport();
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println("Failed to analyze the report.");
//        }
//
//
//        String to = "chukwudi.okoli@tmnxtsupport.com,Ramanjaneyulu@techmsupport.com";
//        String subject = "TestNG Test Suite Report";
//        String body = "Please find the attached test report: " + summary;
//
//
//        // Call the sendEmail method from EmailUtils
//        emailUtils.sendEmail(to, subject, body, extentManager.getReportPath());
//    }
//
//
//}
