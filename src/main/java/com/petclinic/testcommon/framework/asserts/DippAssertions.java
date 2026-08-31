package com.petclinic.testcommon.framework.asserts;

import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;
import static org.skyscreamer.jsonassert.JSONAssert.assertNotEquals;

import java.time.OffsetDateTime;
import java.time.temporal.TemporalUnit;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.AbstractCharSequenceAssert;
import org.assertj.core.api.AbstractOffsetDateTimeAssert;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.GenericComparableAssert;
import org.assertj.core.api.ListAssert;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.assertj.core.api.ThrowableTypeAssert;
import org.assertj.core.data.TemporalUnitOffset;
import org.assertj.core.data.TemporalUnitWithinOffset;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.comparator.JSONComparator;

import com.petclinic.testcommon.framework.report.Bug;
import com.petclinic.testcommon.framework.report.FeatureHeatmapRegister;
import io.restassured.response.Response;

public class DippAssertions {

    private AbstractAssert assertion;
    private ThrowableTypeAssert assertionThrowable;
    private final FeatureHeatmapRegister heatMapRegister;
    private Optional<Bug> bug;

    private static final String BUG_DESC = "bug: ";
    private static final String BUG_DESC_COMMA = ", bug: ";
    public static final String NO_BUG = "No bug assigned!";

    public DippAssertions() {
        heatMapRegister = new FeatureHeatmapRegister();
        bug = Optional.empty();
    }

    public DippAssertions assertThat(String actual) {
        assertion = Assertions.assertThat(actual);
        return this;
    }

    public DippAssertions assertThat(int actual) {
        assertion = Assertions.assertThat(actual);
        return this;
    }

    public <T> DippAssertions assertThatResponseIsEqualToIgnoringGivenFields(T response, T expectedResponse, String... ignoredFields) {
        assertion = Assertions.assertThat(response).isEqualToIgnoringGivenFields(
                expectedResponse, ignoredFields);
        return this;
    }

    public DippAssertions assertThat(long actual) {
        assertion = Assertions.assertThat(actual);
        return this;
    }

    public DippAssertions assertThat(boolean actual) {
        assertion = Assertions.assertThat(actual);
        return this;
    }

    public DippAssertions assertThat(List<? extends Comparable> actual) {
        assertion = Assertions.assertThat(actual);
        return this;
    }

    public DippAssertions assertThat(Enum<?> actual) {
        assertion = Assertions.assertThat(actual);
        return this;
    }

    public DippAssertions assertThat(OffsetDateTime actual) {
        assertion = Assertions.assertThat(actual);
        return this;
    }

    public DippAssertions assertGreaterThan(Integer initialValue, String description, Integer expectedHigherValue) {
        assertion = Assertions.assertThat(expectedHigherValue).as(description).isGreaterThan(initialValue);
        return this;
    }

    public DippAssertions assertGreaterThan(long initialValue, String description, long expectedHigherValue) {
        assertion = Assertions.assertThat(expectedHigherValue).as(description).isGreaterThan(initialValue);
        return this;
    }

    public DippAssertions assertGreaterThanOrEqualTo(long initialValue, String description, long expectedHigherValue) {
        assertion = Assertions.assertThat(expectedHigherValue).as(description).isGreaterThanOrEqualTo(initialValue);
        return this;
    }

    public <T extends GenericComparableAssert> DippAssertions assertThat(T actual) {
        assertion = Assertions.assertThat(actual);
        return this;
    }

    public DippAssertions assertThatNullPointerException() {
        assertionThrowable = Assertions.assertThatNullPointerException();
        return this;
    }

    public DippAssertions bug(String bug) {
        this.bug = Optional.of(new Bug(bug));
        heatMapRegister.addBug(this.bug.get());
        if (assertion != null) {
            StringBuilder sb = new StringBuilder(assertion.getWritableAssertionInfo().descriptionText());
            sb.append(sb.length() > 0 ? BUG_DESC_COMMA : BUG_DESC);
            sb.append(bug);
            assertion.as(sb.toString());
        }
        return this;
    }

    public DippAssertions as(String description, Object... formatValues) {
        if (formatValues.length > 0) {
            description = String.format(description, formatValues);
        }
        if (bug.isPresent()) {
            String descriptionWithBug = description + BUG_DESC_COMMA + bug.get();
            assertion.as(descriptionWithBug);
        } else
            assertion.as(description);
        return this;
    }


    public void isTrue() {
        try {
            assertion.isEqualTo(Boolean.TRUE);
            heatMapRegister.submit(true);
        } catch (AssertionError e) {
            heatMapRegister.submit(false);
            throw e;
        }
    }

    public void isFalse() {
        try {
            assertion.isEqualTo(Boolean.FALSE);
            heatMapRegister.submit(true);
        } catch (AssertionError e) {
            heatMapRegister.submit(false);
            throw e;
        }
    }

    public void isEqualTo(int expected) {
        try {
            assertion.isEqualTo(expected);
            heatMapRegister.submit(true);
        } catch (AssertionError e) {
            heatMapRegister.submit(false);
            throw e;
        }
    }

    public void isEqualTo(long expected) {
        try {
            assertion.isEqualTo(expected);
            heatMapRegister.submit(true);
        } catch (AssertionError e) {
            heatMapRegister.submit(false);
            throw e;
        }
    }

    public void isEqualTo(String expected) {
        try {
            assertion.isEqualTo(expected);
            heatMapRegister.submit(true);
        } catch (AssertionError e) {
            heatMapRegister.submit(false);
            throw e;
        }
    }

