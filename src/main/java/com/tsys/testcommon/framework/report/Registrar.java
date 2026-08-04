package com.tsys.testcommon.framework.report;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class Registrar {

    private final Map<String, RegisterEntry> register = new HashMap<>();

    public void add(String entry, String testName) {
        RegisterEntry key = register.get(entry);
        if (key != null) {
            register.get(entry).updateEntry(testName);
        } else {
            register.put(entry, new RegisterEntry(1, testName, entry));
        }
    }

    public void define(String entry) {
        String[] splitEntry = splitEntry(entry);
        register.put(splitEntry[0], new RegisterEntry(splitEntry[0], splitEntry[1]));
    }

    public void add(String entry, String prefix, String testName) {
        RegisterEntry key = register.get(entry);
        if (key != null) {
            register.get(entry).updateEntry(testName);
        } else {
            register.put(prefix + entry, new RegisterEntry(1, testName, entry));
        }
    }

    private String[] splitEntry(String entry) {
        String separatedService = StringUtils.replaceFirst(entry, "\\|", " ");
        String[] entryParts = StringUtils.split(separatedService, "|");
        if(entryParts.length != 2) {
            throw new IllegalArgumentException("The given line is corrupted - " + entry);
        }
        return entryParts;
    }

    public Integer get(String key) {
        return register.get(key).getNumber();
    }

    public Collection<RegisterEntry> getValues() {
        return register.values();
    }

    public Map<String, RegisterEntry> getRegister() {
        return register;
    }
}
