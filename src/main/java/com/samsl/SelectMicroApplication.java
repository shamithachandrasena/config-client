package com.samsl;

import com.fasterxml.jackson.databind.util.JSONPObject;
import jdk.nashorn.internal.ir.debug.JSONWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@SpringBootApplication
public class SelectMicroApplication {

	public static void main(String[] args) {
		SpringApplication.run(SelectMicroApplication.class, args);
	}

}


@RefreshScope
@RestController
class MessageRestController {

	@Autowired
	private Environment env;

	@Value("${message:Hello default}")
	private String message;

	@RequestMapping("/message")
	String getMessage() {
		return this.message;
	}

	@RequestMapping(value="/property",produces = { "application/json"})
	Object getMessage(@RequestParam String name) {
		HashMap<String, String> map = new HashMap<>();
		map.put(name, this.env.getProperty(name));
		return map;
	}


}
