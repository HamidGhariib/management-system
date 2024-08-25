package com.managementsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller(value = "/v1")
public class CommonController {

    @RequestMapping(path = "/create-request",method = RequestMethod.POST)
    public void createRequest(){

    }
}
