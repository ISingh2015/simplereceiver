package com.demo.test.receiver;

import java.io.IOException;
import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Inderjit Singh Sanhotra
 * @since 01-06-2021
 * @version 0.0.1
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

	@PostMapping(value = "/sendCustomers")
	public ResponseEntity<Customer[]> getAllCustomers(String custJsonList) {
		Customer[] custList = null;
		ObjectMapper mapper = new ObjectMapper();
		if (custJsonList==null || custJsonList.equals("")) throw new ResponseStatusException(HttpStatus.NO_CONTENT,"Customers List not provided to send");
		try {
			custList = mapper.readValue(custJsonList, Customer[].class);
			log.info("{} Customers List {}", new Date(), custList);
			return new ResponseEntity<>(custList, HttpStatus.OK);
		} catch (IOException ioe) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Customers not found or could not be parsed",ioe);
		}

	}

}
