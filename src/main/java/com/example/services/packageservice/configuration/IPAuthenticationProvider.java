package com.example.services.packageservice.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

//@Component
public class IPAuthenticationProvider implements AuthenticationProvider {
    private Set<String> whitelist = new HashSet<String>();
    @PostConstruct
    private void init() throws Exception{
        InputStream permFile = null;
        try {
            permFile = new ClassPathResource("whitelist.json").getInputStream();
            whitelist = new ObjectMapper().readValue(permFile, HashSet.class);
        }
        finally{
            permFile.close();
        }
    }

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        WebAuthenticationDetails details = (WebAuthenticationDetails) auth.getDetails();
        String userIp = details.getRemoteAddress();
        if (!whitelist.contains(userIp) && !matchSubnet(userIp)) {
            throw new BadCredentialsException("Invalid IP Address");
        }
        return auth;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    private boolean matchSubnet(String userIp){
        String[] values = userIp.split("\\.");
        //Values < 4 means it is not an IPv4.
        //Issue: This code does not take care of IPv6.  The IPv6  full address needs to be mentioned in the whitelist.
        if(values.length < 4) return false;
        for(String ip : whitelist) {
            StringBuilder subnet = new StringBuilder();
            for(int i  = 0; i < values.length; i++){
                if(i == 0) subnet.append(values[0]);
                else subnet.append(".").append(values[i]);
                if(ip.contains(subnet)) return true;
            }
        }
        return false;
    }
}
