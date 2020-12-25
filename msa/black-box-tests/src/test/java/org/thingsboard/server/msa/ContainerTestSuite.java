package org.thingsboard.server.msa;

import org.junit.ClassRule;
import org.junit.extensions.cpsuite.ClasspathSuite;
import org.junit.rules.ExternalResource;
import org.junit.runner.RunWith;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.utility.Base58;

import java.io.File;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(ClasspathSuite.class)
@ClasspathSuite.ClassnameFilters({"org.thingsboard.server.msa.*Test"})
public class ContainerTestSuite {

    private static DockerComposeContainer testContainer;

    @ClassRule
    public static ThingsBoardDbInstaller installTb = new ThingsBoardDbInstaller();

    @ClassRule
    public static DockerComposeContainer getTestContainer() {
        if (testContainer == null) {
            boolean skipTailChildContainers = Boolean.valueOf(System.getProperty("blackBoxTests.skipTailChildContainers"));
            testContainer = new DockerComposeContainer(
                    new File("./../../docker/docker-compose.yml"),
                    new File("./../../docker/docker-compose.postgres.yml"),
                    new File("./../../docker/docker-compose.postgres.volumes.yml"),
                    new File("./../../docker/docker-compose.kafka.yml"))
                    .withPull(false)
                    .withLocalCompose(true)
                    .withTailChildContainers(!skipTailChildContainers)
                    .withEnv(installTb.getEnv())
                    .withEnv("LOAD_BALANCER_NAME", "")
                    .withExposedService("haproxy", 80, Wait.forHttp("/swagger-ui.html").withStartupTimeout(Duration.ofSeconds(400)));
        }
        return testContainer;
    }
}
