package com.petclinic.testcommon.framework.report;

import freemarker.template.utility.NullArgumentException;

public class Bug {

    private String issueNumber;

    public String getIssueNumber(){
        return issueNumber;
    }
    private void setIssueNumber(String issueNumber){
        if(null == issueNumber){
            throw new NullArgumentException("issueNumber");
        }
        this.issueNumber = issueNumber;
    }

    public Bug(String issueNumber){
        setIssueNumber(issueNumber);
    }
}
