package com.example.mywechat.controller;

import com.example.mywechat.util.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutTextMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * @ClassName MyWechatController
 * @Description TODO
 * @Author Wenbo Li
 * @Date 5/11/2022 下午 2:35
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/api/weixin/index")
public class MyWechatController {

    @Resource
    private WxMpService mpService;
    private static final String token = "DaboDabo";

    /**
     * 处理微信认证：验证服务器地址的有效性，get提交
     * signature: 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
     * timestamp 时间戳
     * nonce: 随机数
     * echostr: 随机字符串
     */
    @GetMapping
    public void checkSignature(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("============= 处理微信认证 ===============");
        // 拿到微信的请求参数
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");

        // ① 将token、timestamp、nonce三个参数进行字典序排序 b a d c h ==>a b c d h
        String[] strArr = {token, timestamp, nonce};
        // 字典排序
        Arrays.sort(strArr);
        // ② 将三个参数字符串拼接成一个字符串进行sha1加密
        StringBuffer sb = new StringBuffer();
        // 字符串拼接
        for (String str : strArr) {
            sb.append(str);
        }
        // 加密
        String sha1Str = SecurityUtil.sha1(sb.toString());
        // ③ 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
        if (sha1Str.equals(signature)) {
            // 如果相等，就是来自微信请求
            // 若确认此次GET请求来自微信服务器，原样返回echostr参数内容，则接入生效
            response.getWriter().println(echostr);
        }
    }

    /**
     * 被动回复用户消息
     *
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping
    public String post(HttpServletRequest request) throws IOException, WxErrorException {
        //获取消息流,并解析xml
        WxMpXmlMessage message = WxMpXmlMessage.fromXml(request.getInputStream());
        log.info(message.toString());
        //消息类型
        String messageType = message.getMsgType();
        log.info("消息类型:" + messageType);
        //发送者帐号
        String fromUser = message.getFromUser();
        log.info("发送者账号：" + fromUser);
        //开发者微信号
        String touser = message.getToUser();
        log.info("开发者微信：" + touser);
        //文本消息  文本内容
        String text = message.getContent();
        log.info("文本消息：" + text);
        /**
         * 事件推送
         */
        if (messageType.equals("event")) {
            log.info("event：" + message.getEvent());
            // 关注
            if (message.getEvent().equals("subscribe")) {
                log.info("用户关注：{}", fromUser);
                WxMpXmlOutTextMessage texts = WxMpXmlOutTextMessage
                        .TEXT()
                        .toUser(fromUser)
                        .fromUser(touser)
                        .content("谢谢你长的这么好看还关注我~~")
                        .build();
                String result = texts.toXml();
                log.info("响应给用户的消息：" + result);
                return result;
            }
            // 取消关注
            if (message.getEvent().equals("unsubscribe")) {
                log.info("用户取消关注：{}", fromUser);
            }
            // 点击菜单
            if (message.getEvent().equals("CLICK")) {
                log.info("用户点击菜单：{}", message.getEventKey());
            }
            // 点击菜单
            if (message.getEvent().equals("VIEW")) {
                log.info("用户点击菜单：{}", message.getEventKey());
            }
            // 已关注用户扫描带参数二维码
            if (message.getEvent().equals("scancode_waitmsg")) {
                log.info("用户扫描二维码：{}", fromUser);
            }
            // 获取位置信息
            if (message.getEvent().equals("LOCATION")) {
                log.info("用户发送位置信息：经度：{}，纬度：{}", message.getLatitude(), message.getLongitude());
            }
            return null;
        }
        /**
         * 文本消息
         */
        if (messageType.equals("text")) {
            WxMpXmlOutTextMessage texts = WxMpXmlOutTextMessage
                    .TEXT()
                    .toUser(fromUser)
                    .fromUser(touser)
                    .content("你发送的消息为：" + text)
                    .build();
            String result = texts.toXml();
            log.info("响应给用户的消息：" + result);
            return result;
        }
        /**
         * 图片消息
         */
        if (messageType.equals("image")) {
            WxMpXmlOutTextMessage texts = WxMpXmlOutTextMessage
                    .TEXT()
                    .toUser(fromUser)
                    .fromUser(touser)
                    .content("已接收到您发的图片信息")
                    .build();
            String result = texts.toXml();
            log.info("响应给用户的消息：" + result);
            return result;
        }
        /**
         * 语音消息
         */
        if (messageType.equals("voice")) {
            WxMpXmlOutTextMessage texts = WxMpXmlOutTextMessage
                    .TEXT()
                    .toUser(fromUser)
                    .fromUser(touser)
                    .content("已接收到您发的语音信息")
                    .build();
            String result = texts.toXml();
            log.info("响应给用户的消息：" + result);
            return result;
        }
        /**
         * 视频
         */
        if (messageType.equals("video")) {
            WxMpXmlOutTextMessage texts = WxMpXmlOutTextMessage
                    .TEXT()
                    .toUser(fromUser)
                    .fromUser(touser)
                    .content("已接收到您发的视频信息")
                    .build();
            String result = texts.toXml();
            log.info("响应给用户的消息：" + result);
            return result;
        }
        /**
         * 小视频
         */
        if (messageType.equals("shortvideo")) {
            WxMpXmlOutTextMessage texts = WxMpXmlOutTextMessage
                    .TEXT()
                    .toUser(fromUser)
                    .fromUser(touser)
                    .content("已接收到您发的小视频信息")
                    .build();
            String result = texts.toXml();
            log.info("响应给用户的消息：" + result);
            return result;
        }
        /**
         * 地理位置信息
         */
        if (messageType.equals("location")) {
            WxMpXmlOutTextMessage texts = WxMpXmlOutTextMessage
                    .TEXT()
                    .toUser(fromUser)
                    .fromUser(touser)
                    .content("已接收到您发的地理位置信息")
                    .build();
            String result = texts.toXml();
            log.info("响应给用户的消息：" + result);
            return result;
        }
        /**
         * 链接信息
         */
        if (messageType.equals("link")) {
            WxMpXmlOutTextMessage texts = WxMpXmlOutTextMessage
                    .TEXT()
                    .toUser(fromUser)
                    .fromUser(touser)
                    .content("已接收到您发的链接信息")
                    .build();
            String result = texts.toXml();
            log.info("响应给用户的消息：" + result);
            return result;
        }
        return null;
    }

    @GetMapping("/accessToken")
    public String accessToken() throws WxErrorException {
        // this.mpService.getWxMpConfigStorage().getAppId();
        String accessToken = this.mpService.getAccessToken();

        return accessToken;
    }
}
