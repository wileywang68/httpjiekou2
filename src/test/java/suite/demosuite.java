package suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import testcase.test2;
import testcase.test3;
import testcase.testauth;
import testcase.testsession;
//集成测试用例，运行多个测试类
@RunWith(Suite.class)
@Suite.SuiteClasses({
	test2.class,
	test3.class,
	testauth.class,
	testsession.class
	
})
public class demosuite {

}
