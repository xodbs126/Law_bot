package Lawbot.demo.legaldecision;

import jakarta.persistence.*;
import lombok.*;
import Lawbot.demo.legalinquiry.LegalInquiry;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "consultation_decisions")
public class ConsultationDecision {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "inquiry_id", nullable = false)
    private LegalInquiry inquiry;

    @Enumerated(EnumType.STRING)
    private DecisionResult decision;

    public enum DecisionResult {
        NOT_NEEDED,
        NEEDED
    }
}
