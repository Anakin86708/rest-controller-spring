package com.ariel.restController.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserCommandLineRunner implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(UserCommandLineRunner.class);

    @Autowired
    private UserRepository repository;

    @Override
    public void run(String... args) throws Exception {
        repository.save(new User("User0", "Admin"));
        repository.save(new User("User1", "User"));
        repository.save(new User("User2", "Admin"));
        repository.save(new User("User3", "User"));

        log.info("-------------------------------");
        log.info("Finding all users");
        log.info("-------------------------------");
        for (User user : repository.findAll()) {
            log.info(user.toString());
        }

        log.info("\n");
        log.info("-------------------------------");
        log.info("Finding all users with role Admin");
        log.info("-------------------------------");
        for (User user : repository.findByRole("Admin")) {
            log.info(user.toString());
        }
    }
}
