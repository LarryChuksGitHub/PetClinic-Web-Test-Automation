package com.petclinic.testcommon.framework.utils.logging;

import com.github.therapi.runtimejavadoc.ClassJavadoc;
import com.github.therapi.runtimejavadoc.CommentFormatter;
import com.github.therapi.runtimejavadoc.MethodJavadoc;
import com.github.therapi.runtimejavadoc.RuntimeJavadoc;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Member;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestScenarioLogger {

    private static final CommentFormatter FORMATTER = new CommentFormatter();

    public static String getTestJavadoc(final Class<?> clazz, final Member test) {
        ClassJavadoc classDoc = RuntimeJavadoc.getJavadoc(clazz);
        for (MethodJavadoc methodDoc : classDoc.getMethods()) {
            if (methodDoc.getName().equals(test.getName())) {
                return FORMATTER.format(methodDoc.getComment());
            }
        }
        return "Test scenario is not found! Please add it.";
    }

}
