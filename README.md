DELIMITER $$

CREATE TRIGGER log_jadwal_kegiatan
    AFTER INSERT
    ON jadwal_kegiatan FOR EACH ROW
BEGIN
    INSERT INTO log_jadwal VALUES(NULL, NEW.nama_kegiatan, NEW.jadwal,jadwal_kegiatan.tempat,NEW.deskripsi, NULL);
END
$$    

DELIMITER ;
