package com.vinohobby.pro.util;

import com.alibaba.fastjson.JSON;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;

import java.io.IOException;
import java.util.Map;

import static com.vinohobby.pro.util.MapFactory.of;


/**
 * Created by KAI on 2017/11/15.
 * ectest@foxmail.com
 */
public class DingtalkRobotUtil {
    private final static String URL = "https://oapi.dingtalk.com/robot/send?access_token=YOUR_TOKEN";

    /**
     * 发送文字消息
     *
     * @param message
     * @return
     */
    public static String messageText(String message, String[] at, boolean isAtAll) {

        Map<String, Object> map = of(
                "msgtype", "text",
                "text", of("content", message),
                "at", of("atMobiles", at),
                "isAtAll", isAtAll
        );

        String returnString = null;

        try {
            returnString = Request.Post(URL).connectTimeout(3000)
                    .bodyString(JSON.toJSONString(map), ContentType.APPLICATION_JSON).execute().returnContent().asString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return returnString;
    }

    /**
     * 发送超链接消息
     *
     * @param text
     * @param title
     * @param picUrl
     * @param messageUrl
     * @return
     */
    public static String messageLink(String text, String title, String picUrl, String messageUrl, String[] at, boolean isAtAll) {

        Map<String, Object> map = of(
                "msgtype", "link",
                "link", of("text", text, "title", title, "picUrl", picUrl, "messageUrl", messageUrl),
                "at", of("atMobiles", at),
                "isAtAll", isAtAll
        );

        String returnString = null;

        try {
            returnString = Request.Post(URL).connectTimeout(3000)
                    .bodyString(JSON.toJSONString(map), ContentType.APPLICATION_JSON).execute().returnContent().asString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return returnString;
    }

    /**
     * 发送 markdown 消息
     *
     * @param text
     * @param title
     * @return
     */
    public static String messageMarkdown(String text, String title, String[] at, boolean isAtAll) {

        Map<String, Object> map = of(
                "msgtype", "markdown",
                "markdown", of("text", text, "title", title),
                "at", of("atMobiles", at),
                "isAtAll", isAtAll
        );

        String returnString = null;

        try {
            returnString = Request.Post(URL).connectTimeout(3000)
                    .bodyString(JSON.toJSONString(map), ContentType.APPLICATION_JSON).execute().returnContent().asString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return returnString;
    }

}
