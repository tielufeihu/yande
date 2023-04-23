//package game605.websocket;
//
//
//import com.alibaba.fastjson.JSONException;
//import game605.UserMap.OnLineAllClients;
//import game605.utilx.SpringUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
//import javax.websocket.*;
//import javax.websocket.server.ServerEndpoint;
//
//@Component
//@ServerEndpoint(value = "/websocket/test")
//@Slf4j
//public class TestWebSock {
//
//    private final OnLineAllClients onLineAllClients = SpringUtil.getBean(OnLineAllClients.class);
//
//    //连接建立成功调用的方法
//    @OnOpen
//    public void onOpen(Session session) {
//        onLineAllClients.onlineCountAddOne();
//        onLineAllClients.clientJoinMap(session);
////        onlineCount.incrementAndGet(); // 在线数加1
////        clients.put(session.getId(), session);  //将该用户加入clients hashmap
//        log.info("有新连接加入：{}，当前在线人数为：{}", session.getId(), onLineAllClients.getNowClient());
//
//
//        sendMessage("连接成功！",session);  //向客户端session发送一条信息
//    }
//
//    //连接关闭调用的方法
//    @OnClose
//    public void onClose(Session session) {
//        onLineAllClients.onlineCountReduceOne();
//        onLineAllClients.clientRemoveMap(session);
//
////        clients.remove(session.getId());  //将该用户从hashmap中remove
////        onlineCount.decrementAndGet(); // 在线数减1
//    }
//
//    //服务端发送消息给某个客户端
//    private void sendMessage(String message, Session toSession) {
//        try {
//            log.info("服务端给客户端[{}]发送消息[{}]", toSession.getId(), message);
//            toSession.getBasicRemote().sendText(message);
//        } catch (Exception e) {
//            log.error("服务端发送消息给客户端失败：{}", e);
//        }
//    }
//
//    //收到操作消息后调用的方法
//    @OnMessage
//    public void onMessage(String str, Session session) throws JSONException {
//        log.info("从客户机[{}]接收到一条信息[{}]",session.getId(),str);
//        sendMessage("发送成功!",session);  //向客户端session发送一条信息
//    }
//
//    @OnError
//    public void onError(Session session, Throwable error) {
//        log.error("发生错误 客户机id[{}]断开",session.getId());
//        error.printStackTrace();
//    }
//
//
//}
