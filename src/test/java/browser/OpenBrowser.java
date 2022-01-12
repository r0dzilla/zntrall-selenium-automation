package browser;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class OpenBrowser {

	public WebDriver start(String myBrowser) throws MalformedURLException {
		
		DesiredCapabilities caps = new DesiredCapabilities();
		
		if((myBrowser.equalsIgnoreCase("chrome"))){
			caps.setBrowserName(myBrowser);
			caps.setPlatform(Platform.LINUX);
		}
		
		
		if((myBrowser.equalsIgnoreCase("firefox"))){
			caps.setBrowserName(myBrowser);
			caps.setPlatform(Platform.LINUX);
		}
		

		WebDriver driver = new RemoteWebDriver(new URL("http://192.168.31.17:4444"),caps);
		
		return driver;
	}
}
