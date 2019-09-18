package de.autodoc.pages.objects;

import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class VehiclesCataloguePageObjects {
    @AndroidFindBy(id = "de.autodoc.gmbh:id/etSearchField")
    public AndroidElement SearchField;

    @AndroidFindBy(id = "de.autodoc.gmbh:id/coordinator")
    public AndroidElement CoordinatorViewGroup;
}
