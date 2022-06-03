package cz.asenk.vsb.coinapocalypse.server.repository;

import cz.asenk.vsb.coinapocalypse.server.model.entites.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Long> {}
