package com.example.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/")
public class ConfigurationService {

    @Autowired
    DBSettings dbSettings;

    @Autowired
    Environment environment;

    @RequestMapping(path = "/dbsettings",method = RequestMethod.GET)
    public String getDbSettings(){
        return dbSettings.getConnection()
               + "##"
               + dbSettings.getHost()
               + "##"
               + dbSettings.getPort();
    }

    @GetMapping(path = "/getprofile")
    public String getProfileDetails(){
        return environment.toString();
    }
}
