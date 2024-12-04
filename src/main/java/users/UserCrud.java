package users;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserCrud extends CrudRepository<User, String> {
	public List<User> findAll (Pageable pageable);
	public List<User> findAllByLastName (
			@Param("lastName") String lastName,
			Pageable pageable);
	public List<User> findAllByBirthYearGreaterThanEqualAndBirthYearLessThanEqual(
			@Param("fromYear") int fromYear,
			@Param("toYear") int toYear,
			Pageable pageable);
}
