package utils;

import cn.hutool.core.io.resource.ResourceUtil;
import org.apache.poi.ss.formula.functions.T;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import pageobject.LoginCaseData;

import java.io.*;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author hezhaowei
 * @create 2018-07-19 15:34
 **/
public class ExcelUtils {


    private static int hedear = 0;
    private String filePath;
    private String caseSheet;

//    public static interface TestcaseField{
//        int caseID = 0;
//        int Test_Suite = 1;
//        int TestCase_ID = 2;
//        int TestCase_Name = 3;
//        int Operation_Description = 4;
//        int Page_Element = 5;
//        int Element_Type = 6;
//        int Locator_Mode = 7;
//        int Locator_Value = 8;
//        int Action_Type = 9;
//        int Input_Value = 10;d
//        int Expected = 11;
//        int Test_Execute_Time = 12;
//        int Test_Result = 13;
//    }

    public ExcelUtils(String filePath,String caseSheet){
        this.filePath = filePath;
        this.caseSheet = caseSheet;

    }
    public static interface TestcaseField{
        int caseID = 0;
        int TestSuite = 1;
        int TestCaseType = 2;
        int TestCaseName = 3;
    }


    private  String getExcelPath(String FileName) {
        String userdirPath = System.getProperty("user.dir");
        if(userdirPath.endsWith("target")){
            userdirPath = userdirPath.replace("target","");
        }
        return userdirPath + "/resources/" + FileName;
    }


    public  List<Integer> getRowNum(String targetContent, int targetColumn) throws Exception {
        List<Integer> list = new ArrayList<Integer>();
        String getContentTemp = null;
        HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream("src\\test\\resources\\"+filePath));
        HSSFSheet sheet = wb.getSheet(caseSheet);
        HSSFRow row = null;
        int rowTotal = sheet.getLastRowNum();
        for (int i = 0; i <= rowTotal; i++) {
            row = sheet.getRow(i);
            row.getCell(targetColumn).setCellType(CellType.STRING);
            getContentTemp = row.getCell(targetColumn).getStringCellValue();

            if (getContentTemp.equalsIgnoreCase(targetContent)) {
                if(isMergedRegion(sheet,i,targetColumn)){

                    //获得一个 sheet 中合并单元格的数量
                    int sheetmergerCount = sheet.getNumMergedRegions();
                    //遍历所有的合并单元格
                    for(int j = 0; j<sheetmergerCount;j++) {
                        //获得合并单元格保存进list中
                        CellRangeAddress ca = sheet.getMergedRegion(j);
                        //获得合并单元格的起始行, 结束行, 起始列, 结束列
                        int firstC = ca.getFirstColumn();
                        int lastC = ca.getLastColumn();
                        int firstR = ca.getFirstRow();
                        int lastR = ca.getLastRow();
                        if(i >=  firstR && i <= lastR)
                        {
                            if(targetColumn >= firstC && targetColumn <= lastC) {
                                list.add(firstR);
                                list.add(lastR);
                                wb.close();
                                return list;
                            }
                        }
                    }
                }else {
                    wb.close();
                    list.add(i);
                }
            }

        }
        wb.close();
        return list;
    }

