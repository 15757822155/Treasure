package com.zhuoxin.hunttreasure.user.login;

import com.google.gson.annotations.SerializedName;

/**
 * Created by gqq on 2017/1/9.
 */

// 登录响应的实体类
public class LoginResult {


    /**
     * errcode : 1
     * errmsg : 登录成功！
     * headpic : add.jpg
     * tokenid : 171
     */

    @SerializedName("errcode")
    private int code;
    @SerializedName("errmsg")
    private String msg;
    private String headpic;
    private int tokenid;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getHeadpic() {
        return headpic;
    }

    public void setHeadpic(String headpic) {
        this.headpic = headpic;
    }

    public int getTokenid() {
        return tokenid;
    }

    public void setTokenid(int tokenid) {
        this.tokenid = tokenid;
    }
}
