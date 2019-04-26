package com.despegar.jav.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigInteger;

public class TabletsDTO {
    @JsonProperty("tablets")
    private BigInteger tablets;
    private String lastDate;

    public TabletsDTO() {
    }

    public TabletsDTO(BigInteger returnedTablets, String lastDate) {
        this.tablets = returnedTablets;
        this.lastDate = lastDate;
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