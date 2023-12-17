package com.example.basicAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class SqlScriptRunner implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SqlScriptRunner(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
//        executeSqlScript("classpath:sql/createUser.sql");
    }

    private void executeSqlScript(String classPathResource) {
        try {
            ClassPathResource resource = new ClassPathResource(classPathResource);
            System.out.println(resource.getPath());
            System.out.println(resource.getFile());
            byte[] scriptBytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
            String scriptContent = new String(scriptBytes, StandardCharsets.UTF_8);

            jdbcTemplate.execute(scriptContent);
            System.out.println("SQL script executed successfully.");
        } catch (IOException e) {
            System.err.println("Error executing SQL script: " + e.getMessage());
        }
    }
}

