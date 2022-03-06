package browser;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


public class OpenBrowser {
	
	static WebDriver driver = null;
	public static WebDriver start(String myBrowser) throws MalformedURLException {
		
		ChromeOptions options = new ChromeOptions();
		
		DesiredCapabilities caps = new DesiredCapabilities();
		
		if((myBrowser.equalsIgnoreCase("chrome"))){
			caps.setBrowserName(myBrowser);

			options.addArguments("--disable-dev-shm-usage");
			caps.setPlatform(Platform.LINUX);
			options.merge(caps);
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),options);
		}
		
		
		if((myBrowser.equalsIgnoreCase("firefox"))){
			caps.setBrowserName(myBrowser);
			caps.setPlatform(Platform.LINUX);
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),caps);
		}
		
		if((myBrowser.equalsIgnoreCase("MicrosoftEdge"))){
			caps.setBrowserName(myBrowser);
			caps.setPlatform(Platform.LINUX);
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),caps);
		}

		return driver;
	}
	
//	public static WebDriver emuStart(String deviceName, int h, int w) throws MalformedURLException {
//		
//		DesiredCapabilities caps = new DesiredCapabilities();
//		caps.setBrowserName("chrome");
//		caps.setPlatform(Platform.WINDOWS);
//		
//		Map<String, String> deviceMobEmu = new HashMap<>();
//		deviceMobEmu.put("deviceName", deviceName);
//		
//		ChromeOptions options = new ChromeOptions();
//		options.setExperimentalOption("mobileEmulation", deviceMobEmu);
//		Dimension d = new Dimension(w,h);
//		options.merge(caps);
//		String nodeUrl = "http://192.168.31.17:4444/wd/hub";
//		driver = new RemoteWebDriver(new URL(nodeUrl),options);
//		driver.manage().window().setSize(d);
//		return driver;
//	}
	
}
