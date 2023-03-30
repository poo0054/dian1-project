> 基本原理

使用hutools的http进行封装.利用[HttpBeanDefinitionScanner.java](..%2F..%2Fdian1-spring-boot-autoconfigure%2Fhttp-spring-boot-autoconfigure%2Fsrc%2Fmain%2Fjava%2Fcom%2Fdian1%2Fhttp%2Fconfiguration%2FHttpBeanDefinitionScanner.java)
扫描 [OpenHttp.java](..%2F..%2Fdian1-spring-boot-autoconfigure%2Fhttp-spring-boot-autoconfigure%2Fsrc%2Fmain%2Fjava%2Fcom%2Fdian1%2Fhttp%2Fannotate%2FOpenHttp.java)
,注入[HttpFactoryBean.java](..%2F..%2Fdian1-spring-boot-autoconfigure%2Fhttp-spring-boot-autoconfigure%2Fsrc%2Fmain%2Fjava%2Fcom%2Fdian1%2Fhttp%2Fproxy%2FHttpFactoryBean.java)
并创建[HttpProxy.java](..%2F..%2Fdian1-spring-boot-autoconfigure%2Fhttp-spring-boot-autoconfigure%2Fsrc%2Fmain%2Fjava%2Fcom%2Fdian1%2Fhttp%2Fproxy%2FHttpProxy.java)
代理对象

> 案例

使用案例[HttpTest.java](..%2F..%2Fdian1-spring-boot-example%2Fhttp-spring-boot-example%2Fsrc%2Fmain%2Fjava%2Fcom%2Fdian1%2Fhttp%2Fhttp%2FHttpTest.java)

> 注意

1. 格式化都是FastJson,xml暂未支持
2. 注解都可以嵌套使用

> 自定义

支持 [ClassHandle.java](..%2F..%2Fdian1-spring-boot-autoconfigure%2Fhttp-spring-boot-autoconfigure%2Fsrc%2Fmain%2Fjava%2Fcom%2Fdian1%2Fhttp%2Fhandle%2Fbase%2FClassHandle.java)
和[TypeHandle.java](..%2F..%2Fdian1-spring-boot-autoconfigure%2Fhttp-spring-boot-autoconfigure%2Fsrc%2Fmain%2Fjava%2Fcom%2Fdian1%2Fhttp%2Fhandle%2Ftype%2FTypeHandle.java)
和[ParameterHandle.java](..%2F..%2Fdian1-spring-boot-autoconfigure%2Fhttp-spring-boot-autoconfigure%2Fsrc%2Fmain%2Fjava%2Fcom%2Fdian1%2Fhttp%2Fhandle%2Fparameter%2FParameterHandle.java)
和[ResultHandle.java](..%2F..%2Fdian1-spring-boot-autoconfigure%2Fhttp-spring-boot-autoconfigure%2Fsrc%2Fmain%2Fjava%2Fcom%2Fdian1%2Fhttp%2Fhandle%2Fresult%2FResultHandle.java)

1.
ClassHandle对应的是最上层接口.对应[ClassHandle.java](..%2F..%2Fdian1-spring-boot-autoconfigure%2Fhttp-spring-boot-autoconfigure%2Fsrc%2Fmain%2Fjava%2Fcom%2Fdian1%2Fhttp%2Fhandle%2Fbase%2FClassHandle.java)
2. TypeHandle对应的是接口上的注解(method的注解)
   例如[GetHandle.java](..%2F..%2Fdian1-spring-boot-autoconfigure%2Fhttp-spring-boot-autoconfigure%2Fsrc%2Fmain%2Fjava%2Fcom%2Fdian1%2Fhttp%2Fhandle%2Ftype%2FGetHandle.java)
   和[PostHandle.java](..%2F..%2Fdian1-spring-boot-autoconfigure%2Fhttp-spring-boot-autoconfigure%2Fsrc%2Fmain%2Fjava%2Fcom%2Fdian1%2Fhttp%2Fhandle%2Ftype%2FPostHandle.java)
3.
ParameterHandle对的是参数注解.例如[FormHandle.java](..%2F..%2Fdian1-spring-boot-autoconfigure%2Fhttp-spring-boot-autoconfigure%2Fsrc%2Fmain%2Fjava%2Fcom%2Fdian1%2Fhttp%2Fhandle%2Fparameter%2FFormHandle.java)
和[BodyHandle.java](..%2F..%2Fdian1-spring-boot-autoconfigure%2Fhttp-spring-boot-autoconfigure%2Fsrc%2Fmain%2Fjava%2Fcom%2Fdian1%2Fhttp%2Fhandle%2Fparameter%2FBodyHandle.java)
4.
ResultHandle是返回值,[ResultHandle.java](..%2F..%2Fdian1-spring-boot-autoconfigure%2Fhttp-spring-boot-autoconfigure%2Fsrc%2Fmain%2Fjava%2Fcom%2Fdian1%2Fhttp%2Fhandle%2Fresult%2FResultHandle.java)