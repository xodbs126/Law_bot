package Lawbot.demo.legalinquiry;

import Lawbot.demo.user.User;
import Lawbot.demo.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/inquiries")
@RequiredArgsConstructor
public class LegalInquiryController {

    private final LegalInquiryService legalInquiryService;
    private final UserRepository userRepository;

    // 상담 요청 생성
    @PostMapping("/{userId}")
    public ResponseEntity<LegalInquiry> createInquiry(@PathVariable Long userId, @RequestBody String description) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        LegalInquiry inquiry = legalInquiryService.createInquiry(user.get(), description);
        return ResponseEntity.ok(inquiry);
    }

    // 상담 요청 목록 조회
    @GetMapping
    public ResponseEntity<List<LegalInquiry>> getAllInquiries() {
        return ResponseEntity.ok(legalInquiryService.getAllInquiries());
    }

    // 상담 요청 상세 조회
    @GetMapping("/{id}")
    public ResponseEntity<LegalInquiry> getInquiryById(@PathVariable Long id) {
        return legalInquiryService.getInquiryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
