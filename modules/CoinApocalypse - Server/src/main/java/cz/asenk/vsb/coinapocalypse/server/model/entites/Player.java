package cz.asenk.vsb.coinapocalypse.server.model.entites;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String nickname;

    private String name;

    private String surname;

    @Column(name = "last_score")
    private Integer lastScore;

    @Column(name = "best_score")
    private Integer bestScore;
}
