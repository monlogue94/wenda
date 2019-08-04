package com.nhwby.wenda.service;

import com.nhwby.wenda.dao.CommentDao;
import com.nhwby.wenda.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    CommentDao commentDao ;

    public List<Comment> getCommentsByEntity(int entityId, int entityType) {
        return commentDao .selectByEntity(entityId, entityType);
    }

    public int addComment(Comment comment) {
comment .setContent(HtmlUtils.htmlEscape(comment .getContent()));
        return commentDao .addComment(comment)>0?comment.getId():0;
    }

    public int getCommentCount(int entityId, int entityType) {
        return commentDao .getCommentCount(entityId, entityType);
    }

    public void deleteComment(int entityId, int entityType) {
        commentDao .updateStatus(entityId, entityType, 1);
    }
}
