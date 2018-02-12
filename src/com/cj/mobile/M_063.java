package com.cj.mobile;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import java.net.URL;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.cj.util.SmartProperties;
import org.openqa.selenium.support.ui.Select;
public class M_063 {
	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();
	WebElement element = null;
	// boolean setupSuccess = true;
	private String ID_1 = null;
	private String PW_1 = null;
	private String M_URL = null;
	/**
	 * 
	 * @author 조성주 
	 * Date : 2017-06-19
	 * Subject : CJ Mall 운영  
	 * Name : M_063
	 * Scenario :  결제 취소
	 * Assertion : 결제취소 Text 체크
	 *   
	 */

	@Before
	public void setUp() throws Exception {

		//System.out.println("=====setUp start======");
		SmartProperties sp = SmartProperties.getInstance();
		ID_1 = sp.getProperty("ID_1");
		PW_1 = sp.getProperty("PW_1");
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
	public void m_063() throws Exception {
		
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
		
		driver.findElement(By.xpath("//*[@id='header']/div[2]/a[1]")).click();
		//좌측카테고리 버튼 클릭
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='gnb']/div[1]/a")).click();
		Thread.sleep(3000);
		System.out.println("로그인 텍스트 클릭");
		
		//로그인 클릭
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='id_input']")).clear();
		driver.findElement(By.xpath("//*[@id='id_input']")).sendKeys(ID_1);
		driver.findElement(By.xpath(".//*[@id='password_input']")).clear();
		driver.findElement(By.xpath(".//*[@id='password_input']")).sendKeys(PW_1);
		driver.findElement(By.xpath(".//*[@id='content']/div[1]/div[2]/fieldset/div[2]")).click();
		driver.findElement(By.xpath(".//*[@id='loginSubmit']")).click();
		System.out.println("로그인 성공");
		Thread.sleep(5000);
		System.out.println("팝업닫기");
		Thread.sleep(3000);
		//마이존 진입
		driver.findElement(By.xpath("//*[@id='go_myzone']")).click();
		System.out.println("마이존 진입");
		Thread.sleep(3000);
		//주문접수 진입
		driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/div/a")).click();
		System.out.println("주문/배송 전체보기 진입");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"_list_container\"]/div[1]/div/div/div[2]/div/button")).click();
		System.out.println("주문접수 진입");
		Thread.sleep(3000);
		new Select(driver.findElement(By.xpath("//*[@id=\"amount1\"]"))).selectByVisibleText("1");
		System.out.println("취소수량 1개 선택");
		Thread.sleep(3000);
		new Select(driver.findElement(By.xpath("//*[@id=\"claim_info_reason_0\"]/fieldset/ul/li[2]/div[2]/select"))).selectByVisibleText("상품 단순변심 취소");
		System.out.println("취소사유 선택");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"detail_reason1\"]")).sendKeys("1234");
		System.out.println("주문접수 진입");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"claim_content\"]/div[2]/div[2]/fieldset/ul/li[1]/div[2]/span[2]/label/span")).click();
		System.out.println("입금여부 아니오 클릭");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"claim_content\"]/div[5]/div/div[2]/button")).click();
		System.out.println("다음단계 클릭");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"claim_content\"]/div[6]/div/div[2]/button")).click();
		System.out.println("신청완료 클릭");
		Thread.sleep(10000);
		
		
		
		if ("주문취소가 완료되었습니다.".equals(driver.findElement(By.xpath(".//*[@id=\"claim_content\"]/div[4]/div[1]/p")).getText())) {
			Thread.sleep(3000);
			System.out.println("TC_63 PASS");
			assertTrue(true);
			return;
		} else {
			System.out.println("TC_63 FAIL");
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
