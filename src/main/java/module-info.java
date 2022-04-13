module gamedev.CoinApocalypse {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.base;
	requires javafx.graphics;
	requires java.desktop;
	requires java.sql;

    opens cz.asenk.vsb.coinapocalypse.scenes to javafx.fxml;
    exports cz.asenk.vsb.coinapocalypse;
}
