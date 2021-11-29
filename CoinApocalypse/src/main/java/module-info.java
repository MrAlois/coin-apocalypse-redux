module gamedev.CoinApocalypse {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.base;
	requires javafx.graphics;

    opens CoinApocalypse to javafx.fxml;
    exports CoinApocalypse;
}
