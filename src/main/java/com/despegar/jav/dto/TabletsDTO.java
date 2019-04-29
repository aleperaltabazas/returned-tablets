package com.despegar.jav.dto;

import com.despegar.jav.Tablets;

import java.math.BigInteger;

public class TabletsDTO {
    private BigInteger tablets;
    private String lastDate;

    public TabletsDTO() {
    }

    public TabletsDTO(BigInteger returnedTablets, String lastDate) {
        this.tablets = returnedTablets;
        this.lastDate = lastDate;
    }

    public TabletsDTO(Tablets tablets) {
        this.tablets = tablets.getReturnedTablets();
        this.lastDate = tablets.getLastReturned();
    }

    public BigInteger getTablets() {
        return tablets;
    }

    public void setTablets(BigInteger tablets) {
        this.tablets = tablets;
    }

    public String getLastDate() {
        return lastDate;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }
}