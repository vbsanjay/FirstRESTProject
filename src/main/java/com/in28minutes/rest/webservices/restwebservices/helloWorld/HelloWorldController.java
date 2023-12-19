package com.in28minutes.rest.webservices.restwebservices.helloWorld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	//@RequestMapping are used to mapping URLs to the method
	@RequestMapping(path = "/Hello-World-Basic")
	public String sayHelloWorld() {
		return "Hello World using Request Mapping";
	}
	
	//@GetMapping is specialization of @RequestMapping used specifically for handling HTTP GET Request
	@GetMapping(path = "/Hello-World-GetMapping")
	public String sayHelloWorldv2() {
		return "Hello World using GetMapping";
	}
	
	//Returning a Bean will end up converting as json using @RepositoryBody(part of restController) + JacksonHttpMessageConverters(installed as a part of auto configuration)
	@GetMapping("/Hello-World-returnJSON")
	public HelloWorldBean sayHelloWorldv3() {
		return new HelloWorldBean("HelloWorld");
	}
	
	//@PathVariable is used when we have dynamic input form the URL and results should be loaded based on that dynamic input
	@GetMapping("/Hello-World/{name}")
	public HelloWorldBean sayHelloWorldToUser(@PathVariable String name) {
		return new HelloWorldBean("Hello World " + name + " !");
	}
}
