package brave.webmvc;

import java.util.Date;
import java.util.Random;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableAutoConfiguration
@RestController
public class Backend {

  private static final int DIVIDER = 50;
  private long count = 1;
  
  @RequestMapping("/api")
  public String printDate(@RequestHeader(name = "user-name", required = false) String username) throws Exception {
    count++;
    if((count % DIVIDER) == 0) {
      // Creating anomalous sleep for anomaly detection
      Thread.sleep(60000);
    }
    
    if (username != null) {
      return new Date().toString() + " " + username;
    }
    return new Date().toString();
  }

  public static void main(String[] args) {
    SpringApplication.run(Backend.class,
        "--spring.application.name=backend",
        "--server.port=9000"
    );
  }
}
