package com.despegar.jav.controllers;

import com.despegar.jav.Tablets;
import com.despegar.jav.dto.TabletsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TabletsController {
    @Autowired
    private Tablets tablets;

    @GetMapping(value = "/tablets", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public TabletsDTO returnedTablets() {
        return responseTabletsDTO();
    }

    @PostMapping(value = "/tablets", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public TabletsDTO returnTablets(@RequestBody TabletsDTO tabletsDTO) {
        tablets.returnTablets(tabletsDTO.getTablets());
        return responseTabletsDTO();
    }

    private TabletsDTO responseTabletsDTO() {
        return new TabletsDTO(tablets.getReturnedTablets(), tablets.getLastReturned());
    }
}