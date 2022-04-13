module gamedev.CoinApocalypse {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.base;
	requires javafx.graphics;
	requires java.desktop;
	requires java.sql;

    opens CoinApocalypse.scenes to javafx.fxml;
    exports CoinApocalypse;
    exports CoinApocalypse.scenes;
    exports CoinApocalypse.graphics;
    exports CoinApocalypse.game;
}
