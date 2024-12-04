package users;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserInitializer implements CommandLineRunner {
	private Log logger = LogFactory.getLog(UserInitializer.class);
	private UsersService users;
	private Random random;
	private List<String> lastNames;
	private List<String> roles;
	
	public UserInitializer(UsersService users) {
		this.users = users;
		this.random = new Random(System.currentTimeMillis());
		this.lastNames = List.of("Smith", "Jones", "James", "Jills");
		this.roles = List.of("ADMIN", "STUDENT", "TEACHER");
	}

	@Override
	public void run(String... args) throws Exception {
		Stream.of("jane", "jill", "jack", "jeff", "joanna")
			.map(name->{
				User newUser = new User();
				
				newUser.setEmail(name + "@s.afeka.ac.il");
				newUser.setBirthYear(this.random.nextInt(50) + 1950);
				newUser.setFirstName(name.substring(0, 1).toUpperCase() + name.substring(1));
				newUser.setLastName(this.lastNames.get(this.random.nextInt(lastNames.size())));
				newUser.setRoles(List.of(this.roles.get(this.random.nextInt(this.roles.size()))));
				
				return newUser;
			})
			.map(this.users::storeNewUser)
			.forEach(this.logger::debug);
	}

}
