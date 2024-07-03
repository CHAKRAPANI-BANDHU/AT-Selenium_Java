package com.at.testcases;

import org.testng.annotations.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import com.at.utilities.Configuration;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class BaseClass {

    Configuration config = new Configuration();
    protected static WebDriver driver;
    protected static ExtentReports extent;
    protected Logger logger;
    private String reportName;

    @BeforeSuite
    public void setupSuite() {
        logger = Logger.getLogger("Assured_Techmatics");
        PropertyConfigurator.configure("Log4j.properties");
        
        // Initialize ExtentReports only once per suite
        String timestamp = new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss").format(new Date());
        reportName = "./Extent Reports/QA Test Report-" + timestamp + ".html";
        extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter(reportName);
        extent.attachReporter(spark);
        spark.config().setDocumentTitle("Assured_Techmatics Automation Report");
        spark.config().setReportName("QA Test Report");
        spark.config().setTheme(Theme.STANDARD);
        
        logger.info("ExtentReports initialized.");
    }

    @BeforeClass
    public void setup() {
        // Initialize WebDriver only once per test class
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", config.getChromepath());
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--window-size=2560x1600");

            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            
            logger.info("WebDriver initialized.");
        }
    }

    @AfterClass
    public void tearDown() {
        // Quit WebDriver after all tests in the class are done
        if (driver != null) {
            driver.quit();
            driver = null;
            logger.info("WebDriver quit.");
        }
    }

    @AfterSuite
	public void tearDownSuite() {
	    // Flush extent reports after all tests in the suite are done
	    if (extent != null) {
	        extent.flush();
	    }
	}

    public String getScreenshot(WebDriver driver, String screenshotName) throws IOException {
        String dateName = new SimpleDateFormat("dd-MM-yyyy_HH.mm.ss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destination = System.getProperty("user.dir") + "/Failed_Screenshots/" + screenshotName + dateName + ".png";
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);
        return destination;
    }
}
























//import java.io.File;
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.time.Duration;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.concurrent.ScheduledExecutorService;
//
//import org.apache.commons.io.FileUtils;
//import org.apache.log4j.Logger;
//import org.apache.log4j.PropertyConfigurator;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.testng.annotations.AfterClass;
//import org.testng.annotations.AfterSuite;
//import org.testng.annotations.AfterTest;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.BeforeSuite;
//import org.testng.annotations.BeforeTest;
//import org.testng.asserts.Assertion;
//import org.testng.asserts.SoftAssert;
//
//import com.at.utilities.Configuration;
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.reporter.ExtentSparkReporter;
//import com.aventstack.extentreports.reporter.configuration.Theme;
//
//public class BaseClass {
//	Configuration config = new Configuration();
//	public Assertion hardAssert = new Assertion();
//	public SoftAssert softAssert = new SoftAssert();
//
//	String timestamp = new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss").format(new Date());
//	String reportName = "./Extent Reports/QA Test Report-" + timestamp + ".html";
//	ExtentReports extent = new ExtentReports();
//	ExtentSparkReporter spark = new ExtentSparkReporter(reportName);
//
//	public static WebDriver driver;
//	public static Logger logger;
////	private static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss");
//	ArrayList<String> imageFiles = new ArrayList<String>();
//	private static ScheduledExecutorService executor = null;
//
//	@BeforeClass
//	public void setup() {
//		System.setProperty("webdriver.chrome.driver", config.getChromepath());
//		ChromeOptions options = new ChromeOptions();
//		// To run the script in Headless mode
////		options.addArguments("--headless");
////		options.addArguments("--window-size=1366x768");
////		options.addArguments("--window-size=2560×1600");
////		Dimension size = new Dimension(1366, 768);
////		driver.manage().window().setSize(size);
//		driver = new ChromeDriver(options);
//		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//		logger = Logger.getLogger("Assured_Techmatics");
//		PropertyConfigurator.configure("Log4j.properties");
//	}
//
////      Screenshots
////		Runnable takeScreenshot = new Runnable() {
////			public void run() {
////				try {
////					captureScreenShot();
////				} catch (IOException e) {
////					e.printStackTrace();
////				}
////			}
////
////			public void captureScreenShot() throws IOException {
////				File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
////				String pngFile = "./Screenshots/Screenshot_" + sdf.format(new Date()) + ".png";
////				imageFiles.add(pngFile);
////				FileUtils.copyFile(scrFile, new File(pngFile));
////			}
////		};
////		if (executor == null) {
////			executor = Executors.newScheduledThreadPool(1);
////			executor.scheduleAtFixedRate(takeScreenshot, 0, 500, TimeUnit.MILLISECONDS);
////		}
////	}
//
//
//	 public String getScreenshot(WebDriver driver, String screenshotName) throws IOException {
//		String dateName = new SimpleDateFormat("dd-MM-yyyy_HH.mm.ss").format(new Date());
//		TakesScreenshot ts = (TakesScreenshot) driver;
//		File source = ts.getScreenshotAs(OutputType.FILE);
//		String destination = System.getProperty("user.dir") + "/Failed_Screenshots/" + screenshotName + dateName + ".png";
//		File finalDestination = new File(destination);
//		FileUtils.copyFile(source, finalDestination);
//		return destination;
//	}
//
//	@AfterClass
//	public void tearDown() {
//		if (executor != null) {
//			executor.shutdown();
//		}
//		driver.quit();
//	}
//
//	@BeforeTest
//	public void beforeTest() {
//		extent = new ExtentReports();
//		extent.setSystemInfo("Environment", "QA");
//		extent.setSystemInfo("URL", "https://qa1-cloud.apolloeld.com/userlogin");
//		extent.setSystemInfo("Reporter", "Chakrapani Bandhu");
//		extent.setSystemInfo("Designation", "QA Engineer");
//		spark.config().setDocumentTitle("Assured_Techmatics Automation Report");
//		spark.config().setReportName("QA Test Report");
//		spark.config().setTheme(Theme.STANDARD);
//		extent.attachReporter(spark);
//	}
//
//
//	@AfterTest
//	public void afterTest() {
//		extent.flush();
//	}
//
//
//
//    @BeforeSuite
//    public void setUpDriver() {
//        // Initialize the WebDriver instance (e.g., ChromeDriver)
//        System.setProperty("webdriver.chrome.driver", config.getChromepath());
//        driver = new ChromeDriver();
//    }
//
//	    @AfterSuite
//	    public void tearDownDriver() {
//	        // Close the WebDriver instance
//	        if (driver != null) {
//	            driver.quit();
//	        }
//	    }
//	}
//
//
