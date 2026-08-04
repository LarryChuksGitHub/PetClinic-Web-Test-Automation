package com.tsys.testcommon.framework.report;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.Setter;

public class RegisterEntry implements Comparable<RegisterEntry>{

    private final List<String> methods = Arrays.asList("POST", "PUT", "DELETE", "GET", "PATCH");

    @Getter
    int number;

    @Getter
    Set<String> relatedTestNames = new HashSet<>();

    @Setter
    @Getter
    boolean hasFailure = false;

    @Setter
    @Getter
    boolean hasOnlyFailure = false;

    @Getter
    String httpMethod;

    @Getter
    String serviceName;

    RegisterEntry(int number, String testName, String endpointName) {
        this.number = number;
        if(testName != null)
            updateTestNames(testName);
        setHttpMethod(endpointName);
    }

    RegisterEntry(String httpAndTestMethodName, String serviceName) {
        this.number = 0;
        setHttpMethod(httpAndTestMethodName);
        this.serviceName = serviceName;
    }

    private void updateNumber() {
        number += 1;
    }

    private void updateTestNames(String testName) {
        relatedTestNames.add(testName);
    }

    void updateEntry(String testName) {
        if(!relatedTestNames.contains(testName)) {
            updateNumber();
            updateTestNames(testName);
        }
    }

    private void setHttpMethod(String endpointName) {
        Set<String> collect = methods.stream().filter(endpointName::contains)
                .collect(Collectors.toSet());
        if(collect.size() != 0)
            httpMethod = collect.iterator().next();
    }

    @Override
    public int compareTo(RegisterEntry compareEntry) {
        if(number == 0 && compareEntry.getNumber() == 0) {
            return 0;
        } if (number == 0) {
            return -1;
        } if(compareEntry.getNumber() == 0) {
            return 1;
        } if((hasOnlyFailure && !compareEntry.isHasOnlyFailure()) || (hasFailure && !compareEntry.isHasFailure())){
            return -1;
        } if((compareEntry.isHasOnlyFailure() && !hasOnlyFailure) || (compareEntry.hasFailure && !hasFailure)) {
            return 1;
        }
        return 0;
    }
}
