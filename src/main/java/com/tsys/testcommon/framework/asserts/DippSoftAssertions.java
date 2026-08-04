package com.tsys.testcommon.framework.asserts;

import static org.apache.commons.collections4.CollectionUtils.isEqualCollection;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;

import com.tsys.testcommon.framework.report.FeatureHeatmapRegister;

public class DippSoftAssertions {

    private final SoftAssertions softAssertions;
    private final FeatureHeatmapRegister heatMapRegister;

    public DippSoftAssertions() {
        softAssertions = new SoftAssertions();
        heatMapRegister = new FeatureHeatmapRegister();
    }

    public void assertAll() {
         softAssertions.assertAll();
    }

    public void assertTrue(boolean actual, String description) {
        heatMapRegister.submit(actual);
        softAssertions.assertThat(actual).as(description).isTrue();
    }

    public void assertTrue(boolean actual) {
        heatMapRegister.submit(actual);
        softAssertions.assertThat(actual).isTrue();
    }

    public void assertFalse(boolean actual, String description) {
        heatMapRegister.submit(!actual);
        softAssertions.assertThat(actual).as(description).isFalse();
    }

    public void assertFalse(boolean actual) {
        heatMapRegister.submit(!actual);
        softAssertions.assertThat(actual).isFalse();
    }

    public void assertEquals(int actual, String description, int expected) {
        heatMapRegister.submit(expected == actual);
        softAssertions.assertThat(actual).as(description).isEqualTo(expected);
    }

    public void assertEquals(int actual, int expected) {
        heatMapRegister.submit(expected == actual);
        softAssertions.assertThat(actual).isEqualTo(expected);
    }

    public void assertEquals(boolean actual, String description, boolean expected) {
        heatMapRegister.submit(expected == actual);
        softAssertions.assertThat(actual).as(description).isEqualTo(expected);
    }

    public void assertEquals(Set<String> actual, Set<String> expected, String message) {
        heatMapRegister.submit(actual.containsAll(expected));
        softAssertions.assertThat(actual).as(message).containsAll(expected);
    }

