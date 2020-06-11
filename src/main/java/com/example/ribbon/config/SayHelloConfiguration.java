package com.example.ribbon.config;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AvailabilityFilteringRule;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PingUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration        why throwing error saying IClientConfig cannot be found if turned on????
public class SayHelloConfiguration {

    @Autowired
    IClientConfig ribbonClientConfig;

    /**
     * override to ping the server for it's status (200)
     * @param config
     * @return
     */
    @Bean
    public IPing ribonPing(IClientConfig config) {
        return new PingUrl();
    }

    /**
     * for filtering out any "dead" server
     * @param config
     * @return
     */
    @Bean
    public IRule ribbonRule(IClientConfig config) {
        return new AvailabilityFilteringRule();
    }
}
