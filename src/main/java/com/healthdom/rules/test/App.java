package com.healthdom.rules.test;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class App {

	 public static void main(String[] args) throws IOException, GeneralSecurityException {
		 SheetUtilities.openSheet();
	 }
}