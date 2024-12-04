package users;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceInMemory implements UsersService {
	private UserCrud userCrud;

	public UsersServiceInMemory(UserCrud userCrud) {
		this.userCrud = userCrud;
	}

	@Override
	public User storeNewUser(User user) {
		if (this.userCrud.existsById(user.getEmail())) {
			throw new RuntimeException("User already exists with email: " + user.getEmail());
		}
		
		if (user.getEmail() == null 
			|| user.getEmail().isEmpty()
			|| user.getEmail().indexOf("@") < 0
			|| user.getBirthYear() < 1900
			|| user.getFirstName() == null
			|| user.getFirstName().isEmpty()
			|| user.getLastName() == null
			|| user.getLastName().isEmpty()
			|| user.getRoles() == null
			|| user.getRoles().isEmpty()) {
			throw new RuntimeException("missing new user details");
		}
		return this.userCrud
			.save(user);
	}

	@Override
	public Optional<User> findUserByEmail(String email) {
		return this.userCrud
			.findById(email);
	}

	@Override
	public List<User> findAllUsers(int page, int size) {
		return this.userCrud
			.findAll(PageRequest.of(page, size, Direction.ASC, "email"));
	}

	@Override
	public List<User> findUsersByLastName(String lastName, int page, int size) {
		return this.userCrud
			.findAllByLastName(lastName, PageRequest.of(page, size, Direction.ASC, "email"));
	}

	@Override
	public List<User> findUsersBornInYearsRange(int fromYear, int toYear, int page, int size) {
		return this.userCrud
			.findAllByBirthYearGreaterThanEqualAndBirthYearLessThanEqual(
					fromYear, 
					toYear, 
					PageRequest.of(page, size, Direction.ASC, "birthYear", "email"));
	}

	@Override
	public void updateUser(String email, User update) {
		User existing = this.findUserByEmail(email)
			.orElseThrow(()->new UserNotFoundException("Could not find existing user by email: " + email));
		
		if (update.getBirthYear() > 1900){
			existing.setBirthYear(update.getBirthYear());
		}
		
		if (update.getFirstName() != null) {
			existing.setFirstName(update.getFirstName());
		}
		
		if (update.getLastName() != null) {
			existing.setLastName(update.getLastName());
		}
		
		if (update.getRoles() != null && !update.getRoles().isEmpty()) {
			existing.setRoles(update.getRoles());
		}
		
		this.userCrud
			.save(existing);
	}

	@Override
	public void deleteAllUsers() {
		this.userCrud
			.deleteAll();
	}
}
