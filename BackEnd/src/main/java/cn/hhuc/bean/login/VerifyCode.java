package cn.hhuc.bean.login;

public class VerifyCode {
    /**
     * code 表示验证码
     * sendTime 表示验证码发送时间
     */
    private int code;
    private Long sendTime;

    public VerifyCode() {
    }

    public VerifyCode(int code, Long sendTime) {
        this.code = code;
        this.sendTime = sendTime;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setSendTime(Long sendTime) {
        this.sendTime = sendTime;
    }

    public Long getSendTime() {
        return sendTime;
    }

    public int getCode() {
        return code;
    }
}
