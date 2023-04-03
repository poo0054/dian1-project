# 所有功能

1. dynamic:动态表格 [README.md](dian1-spring-boot-starter%2Fdynamic_table-spring-boot-starter%2FREADME.md)
2. gen:代码自动生成 [README.md](dian1-spring-boot-starter%2Fgen-spring-boot-starter%2FREADME.md)
3. http: 封装hutool的http [README.md](dian1-spring-boot-starter%2Fhttp-spring-boot-starter%2FREADME.md)

## http

> 引入

```maven
 <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>com.dian1</groupId>
                <artifactId>dian1-project</artifactId>
                <version>versin</version>
                <scope>import</scope>
                <type>pom</type>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <dependencies>
        <dependency>
            <groupId>com.dian1</groupId>
            <artifactId>http-spring-boot-starter</artifactId>
        </dependency>
     </dependencies>
```

> 使用方法

在spring方法上添加 [EnableHttp.java](..%2F..%2Fdian1-spring-boot-autoconfigure%2Fhttp-spring-boot-autoconfigure%2Fsrc%2Fmain%2Fjava%2Fcom%2Fdian1%2Fhttp%2Fannotate%2FEnableHttp.java)
注解并添加扫描路径即可启动

---------

> 案例

使用案例[HttpTest.java](..%2F..%2Fdian1-spring-boot-example%2Fhttp-spring-boot-example%2Fsrc%2Fmain%2Fjava%2Fcom%2Fdian1%2Fhttp%2Fhttp%2FHttpTest.java)

---------

> 自定义

该框架最大特点可以任意自定义.可以使用嵌套注解做成一个组合注解.

例如:

```java

@Target({ElementType.PARAMETER, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@BasicAuth(username = "", password = "")
@Header(value = "Accept: text/html,application/json,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
@Delete("/delete")
public @interface Custom {

}
```

自定义注解类(必须注入spring才能生效):
[ClassHandle.java](..%2F..%2Fdian1-spring-boot-autoconfigure%2Fhttp-spring-boot-autoconfigure%2Fsrc%2Fmain%2Fjava%2Fcom%2Fdian1%2Fhttp%2Fhandle%2Fbase%2FClassHandle.java)
[TypeHandle.java](..%2F..%2Fdian1-spring-boot-autoconfigure%2Fhttp-spring-boot-autoconfigure%2Fsrc%2Fmain%2Fjava%2Fcom%2Fdian1%2Fhttp%2Fhandle%2Ftype%2FTypeHandle.java)
[ParameterHandle.java](..%2F..%2Fdian1-spring-boot-autoconfigure%2Fhttp-spring-boot-autoconfigure%2Fsrc%2Fmain%2Fjava%2Fcom%2Fdian1%2Fhttp%2Fhandle%2Fparameter%2FParameterHandle.java)
[ResultHandle.java](..%2F..%2Fdian1-spring-boot-autoconfigure%2Fhttp-spring-boot-autoconfigure%2Fsrc%2Fmain%2Fjava%2Fcom%2Fdian1%2Fhttp%2Fhandle%2Fresult%2FResultHandle.java)

--------------------

1. ClassHandle对应的是最上层接口.
   对应[ClassHandle.java](..%2F..%2Fdian1-spring-boot-autoconfigure%2Fhttp-spring-boot-autoconfigure%2Fsrc%2Fmain%2Fjava%2Fcom%2Fdian1%2Fhttp%2Fhandle%2Fbase%2FClassHandle.java)
2. TypeHandle对应的是接口上的注解(method的注解)
   例如[GetHandle.java](..%2F..%2Fdian1-spring-boot-autoconfigure%2Fhttp-spring-boot-autoconfigure%2Fsrc%2Fmain%2Fjava%2Fcom%2Fdian1%2Fhttp%2Fhandle%2Ftype%2FGetHandle.java)
   和[PostHandle.java](..%2F..%2Fdian1-spring-boot-autoconfigure%2Fhttp-spring-boot-autoconfigure%2Fsrc%2Fmain%2Fjava%2Fcom%2Fdian1%2Fhttp%2Fhandle%2Ftype%2FPostHandle.java)
3. ParameterHandle对的是参数注解.
   例如[FormHandle.java](..%2F..%2Fdian1-spring-boot-autoconfigure%2Fhttp-spring-boot-autoconfigure%2Fsrc%2Fmain%2Fjava%2Fcom%2Fdian1%2Fhttp%2Fhandle%2Fparameter%2FFormHandle.java)
   和[BodyHandle.java](..%2F..%2Fdian1-spring-boot-autoconfigure%2Fhttp-spring-boot-autoconfigure%2Fsrc%2Fmain%2Fjava%2Fcom%2Fdian1%2Fhttp%2Fhandle%2Fparameter%2FBodyHandle.java)
4. ResultHandle是返回值,
   [ResultHandle.java](..%2F..%2Fdian1-spring-boot-autoconfigure%2Fhttp-spring-boot-autoconfigure%2Fsrc%2Fmain%2Fjava%2Fcom%2Fdian1%2Fhttp%2Fhandle%2Fresult%2FResultHandle.java)

---------


> 基本原理

使用hutools的http进行封装.利用[HttpBeanDefinitionScanner.java](..%2F..%2Fdian1-spring-boot-autoconfigure%2Fhttp-spring-boot-autoconfigure%2Fsrc%2Fmain%2Fjava%2Fcom%2Fdian1%2Fhttp%2Fconfiguration%2FHttpBeanDefinitionScanner.java)
扫描 [OpenHttp.java](..%2F..%2Fdian1-spring-boot-autoconfigure%2Fhttp-spring-boot-autoconfigure%2Fsrc%2Fmain%2Fjava%2Fcom%2Fdian1%2Fhttp%2Fannotate%2FOpenHttp.java)
,注入[HttpFactoryBean.java](..%2F..%2Fdian1-spring-boot-autoconfigure%2Fhttp-spring-boot-autoconfigure%2Fsrc%2Fmain%2Fjava%2Fcom%2Fdian1%2Fhttp%2Fproxy%2FHttpFactoryBean.java)
并创建[HttpProxy.java](..%2F..%2Fdian1-spring-boot-autoconfigure%2Fhttp-spring-boot-autoconfigure%2Fsrc%2Fmain%2Fjava%2Fcom%2Fdian1%2Fhttp%2Fproxy%2FHttpProxy.java)
代理对象


> 注意

1. 格式化都是FastJson xml暂未支持
2. 注解都可以嵌套使用
3. 根据
   [HttpProperties.java](..%2F..%2Fdian1-spring-boot-autoconfigure%2Fhttp-spring-boot-autoconfigure%2Fsrc%2Fmain%2Fjava%2Fcom%2Fdian1%2Fhttp%2Fproperties%2FHttpProperties.java)
   构建出http请求

---------