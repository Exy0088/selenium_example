package pageobject.contentmanager;

import pageobject.BasePageObject;

/**
 * ${DESCRIPTION}
 *
 * @author hezhaowei
 * @create 2018-07-31 10:55
 **/
public class XAConsultCaseData extends BasePageObject{

    private String title;
    private String newsTitle;
    private String newsSource;
    private String newsUpload;
    private String newsDescribe;
    private String newsContent;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsSource() {
        return newsSource;
    }

    public void setNewsSource(String newsSource) {
        this.newsSource = newsSource;
    }

    public String getNewsUpload() {
        return newsUpload;
    }

    public void setNewsUpload(String newsUpload) {
        this.newsUpload = newsUpload;
    }

    public String getNewsDescribe() {
        return newsDescribe;
    }

    public void setNewsDescribe(String newsDescribe) {
        this.newsDescribe = newsDescribe;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }
}
