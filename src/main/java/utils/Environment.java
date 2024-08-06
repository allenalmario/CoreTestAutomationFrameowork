package utils;

import java.util.Arrays;

public class Environment {

    public final static String QA = "qa";
    public final static String DEV = "dev";
    public final static String PROD = "prod";

    public static final String[] ENVIRONMENTS = {QA, DEV, PROD};

    /**
     * getEnvironment
     * @param n/a
     * @return environment, if not, returns 'qa' by default
     */
    public static String getEnvironment() {
        final String environment = System.getProperty("environment", "qa").trim().toLowerCase();
        return Arrays.asList(ENVIRONMENTS).contains(environment) ? environment : QA;
    }
}
