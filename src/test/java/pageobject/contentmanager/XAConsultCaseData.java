package pageobject.contentmanager;

/**
 * ${DESCRIPTION}
 *
 * @author hezhaowei
 * @create 2018-07-31 10:55
 **/
public class XAConsultCaseData {
    private String ID;//用例ID
    private String TestSuite;//测试集
    private String TestCaseType;//测试用例类型
    private String TestCaseName;//测试用例名称
    private String TitleName;//输入标题
    private String Expected;//预期结果
    private String TestExecuteTime;//执行时间
    private String TestResult;//执行结果

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTestSuite() {
        return TestSuite;
    }

    public void setTestSuite(String testSuite) {
        TestSuite = testSuite;
    }

    public String getTestCaseType() {
        return TestCaseType;
    }

    public void setTestCaseType(String testCaseType) {
        TestCaseType = testCaseType;
    }

    public String getTestCaseName() {
        return TestCaseName;
    }

    public void setTestCaseName(String testCaseName) {
        TestCaseName = testCaseName;
    }

    public String getTitleName() {
        return TitleName;
    }

    public void setTitleName(String titleName) {
        TitleName = titleName;
    }

    public String getExpected() {
        return Expected;
    }

    public void setExpected(String expected) {
        Expected = expected;
    }

    public String getTestExecuteTime() {
        return TestExecuteTime;
    }

    public void setTestExecuteTime(String testExecuteTime) {
        TestExecuteTime = testExecuteTime;
    }

    public String getTestResult() {
        return TestResult;
    }

    public void setTestResult(String testResult) {
        TestResult = testResult;
    }

    @Override
    public String toString() {
        return "XAConsultCaseData{" +
                "ID='" + ID + '\'' +
                ", TestSuite='" + TestSuite + '\'' +
                ", TestCaseType='" + TestCaseType + '\'' +
                ", TestCaseName='" + TestCaseName + '\'' +
                ", TitleName='" + TitleName + '\'' +
                ", Expected='" + Expected + '\'' +
                ", TestExecuteTime='" + TestExecuteTime + '\'' +
                ", TestResult='" + TestResult + '\'' +
                '}';
    }
}
