package com.despegar.jav;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class Tablets {
    private BigInteger returnedTablets;
    private String lastReturned;

    public Tablets() {
        returnedTablets = new BigInteger("0");
        lastReturned = "never";
    }

    public synchronized BigInteger getReturnedTablets() {
        return returnedTablets;
    }

    public synchronized String getLastReturned() {
        return lastReturned;
    }

    public synchronized void returnTablets(BigInteger tablets) {
        returnedTablets = returnedTablets.add(new BigInteger(tablets.toString()));
        lastReturned = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss"));
    }
}