package com.despegar.jav;

import com.despegar.jav.dto.TabletsDTO;
import com.despegar.jav.utils.FileManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Component
public class Tablets implements DisposableBean {
    private BigInteger returnedTablets;
    private String lastReturned;

    public Tablets() {
        ObjectMapper mapper = new ObjectMapper();
        FileManager fm = new FileManager();

        try {
            String json = fm.fileToText("tablillas.json");
            TabletsDTO dto = mapper.readValue(json, TabletsDTO.class);
            this.returnedTablets = dto.getTablets();
            this.lastReturned = dto.getLastDate();
        } catch (Exception e) {
            returnedTablets = new BigInteger("0");
            lastReturned = "never";
        }
    }

    public synchronized BigInteger getReturnedTablets() {
        return returnedTablets;
    }

    public synchronized String getLastReturned() {
        return lastReturned;
    }

    public synchronized void returnTablets(BigInteger tablets) {
        returnedTablets = returnedTablets.add(new BigInteger("" + Math.max(0, Math.min(10, tablets.intValue()))));
        lastReturned = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss"));
    }

    @Override
    public void destroy() throws Exception {
        FileManager fm = new FileManager();

        ObjectMapper mapper = new ObjectMapper();
        TabletsDTO dto = new TabletsDTO(this);

        String json = mapper.writeValueAsString(dto);
        fm.writeFile("tablillas.json", Arrays.asList(json));
    }
}