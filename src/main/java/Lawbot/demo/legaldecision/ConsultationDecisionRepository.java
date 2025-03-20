package Lawbot.demo.legaldecision;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ConsultationDecisionRepository extends JpaRepository<ConsultationDecision, Long> {
    Optional<ConsultationDecision> findByInquiryId(Long inquiryId);
}
