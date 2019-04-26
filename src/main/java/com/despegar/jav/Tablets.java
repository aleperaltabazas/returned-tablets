package com.despegar.jav;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class Tablets {
    private Integer returnedTablets;
    private String lastReturned;

    public Tablets() {
        returnedTablets = 0;
        lastReturned = "never";
    }

    public Integer getReturnedTablets() {
        return returnedTablets;
    }

    public String getLastReturned() {
        return lastReturned;
    }

    public void returnTablets(Integer tablets) {
        returnedTablets += tablets;
        lastReturned = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss"));
    }
}