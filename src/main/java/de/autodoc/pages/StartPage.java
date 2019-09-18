package de.autodoc.pages;

import de.autodoc.core.BasePage;
import de.autodoc.pages.objects.StartPageObjects;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import static de.autodoc.util.InteractionMethods.waitForPresence;


public class StartPage extends BasePage {

    private StartPageObjects startPageObjects;

    public StartPage(AndroidDriver driver) {
        super(driver);
        startPageObjects = new StartPageObjects();
        PageFactory.initElements(new AppiumFieldDecorator(driver), startPageObjects);
    }

    public MainCataloguePage clickFrance() {
        waitForPresence(this.driver, startPageObjects.Countries);
        startPageObjects.CountryFrance.click();
        return new MainCataloguePage(driver);
    }
}
