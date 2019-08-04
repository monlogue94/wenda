package com.nhwby.wenda.controller;

import com.nhwby.wenda.model.HostHolder;
import com.nhwby.wenda.model.Message;
import com.nhwby.wenda.model.User;
import com.nhwby.wenda.service.MessageService;
import com.nhwby.wenda.service.UserService;
import com.nhwby.wenda.util.WendaUtil;
import org.apache.commons.collections.FastArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class MessageController {
    @Autowired
    MessageService messageService ;
    @Autowired
    HostHolder  hostHolder ;
    @Autowired
    UserService userService ;
    private static  final Logger logger=LoggerFactory.getLogger(MessageController.class);

    @RequestMapping(path = {"/msg/list"},method = {RequestMethod.GET })
     public String  getConversationList(Model model ){
        if (hostHolder .getUser() ==null){
            return "redirect:/relogin";
        }

        int localUserId=hostHolder .getUser() .getId() ;
        List<Message > conversationList=messageService.getConversationList(localUserId,0,10);
        List <ViewObject> conversations=new ArrayList<V>()

        return "letter";
    }



    @RequestMapping(path = {"/msg/detail"},method = {RequestMethod.GET })
    public String  getConversationDetail(Model model,@RequestParam("conversationId")String  conversationId){
        try{
            List <Message > messageList messageService.getConversationDetail(conversationId ,0,10) ;
           List<ViewObject> messages=new ArrayList();
           for (Message message :messageList){

           }

        }catch (Exception e ){
            logger .error("获取失败"+e.getMessage());
        }
        return "letterDetail";

    }








    // 发消息功能呢
    @RequestMapping(path = {"/msg/addMessage"},method = {RequestMethod.POST})
    @ResponseBody//因为是弹框用Jason返回
     public String  addMessage(@RequestParam("toName") String  toName,
                               @RequestParam("content")String content){
        try{
         if (hostHolder .getUser() ==null){
             return WendaUtil.getJSONString(999,"哥登录一下") ;
         }
            User user =userService.selectByName(toName);//获取要发送的那个用户
          // 如果要发送的人是空的不存在
            if (user ==null ){
              return WendaUtil.getJSONString(1, " 搭讪的人还没注册呢");
          }
            Message  message =new Message() ;
            message .setCreatedDate(new Date());
            message .setContent(content );
            message .setFromId(hostHolder .getUser() .getId() );
            message .setToId(user .getId());
            messageService.addMessage(message );
            return WendaUtil.getJSONString(0);

        }

    }catch (Exception e){
            logger.error("哥，消息没发送成功"+e.getMessage());
            return WendaUtil.getJSONString(1,"ail");
        }


}
