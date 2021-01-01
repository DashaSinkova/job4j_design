package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte a = 63;
        short b = 1743;
        int c = 10000;
        long d = 1000000;
        float e = 577f;
        double f = 35.4;
        char g = 'g';
        boolean h = true;
        LOG.debug("debug message: {}, {}, {}, {}, {}, {}, {}, {}", a, b, c, d, e, f, g, h);

    }
}
