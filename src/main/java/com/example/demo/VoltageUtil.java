package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class VoltageUtil {

	public String encrypt(String string, String string2) {
		return new StringBuilder(string).reverse().toString();
	}
	
}
