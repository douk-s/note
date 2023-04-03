# 一

## 第一个springboot程序

首先导包

```xml
<parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.3.4.RELEASE</version>
    </parent>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

    </dependencies>
```

然后写一个主程序类，类上加@SpringBootApplication标签，写上运行

```java
@SpringBootApplication
public class Text {
    public static void main(String[] args) {
        SpringApplication.run(Text.class,args);
    }
    
}
```

在写请求类

```java
//@ResponseBody 将方法的返回值，以特定的格式写入到response的body区域，进而将数据返回给客户端
//@Controller 处理 Http 请求
@RestController//@Controller 的衍生注解,就是上面两个
public class HeolloController {

    @RequestMapping("/hello")//路由请求 可以设置各种操作方法
    public String handle01(){
        return "Hello";
    }
}
```



## springboot的统一配置文件

放在resources资源文件夹下面

```application.properties
#配置端口
server.port=8888
```

## 简化部署

把项目打成jar包，直接在目标服务器执行即可。

```xml
 <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <version>2.3.4.RELEASE</version>
            </plugin>
        </plugins>
    </build>
```

然后点开右侧maven，点开项目，点开生命周期

选中clean和package，运行

就可以被打包成jar包

在左侧target文件中

打开在资源管理器中，运行cmd，运行命令：java -jar 刚刚的jar包

就可以运行了



xml里无需关注版本号，自动版本仲裁

可以修改版本号

1.查看spring-boot-dependencies里面规定当前依赖的版本 用的key

2.在当前项目中重写版本号

```xml
<properties>
        <mysql.version>5.1.43</mysql.version>
    </properties>
```



## 自动配置

自动配好了Tomcat

自动配好了SpringMVC

自动配好了Web常见功能，如：字符编码

默认的包结构



## 注解

### @Configuration

告诉SpringBoot这是一个配置类

```
* 1、配置类里面使用@Bean标注在方法上给容器注册组件，默认也是单实例的 
* 2、配置类本身也是组件
* 3、proxyBeanMethods：代理bean的方法
*      Full(proxyBeanMethods = true)、【保证每个@Bean方法被调用多少次返回的组件都是单实例的】
*      Lite(proxyBeanMethods = false)【每个@Bean方法被调用多少次返回的组件都是新创建的】
*      组件依赖必须使用Full模式默认。其他默认是否Lite模式 
*      Full及每次调用里面的组件都是一样的，场景：保证组件依赖的另一个组件是容器中的组件 
*      Lite每次调用的里面的组件都是新的，场景：只是用于配置，组件之间不依赖，速度更快 
*      代理 ：有一只猫A  要猫就给你A 非代理 每次给你一个新的猫 
*/@Configuration(proxyBeanMethods = true) //告诉SpringBoot这是一个配置类 == 配置文件
```

```java
@Configuration(proxyBeanMethods = true) //告诉SpringBoot这是一个配置类 == 配置文件
public class MyConfig {
    /**
     * Full:外部无论对配置类中的这个组件注册方法调用多少次获取的都是之前注册容器中的单实例对象
     * @return
     */
    @Bean //给容器中添加组件。以方法名作为组件的id。返回类型就是组件类型。返回的值，就是组件在容器中的实例
    public User user01(){
        return new User("阿巴阿巴",18);
    }
    @Bean("tom")
    public Pet pat(){
        return new Pet("tom");
    }
}
```

### @Component

代表他是一个组件

### @Controller

代表它是一个控制器

### @Service

代表它是一个业务逻辑组件

### @Repository

代表它是一个数据库组件

### @ComponentScan

指定包扫描规则

### @Import

@Import({User.class, DBHelper.class} )
给容器中自动创建出这两个类型的组件，默认组件的名字就是全类名

### @Conditional

条件装配：满足Conditional指定的条件，则进行组件注入

### @ImportResource

因为现在还有一部分配件使用的是spring的xml配置而又无法快速转化成spring boot的类配置所以可以用ImportResource注解

在需要用到配置的类上加

