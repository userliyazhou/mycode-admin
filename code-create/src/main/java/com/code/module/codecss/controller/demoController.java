package com.code.module.codecss.controller;

import com.code.module.codecss.service.demoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class demoController {
    @Autowired
    demoService demoService;
    @PostMapping(value = "/demofind")
    public String  demofind(){
       String demoString= demoService.demofind();
      return demoString;
    }
}
