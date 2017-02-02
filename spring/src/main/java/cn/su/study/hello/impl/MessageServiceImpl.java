package cn.su.study.hello.impl;

import org.springframework.stereotype.Component;

import cn.su.study.hello.MessageService;


@Component
public class MessageServiceImpl implements MessageService {

	@Override
	public void sayHi() {
		System.out.println("hi");
	}

}