```java
@ImportResource("classpath:beans.xml")
```

可以重新解析加到容器中

## 开发小技巧

### Lombok

简化开发java bean

自动生成get set方法有参无参构造 toString方法

首先引入插件

```xml
<dependency>
	<groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
</dependency>
```

搜索安装插件lombok



要加get set就加@Data注解

有参@AllArgsConstructor

无参@NoArgsConstructor

toString @ToString



### Spring Initailizr

新建项目的时候有Spring Initializr

spring的初始化项

可以选择场景

# 核心功能

## 配置文件

### 文件类型

文件中同时有properties和yaml都会生效

#### properties

application.properties

在bean类里加@ConfigurtionProperties标签指明配置文件里的开头

```java
@ConfigurtionProperties(prefix = "user")
@Data
public class User{
    private String name;
    private int age;
    
}
```

```xml
user.name=阿巴阿巴
user.age=10
```



#### yaml

标记语言

key: value; k v之间有空格
大小写敏感
使用缩进表示层级关系
缩进不允许使用tab,只允许空格
缩进的空格数不重要，只要相同层级的元素左对齐即可
'#'表示注释
“与"表示字符串内容会被转义/不转义

```yaml
字面量
k: v

对象
行内写法
k: {k1:v1,k1:v2,k3:v3}
或
k: 
 k1: v1
 k2: v2
 k3: v3

数组
行内写法
k: [v1,v2,v3]
或
k: 
 - v1
 - v2
 - v3

 
```

在bean类里加@ConfigurtionProperties标签指明配置文件里的开头

如果想在yaml中有配置提示

就加上

```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-configuration-processor</artifactId>
    <optional>true</optional>
</dependency>
```





## web开发

### SpringbootMVC



### 简单功能

#### 静态资源目录

