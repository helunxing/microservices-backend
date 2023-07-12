package timeag.backend.joinInfo.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface JoinInfoJpaRepository extends JpaRepository<Join, Long> {
    @Query("SELECT j FROM Join_table j WHERE j.eventID = ?1 AND j.joinerID = ?2")
    List<Join> findByEventUserPair(long eventId, long userId);
}
