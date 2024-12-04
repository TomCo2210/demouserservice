package users;

import java.util.List;
import java.util.Optional;

public interface UsersService {
	public User storeNewUser(User user);
	public Optional<User> findUserByEmail (String email);
	public List<User> findAllUsers (int page, int size);
	public List<User> findUsersByLastName (String lastName, int page, int size);
	public List<User> findUsersBornInYearsRange(int fromYear, int toYear, int page, int size);
	public void updateUser(String email, User update);
	public void deleteAllUsers();
}
