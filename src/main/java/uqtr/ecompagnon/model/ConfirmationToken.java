package uqtr.ecompagnon.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uqtr.ecompagnon.model.AppUser;

import javax.persistence.*;
import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@Entity(name = "confirmationToken")
public class ConfirmationToken {


    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "confirmation_token_generator"
    )
    @SequenceGenerator(
            name = "confirmation_token_generator",
            sequenceName = "confirmation_token_sequence",
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

    public ConfirmationToken(String token,
                             LocalDateTime createdAt,
                             LocalDateTime expiredAt,
                             AppUser appUser) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiredAt;
        this.appUser=appUser;
    }
}
