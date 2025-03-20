package Lawbot.demo.user;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;  // 사용자 ID

    @Column(nullable = false)
    private String password;  // 비밀번호

    @Column(nullable = false)
    private String email;     // 이메일

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;    // 역할 (일반 사용자 / 변호사)

    public enum UserRole {
        USER, LAWYER
    }
}
