package pageobject;

/**
 * ${DESCRIPTION}
 *
 * @author hezhaowei
 * @create 2018-07-23 15:48
 **/
public class LoginCaseData {

    private String ID;//用例ID
    private String TestSuite;//测试集
    private String TestCaseType;//测试用例类型
    private String TestCaseName;//测试用例名称
    private String InputUsername;//输入用户名
    private String InputPassword;//输入密码
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

    public String getInputUsername() {
        return InputUsername;
    }

    public void setInputUsername(String inputUsername) {
        InputUsername = inputUsername;
    }

    public String getInputPassword() {
        return InputPassword;
    }

    public void setInputPassword(String inputPassword) {
        InputPassword = inputPassword;
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
        return "CaseData{" +
                "ID='" + ID + '\'' +
                ", TestSuite='" + TestSuite + '\'' +
                ", TestCaseID='" + TestCaseType + '\'' +
                ", TestCaseName='" + TestCaseName + '\'' +
                ", InputUsername='" + InputUsername + '\'' +
                ", InputPassword='" + InputPassword + '\'' +
                ", Expected='" + Expected + '\'' +
                ", TestExecuteTime='" + TestExecuteTime + '\'' +
                ", TestResult='" + TestResult + '\'' +
                '}';
    }
}