    public void assertEquals(Object actual, Object expected, String message) {
        heatMapRegister.submit(actual.equals(expected));
        softAssertions.assertThat(actual)
                .as(message)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

    public void assertEquals(String actual, String description, String expected) {
        heatMapRegister.submit(expected.equals(actual));
        softAssertions.assertThat(actual).as(description).isEqualTo(expected);
    }

    public void assertNotEqualTo(String actual, String description, String expected) {
        heatMapRegister.submit(!expected.equals(actual));
        softAssertions.assertThat(actual).as(description).isNotEqualTo(expected);
    }

    public void assertNotEqualTo(int actual, String description, int expected) {
        heatMapRegister.submit(expected != actual);
        softAssertions.assertThat(actual).as(description).isNotEqualTo(expected);
    }

    public void assertEquals(List<String> actual, String description, List<String> expected) {
        heatMapRegister.submit(isEqualCollection(expected, actual));
        softAssertions.assertThat(actual).as(description).isEqualTo(expected);
    }

    public void assertEquals(String actual, String expected) {
        heatMapRegister.submit(expected.equals(actual));
        softAssertions.assertThat(actual).isEqualTo(expected);
    }

    public void assertContains(String actual, String description, String expected) {
        heatMapRegister.submit(actual.contains(expected));
        softAssertions.assertThat(actual).as(description).contains(expected);
    }

    public void assertContains(String actual, String description, String... expected) {
        try {
            Assertions.assertThat(actual).contains(expected);
            heatMapRegister.submit(true);
        } catch (AssertionError e) {
            heatMapRegister.submit(false);
        }
        softAssertions.assertThat(actual).as(description).contains(expected);
    }

    public void assertContainsIgnoreCase(String actual, String description, String expected) {
        boolean result = Pattern.compile(Pattern.quote(expected), Pattern.CASE_INSENSITIVE).matcher(actual).find();
        softAssertions.assertThat(result).as(description).isTrue();
    }

    public void assertContains(List<String> actual, String... expected) {
        try {
            Assertions.assertThat(actual).contains(expected);
            heatMapRegister.submit(true);
        } catch (AssertionError e) {
            heatMapRegister.submit(false);
        }
        softAssertions.assertThat(actual).contains(expected);
    }

    public void assertContains(String description, List<String> actual, String... expected) {
        try {
            Assertions.assertThat(actual).contains(expected);
            heatMapRegister.submit(true);
        } catch (AssertionError e) {
            heatMapRegister.submit(false);
        }
        softAssertions.assertThat(actual).as(description).contains(expected);
    }

    public boolean assertContainsPartial(String description, List<String> actual, String... expected) {
        boolean containsPartial = false;
        for (String exp : expected) {
            boolean found = actual.stream().anyMatch(item -> item.contains(exp));
            softAssertions.assertThat(found)
                    .as(description + " - Expected to find part: '" + exp + "' in " + actual)
                    .isTrue();

            if (found) {
                containsPartial = true;
            }
        }
        // Log the result
        heatMapRegister.submit(containsPartial);
        softAssertions.assertAll();
        return containsPartial;
    }


    public void assertDoesNotContain(List<String> actual, String... expected) {
        try {
            Assertions.assertThat(actual).doesNotContain(expected);
            heatMapRegister.submit(true);
        } catch (AssertionError e) {
            heatMapRegister.submit(false);
        }
        softAssertions.assertThat(actual).doesNotContain(expected);
    }

    public void assertDoesNotContain(String actual, String expected) {
        try {
            Assertions.assertThat(actual).doesNotContain(expected);
            heatMapRegister.submit(true);
        } catch (AssertionError e) {
            heatMapRegister.submit(false);
        }
        softAssertions.assertThat(actual).doesNotContain(expected);
    }

    public void assertDoesNotContain(String actual, String description, String expected) {
        try {
            Assertions.assertThat(actual).doesNotContain(expected);
            heatMapRegister.submit(true);
        } catch (AssertionError e) {
            heatMapRegister.submit(false);
        }
        softAssertions.assertThat(actual).as(description).doesNotContain(expected);
    }

    public void assertHasToString(String actual, String description, String expected) {
        try {
            Assertions.assertThat(actual).hasToString(expected);
            heatMapRegister.submit(true);
        } catch (AssertionError e) {
            heatMapRegister.submit(false);
        }
        softAssertions.assertThat(actual).as(description).hasToString(expected);
    }

    public void assertContainsExactlyElementsOf(List<String> actual, List<String> expected) {
        try {
            Assertions.assertThat(actual).containsExactlyElementsOf(expected);
            heatMapRegister.submit(true);
        } catch (AssertionError e) {
            heatMapRegister.submit(false);
        }
        softAssertions.assertThat(actual).containsExactlyElementsOf(expected);
    }

    public void assertNotEmpty(List<?> actual, String description) {
        try {
            Assertions.assertThat(actual).isNotEmpty();
            heatMapRegister.submit(true);
        } catch (AssertionError e) {
            heatMapRegister.submit(false);
        }
        softAssertions.assertThat(actual).as(description).isNotEmpty();
    }

    public void assertNotEmpty(String actual) {
        try {
            Assertions.assertThat(actual).isNotEmpty();
            heatMapRegister.submit(true);
        } catch (AssertionError e) {
            heatMapRegister.submit(false);
        }
        softAssertions.assertThat(actual).isNotEmpty();
    }

    public void assertEmpty(List<?> actual, String description) {
        try {
            Assertions.assertThat(actual).isEmpty();
            heatMapRegister.submit(true);
        } catch (AssertionError e) {
            heatMapRegister.submit(false);
        }
        softAssertions.assertThat(actual).as(description).isEmpty();
    }

    public void assertEmpty(Map actual, String description) {
        try {
            Assertions.assertThat(actual).isEmpty();
            heatMapRegister.submit(true);
        } catch (AssertionError e) {
            heatMapRegister.submit(false);
        }
        softAssertions.assertThat(actual).as(description).isEmpty();
    }

    public void assertEmpty(String actual, String description) {
        try {
            Assertions.assertThat(actual).isEmpty();
            heatMapRegister.submit(true);
        } catch (AssertionError e) {
            heatMapRegister.submit(false);
        }
        softAssertions.assertThat(actual).as(description).isEmpty();
    }

    public void assertEmpty(String actual) {
        try {
            Assertions.assertThat(actual).isEmpty();
            heatMapRegister.submit(true);
        } catch (AssertionError e) {
            heatMapRegister.submit(false);
        }
        softAssertions.assertThat(actual).isEmpty();
    }

    public void assertSortedAccordingTo(List<String> actual, String description,
                                        Comparator<String> comparator) {
        try {
            Assertions.assertThat(actual).isSortedAccordingTo(comparator);
            heatMapRegister.submit(true);
        } catch (AssertionError e) {
            heatMapRegister.submit(false);
        }
        softAssertions.assertThat(actual).as(description).isSortedAccordingTo(comparator);
    }

    public void assertAllMatch(List<String> actual, String description,
                               Predicate<String> predicate) {
        try {
            Assertions.assertThat(actual).allMatch(predicate);
            heatMapRegister.submit(true);
        } catch (AssertionError e) {
            heatMapRegister.submit(false);
        }
        softAssertions.assertThat(actual).as(description).allMatch(predicate);
    }

    public void assertGreaterThan(Integer actual, String description, Integer expected) {
        try {
            Assertions.assertThat(actual).isGreaterThan(expected);
            heatMapRegister.submit(true);
        } catch (AssertionError e) {
            heatMapRegister.submit(false);
        }
        softAssertions.assertThat(actual).as(description).isGreaterThan(expected);
    }
    public void assertStartsWith(String actual, String expected) {
        try {
            Assertions.assertThat(actual).startsWith(expected);
            heatMapRegister.submit(true);
        } catch (AssertionError e) {
            heatMapRegister.submit(false);
        }
        softAssertions.assertThat(actual).startsWith(expected);
    }

    public void assertEqualToIgnoringCase(String actual, String expected) {
        try {
            Assertions.assertThat(actual).isEqualToIgnoringCase(expected);
            heatMapRegister.submit(true);
        } catch (AssertionError e) {
            heatMapRegister.submit(false);
        }
        softAssertions.assertThat(actual).isEqualToIgnoringCase(expected);
    }

    public void assertEqualToIgnoringCase(String actual, String description, String expected) {
        try {
            Assertions.assertThat(actual).isEqualToIgnoringCase(expected);
            heatMapRegister.submit(true);
        } catch (AssertionError e) {
            heatMapRegister.submit(false);
        }
        softAssertions.assertThat(actual).as(description).isEqualToIgnoringCase(expected);
    }

    public void assertContainsIgnoringCase(String actual, String description, String expected) {
        try {
            Assertions.assertThat(actual).containsIgnoringCase(expected);
            heatMapRegister.submit(true);
        } catch (AssertionError e) {
            heatMapRegister.submit(false);
        }
        softAssertions.assertThat(actual).as(description).containsIgnoringCase(expected);
    }

    public void assertHasSize(List actual, int expected) {
        try {
            Assertions.assertThat(actual).hasSize(expected);
            heatMapRegister.submit(true);
        } catch (AssertionError e) {
            heatMapRegister.submit(false);
        }
        softAssertions.assertThat(actual).hasSize(expected);
    }

    public void assertNotNull(Object actual, String description) {
        heatMapRegister.submit(actual != null);
        softAssertions.assertThat(actual).as(description).isNotNull();
    }

    public void assertMatchesPattern(String actual, String description, Pattern expectedPattern) {
        assertTrue(expectedPattern.matcher(actual).matches(), description);
    }
}
