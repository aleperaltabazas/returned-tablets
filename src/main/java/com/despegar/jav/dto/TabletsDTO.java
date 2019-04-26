package com.despegar.jav.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TabletsDTO {
    @JsonProperty("tablets")
    private Integer tablets;
    private String lastDate;

    public TabletsDTO() {
    }

    public TabletsDTO(Integer returnedTablets, String lastDate) {
        this.tablets = returnedTablets;
        this.lastDate = lastDate;
    }

    public Integer getTablets() {
        return tablets;
    }

    public void setTablets(Integer tablets) {
        this.tablets = tablets;
    }

    public String getLastDate() {
        return lastDate;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }
}