# java项目

## jsp页面

页面添加

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
```

然后抽取页面中相同的部分写在新的一个jsp页面里面，用

```jsp
<%@include file="新的jsp页面路径"%>
```

导入

## 动态更改url

```jsp
<%
    String basePath=request.getScheme()
            +"://"
            +request.getServerName()
            +":"
            +request.getServerPort()
            +request.getContextPath()+"/";
%>

<%--在需要的地方添加--%>
<%=basePath%>
```

## web.xml

为Servlet程序添加访问路径

```xml
<servlet>
    <!--程序名字-->
    <servlet-name>RegistServlet</servlet-name>
    <!--程序路径-->
    <servlet-class>com.book.web.RegistServlet</servlet-class>
  </servlet>
  <servlet-mapping>
      <!--与上面的名字相同-->
    <servlet-name>RegistServlet</servlet-name>
      <!--绑定路径-->
    <url-pattern>/regist</url-pattern>
  </servlet-mapping>
```



## Servlert常用方法

### req

```java
//回传一个键值对
req.setAttribute(键,值);
//在jsp页面中获取值
request.getAttribute(键);


//跳转一个页面
req.getRequestDispatcher(跳转页面的路径).forward(req,resp);

```



## 表单重复提交

表单重复提交有三种常见的情况:
	一:提交完表单。服务器使用请求转来进行页面跳转。这个时候，用户按下功能键F5,就会发起最后一次的请求。造成表单重复提交问题。req.getRequestDispatcher("/ok.jsp").forward(req,resp);

​		解决方法:使用重定向来进行跳转 req.sendRedirect(req.getContextPath+"/ok.jsp");



​	二:用户正常提交服务器，但是由于网络延迟等原因，迟迟未收到服务器的响应，这个时候，用户以为提交失败，就会着急，然后多点了几次提交操作，也会造成表单重复提交。



​	三:用户正常提交服务器。服务器也没有延迟，但是提交完成后，用户回退浏览器。重新提交。也会造成表单重复提交。

验证码可以很好的解决这两个问题，第一次提交，验证码正确提交成功，验证码刷新，便提交不成功



## 验证码

Google

```xml
              <dependency>
                    <groupId>com.github.penggle</groupId>
                    <artifactId>kaptcha</artifactId>
                    <version>2.3.2</version>
                </dependency>
```

然后在web.xml中配置Servlet

```xml
		<servlet>
            <servlet-name>KaptchaServlet</servlet-name>
            <servlet-class>com.google.code.kaptcha.servlet.KaptchaServlet</servlet-class>
        </servlet>
        <servlet-mapping>
            <servlet-name>KaptchaServlet</servlet-name>
            <url-pattern>/keptcha.jpg</url-pattern>
        </servlet-mapping>
```

然后在网页验证码位置写地址

在服务器获取谷歌生成的验证码

```java
//        获取Sesion中的验证码
String attribute = String.valueOf(req.getSession().getAttribute("KAPTCHA_SESSION_KEY"));
```



单击刷新验证码

```js
// 页面加载完成之后
$(function () {      
    //验证码单击      
    $("#code_img").click(function () {         
        this.src="<%=basePath%>keptcha.jpg";
    });
});
```

火狐和edge为了速度快会把每次请求的内容缓存到了浏览器端(内存或磁盘)

就会把验证码的图片缓存起来

下次在一样的地址和请求时**直接在缓存中找**





## 	乱码

### 请求中的乱码

```java
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //一定要加在开头
        req.setCharacterEncoding("utf-8");
}
```

### 浏览器中的乱码

```java
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //一定要加在开头
        resp.setContentType("text/html; charset=UTF-8");
}
```



## EL表达式

```jsp
<c:if test=""></c:if>
```

```jsp
${empty }<!--empty 后面的值如果为空返回false-->
${not }<!--非-->
${sessionScope.}<!--隐式对象session点后面跟服务器保存的名字-->
```







## Cookie

### 创建加发送

```java
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //创建Cookie
        Cookie cookie=new Cookie("key1","value1");
        //通知客户端保存cookie
        resp.addCookie(cookie);

        resp.getWriter().write("cookie创建成功");
}
```

发送给客户端的cookie会进行判断，没有就创建，有就修改



### 服务器如何获取cookie

Cookie[] cookie=req.getCookiea();

返回的是数组，当想获取其中一个cookie时只能通过遍历的.equals(cookie.getName())的方式



### Cookie值的修改

一.(覆盖)

​	创建一个同名的cookie对象

​	resp.addCookie()发送到服务器

二.

​	查找到需要的cookie对象

​	cookie.setValues()

​	resp.addCookie()发送到服务器

### Cookie生命控制

销毁

setMaxAge()设置cookie的最大存储时间

正值：在指定的秒数后删除

0：马上删除

负值（默认值）：浏览器关闭时删除cookie

###  Cookie有效路径Path的设置

Cookie的path属性可以有效的过滤那些cookie可用发送给服务器。那些不发

path属性是通过请求的地址来经行有效的过滤



CookieA	path=/工程路径

CookieB	path=/工程路径/abc



请求地址如下：

​	http://ip:port/工程路径/a.html

​		CookieA发送

​		CookieB不发送

​	http://ip:port/工程路径/abc/a.html

​		CookieA发送

​		CookieB发送

### EL表达式的隐式对象

${cookie.cookie的name.value}

可用用在登录成功后保存账号密码到cookie下次不用输入

## Session会话

1、Session 就一个接口( HttpSession)。
2、Session 就是会话。它是用来维护一个客户端和服务器之间关联的一种技术。
3、每个客户端都有自己的一个Session会话。
4、Session 会话中，我们经常用来保存用户登录之后的信息。

### 创建和获取

创建和获取是一样的

req.getSession()

​	第一次调用：用是创建Session会话

​	之后调用都是获取之前创建的Session

isNew(); 判断到底是不是刚创建的（新的）

​	true 表示刚创建

​	false 表示之前创建



每一个会话都有一个唯一的ID值

getId(); 得到id



### Session域数据的存取

```java
protected void set(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("key1","value1");
    }
    protected void get(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object key1 = req.getSession().getAttribute("key1");

        resp.getWriter().write(String.valueOf(key1));
    }
