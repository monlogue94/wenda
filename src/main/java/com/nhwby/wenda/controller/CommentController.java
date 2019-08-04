package com.nhwby.wenda.controller;

import com.nhwby.wenda.model.Comment;
import com.nhwby.wenda.model.EntityType;
import com.nhwby.wenda.model.HostHolder;
import com.nhwby.wenda.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class CommentController {
    @Autowired
    CommentService commentService;
    @Autowired
    HostHolder hostHolder;

    @RequestMapping(path = {"/addComment"}, method = {RequestMethod.POST})
    public String addComment(@RequestParam("questionId") int questionId,
                             @RequestParam("context") String context) {
        Comment comment = new Comment();
        comment.setContent(context);
        if (hostHolder.getUser() != null) {
            comment.setUserId(hostHolder.getUser().getId());
        } else {
            return "redirect:/relogin";
        }
        comment.setCreatedDate(new Date());
        comment.setEntityType(EntityType.ENTITY_QUESTION);
        comment.setEntityId(questionId);
        commentService.addComment(comment);
        //成功了跳转
        return "redirect:/question/"+questionId ;
    }
}