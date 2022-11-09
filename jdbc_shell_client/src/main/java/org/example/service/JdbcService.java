package org.example.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class JdbcService {

    @Value("${jdbc.path}")
    private String path;
    @Value("${jdbc.classname}")
    private String classname;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;

    @PostConstruct
    public void init() {

        try {
            //通过URLClassLoader加载外部jar
            Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
            URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
            method.setAccessible(true);
            method.invoke(classLoader, new File(path).toURI().toURL());
            //1.加载驱动程序
            Class.forName(classname);
        } catch (Exception e) {
            log.error("加载外部 jdbc 的 jar 包", e);
            System.exit(-1);
        }
    }

    public List<Map<String, Object>> executeQuery(String sql) throws SQLException {

        Connection conn = DriverManager.getConnection(url, username, password);

        //创建SQL执行工具
        QueryRunner qRunner = new QueryRunner();
        List<Map<String, Object>> list = qRunner.query(conn, sql, new MapListHandler());
        DbUtils.close(conn);
        return list;
    }
}
