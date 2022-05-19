package cz.asenk.vsb.coinapocalypse.server;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.*;

import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;

import lombok.*;

@Entity
@Table(name = "gamerecords")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class GameRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @CreationTimestamp
    @Column(name = "date_created")
    private LocalDateTime createDttm;

    @Column(name = "player_name")
    @NonNull private String playerName;

    @Column(name = "final_score")
    @NonNull private String score;

    @Enumerated(EnumType.STRING)
    @Column(name = "game_status")
    @NonNull private GameState gameState;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        GameRecord that = (GameRecord) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
