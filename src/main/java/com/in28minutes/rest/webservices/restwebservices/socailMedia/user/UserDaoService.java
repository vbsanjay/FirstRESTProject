package com.in28minutes.rest.webservices.restwebservices.socailMedia.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	//For simplicity lets have a static list instead of Database
	private static List<User> users = new ArrayList<>();
	private static int userNumber = 0;
	static {
		users.add(new User(++userNumber, "Sanjay", LocalDate.now().minusYears(26)));
		users.add(new User(++userNumber, "Dhinakaran", LocalDate.now().minusYears(27)));
		users.add(new User(++userNumber, "Vijay", LocalDate.now().minusYears(28)));
		}
	
	public List<User> findAll() {
		return users;
	}
	
	//public User save(User user) {}
	public User findOne(int id) {
		Predicate<? super User> predicate = user -> user.getId().equals(id);
		//return users.stream().filter(predicate).findFirst().get();
		//we need to return null if the user is not found. in the above line it does not return null
		return users.stream().filter(predicate).findFirst().orElse(null); 
	}

	public User save(User user) {
		user.setId(++userNumber);
		users.add(user);
		return user;
	}
}
