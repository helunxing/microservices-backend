package timeag.backend.event.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EventJpaRepository extends JpaRepository <Event, Long>{
}
