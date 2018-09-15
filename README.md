# Spring Cloud Starter Config Datasource Issue

A sample project demonstrating that there is an issue related to Spring Boot 2.1.x 
mixed with the Spring Cloud Starter Config dependency.

When I attempt to upgrade this simple JPA application from Spring Boot `v2.0.4.RELEASE`
to `2.1.0.M3` I encounter the following error:

```
2018-09-15 11:56:16.748 [DEBUG] o.s.b.d.LoggingFailureAnalysisReporter : Application failed to start due to an exception

org.springframework.beans.factory.support.BeanDefinitionOverrideException: Invalid bean definition with name 'dataSource' 
defined in BeanDefinition defined in class path resource [org/springframework/boot/autoconfigure/jdbc/DataSourceConfiguration$Hikari.class]: 
Cannot register bean definition [Root bean: class [org.springframework.aop.scope.ScopedProxyFactoryBean]; scope=; abstract=false; 
lazyInit=false; autowireMode=0; dependencyCheck=0; autowireCandidate=true; primary=false; factoryBeanName=null; factoryMethodName=null; 
initMethodName=null; destroyMethodName=null; defined in BeanDefinition defined in class path resource 
[org/springframework/boot/autoconfigure/jdbc/DataSourceConfiguration$Hikari.class]] for bean 'dataSource': There is already 
[Root bean: class [null]; scope=refresh; abstract=false; lazyInit=false; autowireMode=3; dependencyCheck=0; autowireCandidate=false; 
primary=false; factoryBeanName=org.springframework.boot.autoconfigure.jdbc.DataSourceConfiguration$Hikari; factoryMethodName=dataSource; 
initMethodName=null; destroyMethodName=(inferred); defined in class path resource 
[org/springframework/boot/autoconfigure/jdbc/DataSourceConfiguration$Hikari.class]] bound.
  at org.springframework.beans.factory.support.DefaultListableBeanFactory.registerBeanDefinition(DefaultListableBeanFactory.java:877) 
  ~[spring-beans-5.1.0.RC3.jar:5.1.0.RC3]
  at org.springframework.cloud.autoconfigure.RefreshAutoConfiguration$RefreshScopeBeanDefinitionEnhancer.postProcessBeanDefinitionRegistry(RefreshAutoConfiguration.java:140) 
  ~[spring-cloud-context-2.0.1.RELEASE.jar:2.0.1.RELEASE]
  at org.springframework.context.support.PostProcessorRegistrationDelegate.invokeBeanDefinitionRegistryPostProcessors(PostProcessorRegistrationDelegate.java:275) 
  ~[spring-context-5.1.0.RC3.jar:5.1.0.RC3]
  at org.springframework.context.support.PostProcessorRegistrationDelegate.invokeBeanFactoryPostProcessors(PostProcessorRegistrationDelegate.java:125) 
  ~[spring-context-5.1.0.RC3.jar:5.1.0.RC3]
  at org.springframework.context.support.AbstractApplicationContext.invokeBeanFactoryPostProcessors(AbstractApplicationContext.java:693) 
  ~[spring-context-5.1.0.RC3.jar:5.1.0.RC3]
  at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:530) 
  ~[spring-context-5.1.0.RC3.jar:5.1.0.RC3]
  at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.refresh(ServletWebServerApplicationContext.java:140) 
  ~[spring-boot-2.1.0.M3.jar:2.1.0.M3]
  at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:797) [spring-boot-2.1.0.M3.jar:2.1.0.M3]
  at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:421) [spring-boot-2.1.0.M3.jar:2.1.0.M3]
  at org.springframework.boot.SpringApplication.run(SpringApplication.java:340) [spring-boot-2.1.0.M3.jar:2.1.0.M3]
  at org.springframework.boot.SpringApplication.run(SpringApplication.java:1291) [spring-boot-2.1.0.M3.jar:2.1.0.M3]
  at org.springframework.boot.SpringApplication.run(SpringApplication.java:1279) [spring-boot-2.1.0.M3.jar:2.1.0.M3]
  at com.myco.DatasourceTestApplication.main(DatasourceTestApplication.java:10) [classes/:na]

2018-09-15 11:56:16.748 [ERROR] o.s.b.d.LoggingFailureAnalysisReporter : 

***************************
APPLICATION FAILED TO START
***************************

Description:

The bean 'dataSource', defined in BeanDefinition defined in class path resource 
[org/springframework/boot/autoconfigure/jdbc/DataSourceConfiguration$Hikari.class], could not be registered. 
A bean with that name has already been defined in class path resource 
[org/springframework/boot/autoconfigure/jdbc/DataSourceConfiguration$Hikari.class] and overriding is disabled.

Action:

Consider renaming one of the beans or enabling overriding by setting spring.main.allow-bean-definition-overriding=true
```

When this application specifies spring boot version `2.0.4.RELEASE` you can access the following
endpoints, which demonstrates that JPA persistence is working as expected:

* http://localhost:8080/ID1
* http://localhost:8080/ID2
* http://localhost:8080/ID3
