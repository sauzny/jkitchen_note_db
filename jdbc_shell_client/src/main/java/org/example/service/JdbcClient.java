package org.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;
import java.util.Map;

@ShellComponent
@ShellCommandGroup("JdbcClient")
public class JdbcClient {

    @Autowired
    private JdbcService jdbcService;

    @ShellMethod(value = "输入select语句, select语句需要使用单引号''包裹, 举例输入：sql 'select 1 from xxx'")
    public String sql(String sql) {

        if (!sql.startsWith("select")) {
            return "只能执行 select 语句";
        }

        String result = "";

        try {
            List<Map<String, Object>> list = jdbcService.executeQuery(sql);
            result = list.toString();
        } catch (Exception e) {
            result = String.format("%s 执行失败 %s %s", sql, e.getClass().getName(), e.getMessage());
        }

        return result;
    }
}
