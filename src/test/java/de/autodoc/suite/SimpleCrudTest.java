package de.autodoc.suite;

import de.autodoc.core.BasePage;
import de.autodoc.pages.*;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static de.autodoc.util.InteractionMethods.pageContainsText;

public class SimpleCrudTest {
    private AndroidDriver driver;
    private static final String FIRST_CHOSEN_CAR = "BMW";
    private static final String FIRST_CHOSEN_CAR_SERIES = "1 Hatchback (E87) (02.2003 - 01.2013)";
    private static final String FIRST_CHOSEN_CAR_ENGINE = "116d 2.0 (66 KW / 90 PS) (01.2011 - 12.2011)";
    private static final String SECOND_TEST_CHOSEN_CAR = "RENAULT";
    private static final String SECOND_TEST_CHOSEN_CAR_SERIES = "4 Estate (04.1962 - 12.1993)";
    private static final String SECOND_TEST_CHOSEN_CAR_ENGINE = "0.7 (15 KW / 20 PS) (04.1962 - 04.1971)";
    private static final String EDITED_CHOSEN_CAR = "CITROЁN";
    private static final String EDITED_CHOSEN_CAR_SERIES = "ACADIANE (08.1978 - 10.1988)";
    private static final String EDITED_CHOSEN_CAR_ENGINE = "6 (23 KW / 31 PS) (08.1978 - 10.1988)";
    private MainCataloguePage mainCataloguePage;
    private VehiclesCataloguePage vehiclesCataloguePage;


    @BeforeClass
    public void setUp() {
        this.driver = new BasePage(driver).build();
        StartPage startPage = new StartPage(driver);
        mainCataloguePage = startPage.clickFrance();
    }

    @Test
    public void createCar1() {
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(mainCataloguePage.catalogueTextIsPresent());

        CarMenuPage carMenuPage = mainCataloguePage.clickAddCarButton();

        vehiclesCataloguePage = carMenuPage.clickCarMaker();

        vehiclesCataloguePage.fillSearchField(FIRST_CHOSEN_CAR);
        vehiclesCataloguePage.clickOnCarDataFields(FIRST_CHOSEN_CAR);
        vehiclesCataloguePage.clickOnCarDataFields(FIRST_CHOSEN_CAR_SERIES);

        mainCataloguePage = vehiclesCataloguePage.addEngine(FIRST_CHOSEN_CAR_ENGINE);

        softAssert.assertTrue(pageContainsText(driver, FIRST_CHOSEN_CAR));
        softAssert.assertTrue(pageContainsText(driver, FIRST_CHOSEN_CAR_SERIES));
        softAssert.assertTrue(pageContainsText(driver, FIRST_CHOSEN_CAR_ENGINE));

        softAssert.assertAll();
    }

    @Test(dependsOnMethods = {"createCar1"}, priority = 1)
    public void editCar() {
        SoftAssert softAssert = new SoftAssert();

        mainCataloguePage.clickSwipeableTopView();
        mainCataloguePage.swipeTopViewLeft();

        EditCarPage editCarPage = mainCataloguePage.clickEditButton();
        softAssert.assertEquals(editCarPage.getFirstChosenCarText(), FIRST_CHOSEN_CAR);
        softAssert.assertEquals(editCarPage.getFirstChosenCarModelText(), FIRST_CHOSEN_CAR_SERIES);
        softAssert.assertEquals(editCarPage.getFirstChosenCarEngineText(), FIRST_CHOSEN_CAR_ENGINE);

        vehiclesCataloguePage = editCarPage.clickOnEditCarField();
        vehiclesCataloguePage.clickOnCarDataFields(EDITED_CHOSEN_CAR);
        vehiclesCataloguePage.clickOnCarDataFields(EDITED_CHOSEN_CAR_SERIES);

        editCarPage = vehiclesCataloguePage.editEngine(EDITED_CHOSEN_CAR_ENGINE);

        softAssert.assertEquals(editCarPage.getFirstChosenCarText(), EDITED_CHOSEN_CAR);
        softAssert.assertEquals(editCarPage.getFirstChosenCarModelText(), EDITED_CHOSEN_CAR_SERIES);
        softAssert.assertEquals(editCarPage.getFirstChosenCarEngineText(), EDITED_CHOSEN_CAR_ENGINE);

        mainCataloguePage = editCarPage.clickSaveChanges();

        softAssert.assertTrue(pageContainsText(driver, EDITED_CHOSEN_CAR));
        softAssert.assertTrue(pageContainsText(driver, EDITED_CHOSEN_CAR_SERIES));
        softAssert.assertTrue(pageContainsText(driver, EDITED_CHOSEN_CAR_ENGINE));

        softAssert.assertAll();
    }

    @Test(dependsOnMethods = {"editCar"})
    public void deleteCar1() {
        SoftAssert softAssert = new SoftAssert();

        mainCataloguePage.clickSwipeableTopView();
        mainCataloguePage.swipeTopViewLeft();

        mainCataloguePage.clickDeleteButton();

        softAssert.assertTrue(mainCataloguePage.addCarButtonIsPresent());

        softAssert.assertAll();
    }

    @Test(priority = 2)
    public void createCar2() {
        SoftAssert softAssert = new SoftAssert();

        CarMenuPage carMenuPage = mainCataloguePage.clickAddCarButton();

        vehiclesCataloguePage = carMenuPage.clickCarMaker();

        vehiclesCataloguePage.clickOnCarDataFields(SECOND_TEST_CHOSEN_CAR);
        vehiclesCataloguePage.clickOnCarDataFields(SECOND_TEST_CHOSEN_CAR_SERIES);
        mainCataloguePage = vehiclesCataloguePage.addEngine(SECOND_TEST_CHOSEN_CAR_ENGINE);

        softAssert.assertTrue(pageContainsText(driver, SECOND_TEST_CHOSEN_CAR));
        softAssert.assertTrue(pageContainsText(driver, SECOND_TEST_CHOSEN_CAR_SERIES));
        softAssert.assertTrue(pageContainsText(driver, SECOND_TEST_CHOSEN_CAR_ENGINE));

        softAssert.assertAll();
    }


    @Test(dependsOnMethods = {"createCar2"})
    public void deleteCar2() {
        SoftAssert softAssert = new SoftAssert();

        mainCataloguePage.clickSwipeableTopView();
        mainCataloguePage.swipeTopViewLeft();

        EditCarPage editCarPage = mainCataloguePage.clickEditButton();
        mainCataloguePage = editCarPage.clickDeleteButton();

        softAssert.assertTrue(mainCataloguePage.addCarButtonIsPresent());

        softAssert.assertAll();
    }


    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
