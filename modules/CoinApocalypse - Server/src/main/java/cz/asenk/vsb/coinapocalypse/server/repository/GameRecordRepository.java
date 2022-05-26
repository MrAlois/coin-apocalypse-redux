package cz.asenk.vsb.coinapocalypse.server.repository;

import cz.asenk.vsb.coinapocalypse.server.model.GameRecord;
import org.springframework.data.repository.CrudRepository;

public interface GameRecordRepository extends CrudRepository<GameRecord, Long> {}
