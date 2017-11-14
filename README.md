# observer-springboot-starter
springboot自定义观察者模式的starter，开箱即用
使用方法：
1、build完成之后发布到maven仓库，或者maven install
2、在需要使用的项目中引入，maven坐标如下：
```xml
<dependency>
			<groupId>cn.com.sweetcandy</groupId>
			<artifactId>observer-spring-boot-stater</artifactId>
			<version>0.0.1-SNAPSHOT</version>
</dependency>
```
3、配置properties文件开启观察者模式自动扫描：

#开启观察者模式
observer.enabled=true
#自动扫描指定包下面的类文件(需要被注入观察者内的文件需要被spring托管，如使用@Service注解，并且该类实现Handel接口)
observer.observerPackage=cn.com.sweetcandy
