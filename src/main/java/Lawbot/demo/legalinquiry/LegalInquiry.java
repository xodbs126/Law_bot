package Lawbot.demo.legalinquiry;

import jakarta.persistence.*;
import lombok.*;
import Lawbot.demo.user.User;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "legal_inquiries")
public class LegalInquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // 법률 상담 요청한 사용자

    @Column(nullable = false, length = 500)
    private String description; // 사건 설명

    @Enumerated(EnumType.STRING)
    private InquiryStatus status; // 상담 필요 여부 (AI가 판단)

    public enum InquiryStatus {
        PENDING,  // 판단 대기
        NOT_NEEDED, // 상담 불필요
        NEEDED  // 상담 필요
    }
}
