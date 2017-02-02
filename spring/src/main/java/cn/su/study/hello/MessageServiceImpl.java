package cn.su.study.hello;

import org.springframework.stereotype.Component;


@Component
public class MessageServiceImpl implements MessageService {

	@Override
	public void sayHi() {
		System.out.println("hi");
	}

}
