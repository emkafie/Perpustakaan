package backend;

import java.util.ArrayList;
import java.sql.*;
import java.text.SimpleDateFormat;

public class Peminjaman {

    private int idpeminjaman;
    private Anggota anggota;
    private Buku buku;
    private String tanggalPinjam;
    private String tanggalKembali;

    public Peminjaman() {
        anggota = new Anggota();
        buku = new Buku();
    }

    public Peminjaman(Anggota anggota, Buku buku, String tanggalPinjam, String tanggalKembali) {
        this.anggota = anggota;
        this.buku = buku;
        this.tanggalPinjam = tanggalPinjam;
        this.tanggalKembali = tanggalKembali;
    }

    public int getIdpeminjaman() {
        return idpeminjaman;
    }

    public void setIdpeminjaman(int idpeminjaman) {
        this.idpeminjaman = idpeminjaman;
    }

    public Anggota getAnggota() {
        return anggota;
    }

    public void setAnggota(Anggota anggota) {
        this.anggota = anggota;
    }

    public Buku getBuku() {
        return buku;
    }

    public void setBuku(Buku buku) {
        this.buku = buku;
    }

    public String getTanggalPinjam() {
        return tanggalPinjam;
    }

    public void setTanggalPinjam(String tanggalPinjam) {
        this.tanggalPinjam = tanggalPinjam;
    }

    public String getTanggalKembali() {
        return tanggalKembali;
    }

    public void setTanggalKembali(String tanggalKembali) {
        this.tanggalKembali = tanggalKembali;
    }

