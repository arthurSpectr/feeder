package regexit.feeder.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import regexit.feeder.domain.Message;

import java.util.List;

public interface MessageRepo extends JpaRepository<Message, Integer> {
    List<Message> findByTag(String tag);
}