//    public static   List<CaseDataTemplate> getCaseData(List<Integer> list){
//        List<CaseDataTemplate> caseDataList = new ArrayList<CaseDataTemplate>();
//        List<String[]> records = null;
//        int min = list.get(0);
//        int max = list.get(0);
//        HSSFWorkbook wb = null;
//        HSSFSheet sheet = null;
//        HSSFRow row = null;
//        try {
//            wb = new HSSFWorkbook(new FileInputStream(filePath));
//            sheet = wb.getSheet(caseSheet);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        HSSFRow rows = null;
//        if(list.size() != 0){
//            if(list.size() == 1){
//                row = sheet.getRow(list.get(0));
//                String[] fields = new String[row.getLastCellNum()];
//                records = new ArrayList<String[]>();
//                for (int j = 0; j < row.getLastCellNum(); j++) {
//                    row.getCell(j).setCellType(HSSFCell.CELL_TYPE_STRING);
//                    fields[j] = rows.getCell(j).getStringCellValue();
//                    records.add(fields);
//                }
//                for(int i = 0; i < records.size(); i++){
//                    cat = new CaseDataTemplate();
//                }
//            }else {
//                for(int i = 0; i<list.size();i++){
//                    if(min > list.get(i)){
//                        min = list.get(i);
//                    }
//                    if(max < list.get(i)){
//                        max = list.get(i);
//                    }
//                }
//                for(int k = min ; k <= max; k++){
//                    rows = sheet.getRow(k);
//                    String[] fields = new String[rows.getLastCellNum()];
//                    records = new ArrayList<String[]>();
//                    for (int l = 0; l < rows.getLastCellNum(); l++) {
//                        rows.getCell(l).setCellType(HSSFCell.CELL_TYPE_STRING);
//                        fields[l] = rows.getCell(l).getStringCellValue();
//                    }
//                    if(fields != null){
//
//                        records.add(fields);
//                    }
//                   for(int n = 0; n < records.size(); n++ ){
//                       cat = new CaseDataTemplate();
//                       if(records.get(n)[TestcaseField.caseID] != "" || records.get(n)[TestcaseField.caseID] != null ){
//                           cat.setID(records.get(n)[TestcaseField.caseID]);
//                           //System.out.println(records.get(n)[TestcaseField.caseID]);
//                       }
//                       if(records.get(n)[TestcaseField.Test_Suite] != "" || records.get(n)[TestcaseField.Test_Suite] != null){
//                           cat.setTestSuite( records.get(n)[TestcaseField.Test_Suite]);
//                           //System.out.println( records.get(n)[TestcaseField.Test_Suite]);
//                       }
//                       if(records.get(n)[TestcaseField.TestCase_ID] != "" || records.get(n)[TestcaseField.TestCase_ID] != null){
//                           cat.setCaseID(records.get(n)[TestcaseField.TestCase_Name]);
//                           // System.out.println(records.get(n)[TestcaseField.TestCase_Name]);
//                       }
//                       if(records.get(n)[TestcaseField.TestCase_Name] != "" || records.get(n)[TestcaseField.TestCase_Name] != null){
//                           cat.setCaseName(records.get(n)[TestcaseField.TestCase_Name]);
//                          // System.out.println(records.get(n)[TestcaseField.TestCase_Name]);
//                       }
//                       if(records.get(n)[TestcaseField.Operation_Description] != "" || records.get(n)[TestcaseField.Operation_Description] != null){
//                           //cat.setOperationDescription(records.get(n)[TestcaseField.Operation_Description]);
//                           //System.out.println(records.get(n)[TestcaseField.Operation_Description]);
//                       }
//                       if(records.get(n)[TestcaseField.Page_Element] != ""|| records.get(n)[TestcaseField.Page_Element] != null){
//                           cat.setPageElement(records.get(n)[TestcaseField.Page_Element]);
//                           //System.out.println(records.get(n)[TestcaseField.Page_Element]);
//                       }
//                       if(records.get(n)[TestcaseField.Element_Type] != "" || records.get(n)[TestcaseField.Element_Type] != null){
//                           cat.setElementType(records.get(n)[TestcaseField.Element_Type]);
//                          // System.out.println(records.get(n)[TestcaseField.Element_Type]);
//                       }
//                       if(records.get(n)[TestcaseField.Locator_Mode] != "" || records.get(n)[TestcaseField.Locator_Mode] != null){
//                           cat.setLocatorMode(records.get(n)[TestcaseField.Locator_Mode]);
//                          // System.out.println(records.get(n)[TestcaseField.Locator_Mode]);
//                       }
//                       if(records.get(n)[TestcaseField.Locator_Value] != "" || records.get(n)[TestcaseField.Locator_Value] != null ){
//                           cat.setLocatorValue(records.get(n)[TestcaseField.Locator_Value]);
//                           //System.out.println(records.get(n)[TestcaseField.Locator_Value]);
//                       }
//                       if(records.get(n)[TestcaseField.Action_Type] != ""|| records.get(n)[TestcaseField.Action_Type] != null){
//                           cat.setActionType(records.get(n)[TestcaseField.Action_Type]);
//                           //System.out.println(records.get(n)[TestcaseField.Action_Type]);
//                       }
//                       if(records.get(n)[TestcaseField.Input_Value] != "" || records.get(n)[TestcaseField.Input_Value] != null){
//                           cat.setInputValue(records.get(n)[TestcaseField.Input_Value]);
//                          // System.out.println(records.get(n)[TestcaseField.Input_Value]);
//                       }
//                       if(records.get(n)[TestcaseField.Expected] != "" || records.get(n)[TestcaseField.Expected] != null){
//                           cat.setExpected(records.get(n)[TestcaseField.Expected]);
//                           // System.out.println(records.get(n)[TestcaseField.Input_Value]);
//                       }
//                       if(records.get(n)[TestcaseField.Test_Execute_Time] != "" || records.get(n)[TestcaseField.Test_Execute_Time] != null){
//                           cat.setTestExecuteTime(records.get(n)[TestcaseField.Test_Execute_Time]);
//                           // System.out.println(records.get(n)[TestcaseField.Input_Value]);
//                       }
//                       if(records.get(n)[TestcaseField.Test_Result] != "" || records.get(n)[TestcaseField.Test_Result] != null){
//                           cat.setTestResult(records.get(n)[TestcaseField.Test_Result]);
//                           // System.out.println(records.get(n)[TestcaseField.Input_Value]);
//                       }
//                       if(cat != null){
//                           caseDataList.add(cat);
//                       }
//                   }
//                }
//            }
//        }else {
//            return null;
//        }
//        return caseDataList;
//    }




    /**
     * 合并单元格处理,获取合并行
     * @param sheet
     * @return List<CellRangeAddress>
     */
    public List<CellRangeAddress> getCombineCell(Sheet sheet)
    {
        List<CellRangeAddress> list = new ArrayList<CellRangeAddress>();
        //获得一个 sheet 中合并单元格的数量
        int sheetmergerCount = sheet.getNumMergedRegions();
        //遍历所有的合并单元格
        for(int i = 0; i<sheetmergerCount;i++) {
            //获得合并单元格保存进list中
            CellRangeAddress ca = sheet.getMergedRegion(i);

            list.add(ca);
        }
        return list;
    }

    private  int getRowNum(List<CellRangeAddress> listCombineCell,Cell cell,Sheet sheet){
        int xr = 0;
        int firstC = 0;
        int lastC = 0;
        int firstR = 0;
        int lastR = 0;
        for(CellRangeAddress ca:listCombineCell)
        {
            //获得合并单元格的起始行, 结束行, 起始列, 结束列
            firstC = ca.getFirstColumn();
            lastC = ca.getLastColumn();
            firstR = ca.getFirstRow();
            lastR = ca.getLastRow();
            if(cell.getRowIndex() >= firstR && cell.getRowIndex() <= lastR)
            {
                if(cell.getColumnIndex() >= firstC && cell.getColumnIndex() <= lastC)
                {
                    xr = lastR;
                }
            }

        }
        return xr;

    }

    /**
     * 判断单元格是否为合并单元格，是的话则将单元格的值返回
     * @param listCombineCell 存放合并单元格的list
     * @param cell 需要判断的单元格
     * @param sheet sheet
     * @return
     */
    public  String isCombineCell(List<CellRangeAddress> listCombineCell,Cell cell,Sheet sheet) throws Exception{
        int firstC = 0;
        int lastC = 0;
        int firstR = 0;
        int lastR = 0;
        String cellValue = null;
        for(CellRangeAddress ca:listCombineCell)
        {
            //获得合并单元格的起始行, 结束行, 起始列, 结束列
            firstC = ca.getFirstColumn();
            lastC = ca.getLastColumn();
            firstR = ca.getFirstRow();
            lastR = ca.getLastRow();
            if(cell.getRowIndex() >= firstR && cell.getRowIndex() <= lastR)
            {
                if(cell.getColumnIndex() >= firstC && cell.getColumnIndex() <= lastC)
                {
                    Row fRow = sheet.getRow(firstR);
                    Cell fCell = fRow.getCell(firstC);
                    cellValue = getCellValue(fCell);
                    break;
                }
            }
            else
            {
                cellValue = "";
            }
        }
        return cellValue;
    }

    /**
     * 获取单元格的值
     * @param cell
     * @return
     */
    public static String getCellValue(Cell cell){
        if(cell == null) return "";
        return cell.getStringCellValue();
    }

    /**
     * 获取合并单元格的值
     * @param sheet
     * @param row
     * @param column
     * @return
     */
    public  String getMergedRegionValue(Sheet sheet ,int row , int column){
        int sheetMergeCount = sheet.getNumMergedRegions();

        for(int i = 0 ; i < sheetMergeCount ; i++){
            CellRangeAddress ca = sheet.getMergedRegion(i);
            int firstColumn = ca.getFirstColumn();
            int lastColumn = ca.getLastColumn();
            int firstRow = ca.getFirstRow();
            int lastRow = ca.getLastRow();

            if(row >= firstRow && row <= lastRow){
                if(column >= firstColumn && column <= lastColumn){
                    Row fRow = sheet.getRow(firstRow);
                    Cell fCell = fRow.getCell(firstColumn);
                    return getCellValue(fCell) ;
                }
            }
        }

        return null ;
    }

    /**
     * 判断指定的单元格是否是合并单元格
     * @param sheet
     * @param row 行下标
     * @param column 列下标
     * @return
     */
    private static boolean isMergedRegion(Sheet sheet,int row ,int column) {
        int sheetMergeCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress range = sheet.getMergedRegion(i);
            int firstColumn = range.getFirstColumn();
            int lastColumn = range.getLastColumn();
            int firstRow = range.getFirstRow();
            int lastRow = range.getLastRow();
            if(row >= firstRow && row <= lastRow){
                if(column >= firstColumn && column <= lastColumn){
                    return true;
                }
            }
        }
        return false;
    }



    public  static  String isCombineCell(List<CellRangeAddress> listCombineCell,int row,int coul,Sheet sheet){

        int firstC = 0;
        int lastC = 0;
        int firstR = 0;
        int lastR = 0;
        String cellValue = null;
        for(CellRangeAddress ca:listCombineCell)
        {
            //获得合并单元格的起始行, 结束行, 起始列, 结束列
            firstC = ca.getFirstColumn();
            lastC = ca.getLastColumn();
            firstR = ca.getFirstRow();
            lastR = ca.getLastRow();
            if(row >= firstR && row <= lastR)
            {
                if(coul >= firstC && coul <= lastC)
                {
                    Row fRow = sheet.getRow(firstR);
                    Cell fCell = fRow.getCell(firstC);
                    cellValue = getCellValue(fCell);
                    break;
                }
            }
            else
            {
                cellValue = "";
            }
        }
        return cellValue;
    }


