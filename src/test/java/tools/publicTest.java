package tools;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

public class publicTest {
	
	//参数正确的公共返回结果
	public static ResponseSpecification resSucess(){
		  ResponseSpecification rs=new ResponseSpecBuilder().build();
		  rs.statusCode(200);    //全局断言
	 	  rs.time(lessThan(1000L));
		  rs.body("r", equalTo(0));
		  return rs;
	}
	
	
//参数错误的公共返回结果
	public static ResponseSpecification resFail(){
		  ResponseSpecification rs=new ResponseSpecBuilder().build();
		  rs.statusCode(200);    //全局断言
	 	  rs.time(lessThan(1000L));
		  rs.body("r", equalTo(1));
		  return rs;
	}

}
