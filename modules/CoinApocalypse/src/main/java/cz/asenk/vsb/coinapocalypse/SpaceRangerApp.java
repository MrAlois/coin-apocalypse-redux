package cz.asenk.vsb.coinapocalypse;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.core.math.FXGLMath;
import cz.asenk.vsb.coinapocalypse.spacerange.EntityType;
import cz.asenk.vsb.coinapocalypse.spacerange.SpaceRangerFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.*;

public class SpaceRangerApp extends GameApplication {
    @Override
    protected void initSettings(GameSettings settings) {
        settings.setTitle("Space Ranger");
        settings.setWidth(800);
        settings.setHeight(600);
    }

    @Override
    protected void initInput() {
        onKey(KeyCode.W, () -> getGameWorld().getSingleton(EntityType.PLAYER).translateY(-5));
        onKey(KeyCode.S, () -> getGameWorld().getSingleton(EntityType.PLAYER).translateY(5));
        onKey(KeyCode.A, () -> getGameWorld().getSingleton(EntityType.PLAYER).translateX(-5));
        onKey(KeyCode.D, () -> getGameWorld().getSingleton(EntityType.PLAYER).translateX(5));

        onBtnDown(MouseButton.PRIMARY, () -> {
            double y = getGameWorld().getSingleton(EntityType.PLAYER).getY();
            spawn("projectile", 0, y + 10);
            spawn("projectile", 0, y + 50);
        });

        onBtnDown(MouseButton.SECONDARY, () -> {
            double x = getGameWorld().getSingleton(EntityType.PLAYER).getX() + getGameWorld().getSingleton(EntityType.PLAYER).getWidth();
            double y = getGameWorld().getSingleton(EntityType.PLAYER).getY();

            spawn("projectile", x, y + 10);
            spawn("projectile", x, y + 15);
            spawn("projectile", x, y + 20);
            spawn("projectile", x, y + 25);
            spawn("projectile", x, y + 50);
        });
    }

    @Override
    protected void initGame() {
        getGameScene().setBackgroundColor(Color.BLACK);

        getGameWorld().addEntityFactory(new SpaceRangerFactory());

        spawn("player", 0, getAppHeight() / 2.0 - 30);

        run(() -> {
            double x = getAppWidth();
            double y = FXGLMath.random(0, getAppHeight() - 20);

            spawn("enemy", x, y);
        }, Duration.seconds(0.25));
    }

    @Override
    protected void initPhysics() {
        onCollisionBegin(EntityType.PROJECTILE, EntityType.ENEMY, (proj, enemy) -> {
            proj.removeFromWorld();
            enemy.removeFromWorld();
        });
    }

    @Override
    protected void onUpdate(double tpf) {
        var enemiesThatReachedBase = getGameWorld().getEntitiesFiltered(e -> e.isType(EntityType.ENEMY) && e.getX() < 0);

        if (!enemiesThatReachedBase.isEmpty()) {
            showMessage("Game Over!", () -> getGameController().startNewGame());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
