package brave.webmvc;

import java.util.Date;
import java.util.PrimitiveIterator;
import java.util.Random;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableAutoConfiguration
@RestController
public class Backend {

  private final PrimitiveIterator.OfLong random = new Random(System.currentTimeMillis())
          .longs(30000, 60000)
          .iterator();

  @RequestMapping("/api")
  public String printDate(@RequestHeader(name = "user-name", required = false) String username) throws Exception {
    // Creating anomalous sleep for anomaly detection
    if((System.currentTimeMillis() / 1000 % 300) == 0) {
      Thread.sleep(random.nextLong());
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
