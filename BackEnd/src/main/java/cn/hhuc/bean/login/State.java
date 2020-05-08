package cn.hhuc.bean.login;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;

public class State {
    /**
     * status 表示响应状态
     * data 表示响应说明
     */
    private String status;
    private String data;

    public State(String status, String data) {
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public String getData() {
        return data;
    }

    @Override
    public String toString() {
        return "{" + "status:'" + status + '\'' + ", data:'" + data + '\'' + '}';
    }

    public static void main(String[] args) {
        State state = new State("1", "2");
        JSONObject jsonObject = JSONObject.parseObject(state.toString(), Feature.OrderedField);
        System.out.println(jsonObject);
    }

}
