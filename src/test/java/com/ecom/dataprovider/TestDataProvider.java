package com.ecom.dataprovider;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.testng.annotations.DataProvider;
import com.ecom.utils.ExcelUtils;
import com.ecom.utils.JsonUtils;


public class TestDataProvider {

    @DataProvider(name = "getJsonData")
    public Object[][] getJsonData() throws IOException {
        String jsonPath = System.getProperty("user.dir") + "/src/test/resources/testdata/TestData.json";
        List<HashMap<String, String>> dataList = JsonUtils.getJsonDataToMap(jsonPath);
        
        Object[][] data = new Object[dataList.size()][1];
        for (int i = 0; i < dataList.size(); i++) {
            data[i][0] = dataList.get(i);
        }
        return data;
    }

    @DataProvider(name = "getExcelData")
    public Object[][] getExcelData() throws IOException {
        String excelPath = System.getProperty("user.dir") + "/src/test/resources/testdata/OrderData.xlsx";
        return ExcelUtils.getExcelData(excelPath);
    }
}
