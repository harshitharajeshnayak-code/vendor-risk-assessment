package com.internship.tool;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import org.testcontainers.DockerClientFactory;
import org.testcontainers.containers.PostgreSQLContainer;

public class PostgresContainerTest {

    @Test
    void testContainerRunning() {

        // Skip test if Docker is not reachable
        Assumptions.assumeTrue(
                DockerClientFactory.instance().isDockerAvailable(),
                "Docker not available, skipping container test"
        );

        // Create container only after Docker check
        try (PostgreSQLContainer<?> postgres =
                     new PostgreSQLContainer<>("postgres:15")
                             .withDatabaseName("testdb")
                             .withUsername("test")
                             .withPassword("test")) {

            postgres.start();

            System.out.println("DB URL: " + postgres.getJdbcUrl());
            System.out.println("PostgreSQL container started successfully.");
        }
    }
}