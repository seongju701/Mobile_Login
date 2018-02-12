package com.cj.mobile;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import java.net.URL;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.cj.util.SmartProperties;
public class M_078 {
	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();
	WebElement element = null;
	private String M_URL = null;
	/**
	 * 
	 * @author 조성주 
	 * Date : 2017-10-30
	 * Subject : CJ Mall 운영  
	 * Name : M_078
	 * Scenario : 카테고리 > 대분류 선택 > 상품상세
	 * Assertion : 상품 이미지 확인
	 *   
	 */

	@Before
	public void setUp() throws Exception {

		//System.out.println("=====setUp start======");
		SmartProperties sp = SmartProperties.getInstance();
		M_URL = sp.getProperty("M_URL");
		try {
			DesiredCapabilities caps = new DesiredCapabilities();
			caps = DesiredCapabilities.android();
			// Devices Name 은 아무거나 입력해도 상관없음
			caps.setCapability(MobileCapabilityType.DEVICE_NAME, "1a66dc8f");
			caps.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
			caps.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
			URL appiumUrl = new URL("http://127.0.0.1:4723/wd/hub");
			System.out.println("Start driver.");
			driver = new AndroidDriver<WebElement>(appiumUrl, caps);

		} catch (Exception e) {
			try {
				Thread.sleep(5000);
				DesiredCapabilities caps = new DesiredCapabilities();
				caps = DesiredCapabilities.android();

				// device name은 큰 의미없음. LG폰도 이 옵션으로 수행됨
				caps.setCapability(MobileCapabilityType.DEVICE_NAME, "LGF460S859d639d");
				caps.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
				caps.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");

				URL appiumUrl = new URL("http://127.0.0.1:4723/wd/hub");

				System.out.println("Start driver.");
				driver = new AndroidDriver<WebElement>(appiumUrl, caps);

			} catch (Exception e1) {
				{
					try {
						Thread.sleep(5000);
						DesiredCapabilities caps = new DesiredCapabilities();
						caps = DesiredCapabilities.android();

						// device name은 큰 의미없음. LG폰도 이 옵션으로 수행됨
						caps.setCapability(MobileCapabilityType.DEVICE_NAME, "LGF460S859d639d");
						caps.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
						caps.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");

						URL appiumUrl = new URL("http://127.0.0.1:4723/wd/hub");

						System.out.println("Start driver.");
						driver = new AndroidDriver<WebElement>(appiumUrl, caps);

					} catch (Exception e2) {
						System.out.println("Session Creation failed.");
						e.printStackTrace();
						assertTrue(false);
						return;
					}
				}
			}
		}
	}

	@Test
	public void m_078() throws Exception {
		
		driver.get(M_URL);
		
		boolean isExist = false;
		//팝업닫기
		isExist = existElement(driver, By.xpath("//*[@id='notToday']"), "오늘 하루 보지 않기");
		if (isExist) {
			driver.findElement(By.xpath("//*[@id=\"notToday\"]/label")).click();
			System.out.println("닫기버튼 클릭");
		} else {
			System.out.println("팝업 없음");
		}
		System.out.println("팝업닫기");
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/a[1]/span")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"_leftViewContentContainer\"]/div/div/div[1]/ul/li[1]/a/span")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"dpCateIdG00011\"]/div[2]")).click();
		Thread.sleep(3000);
		
		WebElement searchBtn = driver.findElement(By.xpath("//*[@id=\"prd_lst0\"]/li[3]/a/div[1]/div"));
		Actions action = new Actions(driver);
		action.moveToElement(searchBtn).perform();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"prd_lst0\"]/li[1]/a/div[1]/div")).click();
		Thread.sleep(3000);
		
		boolean isExist1 = false;
		String btn = null;
		String btnTag = null;
		//비디오,이미지 확인
		isExist1 = existElement(driver, By.xpath("//*[@id=\"my_video_1\"]"), "비디오");
		if (isExist1) {
			btn = "//*[@id=\"my_video_1\"]";
			btnTag = "div"; 
			System.out.println("비디오용"+btnTag);
		} else {
			btn = "//*[@id=\"_imageFlicking\"]/ul/li[1]/div/div/img";
			btnTag = "img";
			System.out.println("이미지용"+btnTag);
		}
		System.out.println("닫기");
		Thread.sleep(3000);
		
		System.out.println(driver.findElement(By.xpath(btn)).getTagName());
		
		
		// text check
		if (btnTag.equals(driver.findElement(By.xpath(btn)).getTagName())) {
			System.out.println("TC_078 PASS");
			assertTrue(true);
			return;
		} else {
			System.out.println("TC_078 Fail");
			assertTrue(false);
		}
		
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}
	public boolean existElement(WebDriver wd, By by, String meaning) {
		WebDriverWait wait = new WebDriverWait(wd, 2);
		// wait.ignoring(NoSuchElementException.class);

		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(by));

		} catch (TimeoutException e) {

			System.out.println("[" + meaning + "] WebElement does not Exist. time out ");
			return false;
		}
		System.out.println("[" + meaning + "] WebElement Exist.");
		return true;
	}
}
