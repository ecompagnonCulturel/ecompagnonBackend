package uqtr.ecompagnon.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "confirmationPwdToken")
public class ConfirmationPwdToken {


    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "confirmationpwd_token_generator"
    )
    @SequenceGenerator(
            name = "confirmationpwd_token_generator",
            sequenceName = "confirmationpwd_token_sequence",
            allocationSize = 1
    )
    @Column(name = "id")
    private Long id;
    @Column(nullable = false )
    private String token;
    @Column(nullable = false )
    private LocalDateTime createdAt;
    @Column(nullable = false )
    private LocalDateTime expiresAt;
    private LocalDateTime confirmeAt;

    @ManyToOne
    @JoinColumn(nullable = false,
            name="appUser"      )
    private AppUser appUser;

    public ConfirmationPwdToken(String token,
                             LocalDateTime createdAt,
                             LocalDateTime expiredAt,
                             AppUser appUser) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiredAt;
        this.appUser=appUser;
    }
}
