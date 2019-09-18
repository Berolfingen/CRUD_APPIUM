package de.autodoc.pages.objects;

import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class StartPageObjects {
    @AndroidFindBy(uiAutomator = "new UiScrollable(new UiSelector()).scrollIntoView(text(\"France\"))")
    public AndroidElement CountryFrance;

    @AndroidFindBy(id = "de.autodoc.gmbh:id/rvCountriesList")
    public AndroidElement Countries;
}
