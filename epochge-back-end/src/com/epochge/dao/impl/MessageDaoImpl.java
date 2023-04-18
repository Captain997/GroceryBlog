package com.epochge.dao.impl;

import com.epochge.dao.MessageDao;
import com.epochge.entities.Message;
import com.epochge.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * 留言
 */
public class MessageDaoImpl implements MessageDao {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    // 添加留言
    @Override
    public int insertMessage(Message message) {
        String sql = "insert into message(messageName,messageQQ,content) value (?,?,?)";
        int i = jdbcTemplate.update(sql, message.getMessageName(), message.getMessageQQ(), message.getContent());
        return i;
    }

    // 查询所有留言  进行分页
    @Override
    public int findTotalCount() {
        String sql = "select count(1) from message";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return count;
    }

    @Override
    public List<Message> findByPage(int start, int rows) {
        String sql = "select * from message order by messageDate desc limit ?,?";
        List<Message> list;
        list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Message>(Message.class), start, rows);
        return list;
    }

    // 根据留言id  删除留言
    @Override
    public int deleteMessage(Integer messageId) {
        String sql = "delete from message where messageId = ?";
        int i = jdbcTemplate.update(sql, messageId);
        return i;
    }

    // 根据留言id 回复留言  更新留言
    @Override
    public int updateMessageReply(Integer messageId, String replyContent) {
        String sql = "update message set replyContent = ? where messageId = ?";
        int i = jdbcTemplate.update(sql, replyContent, messageId);
        return i;
    }
}
