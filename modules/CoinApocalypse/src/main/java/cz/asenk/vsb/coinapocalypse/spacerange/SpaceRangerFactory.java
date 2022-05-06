package cz.asenk.vsb.coinapocalypse.spacerange;

import com.almasb.fxgl.animation.Interpolators;
import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.components.ProjectileComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGLForKtKt.animationBuilder;
import static com.almasb.fxgl.dsl.FXGLForKtKt.entityBuilder;


public class SpaceRangerFactory implements EntityFactory {

    @Spawns("player")
    public Entity newPlayer(SpawnData data){
        var top = new Rectangle(60, 20, Color.BLUE);
        top.setStroke(Color.GRAY);

        var body = new Rectangle(25, 60, Color.BLUE);
        body.setStroke(Color.GRAY);

        var bot = new Rectangle(60, 20, Color.BLUE);
        bot.setStroke(Color.GRAY);
        bot.setTranslateY(40);

        return entityBuilder()
                .type(EntityType.PLAYER)
                .from(data)
                .view(body)
                .view(top)
                .view(bot)
                .build();
    }

    @Spawns("projectile")
    public Entity newProjectile(SpawnData data) {
        var view = new Rectangle(30, 3, Color.LIGHTBLUE);
        view.setStroke(Color.WHITE);
        view.setArcWidth(15);
        view.setArcHeight(10);

        return entityBuilder()
                .type(EntityType.PROJECTILE)
                .from(data)
                .viewWithBBox(view)
                .collidable()
                .zIndex(-5)
                .with(new ProjectileComponent(new Point2D(1, 0), 760))
                .build();
    }

    @Spawns("enemy")
    public Entity newEnemy(SpawnData data) {
        var view = new Rectangle(80, 20, Color.RED);
        view.setStroke(Color.GRAY);
        view.setStrokeWidth(0.5);

        animationBuilder()
                .interpolator(Interpolators.SMOOTH.EASE_OUT())
                .duration(Duration.seconds(0.5))
                .repeatInfinitely()
                .animate(view.fillProperty())
                .from(Color.RED)
                .to(Color.DARKRED)
                .buildAndPlay();

        return entityBuilder()
                .type(EntityType.ENEMY)
                .from(data)
                .viewWithBBox(view)
                .collidable()
                .with(new ProjectileComponent(new Point2D(-1, 0), FXGLMath.random(50, 150)))
                .build();
    }
}