    public Peminjaman getById(int id) {
        Peminjaman peminjaman = new Peminjaman();
        String sql = "SELECT p.*, a.nama as nama_anggota, a.alamat, a.telepon, "
                + "b.judul, b.penerbit, b.penulis, k.nama as kategori, k.keterangan "
                + "FROM peminjaman p "
                + "LEFT JOIN anggota a ON p.idanggota = a.idanggota "
                + "LEFT JOIN buku b ON p.idbuku = b.idbuku "
                + "LEFT JOIN kategori k ON b.idkategori = k.idkategori "
                + "WHERE p.idpeminjaman = '" + id + "'";
        ResultSet rs = DBHelper.selectQuery(sql);

        try {
            while (rs.next()) {
                peminjaman.setIdpeminjaman(rs.getInt("idpeminjaman"));
                peminjaman.getAnggota().setIdanggota(rs.getInt("idanggota"));
                peminjaman.getAnggota().setNama(rs.getString("nama_anggota"));
                peminjaman.getAnggota().setAlamat(rs.getString("alamat"));
                peminjaman.getAnggota().setTelepon(rs.getString("telepon"));

                peminjaman.getBuku().setIdbuku(rs.getInt("idbuku"));
                peminjaman.getBuku().setJudul(rs.getString("judul"));
                peminjaman.getBuku().setPenerbit(rs.getString("penerbit"));
                peminjaman.getBuku().setPenulis(rs.getString("penulis"));
                peminjaman.getBuku().getKategori().setNama(rs.getString("kategori"));

                peminjaman.setTanggalPinjam(rs.getString("tanggalPinjam"));
                peminjaman.setTanggalKembali(rs.getString("tanggalKembali"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return peminjaman;
    }

    public ArrayList<Peminjaman> getAll() {
        ArrayList<Peminjaman> listPeminjaman = new ArrayList();
        String sql = "SELECT p.*, a.nama as nama_anggota, a.alamat, a.telepon, "
                + "b.judul, b.penerbit, b.penulis, k.nama as kategori, k.keterangan "
                + "FROM peminjaman p "
                + "LEFT JOIN anggota a ON p.idanggota = a.idanggota "
                + "LEFT JOIN buku b ON p.idbuku = b.idbuku "
                + "LEFT JOIN kategori k ON b.idkategori = k.idkategori";
        ResultSet rs = DBHelper.selectQuery(sql);

        try {
            while (rs.next()) {
                Peminjaman peminjaman = new Peminjaman();
                peminjaman.setIdpeminjaman(rs.getInt("idpeminjaman"));
                peminjaman.getAnggota().setIdanggota(rs.getInt("idanggota"));
                peminjaman.getAnggota().setNama(rs.getString("nama_anggota"));
                peminjaman.getAnggota().setAlamat(rs.getString("alamat"));
                peminjaman.getAnggota().setTelepon(rs.getString("telepon"));

                peminjaman.getBuku().setIdbuku(rs.getInt("idbuku"));
                peminjaman.getBuku().setJudul(rs.getString("judul"));
                peminjaman.getBuku().setPenerbit(rs.getString("penerbit"));
                peminjaman.getBuku().setPenulis(rs.getString("penulis"));
                peminjaman.getBuku().getKategori().setNama(rs.getString("kategori"));

                peminjaman.setTanggalPinjam(rs.getString("tanggalPinjam"));
                peminjaman.setTanggalKembali(rs.getString("tanggalKembali"));

                listPeminjaman.add(peminjaman);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listPeminjaman;
    }

    public ArrayList<Peminjaman> search(String keyword) {
        ArrayList<Peminjaman> listPeminjaman = new ArrayList();
        String sql = "SELECT p.*, a.nama as nama_anggota, a.alamat, a.telepon, "
                + "b.judul, b.penerbit, b.penulis, k.nama as kategori, k.keterangan "
                + "FROM peminjaman p "
                + "LEFT JOIN anggota a ON p.idanggota = a.idanggota "
                + "LEFT JOIN buku b ON p.idbuku = b.idbuku "
                + "LEFT JOIN kategori k ON b.idkategori = k.idkategori "
                + "WHERE a.nama LIKE '%" + keyword + "%' "
                + "OR b.judul LIKE '%" + keyword + "%' "
                + "OR tanggalPinjam LIKE '%" + keyword + "%'";
        ResultSet rs = DBHelper.selectQuery(sql);

        try {
            while (rs.next()) {
                Peminjaman peminjaman = new Peminjaman();
                peminjaman.setIdpeminjaman(rs.getInt("idpeminjaman"));
                peminjaman.getAnggota().setIdanggota(rs.getInt("idanggota"));
                peminjaman.getAnggota().setNama(rs.getString("nama_anggota"));
                peminjaman.getAnggota().setAlamat(rs.getString("alamat"));
                peminjaman.getAnggota().setTelepon(rs.getString("telepon"));

                peminjaman.getBuku().setIdbuku(rs.getInt("idbuku"));
                peminjaman.getBuku().setJudul(rs.getString("judul"));
                peminjaman.getBuku().setPenerbit(rs.getString("penerbit"));
                peminjaman.getBuku().setPenulis(rs.getString("penulis"));
                peminjaman.getBuku().getKategori().setNama(rs.getString("kategori"));

                peminjaman.setTanggalPinjam(rs.getString("tanggalPinjam"));
                peminjaman.setTanggalKembali(rs.getString("tanggalKembali"));

                listPeminjaman.add(peminjaman);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listPeminjaman;
    }

    private boolean isValidDate(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            sdf.setLenient(false);
            sdf.parse(date);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void save() {
        if (!isValidDate(this.tanggalPinjam)) {
            System.out.println("Error: Tanggal pinjam tidak valid.");
            return;
        }
        
        if (getById(idpeminjaman).getIdpeminjaman() == 0) {
            String SQL = "INSERT INTO peminjaman (idanggota, idbuku, tanggalPinjam) VALUES("
                    + this.getAnggota().getIdanggota() + ", "
                    + this.getBuku().getidbuku() + ", "
                    + "'" + this.tanggalPinjam + "' "

                    + ")";
            this.idpeminjaman = DBHelper.insertQueryGetId(SQL);
        } else {
            String SQL = "UPDATE peminjaman SET "
                    + "idanggota = " + this.getAnggota().getIdanggota() + ", "
                    + "idbuku = " + this.getBuku().getidbuku() + ", "
                    + "tanggalPinjam = '" + this.tanggalPinjam + "', "
                    + "tanggalKembali = '" + this.tanggalKembali + "' "
                    + "WHERE idpeminjaman = '" + this.idpeminjaman + "'";
            DBHelper.executeQuery(SQL);
        }
    }

    public void delete() {
        String SQL = "DELETE FROM peminjaman WHERE idpeminjaman = '" + this.idpeminjaman + "'";
        DBHelper.executeQuery(SQL);
    }
}
