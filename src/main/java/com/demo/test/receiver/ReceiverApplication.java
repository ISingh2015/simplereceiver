package com.demo.test.receiver;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Inderjit Singh Sanhotra
 * @version 0.0.1
 * @since 01-06-2021
 *
 */

@SpringBootApplication
@RestController()
@Slf4j
public class ReceiverApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReceiverApplication.class, args);
    }

    @GetMapping(value = "/healthcheck-receiver-app")
    public ResponseEntity<String> healthCheck() {
        log.info("Status: UP.  on : {}", new Date());
        return new ResponseEntity<>("Status Receiver App: UP. on : " + new Date(), HttpStatus.OK);
    }

    @GetMapping(value = "/customer/get")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/customer/list";
        try {
            ResponseEntity<List<Customer>> custList = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Customer>>() {
                    }
            );
            if (custList.getStatusCode().is2xxSuccessful()) {
                System.out.println("Response Body: " + custList.getBody());
                System.out.println("Status Code: " + custList.getStatusCode());
            } else {
                System.err.println("Error: " + custList.getStatusCode());
            }

            log.info("{} Customers List {}", new Date(), custList);
            return custList;
        } catch (Exception ioe) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customers not found or could not be parsed ");
        }

    }

}
