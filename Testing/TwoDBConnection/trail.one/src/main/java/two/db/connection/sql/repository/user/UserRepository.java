package two.db.connection.sql.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import two.db.connection.sql.entity.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
