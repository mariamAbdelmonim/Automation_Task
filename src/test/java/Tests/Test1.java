package Tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.time.Duration;

public class Test1 {

    @Test
    public void testcase1() throws InterruptedException, AWTException {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement textInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("my-text-id")));
        textInput.click();
        textInput.sendKeys("marioooom");

        WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/main/div/form/div/div[1]/label[2]/input")));
        passwordField.click();
        passwordField.sendKeys("12345678");

        WebElement textArea = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/main/div/form/div/div[1]/label[3]/textarea")));
        textArea.click();
        textArea.sendKeys("i'm gonna be an SDET");

        WebElement dropdownElement = wait.until(ExpectedConditions.elementToBeClickable(By.name("my-select")));
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByVisibleText("Two");

        WebElement colorPickerElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='color']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '#6543FF';", colorPickerElement);
        colorPickerElement.click();
        Robot robot = new Robot();
        wait.until(ExpectedConditions.elementToBeClickable(colorPickerElement));
        robot.mouseMove(700, 400);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

        wait.until(ExpectedConditions.attributeToBeNotEmpty(colorPickerElement, "value"));
        WebElement dateField = wait.until(ExpectedConditions.elementToBeClickable(By.name("my-date")));
        dateField.click();
        String dateToEnter = "01/24/2025";
        for (char c : dateToEnter.toCharArray()) {
            if (c == '/') {
                robot.keyPress(KeyEvent.VK_SLASH);
                robot.keyRelease(KeyEvent.VK_SLASH);
            } else {
                robot.keyPress(KeyEvent.getExtendedKeyCodeForChar(c));
                robot.keyRelease(KeyEvent.getExtendedKeyCodeForChar(c));
            }
            Thread.sleep(200);
        }
        wait.until(ExpectedConditions.attributeToBe(dateField, "value", "01/24/2025"));

        WebElement uploadButton = wait.until(ExpectedConditions.elementToBeClickable(By.name("my-file")));
        uploadButton.sendKeys("G:\\p1.png");

        wait.until(ExpectedConditions.attributeToBeNotEmpty(uploadButton, "value"));
        WebElement rangeInput = wait.until(ExpectedConditions.elementToBeClickable(By.name("my-range")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '9';", rangeInput);
        wait.until(ExpectedConditions.attributeToBe(rangeInput, "value", "9"));

        WebElement checkBox = wait.until(ExpectedConditions.elementToBeClickable(By.id("my-check-2")));
        checkBox.click();

        WebElement radioButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("my-radio-2")));
        radioButton.click();

        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']")));
        submitButton.click();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("message"), "Received!"));
        System.out.println("Current URL: " + driver.getCurrentUrl());

        driver.quit();
    }
}
