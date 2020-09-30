package com.example.springbootdatajpa.app;

import com.example.springbootdatajpa.app.models.service.IUploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootDataJpaApplication implements CommandLineRunner {

    @Autowired
    IUploadFileService uploadFileService;
    public static void main(String[] args) {
        SpringApplication.run(SpringBootDataJpaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception { //para crear la carpeta uploads cada vez que inicia el proyecto
        uploadFileService.deleteAll();
        uploadFileService.init();
    }
}
