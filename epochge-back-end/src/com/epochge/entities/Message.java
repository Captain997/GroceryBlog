package com.epochge.entities;

import java.util.Date;

/**
 * 留言
 */
public class Message {
    private Integer messageId; // 留言编号
    private String messageName; // 留言昵称
    private String messageQQ; // 留言QQ
    private String content; // 留言内容
    private String replyContent; // 回复留言内容
    private Date messageDate; // 留言日期

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public String getMessageName() {
        return messageName;
    }

    public void setMessageName(String messageName) {
        this.messageName = messageName;
    }

    public String getMessageQQ() {
        return messageQQ;
    }

    public void setMessageQQ(String messageQQ) {
        this.messageQQ = messageQQ;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public Date getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(Date messageDate) {
        this.messageDate = messageDate;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", messageName='" + messageName + '\'' +
                ", messageQQ='" + messageQQ + '\'' +
                ", content='" + content + '\'' +
                ", replyContent='" + replyContent + '\'' +
                ", messageDate=" + messageDate +
                '}';
    }
}
