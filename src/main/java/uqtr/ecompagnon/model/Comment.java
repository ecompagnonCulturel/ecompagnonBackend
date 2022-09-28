package uqtr.ecompagnon.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE,
            generator = "comment_sequence")
    @SequenceGenerator(name = "comment_sequence", sequenceName = "comment_sequence", allocationSize = 1)

    @Column(name = "id")
    private long id;

    @ManyToOne()
    @JoinColumn(name = "commentUser", referencedColumnName = "idUsers")
    private AppUser commentUser;

    @ManyToOne()
    @JoinColumn(name = "commentResource", referencedColumnName = "id")
    private Resources commentResource;

    @Column(name = "commentDate")
    private LocalDateTime commentDate;

    @Column(name = "commentDesc")
    private String commentDesc;


    @Column(name = "commentStatus")
    private long commentStatus;


}
