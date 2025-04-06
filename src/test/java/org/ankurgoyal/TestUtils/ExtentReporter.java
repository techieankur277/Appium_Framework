package org.ankurgoyal.TestUtils;

import java.io.File;



import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporter {
	
static ExtentReports extent;
	
	public static ExtentReports config() {
		//ExtentReport,  ExtentSparkReporter
		String reportDir = System.getProperty("user.dir") + "/reports";
	    String filePath = reportDir + "/index.html";

	    // Ensure the directory exists
//	    File directory = new File(reportDir);
//	    System.out.println("not present directory: "+ directory);
//	    if (!directory.exists()) {
//	        directory.mkdirs();  // Creates the directory if it doesn't exist
//	    }
	    try {
	        File directory = new File(reportDir);
	        if (!directory.exists()) {
	            boolean created = directory.mkdirs();
	            System.out.println("Reports directory created: " + created);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
//	    System.out.println("directory: "+ directory);
	    
		ExtentSparkReporter reporter = new ExtentSparkReporter(filePath);
		reporter.config().setReportName("Web Automation Report");
		reporter.config().setDocumentTitle("Test Results");
		extent = new ExtentReports();  // this is a helper class to maintain the test reports
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Ankur Goyal");
		return extent;
	}

}
