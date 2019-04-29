package com.despegar.jav.dto;

import java.time.LocalDateTime;

public class BanDTO {
    private String ip;
    private Integer bans;
    private LocalDateTime lastBanDate;

    public BanDTO() {}

    public BanDTO(String ip, Integer bans, LocalDateTime lastBanDate) {
        this.ip = ip;
        this.bans = bans;
        this.lastBanDate = lastBanDate;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getBans() {
        return bans;
    }

    public void setBans(Integer bans) {
        this.bans = bans;
    }

    public LocalDateTime getLastBanDate() {
        return lastBanDate;
    }

    public void setLastBanDate(LocalDateTime lastBanDate) {
        this.lastBanDate = lastBanDate;
    }
}