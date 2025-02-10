package com.yomahub.liteflow.test.bindData;

import com.yomahub.liteflow.core.FlowExecutor;
import com.yomahub.liteflow.flow.LiteflowResponse;
import com.yomahub.liteflow.slot.DefaultContext;
import com.yomahub.liteflow.test.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestPropertySource;

import javax.annotation.Resource;

/**
 * springboot环境EL常规的例子测试
 *
 * @author Bryan.Zhang
 */
@TestPropertySource(value = "classpath:/bindData/application.properties")
@SpringBootTest(classes = BindDataSpringbootTest.class)
@EnableAutoConfiguration
@ComponentScan({ "com.yomahub.liteflow.test.bindData.cmp" })
public class BindDataSpringbootTest extends BaseTest {

	@Resource
	private FlowExecutor flowExecutor;

	// 测试bind关键字,简单情况
	@Test
	public void testBind1() throws Exception {
		LiteflowResponse response = flowExecutor.execute2Resp("chain1", "arg");
		DefaultContext context = response.getFirstContextBean();
		Assertions.assertEquals("test", context.getData("a"));
		Assertions.assertNull(context.getData("b"));
		Assertions.assertTrue(response.isSuccess());
	}

	// 测试bind关键字,表达式上加
	@Test
	public void testBind2() throws Exception {
		LiteflowResponse response = flowExecutor.execute2Resp("chain2", "arg");
		DefaultContext context = response.getFirstContextBean();
		Assertions.assertEquals("test", context.getData("a"));
		Assertions.assertEquals("test", context.getData("b"));
		Assertions.assertTrue(response.isSuccess());
	}

	// 测试bind关键字,相对复杂情况
	@Test
	public void testBind3() throws Exception {
		LiteflowResponse response = flowExecutor.execute2Resp("chain3", "arg");
		DefaultContext context = response.getFirstContextBean();
		Assertions.assertEquals("test", context.getData("a"));
		Assertions.assertEquals("test", context.getData("b"));
		Assertions.assertEquals("test", context.getData("c"));
		Assertions.assertEquals("test", context.getData("d"));
		Assertions.assertEquals("test", context.getData("x"));
		Assertions.assertEquals("test", context.getData("y"));
		Assertions.assertTrue(response.isSuccess());
	}
}
