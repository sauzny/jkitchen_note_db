# Neo4j

- 嵌入式连接
- jdbc连接
- rest api连接

此项目中使用了嵌入式、jdbc，另有spring data neo4j在其它项目中演示

查询：`MATCH p=()-[r:LIKES]->() RETURN p LIMIT 25`

![demo](./20180517175546.png)