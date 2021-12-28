package org.vayne.websocket.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @classname: WebSocketServer
 * @description: 服务端监听
 * @author: Vayne.Luo
 * @date 2021/3/31 09:39
 */
@Component
@Slf4j
@Service
@ServerEndpoint("/api/websocket/{sid}")
public class WebSocketServer {

    /** 在线人数 **/
    private static int onlineCount = 0;

    private static CopyOnWriteArraySet<WebSocketServer> webSocketServers = new CopyOnWriteArraySet<>();

    private Session session;

    private String sid = "";

    @OnOpen
    public void onOpen(Session session, @PathParam("sid") String sid){
        this.session = session;
        webSocketServers.add(this);
        this.sid = sid;
        addOnlineCount();
        try {
            sendMessage("client has been connected");
            log.info("新客户端上线{},当前在线人数:{}",sid,getOnlineCount());
        } catch (IOException e) {
            log.error("websocket IO Exception");
        }
    }

    @OnClose
    public void onClose(){
        webSocketServers.remove(this);
        subOnlineCount();
        log.info("客户端断开连接:{}",sid);
        log.info("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("收到客户端" + sid + "的信息:" + message);
        // 群发消息
        for (WebSocketServer item : webSocketServers) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }

    private void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }


    /**
     * 群发自定义消息
     */
    public static void sendInfo(String message, @PathParam("sid") String sid) throws IOException {
        log.info("推送消息到客户端" + sid + "，推送内容:" + message);

        for (WebSocketServer item : webSocketServers) {
            try {
                //这里可以设定只推送给这个sid的，为null则全部推送
                if (sid == null) {
                    item.sendMessage(message);
                } else if (item.sid.equals(sid)) {
                    item.sendMessage(message);
                }
            } catch (IOException e) {
                continue;
            }
        }
    }




    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized  void addOnlineCount(){
        WebSocketServer.onlineCount++;
    }
    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }

    public static CopyOnWriteArraySet<WebSocketServer> getWebSocketSet() {
        return webSocketServers;
    }
}
