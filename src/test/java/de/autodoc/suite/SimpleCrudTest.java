package de.autodoc.suite;

import de.autodoc.core.BasePage;
import de.autodoc.pages.*;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.connection.ConnectionStateBuilder;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.lang.reflect.Method;

public class SimpleCrudTest {

    private static Logger logger = Logger.getLogger(SimpleCrudTest.class);
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
        SoftAssert softAssert = new SoftAssert();
        logger.info("Starting setUp");
        this.driver = new BasePage(driver).build();
        logger.info("Turning on Internet");
        this.driver.setConnection(new ConnectionStateBuilder().withWiFiEnabled().withDataEnabled().build());

        logger.info("Checking Internet connection");
        softAssert.assertTrue(driver.getConnection().isDataEnabled());
        softAssert.assertTrue(driver.getConnection().isWiFiEnabled());
        StartPage startPage = new StartPage(driver);
        mainCataloguePage = startPage.clickFrance();

        softAssert.assertAll();
    }

    @BeforeMethod
    public void handleTestMethodName(Method method) {
        String testName = method.getName();
        logger.info("Test " + testName + " starts...");
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

        logger.info("Check that chosen car features are on the main page.");
        String pageSource = driver.getPageSource();

        softAssert.assertTrue(pageSource.contains(FIRST_CHOSEN_CAR));
        softAssert.assertTrue(pageSource.contains(FIRST_CHOSEN_CAR_SERIES));
        softAssert.assertTrue(pageSource.contains(FIRST_CHOSEN_CAR_ENGINE));

        softAssert.assertAll();
    }

    @Test(dependsOnMethods = {"createCar1"}, priority = 1)
    public void editCar() {
        SoftAssert softAssert = new SoftAssert();

        mainCataloguePage.clickSwipeableTopView();
        mainCataloguePage.swipeTopViewLeft();

        EditCarPage editCarPage = mainCataloguePage.clickEditButton();
        logger.info("Check that previously chosen car features same as in the Edit View.");
        softAssert.assertEquals(editCarPage.getFirstChosenCarText(), FIRST_CHOSEN_CAR);
        softAssert.assertEquals(editCarPage.getFirstChosenCarModelText(), FIRST_CHOSEN_CAR_SERIES);
        softAssert.assertEquals(editCarPage.getFirstChosenCarEngineText(), FIRST_CHOSEN_CAR_ENGINE);

        vehiclesCataloguePage = editCarPage.clickOnEditCarField();
        vehiclesCataloguePage.clickOnCarDataFields(EDITED_CHOSEN_CAR);
        vehiclesCataloguePage.clickOnCarDataFields(EDITED_CHOSEN_CAR_SERIES);

        editCarPage = vehiclesCataloguePage.editEngine(EDITED_CHOSEN_CAR_ENGINE);

        logger.info("Check that newly selected car features displayed correctly in the Edit View.");
        softAssert.assertEquals(editCarPage.getFirstChosenCarText(), EDITED_CHOSEN_CAR);
        softAssert.assertEquals(editCarPage.getFirstChosenCarModelText(), EDITED_CHOSEN_CAR_SERIES);
        softAssert.assertEquals(editCarPage.getFirstChosenCarEngineText(), EDITED_CHOSEN_CAR_ENGINE);

        mainCataloguePage = editCarPage.clickSaveChanges();

        logger.info("Check that changed car features are on the main page.");
        String pageSource = driver.getPageSource();

        softAssert.assertTrue(pageSource.contains(EDITED_CHOSEN_CAR));
        softAssert.assertTrue(pageSource.contains(EDITED_CHOSEN_CAR_SERIES));
        softAssert.assertTrue(pageSource.contains(EDITED_CHOSEN_CAR_ENGINE));

        softAssert.assertAll();
    }

    @Test(dependsOnMethods = {"editCar"})
    public void deleteCar1() {
        SoftAssert softAssert = new SoftAssert();

        mainCataloguePage.clickSwipeableTopView();
        mainCataloguePage.swipeTopViewLeft();

        mainCataloguePage.clickDeleteButton();

        logger.info("Check that button meaning we haven't chosen a single car yet is present");
        softAssert.assertTrue(mainCataloguePage.addCarButtonIsPresent());

        logger.info("Check that previously chosen car features are not present on the main page.");
        String pageSource = driver.getPageSource();

        softAssert.assertFalse(pageSource.contains(EDITED_CHOSEN_CAR));
        softAssert.assertFalse(pageSource.contains(EDITED_CHOSEN_CAR_SERIES));
        softAssert.assertFalse(pageSource.contains(EDITED_CHOSEN_CAR_ENGINE));

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

        logger.info("Check that chosen car features are on the main page.");
        String pageSource = driver.getPageSource();
        softAssert.assertTrue(pageSource.contains(SECOND_TEST_CHOSEN_CAR));
        softAssert.assertTrue(pageSource.contains(SECOND_TEST_CHOSEN_CAR_SERIES));
        softAssert.assertTrue(pageSource.contains(SECOND_TEST_CHOSEN_CAR_ENGINE));

        softAssert.assertAll();
    }


    @Test(dependsOnMethods = {"createCar2"})
    public void deleteCar2() {
        SoftAssert softAssert = new SoftAssert();

        mainCataloguePage.clickSwipeableTopView();
        mainCataloguePage.swipeTopViewLeft();

        EditCarPage editCarPage = mainCataloguePage.clickEditButton();
        mainCataloguePage = editCarPage.clickDeleteButton();

        logger.info("Check that button meaning we haven't chosen a single car yet is present");
        softAssert.assertTrue(mainCataloguePage.addCarButtonIsPresent());

        logger.info("Check that previously chosen car features are not present on the main page.");
        String pageSource = driver.getPageSource();
        softAssert.assertFalse(pageSource.contains(SECOND_TEST_CHOSEN_CAR));
        softAssert.assertFalse(pageSource.contains(SECOND_TEST_CHOSEN_CAR_SERIES));
        softAssert.assertFalse(pageSource.contains(SECOND_TEST_CHOSEN_CAR_ENGINE));

        softAssert.assertAll();
    }


    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