只要静态资源放在类路径下: called /static (or /public or /resources or /META- INF /resources
访问:当前项目根路径/ +静态资源名



原理:静态映射/**.

请求进来,先去找Contoller看能不能处理。不能处理的所有请求又都交给静态资源处理器。静态资源也找到404



#### 静态资源访问前缀

默认无前缀/**



如果想要添加访问前缀

```yaml
spring:
 mvc:
  static-path-pattern: /res/**
```

现在要访问静态资源就要http://ip:端口/res/a.png



如果要指定静态资源文件

指定haha文件夹为静态资源文件夹

```yaml
resources:
 static-locations: [classpath:/haha/]
```

其他文件夹的静态资源都找不到了



#### 欢迎页的支持

静态方式:静态资源路径下有index.html

模板方式:controller能处理/index



#### favicon设置图标

将图标名设置为favicon

设置静态资源前缀会影响此方式



### 请求参数的处理

#### 请求映射

rest风格支持

 以前：/getUser 获取用户 /delectUser删除用户 /editUser修改用户 /saveUser保存用户



现在：/user GET获取用户  DELETE删除用户  PUT修改用户  POST保存用户

以同一个请求不同请求方式完成操作



核心Filter：HiddenHttpMethodFilter

​	用法：表单method=post，隐藏域_method=put



如果要提交delete请求或者put请求

在springboot开启配置

```yaml
spring:
  mvc:
    hiddenmethod:
      filter:
        enabled: true
```

把请求方式改为post

然后在请求中加一个隐藏域name为_method,value为DELETE或者PUT

```html
<form action="/user" method="post">
        <input type="hidden" name="_method" value="DELETE">
        <input value="delete" type="submit" />
    </form>
    <form action="/user" method="post">
        <input type="hidden" name="_method" value="PUT">
        <input value="put" type="submit" />
    </form>
```

```java
package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {
    @RequestMapping("/a.png")
    public String hllo(){
        return "her";
    }
//    @RequestMapping(value = "/user",method = RequestMethod.GET)
    @GetMapping("/user")
    public String getUser(){
        return "GET-张三";
    }

//    @RequestMapping(value = "/user",method = RequestMethod.POST)
    @PostMapping("/user")
    public String saveUser(){
        return "POST-张三";
    }


//    @RequestMapping(value = "/user",method = RequestMethod.PUT)
    @PutMapping("/user")
    public String putUser(){
        
        return "PUT-张三";
    }

//    @RequestMapping(value = "/user",method = RequestMethod.DELETE)
    @DeleteMapping("/user")
    public String deleteUser(){
        return "DELETE-张三";
    }

}
```

#### 普通参数和基本注解

##### 获取请求参数

```java

@RestController
public class HelloController {
    //设置请求参数@RequestParam
    @RequestMapping("/hello")
    public String hllo(@RequestParam("username") String name){
        return "her";
    }
}
```

##### 动态参数

```java

@RestController
public class HelloController {
	//通过占位符的方式设置参数@PathVariable
    //   /car/2/owner/zhangsan
    @RequestMapping("/car/{id}/owner/{username}")
    public Map<String,Object> car(@PathVariable("id") Integer id,
                                  @PathVariable("username") String name,
                                  @PathVariable Map<String,String> pv){//返回所有的值（id，username）到Map中Map只能为两个String
        Map<String,Object>=new HashMap<>();
        map.put("id",id);
        return map;
    }
}
```

##### 获取请求头

```java

@RestController
public class HelloController {
	@RequesMapping("/hello")
    public String hello(@RequestHeader("User-Agent") String userAgent,//拿一个
                        @RequestHeader Map<String,String> header//拿所有){
        return userAgent;
    }

}
```

##### 所有请求参数值

```java
@RestController
public class HelloController {
	@RequesMapping("/hello")
    public String hello(@RequestParam Map<String,String> params){
        return "";
    }

}
```

##### 获取Cookie的值

##### 

```java
@RestController
public class HelloController {
	@RequesMapping("/hello")
    public String hello(@CookieValue("_ga") String _ga,//Cookie的值
                        @CookieValue("_ga") Cookie cook //整个Cookie){
        return _ga;
    }

}
```

##### 获取请求体

##### 

```java
@RestController
public class HelloController {
	@RequesMapping("/hello")
    public String hello(@RequestBody String content){
        return content;
    }

}
```



##### 获取request域中的属性

##### 

```java
@Controller
public class HelloController {
	@GetMapping("/hello")
    public String goToPage(HttpServletRequest req){
        req.setAttribute("msg","成功")
        return "forword:/success";//转发到 /success请求
    }
    
	@RequestBody
    @GetMapping("/success")
    public String success(@RequestAttribute("msg") String msg){
        
        return msgS;
    }
    
}
```



##### 矩阵变量

SpringBoot默认关闭矩阵变量

需要手动开启



/cars/{path}/?xxx=xxx&aaa=ccc  queryString 查询字符串。@RequestParam

/cars/path;low=34;brand=byd,audi,yd   ; 矩阵变量



例子：

 页面开发，cookie禁用了，session里面的内容怎么使用；

 session.set(a,b)--->jsessionid--->cookie--->每次发请求携带

可以用

 url重写：/abc;jsessionid=xxxx 把cookie的值使用矩阵变量的方式经行传递

##### 

```java
@RestController
public class HelloController {
    // /cars/path;low=34;brand=byd,audi,yd
	@RequesMapping("/cars/{path}")
    public String hello(@MatrixVariable("low") Integer low,
                        @MatrixVariable("brand") List<String> brand){
        return "";
    }

}
```



### 视图解析与模板引擎

视图解析：SpringBoot默认不支持JSP，需要引入第三方模板引擎技术实现页面渲染



### 拦截器

##### HandlerInterceptor

拦截器接口

方法：preHandle目标方法执行之前，postHandle目标方法执行完成以后，afterCompletion页面渲染以后

（目标方法指的是路径映射方法）

一般拦截请求在preHandle中

```java
/**
 * 登录检查
 * 1.编写一个拦截器实现HandlerInterceptor接口
 * 2.把这些配置放在容器中（实现WebMvcConfigurer接口）
 * 3.指定拦截规则（拦截所有会拦截静态资源）
 */
public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 目标方法执行之前
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //登录检查逻辑
        HttpSession session=request.getSession();
        Object user = session.getAttribute("user");

        if(user!=null){
            //放行
            return true;
        }
        //拦截 未登录 跳转到登录
        session.setAttribute("msg","请先登录");
        response.sendRedirect("/");
        return false;
    }
}
```

preHandle返回true放行，返回false拦截，拦截一般跟上请求重定向

在SpringBoot中配合WebMvcConfigurer接口使用

其他用xml

##### WebMvcConfigurer

SpringBoot适配器接口 用来配置/定制 web功能

```java
@Configuration
public class AdminWebConfig implements WebMvcConfigurer {
    //配置拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加拦截器
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")//需要拦截的 /**是所有请求都被拦截包括静态资源
                .excludePathPatterns("/","/login","/css/**");//放行的
    }
}
```



### 文件上传

上传

```html
//action提交地址 上传表单提交方式必须是post enctype=""表示上传的文件不止有文本还有二进制文件
<form action="/up" method="post" enctype="multipart/form-data" >
    <input type="file" >单文件
    <input type="file" multiple>多文件
