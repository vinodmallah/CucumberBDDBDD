package parallel;

import java.util.Properties;

import org.openqa.selenium.WebDriver;

import com.intsof.factory.DriverFactory;
import com.intsof.util.ConfigReader;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class MyHooks {
	
	private DriverFactory driverFactory;
	private WebDriver driver; 
	private ConfigReader configReader;
	Properties prop;
	
	@Before(order=0)
	public void loadProperties() {
		configReader = new ConfigReader();
		prop = configReader.initProperties();		
	}
	
	@Before(order=1)
	public void launchBrowser() {
		String browserName = prop.getProperty("browser");
		driverFactory = new DriverFactory();
		driver = driverFactory.init_driver(browserName);
		driver.get("");
	}
	
	@After(order = 1)
	public void closeBrowser() {
		driver.quit();
	}

}
