package cn.su.study.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Component;

import cn.su.study.springtest.hello.MessageService;

/**
 * Hello world!
 *
 */
@Component
public class App {

	@Autowired
	MessageService messageService;

	public static void main(String[] args) {
		// If Java SE 7 and later, don't close, use try-with-resources which
		// ensures that each resource is closed at the end of the statement.

		// AbstractApplicationContext 才有close的方法。
		// 在非Web应用中，手工加载Spring
		// IoC容器，不能用ApplicationContext，要用AbstractApplicationContext。用完以后要记得调用ctx.close()关闭容器。如果不记得关闭容器，最典型的问题就是数据库连接不能释放
		try (final AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfigure.class)) {
			App app = ctx.getBean(App.class);
			app.messageService.sayHi();
		}
	}
}