</form>
```

```java

/**
 * 文件上传
 */
@Controller
public class FormController {

    @GetMapping("/form_layouts")
    public String layouts(){
        return "form/form_layouts";
    }

    @PostMapping("/upload")
    public String upload(HttpSession session,
                         @RequestParam("email") String email,
                         @RequestParam("username") String username,
                         //@ 封装上传数据
                         @RequestParam("headerImg")MultipartFile headerImg,
                         @RequestParam("photos")MultipartFile[] photos) throws IOException {
        User user = (User) session.getAttribute("user");
        System.out.println(user.toString());
        //判空
        if(!headerImg.isEmpty()){
            //getOriginalFilename上传文件的文件名 有后缀
            headerImg.transferTo(new File("D:\\WebFile\\"+headerImg.getOriginalFilename()));
        }
        for (MultipartFile photo : photos) {
            if(!photo.isEmpty()){
                photo.transferTo(new File("D:\\WebFile\\"+headerImg.getOriginalFilename()));
            }
        }

        return "form/form_layouts";
    }
}

```



```properties
#单个文件最大上传大小
spring.servlet.multipart.max-file-size=1024MB
#总上传文件最大大小
spring.servlet.multipart.max-request-size=10240MB


```



## 使用原生的servlet API

### 原生方法

指定原生的servlet所在的包，要在主程序类中配置

```java
@ServletComponentScan("com.demo01_ht.admin")
```





```java
@ServletComponentScan("com.demo01_ht.admin")
@SpringBootApplication
public class Demo01HtApplication {

    public static void main(String[] args) {
        SpringApplication.run(Demo01HtApplication.class, args);
    }

}
```

```java
@WebServlet("/my")
public class MyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("66666");
    }
}
```

直接响应 /my 没有被springboot拦截器拦截，如果要拦截可以使用过滤器Filter

#### 过滤器

Filter

##### servlet的过滤器接口

有三个方法： 初始化方法  init()  

​					   过滤方法     doFilter()

​					   销毁方法     destroy()

```java
//拦截的路径
@WebFilter({"/**","/abc","/bcd"})
public class MannagerFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req= (HttpServletRequest) servletRequest;

        Object user = req.getSession().getAttribute("user");

        if(user==null){
            req.getRequestDispatcher("/pages/user/login.jsp").forward(servletRequest,servletResponse);
        }else {
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}

