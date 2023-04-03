# Spring框架

## 概述

1.轻量级的开源javaEE框架

2.Spring可以解决企业应用开发的复杂性
3.Spring 有两个核心部分: IOC 和Aop.
	(1) IOC:控制反转，把创建对象过程交给Spring进行管理.
	(2) Aop:面向切面，不修改源代码进行功能增强。

## 入门案例

spring的方式导入类

```java
package com.douk.spring;

public class User {
    public void add(){
        System.out.println("add……");
    }
}

```

```java
package com.douk.spring;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void add() {
        ApplicationContext context=new FileSystemXmlApplicationContext("D:\\demo\\java\\Spring_demo\\src\\main\\java\\beanl.xml");

        User user=context.getBean("user",User.class);

         System.out.println(user);
        user.add();

    }
}
```

```xml
<bean id="user" class="com.douk.spring.User"></bean>
```

## IOC容器

控制反转，把对象的创建和对象之间的调用过程，交给Spring进行管理

为了耦合度降低

### 底层原理

xml解析、工厂模式、反射

 

### IOC过程

 在xml中配置对象

<bean id="dao" class="com.douk.UserDao"></bean>

然后创建工厂类

```java
class UserFactory {
	public static UserDao getDao() {
		String classValue = class属性值; //1 xml解析
		Class clazz = Class. forName (classValue);//2 通过反射创建对象
		return (UserDao) clazz. newInstance();
}

```



### IOC接口

1.IOC思想基于IOC容器完成，IOC容器底层就是工厂对象

Spring提供IOC容器实现两种方式



BeanFactory：IOC容器基本实现，是Spring内部的使用接口，不提供开发人员使用

​							加载配置文件时候不会创建对象，在获取对象(使用)才去创建对象

ApplicationContext：BeanFactory接口的子接口，提供更多更强大的功能，-般由开发人员进行使用

​										加载配置文件时候就会把在配置文件对象进行创建





ApplicationContext实现类

FileSystemXmlApplicationContext：绝对路径
ClassPathXmlApplicationContext：类路径

### IOC操作Bean管理

基于xml方式

​	创建对象

​		<bean id="dao" class="com.douk.UserDao"></bean>

​		id：唯一标识

​		class：类全路径

​		创建对象时候，默认也是执行无参构造方法

​	注入属性

​		DI：依赖注入，就是注入属性

​			通过set()方法传参

```xml
<bean id="dao" class="com.douk.UserDao">
    <!--name是属性，value向属性里注入值-->
	<property name="bname" value="阿巴阿巴"></property>
</bean>
```

​			有参构造注入

```xml
<bean id="dao" class="com.douk.UserDao">
    <!--name是属性可以用数字代替，value向属性里注入值-->
	<constructor-arg name="bname" value="阿巴阿巴"></constructor-arg>  
</bean>
```

**属性值包含特殊符号**

属性值包含特殊符号
把<>进行转意\&lt;\&gt;
把带特殊符号内容写到CDATA

```xml
<bean id="dao" class="com.douk.UserDao">
    <!--name是属性，value向属性里注入值-->
	<property name="bname" value="&lt;&gt;阿巴阿巴"></property>
</bean>
```

或者

```xml
<bean id="dao" class="com.douk.UserDao">
    <!--name是属性，value向属性里注入值-->
	<property name="bname">
        <value><![CDATA[<<阿巴阿巴>>]]></value>
    </property></property>
</bean>
```

null值

```xml
<bean id="dao" class="com.douk.UserDao">
    <!--name是属性，value向属性里注入值-->
	<property name="bname">
        <null/>
    </property>
</bean>
```

**属性值为对象**

```xml
<bean id="UserDao" class="com.douk.UserDao">
    <!--注入UserService对象
		name类里属性名称
		ref 创建userService的bean标签id值-->
	<property name="userService" ref="UserService"></property>
</bean>
<bean id="UserService" class="com.douk.UserService">
```

