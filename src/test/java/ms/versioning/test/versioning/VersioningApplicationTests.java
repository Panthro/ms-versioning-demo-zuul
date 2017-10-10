package ms.versioning.test.versioning;

import ms.versioning.test.versioning.ms.VersioningMSApplication;
import org.awaitility.Duration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = VersioningMSApplication.class)
public class VersioningApplicationTests {


    @Autowired
    private Environment environment;

    @Autowired
    private RouteLocator routeLocator;

    @Test
    public void versionIsLoaded() {

        final String metadataVersion = environment.getProperty("eureka.instance.metadata-map.version");

        assertThat(metadataVersion).isNotEmpty();

    }

    @Test
    public void versionIsMappedInZuul() {
        await().atMost(Duration.ONE_MINUTE).untilAsserted(() -> {

            final boolean matchRoute = routeLocator.getRoutes().stream()
                    .filter(route -> "ms-versioning-v1".equals(route.getId()))
                    .allMatch(route -> route.getFullPath().equals("v1/versioning"));

            assertThat(routeLocator.getRoutes()).hasSize(1);

            assertThat(matchRoute).isTrue();
        });
    }

}
