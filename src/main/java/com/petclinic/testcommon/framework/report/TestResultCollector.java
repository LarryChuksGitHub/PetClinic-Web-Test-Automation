package com.petclinic.testcommon.framework.report;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.testng.ITestResult;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TestResultCollector {

    private long passed = 0;
    private long failed = 0;
    private long skipped = 0;
    private Set<String> bugs = new HashSet<>();

    @SuppressWarnings("unused")
    TestResultCollector(int testResultStatus, List<String> bugs) {
        addResult(testResultStatus, bugs);
    }

    final void addResult(int testResultStatus, List<String> bugs) {
        switch (testResultStatus) {
            case ITestResult.SUCCESS:
                ++passed;
                break;
            case ITestResult.FAILURE:
                ++failed;
                this.bugs.addAll(bugs);
                break;
            case ITestResult.SKIP:
                ++skipped;
        }
    }

    Optional<Long> getSuccessRate() {
        if (getTotal() != 0) {
            double percent = (double) passed / getTotal() * 100;
            return Optional.of( (long) Math.floor(percent));
        }
        return Optional.empty();
    }

    long getNonSuccessRate() {
        Optional<Long> successRate = getSuccessRate();
        if (successRate.isPresent()) {
            return 100 - successRate.get();
        }
        return 0;
    }

    @SuppressWarnings("unused")
    long getPassed() {
        return passed;
    }

    @SuppressWarnings("unused")
    Set<String> getBugs() {
        return this.bugs;
    }

    long getTotal() {
        return passed + failed + skipped;
    }

    @SuppressWarnings("unused")
    void updateValue(int type, long increase, Set<String> bugs) {
        switch (type) {
            case ITestResult.SUCCESS:
                passed += increase;
                break;
            case ITestResult.FAILURE:
                failed += increase;
                this.bugs.addAll(bugs);
                break;
            case ITestResult.SKIP:
                skipped += increase;
        }
    }

    @Override
    public String toString() {
        return "['Passed', " + passed + "]," +
                "['Skipped', " + skipped + "]," +
                "['Failed', " + failed + "]";
    }
}
