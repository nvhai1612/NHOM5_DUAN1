package DomainModel;

public class ThongKeHD {

    private String maHD;
    private String tenKH;
    private int soDienThoai;
    private String ngayTao;
    private float tongTien;

    public ThongKeHD() {
    }

    public ThongKeHD(String maHD, String tenKH, int soDienThoai, String ngayTao, float tongTien) {
        this.maHD = maHD;
        this.tenKH = tenKH;
        this.soDienThoai = soDienThoai;
        this.ngayTao = ngayTao;
        this.tongTien = tongTien;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public int getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(int soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }

    public Object[] toDataRow() {
        return new Object[]{this.maHD, this.tenKH, this.soDienThoai, this.ngayTao, this.tongTien};
    }

}
