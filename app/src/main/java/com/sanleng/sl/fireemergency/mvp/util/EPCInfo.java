package com.sanleng.sl.fireemergency.mvp.util;

import java.io.Serializable;

public class EPCInfo implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String strxuhao;//序号
    private String str1;//PC
    private String strEpc;//EPC
    private String str3;//CRC
    private String str4;//天线号
    private String str5;//次数
    private String str6;//RSSI
    private String str7;//频虑
    private String str8;//设备号
    private String strTid;//TID
    private String strUser;//USR

    private Long id;/*与数据库索引保持一致*/
    private int iTextColor; //文本色
    private int iBackColor; //背景色

    //public EPCInfo() {
    //    id = null;
    //}

    public EPCInfo() {
        this.id = 0L;
        strxuhao = "0";//序号
        str1 = "";//PC
        strEpc = "";//EPC
        str3 = "";//CRC
        str4 = "";//天线号
        str5 = "";//次数
        str6 = "";//RSSI
        str7 = "";//频虑
        str8 = "";//设备号
        strTid = "";//TID
        strUser = "";//USR


        //this.iTextColor = iTColor;
        //this.iBackColor = iBColor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTextColor(int iTColor) {
        this.iTextColor = iTColor;
    }

    public int getTextColor() {
        return iTextColor;
    }

    public void setBackColor(int iBColor) {
        this.iBackColor = iBColor;
    }

    public int getBackColor() {
        return iBackColor;
    }

    public String getstrxuhao() {
        return strxuhao;
    }

    public void setstrxuhao(String strxuhao) {
        this.strxuhao = strxuhao;
    }

    public String getstr1() {
        return str1;
    }
    public void setstr1(String str1) {
        this.str1 = str1;
    }

    public String getstrEpc() {
        return strEpc;
    }
    public void setstrEpc(String strEpc) {
        this.strEpc = strEpc;
    }

    public String getstr3() {
        return str3;
    }
    public void setstr3(String str3) {
        this.str3 = str3;
    }

    public String getstr4() {
        return str4;
    }
    public void setstr4(String str4) {
        this.str4 = str4;
    }

    public String getstr5() {
        return str5;
    }
    public void setstr5(String str5) {
        this.str5 = str5;
    }

    public String getstr6() {
        return str6;
    }
    public void setstr6(String str6) {
        this.str6 = str6;
    }

    public String getstr7() {
        return str7;
    }
    public void setstr7(String str7) {
        this.str7 = str7;
    }

    public String getstr8() {
        return str8;
    }
    public void setstr8(String str8) {
        this.str8 = str8;
    }


    public String getstrTid() {
        return strTid;
    }
    public void setstrTid(String strTid) {
        this.strTid = strTid;
    }

    public String getstrUser() {
        return strUser;
    }
    public void setstrUser(String strUser) {
        this.strUser = strUser;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}

