package cn.hhuc.service.aliyun.sms;


import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

/**
 * 阿里短信服务接口
 */
public class ShortMessageServer {

    public String sendVerifyCode(String phoneNum,int code) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "your aliyun AccessKey", "your aliyun secret");
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phoneNum);
        request.putQueryParameter("SignName", "smsforjava");
        request.putQueryParameter("TemplateCode", "SMS_189523497");
        request.putQueryParameter("TemplateParam", "{\"code\":\"" +  code + "\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            String res = new String(response.getData().getBytes());
            Object o = JSONObject.parseObject(res).get("Code");
            return (String) o;
        } catch (ServerException e) {
            e.printStackTrace();
            return "error";
        } catch (ClientException e) {
            e.printStackTrace();
            return "error";
        }
    }
}
