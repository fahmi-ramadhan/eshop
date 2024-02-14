package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
class CreateProductFunctionalTest {
    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;
    private String baseUrl;

    @BeforeEach
    void setupTest() {
        baseUrl = String.format("%s:%d/product/list", testBaseUrl, serverPort);
    }

    void fillForm(ChromeDriver driver, String name, int quantity) {
        driver.findElement(By.id("nameInput")).clear();
        driver.findElement(By.id("nameInput")).sendKeys(name);
        driver.findElement(By.id("quantityInput")).clear();
        driver.findElement(By.id("quantityInput")).sendKeys(String.valueOf(quantity));
        driver.findElement(By.className("btn")).click();
    }

    @Test
    void createProduct_isSuccess(ChromeDriver driver) throws Exception {
        driver.get(baseUrl);

        WebElement createProductPageLink = driver.findElement(By.xpath("//a[text()='Create Product']"));
        createProductPageLink.click();

        fillForm(driver, "Sampo Cap Udin", 10);

        assertTrue(driver.findElement(By.xpath("//td[text()='Sampo Cap Udin']")).isDisplayed());
        assertTrue(driver.findElement(By.xpath("//td[text()='10']")).isDisplayed());
    }

    @Test
    void createAndEditProduct_isSuccess(ChromeDriver driver) throws Exception {
        driver.get(baseUrl);

        WebElement createProductPageLink = driver.findElement(By.xpath("//a[text()='Create Product']"));
        createProductPageLink.click();

        fillForm(driver, "Sampo Cap Kuda", 10);

        WebElement editButton = driver.findElement(By.xpath("//a/button[text()='Edit']"));
        editButton.click();

        fillForm(driver, "Sampo Cap Bambang", 25);

        assertTrue(driver.findElement(By.xpath("//td[text()='Sampo Cap Bambang']")).isDisplayed());
        assertTrue(driver.findElement(By.xpath("//td[text()='25']")).isDisplayed());
    }

    @Test
    void createAndDeleteProduct_isSuccess(ChromeDriver driver) throws Exception {
        driver.get(baseUrl);

        WebElement createProductPageLink = driver.findElement(By.xpath("//a[text()='Create Product']"));
        createProductPageLink.click();

        fillForm(driver, "Sampo Cap Ucok", 10);

        WebElement deleteButton = driver.findElement(By.xpath("//button[text()='Delete']"));
        deleteButton.click();

        boolean isProductDeleted = driver.findElements(By.xpath("//td[text()='Sampo Cap Ucok']")).isEmpty();
        assertTrue(isProductDeleted);
    }
}
