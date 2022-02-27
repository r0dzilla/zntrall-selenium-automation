package browser;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


public class OpenBrowser {
	

	public static WebDriver start(String myBrowser) throws MalformedURLException {
		
		ChromeOptions options = new ChromeOptions();
		WebDriver driver = null;
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
	
}
