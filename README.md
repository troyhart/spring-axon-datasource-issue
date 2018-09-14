# Spring Axon Datasource Issue

A sample project demonstrating an issue with Axon when deployed in Spring Boot >= 2.1.x 

If you attempt to deploy this application when the `spring-boot-starter-parent` version is set to `2.1.0.M3`, you 
will encounter the following error:

```
***************************
APPLICATION FAILED TO START
***************************

Description:

The bean 'dataSource', defined in BeanDefinition defined in class path resource 
[org/springframework/boot/autoconfigure/jdbc/DataSourceConfiguration$Hikari.class], 
could not be registered. A bean with that name has already been defined in class path resource 
[org/springframework/boot/autoconfigure/jdbc/DataSourceConfiguration$Hikari.class] and overriding is disabled.
```

However, if you switch the `spring-boot-starter-parent` version to `2.0.4.RELEASE` the application will 
deploy and the following endpoints will return a result:

* http://localhost:8080/ID1
* http://localhost:8080/ID2
* http://localhost:8080/ID3

NOTE: this application doesn't actually use anything from the axon framework (it doesn't define an aggregate 
or any command/event handlers), it just provides some basic axon configuration.
