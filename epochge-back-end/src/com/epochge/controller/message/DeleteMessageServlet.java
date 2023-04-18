package com.epochge.controller.message;

import com.epochge.service.MessageService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * 根据留言编号 删除留言
 * @author Bpvank
 */
@WebServlet("/message/delete")
public class DeleteMessageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String msg = "";
        int code;
        Integer messageId = Integer.valueOf(req.getParameter("messageId"));
        ObjectMapper mapper = new ObjectMapper();
        MessageService service = new MessageService();
        int i = service.deleteMessage(messageId);
        if (i > 0){
            // 删除成功
            msg =  "删除成功";
            code = 0;
        }else{
            // 删除失败
            msg =  "删除失败";
            code = 1;
        }
        HashMap<Object, Object> map = new HashMap<>();
        map.put("msg", msg);
        map.put("code", code);
        mapper.writeValue(resp.getWriter(),map);
    }
}