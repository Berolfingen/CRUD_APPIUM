package de.autodoc.pages;

import de.autodoc.core.BasePage;
import de.autodoc.pages.objects.MainCataloguePageObjects;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import static de.autodoc.util.InteractionMethods.*;

public class MainCataloguePage extends BasePage {

    private MainCataloguePageObjects mainCataloguePageObjects;
    private static final int LEFT_SHIFT_PERCENTAGE = 90;

    MainCataloguePage(AndroidDriver driver) {
        super(driver);
        mainCataloguePageObjects = new MainCataloguePageObjects();
        PageFactory.initElements(new AppiumFieldDecorator(driver), mainCataloguePageObjects);
    }

    public boolean catalogueTextIsPresent() {
        waitForPresence(this.driver, mainCataloguePageObjects.Footer);
        return mainCataloguePageObjects.CatalogueTextView.isDisplayed();
    }

    public CarMenuPage clickAddCarButton() {
        waitForPresence(this.driver, mainCataloguePageObjects.CarsList);
        mainCataloguePageObjects.AddCarButton.click();
        return new CarMenuPage(driver);
    }

    public boolean addCarButtonIsPresent() {
        return elementIsPresent(mainCataloguePageObjects.AddCarButton);
    }

    public void clickSwipeableTopView() {
        waitForPresence(this.driver, mainCataloguePageObjects.CategoriesView);
        mainCataloguePageObjects.SwipeableTopCarInfo.click();
    }

    public void swipeTopViewLeft() {
        scrollElementLeftByNPercents(this.driver, mainCataloguePageObjects.SwipeableTopCarInfo, LEFT_SHIFT_PERCENTAGE);
    }

    public EditCarPage clickEditButton() {
        clickSimulation(this.driver, mainCataloguePageObjects.EditButton);
        //mainCataloguePageObjects.EditButton.click();
        return new EditCarPage(driver);
    }

    public void clickDeleteButton() {
        clickSimulation(this.driver, mainCataloguePageObjects.DeleteButton);
        //mainCataloguePageObjects.DeleteButton.click();
        waitForPresence(this.driver, mainCataloguePageObjects.AddCarButton);
    }
}
