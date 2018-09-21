package com.sean.myquery.bean;

/*****************************************
 * @description:区排队
 * @author:lixiaohui
 * @date: 2017/9/19
 * @company:深圳动态网络科技有限公司
 *****************************************/
public class QuLunhouBean {

    /**
     * SHIFZSQR : 1
     * AREA_PAIX : 20617
     * WAIT_TPYE : 公共租赁住房
     * SHOULHZH : BHR00072854
     * SFZH : 43102319890421****
     * XINGM : 李校辉
     * PAIX : 73268
     * PERSON_TYPE : 申请人
     */

    private String SHIFZSQR;
    private int AREA_PAIX;
    private String WAIT_TPYE;
    private String SHOULHZH;
    private String SFZH;
    private String XINGM;
    private int PAIX;
    private String PERSON_TYPE;

    public String getSHIFZSQR() {
        return SHIFZSQR;
    }

    public void setSHIFZSQR(String SHIFZSQR) {
        this.SHIFZSQR = SHIFZSQR;
    }

    public int getAREA_PAIX() {
        return AREA_PAIX;
    }

    public void setAREA_PAIX(int AREA_PAIX) {
        this.AREA_PAIX = AREA_PAIX;
    }

    public String getWAIT_TPYE() {
        return WAIT_TPYE;
    }

    public void setWAIT_TPYE(String WAIT_TPYE) {
        this.WAIT_TPYE = WAIT_TPYE;
    }

    public String getSHOULHZH() {
        return SHOULHZH;
    }

    public void setSHOULHZH(String SHOULHZH) {
        this.SHOULHZH = SHOULHZH;
    }

    public String getSFZH() {
        return SFZH;
    }

    public void setSFZH(String SFZH) {
        this.SFZH = SFZH;
    }

    public String getXINGM() {
        return XINGM;
    }

    public void setXINGM(String XINGM) {
        this.XINGM = XINGM;
    }

    public int getPAIX() {
        return PAIX;
    }

    public void setPAIX(int PAIX) {
        this.PAIX = PAIX;
    }

    public String getPERSON_TYPE() {
        return PERSON_TYPE;
    }

    public void setPERSON_TYPE(String PERSON_TYPE) {
        this.PERSON_TYPE = PERSON_TYPE;
    }

    @Override
    public String toString() {
        return "QuLunhouBean{" +
                "SHIFZSQR='" + SHIFZSQR + '\'' +
                ", AREA_PAIX=" + AREA_PAIX +
                ", WAIT_TPYE='" + WAIT_TPYE + '\'' +
                ", SHOULHZH='" + SHOULHZH + '\'' +
                ", SFZH='" + SFZH + '\'' +
                ", XINGM='" + XINGM + '\'' +
                ", PAIX=" + PAIX +
                ", PERSON_TYPE='" + PERSON_TYPE + '\'' +
                '}';
    }
}
