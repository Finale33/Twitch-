package com.example.jupiter.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.jupiter.entity.Item;
import org.apache.commons.codec.digest.DigestUtils;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ServletUtil {
    public static void writeItemMap(HttpServletResponse response, Map<String, List<Item>> itemMap) throws IOException {
        response.setContentType("application/json;charset=UTF-8"); //后面一半是语言转译
        response.getWriter().print(new ObjectMapper().writeValueAsString(itemMap));//writeValue...是把item转换成json 在逻辑层坐了一系列操作之后，又将它们转回json文件还给前端
    }
    public static String encryptPassword(String userId, String password) throws IOException {
        return DigestUtils.md5Hex(userId + DigestUtils.md5Hex(password)).toLowerCase();
    }

}