    public void isEqualTo(List<?> expected) {
        try {
            assertion.isEqualTo(expected);
            heatMapRegister.submit(true);
        } catch (AssertionError e) {
            heatMapRegister.submit(false);
            throw e;
        }
    }

    public void isEqualTo(Object expected) {
        try {
            assertion.isEqualTo(expected);
            heatMapRegister.submit(true);
        } catch (AssertionError e) {
            heatMapRegister.submit(false);
            throw e;
        }
    }

    public void isNotEqualTo(String expected) {
        try {
            assertion.isNotEqualTo(expected);
            heatMapRegister.submit(true);
        } catch (AssertionError e) {
            heatMapRegister.submit(false);
            throw e;
        }
    }

    public void isSortedAccordingTo(Comparator<?> comparator) {
        try {
            ((ListAssert) assertion).isSortedAccordingTo(comparator);
            heatMapRegister.submit(true);
        } catch (AssertionError e) {
            heatMapRegister.submit(false);
            throw e;
        }
    }

    public void isEmpty() {
        try {
            ((ListAssert) assertion).isEmpty();
            heatMapRegister.submit(true);
        } catch (AssertionError e) {
            heatMapRegister.submit(false);
            throw e;
        }
    }

    public void isNotNull() {
        try {
            assertion.isNotNull();
            heatMapRegister.submit(true);
        } catch (AssertionError e) {
            heatMapRegister.submit(false);
            throw e;
        }
    }

    public void isNull() {
        try {
            assertion.isNull();
            heatMapRegister.submit(true);
        } catch (AssertionError e) {
            heatMapRegister.submit(false);
            throw e;
        }
    }

    public void isCloseTo(OffsetDateTime dateTime, TemporalUnitOffset offset) {
        try {
            ((AbstractOffsetDateTimeAssert) assertion).isCloseTo(dateTime, offset);
            heatMapRegister.submit(true);
        } catch (AssertionError e) {
            heatMapRegister.submit(false);
            throw e;
        }
    }

    public void isAfter(OffsetDateTime dateTime) {
        try {
            ((AbstractOffsetDateTimeAssert) assertion).isAfter(dateTime);
            heatMapRegister.submit(true);
        } catch (AssertionError e) {
            heatMapRegister.submit(false);
            throw e;
        }
    }

    public void contains(String... expected) {
        try {
            ((AbstractCharSequenceAssert) assertion).contains(expected);
            heatMapRegister.submit(true);
        } catch (AssertionError e) {
            heatMapRegister.submit(false);
            throw e;
        }
    }

    public void doesNotContain(String... expected) {
        try {
            ((AbstractCharSequenceAssert) assertion).doesNotContain(expected);
            heatMapRegister.submit(true);
        } catch (AssertionError e) {
            heatMapRegister.submit(false);
            throw e;
        }
    }

    public void isThrownBy(final ThrowingCallable throwingCallable) {
        try {
            assertionThrowable.isThrownBy(throwingCallable);
            heatMapRegister.submit(true);
        } catch (AssertionError e) {
            heatMapRegister.submit(false);
            throw e;
        }
    }

    public TemporalUnitOffset within(long value, TemporalUnit unit) {
        return new TemporalUnitWithinOffset(value, unit);
    }

    public void assertJsonsEqual(String expectedStr, String actualStr, JSONComparator comparator) {
        try {
            assertEquals(expectedStr, actualStr, comparator);
            heatMapRegister.submit(true);
        } catch (AssertionError | JSONException e) {
            heatMapRegister.submit(false);
            throw new AssertionError(getExtendedExceptionMessage(e));
        }
    }

    public void assertJsonsNotEqual(String expectedStr, String actualStr, JSONComparator comparator) {
        try {
            assertNotEquals(expectedStr, actualStr, comparator);
            heatMapRegister.submit(true);
        } catch (AssertionError | JSONException e) {
            heatMapRegister.submit(false);
            throw new AssertionError(getExtendedExceptionMessage(e));
        }
    }

    public void assertJsonsEqual(String expectedStr, String actualStr, JSONCompareMode compareMode) {
        try {
            assertEquals(expectedStr, actualStr, compareMode);
            heatMapRegister.submit(true);
        } catch (AssertionError | JSONException e) {
            heatMapRegister.submit(false);
            throw new AssertionError(getExtendedExceptionMessage(e));
        }
    }

    public void assertJsonsNotEqual(String expectedStr, String actualStr, JSONCompareMode compareMode) {
        try {
            assertNotEquals(expectedStr, actualStr, compareMode);
            heatMapRegister.submit(true);
        } catch (AssertionError | JSONException e) {
            heatMapRegister.submit(false);
            throw new AssertionError(getExtendedExceptionMessage(e));
        }
    }

    private String getBugIssueNumber() {
        return bug.isPresent() ? bug.get().getIssueNumber() : NO_BUG;
    }

    public void assertResponseStatusCodeEqual(int expectedCode, Response response, String description) {
        if (response.statusCode() == expectedCode) {
            heatMapRegister.submit(true);
        } else {
            heatMapRegister.submit(false);
            String detailedDescription = "[" + description +
                    BUG_DESC_COMMA +
                    getBugIssueNumber() +
                    "] " +
                    "expected status code <" +
                    expectedCode +
                    "> but was <" +
                    response.statusCode() +
                    ">.\n\nDetailed response message:\n" +
                    response.prettyPrint();
            throw new AssertionError(detailedDescription);
        }
    }

    private String getExtendedExceptionMessage(Throwable e) {
        return BUG_DESC + getBugIssueNumber() +
                "\n\n" +
                e.getMessage();
    }
}
