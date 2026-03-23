package com.planetrehab.automation.dataproviders;

import org.testng.annotations.DataProvider;

import com.planetrehab.automation.constants.FrameworkConstants;
import com.planetrehab.automation.utils.ExcelUtils;

public class RegisterPatientDataProvider {

    @DataProvider(name = "registerPatientData")
    public Object[][] getPatientData() {

    	String path = FrameworkConstants.TESTDATA_FILE_PATH;

        return ExcelUtils.getSheetData(path, "RegisterPatient");
    }
}