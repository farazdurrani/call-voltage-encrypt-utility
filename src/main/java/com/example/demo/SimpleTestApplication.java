package com.example.demo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/rest")
public class SimpleTestApplication {

    public static void main(String[] args) {
	SpringApplication.run(SimpleTestApplication.class, args);
    }

    @Autowired
    private VoltageUtil voltage;

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @PostMapping("voltage")
    public ResponseEntity<Map> voltage(RequestEntity<Map> req) {

	Map<String, String> envValues = new HashMap<>();

	req.getBody().forEach((parentIdentifier, valueToEncryptAndIdentityToUse) -> {

	    ((Map) valueToEncryptAndIdentityToUse)
	        .forEach((valueToEncrypt, keyUsedToEncryptValue) -> {

		    envValues.put((String) parentIdentifier,

		        voltage.encrypt(valueToEncrypt.toString(),
		            keyUsedToEncryptValue.toString()));

	        });

	});

	return new ResponseEntity<>(envValues, HttpStatus.OK);

    }

}
