package app.repository;

import app.model.InboxMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface InboxMessageRepository extends JpaRepository<InboxMessage, UUID> {

    List<InboxMessage> findByUserId(UUID userId);
}
