package com.stb.money_transfer_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@SpringBootApplication
public class MoneyTransferAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoneyTransferAppApplication.class, args);


    }

}
