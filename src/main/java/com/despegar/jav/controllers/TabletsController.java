package com.despegar.jav.controllers;

import com.despegar.jav.Tablets;
import com.despegar.jav.dto.TabletsDTO;
import com.despegar.jav.utils.FileManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class TabletsController {
    @Autowired
    private Tablets tablets;

    @Autowired
    private FileManager fileManager;

    @Autowired
    private ObjectMapper mapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(TabletsController.class);

    private Map<String, Integer> hits = new HashMap<>();
    private Map<String, Integer> banStory = new HashMap<>();
    private Set<String> banned = new HashSet<>();

    @GetMapping(value = "/tablets", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public TabletsDTO returnedTablets() {
        return responseTabletsDTO();
    }

    @PostMapping(value = "/tablets", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public TabletsDTO returnTablets(@RequestBody TabletsDTO tabletsDTO, HttpServletRequest request) {
        String clientIP = request.getRemoteAddr();
        Integer clientHits = hits.getOrDefault(clientIP, 0);

        LOGGER.info("Request from " + clientIP);
        LOGGER.info("Hits: " + clientHits);

        if (!isBanned(clientIP)) {
            clientHits++;
            hits.put(clientIP, clientHits);

            tablets.returnTablets(tabletsDTO.getTablets());
            if (clientHits % 10 == 0) {
                ban(clientIP);
            }
        }

        return responseTabletsDTO();
    }

    private boolean isBanned(String clientIP) {
        return banned.contains(clientIP);
    }

    private void ban(String ip) {
        banned.add(ip);
        int bans = banStory.getOrDefault(ip, 0);
        banStory.put(ip, bans + 1);

        programUnban(ip);

        LOGGER.info("Banned " + ip);
    }

    private void programUnban(String ip) {
        int baseTimer = 1000 * 60 * 5;
        int effectiveBan;

        int bans = banStory.get(ip);

        if (bans <= 5) {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    unban(ip);
                }
            }, effectiveBan = new Double(baseTimer * Math.pow(2, bans)).intValue());
            LOGGER.info("Unbanning ip in" + effectiveBan / 60 / 1000 + " minutes");
        } else {
            LOGGER.info("Banned " + ip + " permanently");
        }
    }

    private void unban(String ip) {
        banned.remove(ip);
        LOGGER.info("Unbanned " + ip);
    }

    private TabletsDTO responseTabletsDTO() {
        return new TabletsDTO(tablets.getReturnedTablets(), tablets.getLastReturned());
    }
}