```



#### 监听器

##### servlet 监听器

contextInitialized() 监听项目初始化完成

contextDestroyed() 监听项目销毁

```java
//
@WebListener
public class MyListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("监听到项目初始化完成");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("监听到项目销毁");
    }
}
```



### springboot方法

```java
@Configuration
public class MyRegistConfig {
//注册Servlet，MyServlet是Servlet实现类
    @Bean
    public ServletRegistrationBean myServlet(){
        MyServlet myServlet = new MyServlet();
		//映射地址
        return new ServletRegistrationBean(myServlet,"/my","/my02");
    }

//注册过滤器,MyFilter是Filter实现类
    @Bean
    public FilterRegistrationBean myFilter(){

        MyFilter myFilter = new MyFilter();
//        return new FilterRegistrationBean(myFilter,myServlet());
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(myFilter);
        //过滤地址
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/my","/css/*"));
        return filterRegistrationBean;
    }
//注册监听器，MySwervletContextListener是ServletContextListener实现类
    @Bean
    public ServletListenerRegistrationBean myListener(){
        MySwervletContextListener mySwervletContextListener = new MySwervletContextListener();
        return new ServletListenerRegistrationBean(mySwervletContextListener);
    }
}
```





## 数据访问

### 数据源的自动配置---**HikariDataSource**

#### 导入JDBC场景

```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jdbc</artifactId>
		</dependency>
```



![image.png](https://cdn.nlark.com/yuque/0/2020/png/1354552/1606366100317-5e0199fa-6709-4d32-bce3-bb262e2e5e6a.png?x-oss-process=image%2Fwatermark%2Ctype_d3F5LW1pY3JvaGVp%2Csize_20%2Ctext_YXRndWlndS5jb20g5bCa56GF6LC3%2Ccolor_FFFFFF%2Cshadow_50%2Ct_80%2Cg_se%2Cx_10%2Cy_10)

数据库驱动？

为什么导入JDBC场景，官方不导入驱动？官方不知道我们接下要操作什么数据库。

数据库版本和驱动版本对应

```xml
默认版本：<mysql.version>8.0.22</mysql.version>

        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
<!--            <version>5.1.49</version>-->
        </dependency>
想要修改版本
1、直接依赖引入具体版本（maven的就近依赖原则）
2、重新声明版本（maven的属性的就近优先原则）
    <properties>
        <java.version>1.8</java.version>
        <mysql.version>5.1.49</mysql.version>
    </properties>
```



#### 修改配置项

```yml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/db_account
    username: root
    password: 1234
    driver-class-name: com.mysql.cj.jdbc.Driver
```

#### 使用

```java
class Demo01HtApplicationTests {

    @Autowired
    JdbcTemplate jdbc;

    @Test
    void contextLoads() {
        System.out.println(jdbc.queryForList("select * from tb_demo"));
    }

}
```



### 整合druid

首先导包

```xml
              <dependency>
                    <groupId>com.alibaba</groupId>
                    <artifactId>druid</artifactId>
                    <version>1.2.15</version>
                </dependency>
```



配置

```java
package com.demo01_ht.admin.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.zaxxer.hikari.util.DriverDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class MyDataSource {

    //和配置文件进行绑定然后注入到里面
    @ConfigurationProperties("spring.datasource")
    @Bean
    public DataSource dataSource(){
        //这里面的属性都会和配置文件中进行绑定
        DruidDataSource druidDataSource = new DruidDataSource();
//        druidDataSource.setUrl();
//        druidDataSource.setUsername();
//        druidDataSource.setPassword();

        //加入监控功能
        druidDataSource.setFilters("stat");
        return druidDataSource;
    }

    /**
     * 配置druid的监控页的功能,访问/druid时就会出现监控页
     * @return
     */

    @Bean
    public ServletRegistrationBean statViewServlet(){
        StatViewServlet statViewServlet = new StatViewServlet();
        ServletRegistrationBean<StatViewServlet> registrationBean = new ServletRegistrationBean<>(statViewServlet,"/druid/*");

        
        return registrationBean;
    }
    
}
```

### 整合Mybatis

首先引入场景启动器

```xml
              <dependency>
                    <groupId>org.mybatis.spring.boot</groupId>
                    <artifactId>mybatis-spring-boot-starter</artifactId>
                    <version>3.0.1</version>
                </dependency>
```





## 单元测试

## 指标监控

## 原理解析