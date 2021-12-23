package com.atguigu.hospital;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@SpringBootTest
class HospitalManagerApplicationTests {

	@Test
	void contextLoads() {
		File file = new File("E:\\00\\医疗项目--尚医通\\资料\\资料\\05-医院接口模拟系统\\示例数据\\hospital.json");
		System.out.println("进入业务实现方法"+file);
		StringBuilder data = new StringBuilder();
		try {
			InputStream in = new FileInputStream(file);
			byte[] bytes = new byte[1024];
			while(in.read(bytes)>0){
				data.append(new String(bytes));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(data.toString());
	}

}
