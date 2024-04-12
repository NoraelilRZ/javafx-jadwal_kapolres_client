module JadwalKapolresClient {
	requires javafx.controls;
	requires java.logging;
	requires javafx.fxml;
	requires java.sql;
	requires javafx.graphics;
	requires javafx.base;
	
	opens models to javafx.fxml;
	opens application to javafx.graphics, javafx.fxml;
	
	exports models;
	exports application;
}
