package cz.asenk.vsb.coinapocalypse.server.repository;

import cz.asenk.vsb.coinapocalypse.server.model.Player;
import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<Player, Long> {}