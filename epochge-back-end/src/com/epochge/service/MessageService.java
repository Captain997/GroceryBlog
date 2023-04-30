package com.epochge.service;

import com.epochge.dao.MessageDao;
import com.epochge.dao.impl.MessageDaoImpl;
import com.epochge.entities.Message;
import com.epochge.entities.PageBean;

import java.util.List;

/**
 *
 */
public class MessageService {
    MessageDao dao = new MessageDaoImpl();

    // 添加留言
    public int insertMessage(Message message) {
        if (message == null) {
            return -1;
        }
        return dao.insertMessage(message);
    }

    // 查询所有留言  进行分页
    public PageBean<Message> findMessageByPage(int _currentPage, int _pageSize) {
        int currentPage = _currentPage;//当前页码
        int rows = _pageSize;//每页显示行数

        // 创建空的pageBean
        PageBean<Message> pb = new PageBean<Message>();
        // 2、设置参数
        pb.setPageSize(rows);//每条显示行数
        pb.setCurrentPage(currentPage);   // 当前页码

        //3：调用dao查询总记录数
        int total = dao.findTotalCount();
        pb.setTotal(total);

        // 4:调用dao查询的list集合
        // 计算开始记录的索引  从索引几开始 = (当前页码-1)*5
        int start = (currentPage - 1) * rows;

        List<Message> articleInfoList = dao.findByPage(start, rows);
        pb.setList(articleInfoList);


        // 5：计算总页码  总记录数 % 每页显示的条数 ==0 ？总记录数/每页的条数 ： 总记录数/每页的条数+1
        int totalPage = (total % rows) == 0 ? total / rows : total / rows + 1;
        pb.setTotalPage(totalPage);

        return pb;
    }

    // 根据留言id  删除留言
    public int deleteMessage(Integer messageId) {
        if (messageId != null) {
            return dao.deleteMessage(messageId);
        }
        return -1;
    }

    // 根据留言id 回复留言  更新留言
    public int updateMessageReply(Integer messageId, String replyContent) {
        if (messageId != null && replyContent != null) {
            return dao.updateMessageReply(messageId, replyContent);
        }
        return -1;
    }
}
