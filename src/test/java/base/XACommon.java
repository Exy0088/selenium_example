package base;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import org.apache.poi.ss.formula.functions.T;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageobject.LoginCaseData;
import utils.ExcelUtils;
import utils.PropertiesUtil;

import java.lang.reflect.Method;
import java.net.HttpCookie;
import java.util.*;

/**
 * ${DESCRIPTION}
 *
 * @author hezhaowei
 * @create 2018-07-30 16:25
 **/
public class XACommon {

    public static Map getSession(String url, String username, String password) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("username", username);
        map.put("password", password);
        String session = "";
        HttpResponse response = HttpRequest.post(url).form(map).timeout(2000).execute();
        List<HttpCookie> cookie = response.getCookie();
        map.clear();
        for (HttpCookie httpCookie : cookie) {
            if (httpCookie.getName().equals("SESSION")) {
                map.put(httpCookie.getName(),httpCookie.getValue());
            }
        }
        return map;
    }

//    @Test
//    public void test(){
//        getSession(GlobalConfig.getLoginUrl(),GlobalConfig.DEFAULTUSERNAME,GlobalConfig.DEFAULTPASSWORD);
//    }
    /**
     * 注入数据驱动
     * @param method 获得调用该方法的方法名
     * @param beanType
     * @return
     * @throws Exception
     */
    public static <T> List<List<T>>  casesData(String filePath, String caseSheet, Method method, Class<T> beanType) throws Exception{
        //创建一个Object数组集合
        List<List<T>> caseDatas = new ArrayList<List<T>>();

        //实例化一个ExcelUtils对象，需要传入文件路径、工作表名称
        ExcelUtils eu = new ExcelUtils(filePath,caseSheet);
        try {
            //调用ExcelUtils中的获得行的方法，需要传入用例名称、列号
            List<Integer> rowNum = eu.getRowNum(method.getName(),ExcelUtils.TestcaseField.TestCaseType);
            //根据行去读取数据，需要传入行号、工作表字段对应的实体类，将获得数据放到泛型类型为映射的实体类中
            caseDatas = eu.readExcel(rowNum,beanType);
//            //循环遍历集合
//            for(int i=0;i<caseDatas.size();i++){
//                for(int j =0;j<caseDatas.get(i).size();j++){
//                    lcd = new LoginCaseData();
//                    lcd = caseDatas.get(i).get(j);
//                    //将遍历出来的映射实体类，添加到Object数组集合中
//                    result.add(new Object[]{lcd});
//                }
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return caseDatas;
    }

    public static String getNum(String exce){
        String size = "";
        if(exce != null && !"".equals(exce)){
            exce.trim();
            for(int i = 0;i <exce.length();i++ ){
                if(exce.charAt(i) >= 48 && exce.charAt(i) <= 57){
                    size += exce.charAt(i);
                }
            }
        }
        return size;
    }


}
