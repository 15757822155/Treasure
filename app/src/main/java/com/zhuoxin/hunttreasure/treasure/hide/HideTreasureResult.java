package com.zhuoxin.hunttreasure.treasure.hide;

import com.google.gson.annotations.SerializedName;

/**
 * 埋藏宝藏响应数据
 */
public class HideTreasureResult {

//    {
//        "errcode":0,                                    //返回值
//            "errmsg":"参数格式不正确!请检测传入参数格式"   //返回信息
//    }

    @SerializedName("errcode")
    public int code;

    @SerializedName("errmsg")
    public String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
