package it.wcc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
 
@SpringBootApplication
@EnableResourceServer
//@RestController
public class Application /*extends ResourceServerConfigurerAdapter */ {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

//    private static final Log logger = LogFactory.getLog(Application.class);
//
//    
//    private String message = "Hello world!";
    
//    @RequestMapping("/user")
//    public Principal user(Principal user) {
//        logger.info("AS /user has been called");
//        logger.debug("user info: " + user.toString());
//        return user;
//    }
    
//    //@PreAuthorize("#oauth2.hasScope('resource-server-read')")
//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public String home() {
//        return "Hollaaaaaa  "+message;
//    }
//    
//    @RequestMapping(value = "/", method = RequestMethod.POST)
//    public void updateMessage(@RequestBody String message) {
//        this.message = message;
//    }
}
