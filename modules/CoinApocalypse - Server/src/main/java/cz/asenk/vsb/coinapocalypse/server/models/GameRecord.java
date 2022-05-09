package cz.asenk.vsb.coinapocalypse.server.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "gamerecords")
@Data
@NoArgsConstructor
public class GameRecord {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "date_created")
    private LocalDateTime createDttm;

    @Column(name = "player_name")
    private String playerName;

    @Column(name = "final_score")
    private String score;

    @Column(name = "game_status")
    private GameState gameState;
}
