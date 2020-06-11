package com.example.ribbon.controller;


import com.example.ribbon.config.SayHelloConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RibbonClient(name = "say-hello", configuration = SayHelloConfiguration.class)
public class WebController {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/hi")
    public String sayHi(@RequestParam(value = "name", defaultValue = "Stranger") String name) {
        String greeting = this.restTemplate.getForObject(
                "http://localhost:9090/greeting",
                String.class
        );

        return String.format("%s, %s!", greeting, name);
    }

    /**
     * Using ribbon client
     * @param name
     * @return
     */
    @RequestMapping("/")
    public String sayHello(@RequestParam(value = "name", defaultValue = "Stranger") String name) {
        String greeting = this.restTemplate.getForObject(
                "http://say-hello/greeting",
                String.class
        );

        return String.format("%s, %s!", greeting, name);
    }
}
