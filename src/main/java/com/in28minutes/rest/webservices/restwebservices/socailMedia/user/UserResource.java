package com.in28minutes.rest.webservices.restwebservices.socailMedia.user;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

//This is a controller class where user resouces are controllerd

@RestController
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
	
	//use: Talent API tester to check how post methods are executed
	@PostMapping("/users") 
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User savedUser = service.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location ).build(); //201 response status is taken care here
	}
	
	@DeleteMapping("/users/{id}") 
	public void deleteUser(@PathVariable int id) {
		service.deleteById(id);
	}
	
}
