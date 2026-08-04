package com.tsys.testcommon.framework.report;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.tsys.testcommon.framework.asserts.DippAssertions;

public class FeatureHeatmapRegister {

    private List<Bug> bugs;

    public FeatureHeatmapRegister() {
        this.bugs = new ArrayList<>();
    }

    @SuppressWarnings("unused")
    private List<String> getBugNumbers(){
        if(bugs.isEmpty()) {
            return Collections.singletonList(DippAssertions.NO_BUG);
        }
        return bugs.stream().map(Bug::getIssueNumber).collect(Collectors.toList());
    }

    public void addBug(Bug bug) {
        this.bugs.add(bug);
    }

    public void submit(boolean isPassed) {
        bugs = new ArrayList<>();
    }

}
