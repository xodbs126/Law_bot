//package Lawbot.demo.legalinquiry;
//
//import Lawbot.demo.user.User;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//public class LegalInquiryService {
//
//    private final LegalInquiryRepository legalInquiryRepository;
//
//    public LegalInquiry createInquiry(User user, String description) {
//        LegalInquiry inquiry = new LegalInquiry();
//        inquiry.setUser(user);
//        inquiry.setDescription(description);
//        inquiry.setStatus(LegalInquiry.InquiryStatus.PENDING);
//        return legalInquiryRepository.save(inquiry);
//    }
//
//    public List<LegalInquiry> getAllInquiries() {
//        return legalInquiryRepository.findAll();
//    }
//
//    public Optional<LegalInquiry> getInquiryById(Long id) {
//        return legalInquiryRepository.findById(id);
//    }
//}
