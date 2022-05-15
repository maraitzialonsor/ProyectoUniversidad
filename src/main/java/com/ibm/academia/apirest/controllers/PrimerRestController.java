package com.ibm.academia.apirest.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;

public class PrimerRestController {
    Logger logger = LoggerFactory.getLogger(PrimerRestController.class);

    @GetMapping
    public  String holaMundo(){
        logger.trace("trace log");
        logger.debug("debug log");
        logger.info("info log");
        logger.warn("warning log");
        logger.error("error log");
        return "Hola mundo API";
    }
}
