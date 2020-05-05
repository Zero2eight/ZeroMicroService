package com.example.serviceconsumerfeign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignController {
    @Autowired
    private FeignInterface fi;

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public String ask(@PathVariable("name")String name) {
        return fi.ask(name);
    }
}
