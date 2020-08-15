package test;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import main.Base;
import main.Utilities;
import pages.HomePage;

public class Module1Test extends Base {
	
		HomePage hp;
		Utilities ut;
		AppiumDriverLocalService  service;
		
		@BeforeTest
		public void startService() throws IOException {
			
			killAllNodes();
			service=startServer();
		}
		
		@AfterTest
		public void stopService() {
			service.stop();
		}
	
	@Test
	public void totalValidation() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		//emulator
		//real
		//valid locators: xpath,id,class name,accessability id,android ui automator
		
		
		AndroidDriver<AndroidElement> driver=capabilities("DemoApp");
		TouchAction t=new TouchAction(driver);
		
		//Initializing Page Objects
		hp=new HomePage(driver);
		ut=new Utilities(driver);
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//TC1
		
		
						
		hp.preference.click();
		//driver.findElementByXPath("//android.widget.TextView[@text='Preference']").click();
		driver.findElementByXPath("//android.widget.TextView[@text='3. Preference dependencies']").click();
		driver.findElementById("android:id/checkbox").click();
		driver.findElementByXPath("(//android.widget.RelativeLayout)[2]").click();
		driver.findElementByClassName("android.widget.EditText").sendKeys("Test");
		driver.hideKeyboard();
		driver.findElementsByClassName("android.widget.Button").get(1).click();
		((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.BACK));
		((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.BACK));
		
	
		
		//TC2
		hp.getViews().click();
		System.out.println(driver.findElementByAndroidUIAutomator("new UiSelector().clickable(true)").getSize());
		((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.BACK));
	
		
		//TC3-Gestures
		driver.findElementByAndroidUIAutomator("text(\"Views\")").click();
		WebElement ele1=driver.findElementByAndroidUIAutomator("text(\"Expandable Lists\")");
		t.tap(tapOptions().withElement(element(ele1))).perform();
		((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.BACK));
		((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.BACK));

		
		//Long Press
		driver.findElementByAndroidUIAutomator("text(\"Views\")").click();
		WebElement elee=driver.findElementByAndroidUIAutomator("text(\"Expandable Lists\")");
		
		
		t.tap(tapOptions().withElement(element(elee))).perform();
		
		driver.findElementByAndroidUIAutomator("text(\"1. Custom Adapter\")").click(); //1. Custom Adapter
		
		WebElement ele2=driver.findElementByAndroidUIAutomator("text(\"People Names\")");
		t.longPress(longPressOptions().withElement(element(ele2)).withDuration(ofSeconds(2))).release().perform();

		
	
		System.out.println(driver.findElementByXPath("//android.widget.TextView[@text='Sample menu']").isDisplayed());
		((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.BACK));
		((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.BACK));
		((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.BACK));
		((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.BACK));
		
		//Scroll
		
		driver.findElementByAndroidUIAutomator("text(\"Views\")").click();
		ut.scrollToText("WebView");
		//driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"WebView\"));");
		driver.findElementByAndroidUIAutomator("text(\"WebView\")").click();
		((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.BACK));
		Thread.sleep(1000);
		((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.BACK));
		
		
		//Swipe Dates
		driver.findElementByAndroidUIAutomator("text(\"Views\")").click();
		driver.findElementByXPath("//android.widget.TextView[@text='Date Widgets']").click();
		driver.findElementByAndroidUIAutomator("text(\"2. Inline\")").click();
		driver.findElementByXPath("//*[@content-desc='9']").click();
		WebElement first=driver.findElementByXPath("//*[@content-desc='15']");
		WebElement second=driver.findElementByXPath("//*[@content-desc='45']");
		t.longPress(longPressOptions().withElement(element(first)).withDuration(ofSeconds(2))).moveTo(element(second)).release().perform();
		((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.BACK));
		((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.BACK));
		((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.BACK));
		
		
		//Drag & Drop
		driver.findElementByAndroidUIAutomator("text(\"Views\")").click();
		driver.findElementByAndroidUIAutomator("text(\"Drag and Drop\")").click();
	
		WebElement source=driver.findElementsByClassName("android.view.View").get(0);
		WebElement destination=driver.findElementsByClassName("android.view.View").get(1);
		t.longPress(longPressOptions().withElement(element(source))).moveTo(element(destination)).release().perform();
		((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.BACK));
		((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.BACK));
		((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.BACK));
		

	}


}