//    public  static  String getMergedRegionValue(String targetSheet, int row, int column,String excelPath) {
//        String getContentTemp = null;
//        HSSFWorkbook wb = null;
//        HSSFSheet sheet = null;
//        try {
//            wb = new HSSFWorkbook(new FileInputStream(excelPath));
//            sheet = wb.getSheet(targetSheet);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        int sheetMergeCount = sheet.getNumMergedRegions();
//        for (int i = 0; i < sheetMergeCount; i++) {
//            CellRangeAddress ca = sheet.getMergedRegion(i);
//            int firstColumn = ca.getFirstColumn();
//            int lastColumn = ca.getLastColumn();
//            int firstRow = ca.getFirstRow();
//            int lastRow = ca.getLastRow();
//            if (row >= firstRow && row <= lastRow) {
//                if (column >= firstColumn && column <= lastColumn) {
//                    Row fRow = sheet.getRow(firstRow);
//                    Cell fCell = fRow.getCell(firstColumn);
//                    return null;
//                }
//            }
//        }
//        return null;
//    }

  public <T> List<List<T> >readExcel(List<Integer> list, Class<T> beanType){

      List<List<T>> readDataList = new ArrayList<List<T>>();
      List<T> readData = null;
        if(list.size() > 0){
            ExcelReader reader = ExcelUtil.getReader(ResourceUtil.getStream(filePath),caseSheet);

            for(int i = 0; i < list.size(); i++){

                readData = reader.read(hedear, list.get(i), list.get(i), beanType);
                readDataList.add(readData);
            }
        }
        return readDataList;
  }



  public static void main(String[] args) {


//      List getnum = null;
//      try {
//         getnum =  getRowNum("LoginFail",TestcaseField.TestCaseName,"登录测试数据","src\\test\\resources\\testcase\\xiongan.xls");
//         for (Object get :getnum){
//             System.out.println(get);
//         }
//      } catch (Exception e) {
//          e.printStackTrace();
//      }
//
//      ExcelUtils eu = new ExcelUtils();
//      List<CaseData> list = eu.readExcel(getnum, "testcase\\xiongan.xls", "登录测试数据");
//     for(CaseData c : list){
//          System.out.println(c.getTestCaseName());
//
//      }


//          ExcelReader dd = ExcelUtil.getReader(ResourceUtil.getStream("testcase\\xiongan.xls"), 1);
//      List<Map<String, Object>> read = dd.read(0, 3, 3);
//      for (Map c: read
//           ) {
//          System.out.println();
//      }


  }

}
