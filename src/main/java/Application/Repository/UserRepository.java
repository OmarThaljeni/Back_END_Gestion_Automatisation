package Application.Repository;

import Application.Models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);


    Boolean existsByEmail(String email);

    @Query(fields = "{ '_id': 0, 'roles.$': 1 }")
    List<User> findByRolesId(String id);


}
