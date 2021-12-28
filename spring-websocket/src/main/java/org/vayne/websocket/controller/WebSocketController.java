package org.vayne.websocket.controller;

import org.springframework.web.bind.annotation.*;
import org.vayne.websocket.config.WebSocketServer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @classname: WebSocketController
 * @description: 消息推送类
 * @author: Vayne.Luo
 * @date 2021/3/31 10:35
 */
@RestController
@RequestMapping("/api/socket")
public class WebSocketController {

    @GetMapping("/index/{userId}")
    public Map<String,Object> connect(@PathVariable String userId){
        Map<String,Object> map = new HashMap<>();
        map.put("userId",userId);
        return map;
    }

    @PostMapping("/push")
    public Map<String,Object> pushToWeb(@RequestBody ChatForm chatForm) {
        Map<String,Object> result = new HashMap<>();
        try {
            WebSocketServer.sendInfo(chatForm.getMessage(), chatForm.getSid());
            result.put("code", chatForm.getSid());
            result.put("msg", chatForm.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
