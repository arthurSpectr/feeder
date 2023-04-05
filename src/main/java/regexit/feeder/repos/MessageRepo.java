package regexit.feeder.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import regexit.feeder.domain.Message;

import java.util.List;

public interface MessageRepo extends JpaRepository<Message, Long> {
    Page<Message> findAll(Pageable pageable);

    Page<Message> findByTag(String tag, Pageable pageable);
}
