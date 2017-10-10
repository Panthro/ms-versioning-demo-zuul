package ms.versioning.test.versioning.ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class VersioningMSApplication {

    public static void main(String[] args) {
        SpringApplication.run(VersioningMSApplication.class, args);
    }




    @GetMapping("/route1")
    public ResponseEntity<String> route1() {
        return ResponseEntity.ok("OK");
    }
}
