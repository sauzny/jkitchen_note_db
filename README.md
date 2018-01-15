# jkitchen_note_db

db相关的客户端demo，依赖spring的demo

| 名字 | 版本 | 作用 | 说明 |
|--------|--------|--------|--------|
| jedis | 2.9.0 | redis客户端 | api与redis原生的命令几乎一一对应，redis集群情况，有些命令无法支持 |
| redisson | 3.5.7 | redis客户端 | 高级api，将命令组合使用实现了一些功能。支持集群，其他高级功能。 |
| dbutils | 1.7 | sql执行工具 | 对查询结果进行了封装 |