//package game605.websocket;
//
//import com.alibaba.fastjson.JSONException;
//import game605.UserMap.OnLineAllClients;
//import game605.bean.GobangRoom;
//import game605.bean.OpData;
//import game605.service.GobangOP;
//import game605.utilx.SpringUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
//import javax.websocket.*;
//import javax.websocket.server.ServerEndpoint;
//import java.util.Objects;
//
///**
// *
// * @ServerEndpoint(value) = "/websocket/aliveGobang") 前端通过此URI和后端交互，建立连接
// * 踩坑：这个@ServerEndpoint类 比较特殊  无法注入
// * （原因：这个类是线程安全的，运行时有多少客户机建立连接就会有多少个ServerEndpoint实体类，
// * 而运行前无ServerEndpoint实体类，SpringBoot的自动注入是运行前注入，运行前无ServerEndpoint实体类所以注入失败！
// * 如果运行后使用ApplicationContext获取这个实体类查看这个实体类是否注入成功，结果为注入成功）
// * 解决方法如下
// *   静态导入
// *   GobangOP gobangOP = SpringUtil.getBean(GobangOP.class);
// *
// */
//@Component
//@Slf4j
//@ServerEndpoint(value = "/websocket/aliveGobang")
//public class GobangWebSock {
//
//    //固定注入
//    private static final GobangOP gobangOP = SpringUtil.getBean(GobangOP.class);
//    private static final OnLineAllClients onLineAllClients = SpringUtil.getBean(OnLineAllClients.class);
//
//    //记录当前在线连接数
//    //private static AtomicInteger onlineCount = new AtomicInteger(0);
//
//    //存放所有在线的客户端
//    //private static Map<String, Session> clients = new ConcurrentHashMap<>();
//
//    //连接建立成功调用的方法
//    @OnOpen
//    public void onOpen(Session session) {
////        onlineCount.incrementAndGet(); // 在线数加1
////        clients.put(session.getId(), session);  //将该用户加入clients hashmap
//        onLineAllClients.onlineCountAddOne();
//        onLineAllClients.clientJoinMap(session);
//        log.info("有新连接加入：{}，当前在线人数为：{}", session.getId(), onLineAllClients.getNowClient());
//
//        sendMessage("欢迎来到五子棋大厅",session);
//    }
//
//    //连接关闭调用的方法
//    @OnClose
//    public void onClose(Session session) {
////        clients.remove(session.getId());  //将该用户从hashmap中remove
////        onlineCount.decrementAndGet(); // 在线数减1
//        onLineAllClients.onlineCountReduceOne();
//        onLineAllClients.clientRemoveMap(session);
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
//    //广播到每一个在线的客户机
//    private void sendMessageToAllClients(String message){
//        try {
//            for(Session client: onLineAllClients.getClients().values()){
//                log.info("服务端给客户端[{}]发送消息[{}]", client.getId(), message);
//                sendMessage(message,client);
//            }
//        } catch (Exception e) {
//            log.error("服务端发送消息给客户端失败：{}", e);
//        }
//    }
//
//    //收到操作消息后调用的方法
//    @OnMessage
//    public void onOpCode(String opJson, Session session) throws JSONException {
//        //调用操作函数  客户端发送json数据
//        GobangRoom opRoom = null;
//        OpData op = null;
//        try {
//            //json字符串转op对象
//            op = OpData.jsonToOP(opJson);
//            //执行指令
//            opRoom = gobangOP.queryRoom(op.getIndex());
//            gobangOP.executeOP(op,session);
//
//            if(!Objects.equals(op.getOpStringValue(), "ping"))
//                log.info("服务器接收到客户机{}发来的opData:{}",session.getId(),op);
//
//            //（0无操作、1黑色走、2白色走、3黑方胜、4白方胜、5创建房间、6加入房间、7删除房间、8开局、9退出、10查找房间）
//            //局内指令发送给双方, 大厅指令广播給所有客户端
//            if(op.getOpcode()<=4 && op.getOpcode()>=1){
//                sendMessage(opJson,opRoom.getBlackCamp());
//                sendMessage(opJson,opRoom.getWhiteCamp());
//            }else
//            {
//                sendMessageToAllClients(opJson);
//            }
//
//        }catch (JSONException e){
//            log.info("转换操作码失败信息为{}",opJson);
//        }
//    }
//
//
//    @OnError
//    public void onError(Session session, Throwable error) {
//        log.error("发生错误 客户机id[{}]断开",session.getId());
//        error.printStackTrace();
//    }
//
//}
