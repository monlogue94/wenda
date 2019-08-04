package com.nhwby.wenda.controller;

import com.nhwby.wenda.model.Question;
import com.nhwby.wenda.service.CommentService;
import com.nhwby.wenda.service.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;


@Controller
public class  QuestionController {
    private  static final Logger logger = LoggerFactory.getLogger(QuestionController.class);
    @Autowired
    QuestionService questionService;
    @Autowired
    CommentService commentService ;
    @RequestMapping(value = "/question/add",method = {RequestMethod.POST})
    @ResponseBody
    public String  addQuestion (@RequestParam("title")String title,@RequestParam("context")String context){
        try {
            Question question =new Question();
            question.setContent(context );
            question.setTitle(title );
            question .setCreatedDate(new Date());
            question .setCommentCount(0);
            question .setUserId();

            questionService.addQuestion(question );
        }catch (Exception e){
            logger.error("增加题目失败"+e.getMessage());
        }
    }
}
