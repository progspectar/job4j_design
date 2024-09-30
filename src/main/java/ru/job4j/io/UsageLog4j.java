package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte age = 1;
        short velocity = 2;
        int counter = 3;
        long lcounter = 4L;
        float fcounter = 5F;
        double dcounter = 6;
        char ch = '7';
        boolean bool = true;
        LOG.debug("Age: {}, velocity: {}, counter: {}, lcounter: {}, fcounter: {}, dcounter: {}, ch: {}, bool: {}",
                age, velocity, counter, lcounter, fcounter, dcounter, ch, bool);
    }
}