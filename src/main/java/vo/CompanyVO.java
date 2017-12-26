package vo;

import model.GlobalConfigure;

/**
 * Created by ldchao on 2017/12/26.
 */
public class CompanyVO {

    private GlobalConfigure introduce;
    private GlobalConfigure characteristic1;
    private GlobalConfigure characteristic2;
    private GlobalConfigure characteristic3;

    public CompanyVO() {
    }

    public GlobalConfigure getIntroduce() {
        return introduce;
    }

    public void setIntroduce(GlobalConfigure introduce) {
        this.introduce = introduce;
    }

    public GlobalConfigure getCharacteristic1() {
        return characteristic1;
    }

    public void setCharacteristic1(GlobalConfigure characteristic1) {
        this.characteristic1 = characteristic1;
    }

    public GlobalConfigure getCharacteristic2() {
        return characteristic2;
    }

    public void setCharacteristic2(GlobalConfigure characteristic2) {
        this.characteristic2 = characteristic2;
    }

    public GlobalConfigure getCharacteristic3() {
        return characteristic3;
    }

    public void setCharacteristic3(GlobalConfigure characteristic3) {
        this.characteristic3 = characteristic3;
    }
}
