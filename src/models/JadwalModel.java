package models;

public class JadwalModel {
  private int id;
  
  private String nama_kegiatan;
  
  private String waktu;
  
  private String tanggal;
  
  private String hari;
  
  private String tempat;
  
  private String deskripsi;
  
  public String getHari() {
	return hari;
}

public void setHari(String hari) {
	this.hari = hari;
}

public JadwalModel(String kegiatan, String waktu, String tanggal,String hari, String tempat, String deskripsi) {
    this.deskripsi = deskripsi;
    this.nama_kegiatan = kegiatan;
    this.waktu = waktu;
    this.tanggal = tanggal;
    this.tempat = tempat;
    this.hari = hari;
  }
  
  public int getId() {
    return id;
  }
  
  public void setId(int id) {
    id = id;
  }
  
  public String getNama_kegiatan() {
    return nama_kegiatan;
  }
  
  public void setNama_kegiatan(String nama_kegiatan) {
    nama_kegiatan = nama_kegiatan;
  }
  
  public String getWaktu() {
    return waktu;
  }
  
  public void setWaktu(String waktu) {
    waktu = waktu;
  }
  
  public String getTanggal() {
    return tanggal;
  }
  
  public void setTanggal(String tanggal) {
    tanggal = tanggal;
  }
  
  public String getTempat() {
    return tempat;
  }
  
  public void setTempat(String tempat) {
    tempat = tempat;
  }
  
  public String getDeskripsi() {
    return deskripsi;
  }
  
  public void setDeskripsi(String deskripsi) {
    deskripsi = deskripsi;
  }
}
