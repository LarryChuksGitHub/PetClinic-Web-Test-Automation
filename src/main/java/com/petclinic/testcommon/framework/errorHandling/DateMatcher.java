package com.petclinic.testcommon.framework.errorHandling;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.skyscreamer.jsonassert.ValueMatcher;
import org.skyscreamer.jsonassert.ValueMatcherException;

public class DateMatcher<T> implements ValueMatcher<T> {

    private final SimpleDateFormat format;

    public DateMatcher(String pattern) {
        format = new SimpleDateFormat(pattern);
    }

    @Override
    public boolean equal(T actual, T expected) {
        String actualString = actual.toString();
        //No need for expected
        try {
            format.parse(actualString);
            return true;
        } catch (ParseException e) {
            throw new ValueMatcherException(format.toPattern() + " expected pattern did not match value", e, format.toPattern(), actualString);
        }
    }
}
