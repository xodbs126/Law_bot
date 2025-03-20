package Lawbot.demo.legalinquiry;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LegalInquiryRepository extends JpaRepository<LegalInquiry, Long> {
    List<LegalInquiry> findByStatus(LegalInquiry.InquiryStatus status);
}
