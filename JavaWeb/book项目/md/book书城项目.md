# book书城项目

## 导入web工程

把html、css、js导入

## 写包名分类

![01](D:\笔记\JavaWeb\book项目\mdimg\01.png)

## 创建数据库

```mysql
create database book;

use book;

create table t_user(
    id int primary key auto_increment,
    username varchar(20) not null unique ,
    password varchar(32) not null ,
    email varchar(200)
);
insert into t_user values(null,'admin','12345','admin@qq.com');
select * from t_user;
```

## 创建相应的JavaBean对象

也就是创建User类与mysql对应

创建在pojo包下

## 编写Dao持久层

1. 编写 工具类JdbcUtils

先添加驱动 

将xml配置添加到pom.xml

mysql

```xml
				<dependency>
                    <groupId>mysql</groupId>
                    <artifactId>mysql-connector-java</artifactId>
                    <version>8.0.31</version>
                </dependency>
```

德鲁伊druid

```xml
              <dependency>
                    <groupId>com.alibaba</groupId>
                    <artifactId>druid</artifactId>
                    <version>1.2.14</version>
                </dependency>
```

然后写德鲁伊的配置文件

jdbc.properties

**编写JdbcUtils工具类**

```java
package com.book.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.FileInputStream;
import java.io.FilterInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {

    private static DruidDataSource dataSource;
    static {
        try {
            Properties ppt=new Properties();
            ppt.load(new FileInputStream("D:\\demo\\javaWab\\book\\src\\main\\java\\jdbc.properties"));
            dataSource= (DruidDataSource) DruidDataSourceFactory.createDataSource(ppt);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    /*
    * 获取数据库连接池的连接
    *如果返回null说明获取连接失败
    * */
    public static Connection getConnection(){
        Connection conn=null;

        try {
            conn= dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }
    public static void close(Connection con){
        try{
           if(con!=null){
               con.close();
           }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
```

编写jdbc.properties配置文件

```properties
username=root
password=1234
url=jdbc:mysql://localhost:3306/book
driverClassName=com.mysql.cj.jdbc.Driver
#初始连接数
initialSize=5
#最大连接数
maxActive=10
```

编写