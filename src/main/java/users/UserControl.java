package users;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = { "/users" })
public class UserControl {
	private UsersService users;

	public UserControl(UsersService users) {
		this.users = users;
	}

	@PostMapping(
		consumes = {MediaType.APPLICATION_JSON_VALUE},
		produces = {MediaType.APPLICATION_JSON_VALUE})
	public User storeNewUser(@RequestBody User user) {
		return users.storeNewUser(user);
	}

	@GetMapping(
		path = {"/{email}"}, 
		produces = {MediaType.APPLICATION_JSON_VALUE})
	public User findUserByEmail(
			@PathVariable("email") String email) {
		return users.findUserByEmail(email)
			.orElseThrow(()->new UserNotFoundException("Could not find user by email: " + email));
	}

	@GetMapping(
		produces = {MediaType.APPLICATION_JSON_VALUE})
	public User[] findAllUsers(
			@RequestParam(name = "page", required = false, defaultValue = "0") int page, 
			@RequestParam(name = "size", required = false, defaultValue = "20") int size) {
		return users.findAllUsers(page, size)
			.toArray(new User[0]);
	}

	@GetMapping(
		path = {"/search/byLastName/{lastName}"}, 
		produces = {MediaType.APPLICATION_JSON_VALUE})
	public User[] findUsersByLastName(
			@PathVariable("lastName") String lastName, 
			@RequestParam(name = "page", required = false, defaultValue = "0") int page, 
			@RequestParam(name = "size", required = false, defaultValue = "20") int size) {
		return users.findUsersByLastName(lastName, page, size)
			.toArray(new User[0]);
	}

	@GetMapping(
		path = {"/search/byBirthYear/{fromYear}/{toYear}"}, 
		produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<User> findUsersBornInYearsRange(
			@PathVariable("fromYear") int fromYear, 
			@PathVariable("toYear") int toYear, 
			@RequestParam(name = "page", required = false, defaultValue = "0") int page, 
			@RequestParam(name = "size", required = false, defaultValue = "20") int size) {
		return users.findUsersBornInYearsRange(fromYear, toYear, page, size);
	}

	@PutMapping(
		path = {"/{email}"},
		consumes = {MediaType.APPLICATION_JSON_VALUE})
	public void updateUser(
		@PathVariable("email") String email, 
		@RequestBody User update) {
		users.updateUser(email, update);
	}

	@DeleteMapping
	public void deleteAllUsers() {
		users.deleteAllUsers();
	}
}
