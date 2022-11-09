package org.example;

import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@ShellCommandGroup("测试分组的组名")
public class DemoShell {

    @ShellMethod(value = "测试函数，举例输入：demo")
    public String demo() {
        return "测试功能";
    }
}
