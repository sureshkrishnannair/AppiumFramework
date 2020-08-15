package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;


public class Base {
	
	public static AppiumDriverLocalService service;
	
	public AppiumDriverLocalService startServer() {
		
		checkIfServerIsRunning(4723);
		service=AppiumDriverLocalService.buildDefaultService();
		service.start();
		return service;
	}
	
	
	public void killAllNodes() throws IOException {
		
		try {
			Runtime.getRuntime().exec("taskkill /f /im node.exe");
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	

	public static boolean checkIfServerIsRunning(int port) {
		
		boolean isServerRunning=false;
		ServerSocket serverSocket;
		
		try {
			serverSocket=new ServerSocket(port);
			serverSocket.close();
		}catch (IOException e) {
			isServerRunning=true;
		}finally {
			serverSocket=null;
		}
		
		return isServerRunning;
		
	}
	
	
	
	
	
	public static void startEmulator() throws IOException, InterruptedException {
		
		Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\startEmulator.bat");
		Thread.sleep(10000);
	}
	
	public static AndroidDriver<AndroidElement> capabilities(String appName) throws IOException, InterruptedException {
		
		
		
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\main\\global.properties");
		Properties prop=new Properties();
		prop.load(fis);
		
		

		File appDir=new File("src/main/java");
		File fs=new File(appDir,(String) prop.get(appName));
		AndroidDriver<AndroidElement> driver;
		DesiredCapabilities cap=new DesiredCapabilities();
		
		if(prop.get("device").equals("emulator")) {
			
			startEmulator();
			cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel");
			cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
			cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 15);
			
		}else if(prop.get("device").equals("real")) {
			cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Device");
		}
		
		cap.setCapability(MobileCapabilityType.APP, fs.getAbsolutePath());
		driver=new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"),cap);
		return driver;

}
	
	
}
