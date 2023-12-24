package com.in28minutes.rest.webservices.restwebservices.socailMedia.user;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

//This is a controller class where user resouces are controllerd

@RestController
@Validated
public class UserResource {
	
	private UserDaoService service;
	
	public UserResource(UserDaoService service) {
		this.service = service;
	}
	
	@GetMapping("/users")
	public List<User> retrieveAllUser(){
		return service.findAll();
	}
	
	@GetMapping("/users/{id}")
	public User retrieveUser(@PathVariable int id){
		User foundUser = service.findOne(id);
		if(foundUser == null) { //if no user found return the below object
			throw new UserNotFoundException("id: " + id);
		}
		return foundUser;
	}
	
	//This will work but the status code returned is 200 which is not appropriate. 201 should be returned <- it is done by the below method
//	@PostMapping("/users") 
//	public void createUser(@RequestBody User user) {
//		service.save(user);
//	}
	
	//use Talent API tester to check how post methods are executed
	//this method will return correct response code 201
	@PostMapping("/users") 
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = service.save(user);
		//return ResponseEntity.created(null ).build(); // <-- this line and above line is more enough to return 201 response
		URI location = ServletUriComponentsBuilder.fromCurrentRequest() //get the current directory "/users"
				.path("/{id}") //link followed by the current directory "/users/{id}"
				.buildAndExpand(savedUser.getId()).toUri(); //assign value to the dynamic variable and convert it into URI
		return ResponseEntity.created(location ).build(); // <-- we need to in terms of consumer. consumer can have the URI of the user created from the above line.
		
	}
	
	@DeleteMapping("/users/{id}") 
	public void deleteUser(@PathVariable int id) {
		service.deleteById(id);
	}
	
}
