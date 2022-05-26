package cz.asenk.vsb.coinapocalypse.server;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;


import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Component;

@Component
public class GameSessionRepository implements Repository<GameRecord, Long> {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public CompletableFuture<GameRecord> save(GameRecord gr){
        if (gr.getId() == null) {
            em.persist(gr);
            return CompletableFuture.completedFuture(gr);
        }

        return CompletableFuture.completedFuture(em.merge(gr));
    }

    public List<GameRecord> findByPlayer(String name){
        TypedQuery<GameRecord> query = em.createQuery(
                "select c from GameRecord c where c.playerName = :name", GameRecord.class);

        return query.getResultList();
    }
}
