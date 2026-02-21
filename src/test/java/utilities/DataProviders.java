package utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class DataProviders {
    //DataProvider 1
    @DataProvider(name="LoginData")
    public String [][] getData() throws IOException{

        String path="testData/OpenCart_LoginData.xlsx";
        ExcelUtility xlutil=new ExcelUtility(path);

        int totalrows= xlutil.getRowCount("Sheet1");
        int totalcols= xlutil.getCellCount("Sheet1",1);

        List<String[]> dataList = new ArrayList<>();

        for (int i = 1; i <= totalrows; i++) {

            boolean isEmptyRow = true;
            String rowData[] = new String[totalcols];

            for(int j=0; j<totalcols; j++){
                String cellData = xlutil.getCellData("Sheet1",i,j);
                rowData[j] = cellData;

                if(cellData != null && !cellData.trim().isEmpty()){
                    isEmptyRow = false;
                }
            }

            if(!isEmptyRow){
                dataList.add(rowData);
            }
        }

        String [][] logindata = new String[dataList.size()][totalcols];

        for(int i=0; i<dataList.size(); i++){
            logindata[i] = dataList.get(i);
        }

        return logindata;
    }

    //DataProvider 2


    //DataProvider 3
}