```

### Session生命周期控制

setMaxInactiveInterval(int ); 设置sesion的超时时间（秒），超过指定的时长，session就会被销毁

​	值为正数时，设置超时时长

​	值为负数时，永不超时，一直在服务器内存

getMaxInactiveInterval(int ); 获取sesion的超时时间

默认30分钟

可以在webxml里设置项目的sesion的默认时长

```xml
<!--表示当前web工程。创建出来的所有session默认是20分钟-->
<session-config>
    <session-timeout>20</session-timeout>
</session-config>
```



req.getSesion().invalidate(); 让当前session马上超时



### 浏览器和Session之间关联的技术

在给定时长内关闭浏览器，session也会被消除

服务器每次创建Session会话的时候，都会创建一个Cookie对象

这个key的值永远是：JSESSIONID 

value是新创建出来的Session会话的id

然后响应把新创建出来的Session的id发给客户端：Set-Cookie：JSESSIONID=

浏览器解析后马上创建一个Cookie对象

有了Cookie之后，每次请求，都会把Sesion的id以Cookie的形式发送给服务器

在在服务器内存中，通过Cookie中的id值找到自己之前创建好的Session对象，并返回





## Filter过滤器

Filter过滤器它是JavaWeb的三大组件之一。三大组件分别是: Servlet 程序、Listener 监听器、Filter 过滤器
Filter过滤器它是JavaEE的规范。也就是接口
Filter过滤器它的作用是:拦截请求，过滤响应。



### 用法

实现Filter接口

```java
public class AdminFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req= (HttpServletRequest) servletRequest;
        Object user=req.getSession().getAttribute("user");
        //如果等于null，说明还没有登录
        if(user==null){
            req.getRequestDispatcher("/login.jsp").forward(servletRequest,servletResponse);
            return;
        }else {
            //让用户继续往下访问用户的目标资源,很重要必须写
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
```

然后在web.xml中配置

```xml

<!--  filter标签用于配置一个filter过滤器-->
  <filter>
<!--    给filter起一个别名-->
    <filter-name>AdminFilter</filter-name>
<!--    配置全路径-->
    <filter-class>com.douk.servlet.AdminFilter</filter-class>
  </filter>
<!--    配置filter过滤器的拦截路径-->
  <filter-mapping>
<!--      当前的拦截路径给那个filter使用-->
    <filter-name>AdminFilter</filter-name>
<!--      配置拦截路径 可以写多个，拦截多个
          /表示请求地址为：http://ip:port/工程路径/
          /admin/* 表示拦截：http://ip:port/工程路径/admin/* -->
    <url-pattern>/admin/*</url-pattern>
  </filter-mapping>
```

### 生命周期

1.构造器方法

2.init初始化方法

​	第1、2步在web工程启动的时候执行（Filter已经创建）



3.doFilter过滤方法

​	每次拦截到请求执行

4.destroy销毁方法

​	停止web工程的时候，就会执行（停止web工程，也会销毁Filter过滤器）



### FilterChain 过滤器链

多个过滤器如何一起工作

###### ![QQ截图20221220224726](D:\笔记\JavaWeb\mdimg\QQ截图20221220224726.png)

一起工作的Filter都要加上filterChain.doFilter();

 多个Filter的执行顺序是根据web.xml的配置顺序

默认在同一个线程中

多个filter共同执行的时候，他们使用同一个Request对象



### Filter的拦截路径

精确匹配

​	<url-pattern>/target.jap</url-pattern>

​	有精确的文件名

目录匹配

​	<url-pattern>/admin/*</url-pattern>



后缀名匹配

​	<url-pattern>*.jap</url-pattern>



## ThreadLocal

可以解决多线程的数据安全

它可以给当前线程关联一个数据（普通变量，对象，数组，集合）

特点

​	可以为当前线程关联一个数据（它可以像Map一样存取数据，key为当前线程）、

​	每一个ThreadLocal对象，只能为当前线程关联一个数据，如果要为当前线程关联多个数据，就需要使用多个ThreadLocal对象实例

​	每个ThreadLocal对象定义的时候，一般都是static类型

​	ThreadLocal中保存的数据，在线程销毁后。会由JVM虚拟机自动释放。