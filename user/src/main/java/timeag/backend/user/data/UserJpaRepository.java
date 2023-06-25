package timeag.backend.user.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserJpaRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM user_table u WHERE u.sub = ?1")
    List<User> findBySub(String sub);
}
