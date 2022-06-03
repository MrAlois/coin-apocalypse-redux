package cz.asenk.vsb.coinapocalypse.game.entities;

import cz.asenk.vsb.coinapocalypse.game.enums.GameState;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Record {
    private final String playerName;
    private final String score;
    private final GameState gameState;
}
