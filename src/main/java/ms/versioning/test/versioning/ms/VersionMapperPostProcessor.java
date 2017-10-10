package ms.versioning.test.versioning.ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class VersionMapperPostProcessor implements EnvironmentPostProcessor {
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment configurableEnvironment, SpringApplication springApplication) {
        final String appName = configurableEnvironment.getProperty("spring.application.name");

        final String version = appName.substring(appName.lastIndexOf('v'));


        final MapPropertySource versionPropertySource = new MapPropertySource("versioning-property-source", Collections.singletonMap("eureka.instance.metadata-map.version", version));

        configurableEnvironment.getPropertySources().addLast(versionPropertySource);

    }
}
