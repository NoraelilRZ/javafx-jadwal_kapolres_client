package application;

import config.ConDatabase;

import java.lang.reflect.Method;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;
import models.JadwalModel;

public class JadwalController implements Initializable {

	
  @FXML
  private TableView<JadwalModel> tableJadwal;
  
  @FXML
  private TableColumn<JadwalModel, String> kegiatanCol;
  
  @FXML
  private TableColumn<JadwalModel, String> tanggalCol;
  
  @FXML
  private TableColumn<JadwalModel, String> waktuCol;
  
  @FXML
  private TableColumn<JadwalModel, String> hariCol;
  
  @FXML
  private TableColumn<JadwalModel, String> tempatCol;
  
  @FXML
  private TableColumn<JadwalModel, String> deskripsiCol;
  
  private List<JadwalModel> list;
  
  ObservableList<JadwalModel> JadwalList;
  
  
  public void initialize(URL arg0, ResourceBundle arg1) {
	  Timer timer = new Timer();
	  
	  timer.schedule(new TimerTask() {
		  public void run() {
			  refreshTable();
		  }
	  }, 0, 1000);
  }
  
  private String getHariDariJadwal(int Hari) {
	  switch(Hari) {
	  case 1: return "Minggu";
	  case 2:return "Senin";
	  case 3:return "Selasa";
	  case 4:return "Rabu";
	  case 5:return "Kamis";
	  case 6:return "Jum'at";
	  case 7:return "Sabtu";
		  default: return "";
	  }
	  
  }
  
  @FXML
  private void refreshTable() {
    JadwalList = FXCollections.observableArrayList();
    try {
      JadwalList.clear();
      Statement statement = ConDatabase.getConnection().createStatement();
      ResultSet result = statement.executeQuery("SELECT id_kegiatan, nama_kegiatan,tempat,DATE(jadwal) as tanggal , TIME(jadwal) as waktu, DAYOFWEEK(jadwal) as hari,deskripsi FROM jadwal_kegiatan ORDER BY jadwal ASC");
      while (result.next()) {
    	  String tanggals = result.getString("tanggal").substring(8,10)+"-"+result.getString("tanggal").substring(5,7)+"-"+result.getString("tanggal").substring(0,4);
        JadwalModel jadwal = new JadwalModel(
            result.getString("nama_kegiatan"), 
            result.getString("waktu").substring(0, 5) + " WIB",
            tanggals, 
            getHariDariJadwal(result.getInt("hari")),
            result.getString("tempat"), 
            result.getString("deskripsi"));
        JadwalList.add(jadwal);
      } 
      tanggalCol.setCellValueFactory((Callback)new PropertyValueFactory("tanggal"));
      tempatCol.setCellValueFactory((Callback)new PropertyValueFactory("tempat"));
      tempatCol.setCellFactory(tc -> {
  	    TableCell<JadwalModel, String> cell = new TableCell<>();
  	    Text text = new Text();
  	    cell.setGraphic(text);
  	    cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
  	    text.wrappingWidthProperty().bind(tempatCol.widthProperty());
  	    text.textProperty().bind(cell.itemProperty());
  	    return cell ;
  	});
      kegiatanCol.setCellValueFactory((Callback)new PropertyValueFactory("nama_kegiatan"));
      kegiatanCol.setCellFactory(dc -> {
  	    TableCell<JadwalModel, String> cell = new TableCell<>();
  	    Text text = new Text();
  	    cell.setGraphic(text);
  	    cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
  	    text.wrappingWidthProperty().bind(kegiatanCol.widthProperty());
  	    text.textProperty().bind(cell.itemProperty());
  	    return cell ;
  	});
      waktuCol.setCellValueFactory((Callback)new PropertyValueFactory("waktu"));
      hariCol.setCellValueFactory((Callback)new PropertyValueFactory("hari"));
      deskripsiCol.setCellValueFactory((Callback)new PropertyValueFactory("deskripsi"));
      deskripsiCol.setCellFactory(dc -> {
    	    TableCell<JadwalModel, String> cell = new TableCell<>();
    	    Text text = new Text();
    	    cell.setGraphic(text);
    	    cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
    	    text.wrappingWidthProperty().bind(deskripsiCol.widthProperty());
    	    text.textProperty().bind(cell.itemProperty());
    	    return cell ;
    	});
      tableJadwal.setItems(JadwalList);
      tableJadwal.setSelectionModel(null);
      
      statement.close();
      result.close();
    } catch (SQLException ex) {
      Logger.getLogger(application.JadwalController.class.getName()).log(Level.SEVERE, (String)null, ex);
    } 
  }
}
