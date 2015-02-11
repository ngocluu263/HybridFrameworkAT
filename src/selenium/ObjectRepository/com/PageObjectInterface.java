package selenium.ObjectRepository.com;

//Import Java libs
import org.openqa.selenium.WebDriver;

//Interface class
public interface PageObjectInterface {
	
	public String visitURLTitle(WebDriver driver);
	public void visitURL(String data, WebDriver driver);
	public void LeftClick(String data, WebDriver driver);
}
