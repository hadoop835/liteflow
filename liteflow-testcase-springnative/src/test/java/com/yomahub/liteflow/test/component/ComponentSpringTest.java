package com.yomahub.liteflow.test.component;

import com.yomahub.liteflow.core.FlowExecutor;
import com.yomahub.liteflow.flow.LiteflowResponse;
import com.yomahub.liteflow.slot.DefaultContext;
import com.yomahub.liteflow.test.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ReflectionUtils;

import javax.annotation.Resource;

/**
 * 组件功能点测试
 * 单元测试
 *
 * @author donguo.tao
 */
@RunWith(SpringRunner.class)
@ContextConfiguration("classpath:/component/application.xml")
public class ComponentSpringTest extends BaseTest {
    private static final Logger LOG = LoggerFactory.getLogger(ComponentSpringTest.class);

    @Resource
    private FlowExecutor flowExecutor;

    //isAccess方法的功能测试
    @Test
    public void testIsAccess() {
        LiteflowResponse<DefaultContext> response = flowExecutor.execute2Resp("chain1", 101);
        Assert.assertTrue(response.isSuccess());
        Assert.assertNotNull(response.getSlot().getResponseData());
    }

    //组件抛错的功能点测试
    @Test(expected = ArithmeticException.class)
    public void testComponentException() throws Exception {
        LiteflowResponse<DefaultContext> response = flowExecutor.execute2Resp("chain2", 0);
        Assert.assertFalse(response.isSuccess());
        Assert.assertEquals("/ by zero", response.getMessage());
        ReflectionUtils.rethrowException(response.getCause());
    }

    //isContinueOnError方法的功能点测试
    @Test
    public void testIsContinueOnError() throws Exception {
        LiteflowResponse<DefaultContext> response = flowExecutor.execute2Resp("chain3", 0);
        Assert.assertTrue(response.isSuccess());
        Assert.assertNull(response.getCause());
    }

    //isEnd方法的功能点测试
    @Test
    public void testIsEnd() throws Exception {
        LiteflowResponse<DefaultContext> response = flowExecutor.execute2Resp("chain4", 10);
        Assert.assertTrue(response.isSuccess());
        Assert.assertEquals("d",response.getExecuteStepStr());
    }

    //setIsEnd方法的功能点测试
    @Test
    public void testSetIsEnd1() throws Exception {
        LiteflowResponse<DefaultContext> response = flowExecutor.execute2Resp("chain5", 10);
        Assert.assertTrue(response.isSuccess());
        Assert.assertEquals("e",response.getExecuteStepStr());
    }

    //条件组件的功能点测试
    @Test
    public void testNodeCondComponent() {
        LiteflowResponse<DefaultContext> response = flowExecutor.execute2Resp("chain6", 0);
        Assert.assertTrue(response.isSuccess());
    }

    //测试setIsEnd如果为true，continueError也为true，那不应该continue了
    @Test
    public void testSetIsEnd2() throws Exception {
        LiteflowResponse<DefaultContext> response = flowExecutor.execute2Resp("chain7", 10);
        Assert.assertTrue(response.isSuccess());
        Assert.assertEquals("g",response.getExecuteStepStr());
    }

}
