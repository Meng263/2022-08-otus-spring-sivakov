package ru.otus.mongo;

import org.springframework.context.annotation.Configuration;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.MountableFile;

@Configuration
public class MongoDBTestContainerConfig {
    @Container
    public static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0.3")
            .withExposedPorts(27017)
            .withCopyFileToContainer(
                    MountableFile.forClasspathResource("./init-scripts.js"),
                    "/docker-entrypoint-initdb.d/init-script.js"
            );

    static {
        mongoDBContainer.start();
        var mappedPort = mongoDBContainer.getMappedPort(27017);
        System.setProperty("mongodb.container.port", String.valueOf(mappedPort));
    }
}
