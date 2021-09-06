package com.example.jupiter.db;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MySQLDBUtil {
    private static final String INSTANCE = "laiproject1twitch.cuqbpiyjxi7a.us-east-2.rds.amazonaws.com"; //localhost for 本机
    private static final String PORT_NUM = "3306";
    private static final String DB_NAME = "twitch";

    public static String getMySQLAddress() throws IOException {  //获取数据库地址
        Properties prop = new Properties();
        String propFileName = "config.properties";  //定义property名字


        InputStream inputStream = MySQLDBUtil.class.getClassLoader().getResourceAsStream(propFileName);
        prop.load(inputStream);


        String username = prop.getProperty("user");
        String password = prop.getProperty("password");

        return String.format("jdbc:mysql://%s:%s/%s?user=%s&password=%s&autoReconnect=true&serverTimezone=UTC&createDatabaseIfNotExist=true",
                INSTANCE, PORT_NUM, DB_NAME, username, password);  //固定写法 jdbc： java database connector
    }



}
