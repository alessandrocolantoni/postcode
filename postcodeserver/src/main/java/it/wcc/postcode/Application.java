package it.wcc.postcode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableResourceServer
@RestController
public class Application extends ResourceServerConfigurerAdapter  {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    
    private String message = "Hello world!";
    
    
    //@PreAuthorize("#oauth2.hasScope('resource-server-read')")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        return "Hollaaaaaa  "+message;
    }
    
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void updateMessage(@RequestBody String message) {
        this.message = message;
    }
}
