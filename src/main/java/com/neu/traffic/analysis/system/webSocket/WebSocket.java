/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: WebSocket
 * Author:   pengzijun
 * Date:     2020/4/4 6:48 下午
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.neu.traffic.analysis.system.webSocket;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author pengzijun
 * @create 2020/4/4
 * @since 1.0.0
 */

import com.neu.traffic.analysis.system.utils.SendMessage;
import org.springframework.stereotype.Component;

import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;


@Component

@ServerEndpoint(value = "/websocket", configurator = MySpringConfigurator.class)

public class WebSocket {
    @OnOpen
    public void onOpen(Session session){
        SendMessage sendMessage = new SendMessage(session);

        Thread thread = new Thread(sendMessage);

        System.out.println("Websocket Started");

        thread.start();

    }

}