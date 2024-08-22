import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class App {

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\Internship\\demo\\src\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Test case 1: Successful login
        driver.get("https://www.saucedemo.com/");

        driver.findElement(By.id("user-name")).sendKeys("standard_user");

        Thread.sleep(2000);

        driver.findElement(By.id("password")).sendKeys("secret_sauce");

        Thread.sleep(2000);
        
        driver.findElement(By.id("login-button")).click();

        String expectedTitle = "Swag Labs";
        String actualTitle = driver.getTitle();
        if (expectedTitle.equals(actualTitle)) {
            System.out.println("Successful login: Navigated to home page");
        } else {
            System.out.println("Failed to navigate to home page after successful login");
        }

        driver.findElement(By.cssSelector(".bm-burger-button")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("logout_sidebar_link")).click();

        if (expectedTitle.equals(actualTitle)) {
            System.out.println("Successful logout: Navigated to login page");
        } else {
            System.out.println("Failed to navigate to login page after logout");
        }

        // Test case 2: Locked out user
        driver.findElement(By.id("user-name")).sendKeys("locked_out_user");
        Thread.sleep(2000);
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        Thread.sleep(2000);
        driver.findElement(By.id("login-button")).click();
        Thread.sleep(5000);

        WebElement errorMessage = driver.findElement(By.cssSelector(".error-message-container.error h3"));
        String actualErrorMessage = errorMessage.getText();
        
        if ("Epic sadface: Sorry, this user has been locked out.".equals(actualErrorMessage)) {
            System.out.println("Correct error message displayed for locked user");
        } else {
            System.out.println("Incorrect error message displayed for locked user");
        }

        driver.quit();
    }
}