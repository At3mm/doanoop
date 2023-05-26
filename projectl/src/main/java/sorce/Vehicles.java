package sorce;

public class Vehicles {
    private String stt;
    private String bien_so;
    private String ma_khu;
    private String ma_the;
    private String loai_xe;

    public Vehicles(String stt, String bien_so, String ma_khu, String ma_the, String loai_xe) {
        this.stt = stt;
        this.bien_so = bien_so;
        this.ma_khu = ma_khu;
        this.ma_the = ma_the;
        this.loai_xe = loai_xe;
    }

    public Vehicles() {
    }

    public String getStt() {
        return stt;
    }

    public void setStt(String stt) {
        this.stt = stt;
    }

    public String getBien_so() {
        return bien_so;
    }

    public void setBien_so(String bien_so) {
        this.bien_so = bien_so;
    }

    public String getMa_khu() {
        return ma_khu;
    }

    public void setMa_khu(String ma_khu) {
        this.ma_khu = ma_khu;
    }

    public String getMa_the() {
        return ma_the;
    }

    public void setMa_the(String ma_the) {
        this.ma_the = ma_the;
    }

    public String getLoai_xe() {
        return loai_xe;
    }

    public void setLoai_xe(String loai_xe) {
        this.loai_xe = loai_xe;
    }
}