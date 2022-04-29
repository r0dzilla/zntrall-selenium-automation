package browser;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
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
		
		if((myBrowser.equalsIgnoreCase("iPhone 6"))) {
			caps.setBrowserName("chrome");
			caps.setPlatform(Platform.WINDOWS);
			
			Map<String, String> deviceMobEmu = new HashMap<>();
			deviceMobEmu.put("deviceName", myBrowser);
			
			options.setExperimentalOption("mobileEmulation", deviceMobEmu);
			Dimension d = new Dimension(375,667);
			options.merge(caps);
			String nodeUrl = "http://localhost:4444/wd/hub";
			driver = new RemoteWebDriver(new URL(nodeUrl),options);
			driver.manage().window().setSize(d);
		}
		
		if((myBrowser.equalsIgnoreCase("iPhone 6 Plus"))) {
			caps.setBrowserName("chrome");
			caps.setPlatform(Platform.WINDOWS);
			
			Map<String, String> deviceMobEmu = new HashMap<>();
			deviceMobEmu.put("deviceName", myBrowser);
			
			options.setExperimentalOption("mobileEmulation", deviceMobEmu);
			Dimension d = new Dimension(414,736);
			options.merge(caps);
			String nodeUrl = "http://localhost:4444/wd/hub";
			driver = new RemoteWebDriver(new URL(nodeUrl),options);
			driver.manage().window().setSize(d);
		}
		
		if((myBrowser.equalsIgnoreCase("iPhone 7"))) {
			caps.setBrowserName("chrome");
			caps.setPlatform(Platform.WINDOWS);
			
			Map<String, String> deviceMobEmu = new HashMap<>();
			deviceMobEmu.put("deviceName", myBrowser);
			
			options.setExperimentalOption("mobileEmulation", deviceMobEmu);
			Dimension d = new Dimension(375,667);
			options.merge(caps);
			String nodeUrl = "http://localhost:4444/wd/hub";
			driver = new RemoteWebDriver(new URL(nodeUrl),options);
			driver.manage().window().setSize(d);
		}
		
		if((myBrowser.equalsIgnoreCase("iPhone 7 Plus"))) {
			caps.setBrowserName("chrome");
			caps.setPlatform(Platform.WINDOWS);
			
			Map<String, String> deviceMobEmu = new HashMap<>();
			deviceMobEmu.put("deviceName", myBrowser);
			
			options.setExperimentalOption("mobileEmulation", deviceMobEmu);
			Dimension d = new Dimension(414,736);
			options.merge(caps);
			String nodeUrl = "http://localhost:4444/wd/hub";
			driver = new RemoteWebDriver(new URL(nodeUrl),options);
			driver.manage().window().setSize(d);
		}
		
		if((myBrowser.equalsIgnoreCase("iPhone 8"))) {
			caps.setBrowserName("chrome");
			caps.setPlatform(Platform.WINDOWS);
			
			Map<String, String> deviceMobEmu = new HashMap<>();
			deviceMobEmu.put("deviceName", myBrowser);
			
			options.setExperimentalOption("mobileEmulation", deviceMobEmu);
			Dimension d = new Dimension(375,667);
			options.merge(caps);
			String nodeUrl = "http://localhost:4444/wd/hub";
			driver = new RemoteWebDriver(new URL(nodeUrl),options);
			driver.manage().window().setSize(d);
		}
		
		if((myBrowser.equalsIgnoreCase("iPhone 8 Plus"))) {
			caps.setBrowserName("chrome");
			caps.setPlatform(Platform.WINDOWS);
			
			Map<String, String> deviceMobEmu = new HashMap<>();
			deviceMobEmu.put("deviceName", myBrowser);
			
			options.setExperimentalOption("mobileEmulation", deviceMobEmu);
			Dimension d = new Dimension(414,736);
			options.merge(caps);
			String nodeUrl = "http://localhost:4444/wd/hub";
			driver = new RemoteWebDriver(new URL(nodeUrl),options);
			driver.manage().window().setSize(d);
		}
		
		if((myBrowser.equalsIgnoreCase("Surface Pro 7"))) {
			caps.setBrowserName("chrome");
			caps.setPlatform(Platform.WINDOWS);
			
			HashMap<String, Object> edgePrefs = new HashMap<String, Object>();
			edgePrefs.put("profile.default_content_settings.popups", 0);
			edgePrefs.put("profile.default_content_setting_values.notifications", 2);		
			edgePrefs.put("profile.default_content_setting_values.automatic_downloads" , 1);		
			edgePrefs.put("profile.content_settings.pattern_pairs.*,*.multiple-automatic-downloads",1);
			EdgeOptions egdeOptions = new EdgeOptions();
			egdeOptions.setExperimentalOption("prefs",edgePrefs);
			
			Dimension d = new Dimension(912,1368);
			options.merge(caps);
			String nodeUrl = "http://localhost:4444/wd/hub";
			driver = new RemoteWebDriver(new URL(nodeUrl),options);
			driver.manage().window().setSize(d);
		}

		return driver;
	}
	
	
}
