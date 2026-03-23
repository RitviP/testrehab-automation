package com.planetrehab.automation.dataproviders;

import org.testng.annotations.DataProvider;

import com.planetrehab.automation.constants.FrameworkConstants;
import com.planetrehab.automation.utils.ExcelUtils;

public class LoginDataProvider {

    @DataProvider(name = "loginData")
    public static Object[][] getLoginData() {

        return ExcelUtils.getSheetData(
                FrameworkConstants.TESTDATA_FILE_PATH,
                "Login"
        );
    }
}