package vo;

import model.GlobalConfigure;

/**
 * Created by ldchao on 2017/12/26.
 */
public class ContactInformationVO {

    private GlobalConfigure address;
    private GlobalConfigure phoneNumber;
    private GlobalConfigure email;
    private GlobalConfigure qq;
    private GlobalConfigure wChart;
    private GlobalConfigure QRcode;

    public ContactInformationVO() {
    }

    public GlobalConfigure getAddress() {
        return address;
    }

    public void setAddress(GlobalConfigure address) {
        this.address = address;
    }

    public GlobalConfigure getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(GlobalConfigure phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public GlobalConfigure getEmail() {
        return email;
    }

    public void setEmail(GlobalConfigure email) {
        this.email = email;
    }

    public GlobalConfigure getQq() {
        return qq;
    }

    public void setQq(GlobalConfigure qq) {
        this.qq = qq;
    }

    public GlobalConfigure getwChart() {
        return wChart;
    }

    public void setwChart(GlobalConfigure wChart) {
        this.wChart = wChart;
    }

    public GlobalConfigure getQRcode() {
        return QRcode;
    }

    public void setQRcode(GlobalConfigure QRcode) {
        this.QRcode = QRcode;
    }
}
