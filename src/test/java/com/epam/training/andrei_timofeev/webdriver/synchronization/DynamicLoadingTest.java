package com.epam.training.andrei_timofeev.webdriver.synchronization;

import java.time.Duration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class DynamicLoadingTest {

    private static final String URL = "https://the-internet.herokuapp.com/dynamic_loading/1";

    private static final By START_BUTTON = By.cssSelector("#start button");
    private static final By LOADING_BAR = By.id("loading");
    private static final By RESULT_TEXT = By.cssSelector("#finish h4");

    private  WebDriver driver;
    private  WebDriverWait wait;

    @BeforeEach
    void setUP() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        driver.get(URL);
    }

    @Test
    void helloWorldAppearsAfterLoadingFinishes() {
        wait.until(ExpectedConditions.elementToBeClickable(START_BUTTON)).click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(LOADING_BAR));

        WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(RESULT_TEXT));

        assertEquals("Hello World!", result.getText().trim());
    }

    @AfterEach
    void tearDown(){
        if (driver != null) {
            driver.quit();
        }
    }
}

