package Lawbot.demo.legalinquiry;

import Lawbot.demo.legaldecision.ConsultationDecision;
import Lawbot.demo.legaldecision.ConsultationDecisionRepository;
import Lawbot.demo.user.User;
import Lawbot.demo.ai.LawAdvisorAIClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LegalInquiryService {

    private final LegalInquiryRepository legalInquiryRepository;
    private final ConsultationDecisionRepository consultationDecisionRepository;
    private final LawAdvisorAIClient aiClient;

    public LegalInquiry createInquiry(User user, String description) {
        LegalInquiry inquiry = new LegalInquiry();
        inquiry.setUser(user);
        inquiry.setDescription(description);
        inquiry.setStatus(LegalInquiry.InquiryStatus.PENDING);

        LegalInquiry savedInquiry = legalInquiryRepository.save(inquiry);

        // AI 판단 실행
        String aiResult = aiClient.getLegalAdviceDecision(description);
        LegalInquiry.InquiryStatus decision = aiResult.equals("NEEDED") ?
                LegalInquiry.InquiryStatus.NEEDED : LegalInquiry.InquiryStatus.NOT_NEEDED;

        savedInquiry.setStatus(decision);
        legalInquiryRepository.save(savedInquiry);

        // 상담 결정 저장
        ConsultationDecision consultationDecision = new ConsultationDecision();
        consultationDecision.setInquiry(savedInquiry);
        ConsultationDecision.DecisionResult decisionResult =
                (decision == LegalInquiry.InquiryStatus.NEEDED) ?
                        ConsultationDecision.DecisionResult.NEEDED :
                        ConsultationDecision.DecisionResult.NOT_NEEDED;

        consultationDecision.setDecision(decisionResult);
        consultationDecisionRepository.save(consultationDecision);

        // 로그 출력
        log.info("📨 법률 상담 요청: {}", description);
        log.info("🤖 AI 판단 결과: {}", aiResult);
        log.info("📜 저장된 상담 결정: {}", decisionResult);

        return savedInquiry;
    }

    public List<LegalInquiry> getAllInquiries() {
        return legalInquiryRepository.findAll();
    }

    public Optional<LegalInquiry> getInquiryById(Long id) {
        return legalInquiryRepository.findById(id);
    }
}
