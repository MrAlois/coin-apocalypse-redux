package cz.asenk.vsb.coinapocalypse.server.model.entites;

import cz.asenk.vsb.coinapocalypse.server.enums.GameState;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Builder(toBuilder = true)
@Getter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "gamerecords")
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
