package test;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class OpenBrowser {

	public static RemoteWebDriver driver = null;
	@Parameters("myBrowser")
	
	@BeforeTest
	public static void setup(String myBrowser) throws MalformedURLException {
		
		if(myBrowser.equalsIgnoreCase("chrome")){
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setBrowserName("chrome");
			caps.setPlatform(Platform.WINDOWS);
			ChromeOptions options = new ChromeOptions();
			options.merge(caps);
			String nodeUrl = "http://192.168.31.17:4444/wd/hub";
			driver = new RemoteWebDriver(new URL(nodeUrl),options);
			
		}

		if(myBrowser.equalsIgnoreCase("firefox")) {
			//System.setProperty("webdriver.gecko.driver","C:\\Users\\tahni\\eclipse-workspace\\geckodriver.exe");
			DesiredCapabilities caps = new DesiredCapabilities();
			//driver = new FirefoxDriver();
			caps.setPlatform(Platform.WINDOWS);
			FirefoxOptions options = new FirefoxOptions();
			options.merge(caps);
			String nodeUrl = "http://192.168.31.17:4444/wd/hub";
			driver = new RemoteWebDriver(new URL(nodeUrl),options);
		
		}

	}

	}

