package testcase.login;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import net.sf.json.JSONObject;
import tools.publicTest;

public class testUserLogin {
	
    public String url="http://192.168.5.116:8590/user/login";
	
	public static Map <String,Object> postdata = new HashMap<String,Object>();
	
	@BeforeClass
	public static void testsetUp(){		
		 postdata.put("seq", "1");
		 postdata.put("rseq", 1);
		 postdata.put("d", "");
		 System.out.println("来了");
	
	}

	//正确的用户名、密码
	@Test
	public void testUserlogin(){
		String j="{'pwd':'c','capid':'d05f4c5d4a064ce68b040e59fe809cb8','name':'ccc@ccc.com','udid':'d05f4c5d4a064ce68b040e59fe809cb8'}";
		 JSONObject jsond= JSONObject.fromObject(j);  
		 postdata.put("d", jsond);
		 given()
					.params(postdata)
		.when()
					.post(url).prettyPeek()
		.then()
					.spec(publicTest.resSucess())
					.body("d.uinfo.capstoneID", equalTo("5adc929ae13823789172bae3"));
	}
	
	//用户名格式错误
	@Test
	public void testUserlogin1(){
		String j="{'pwd':'c','capid':'d05f4c5d4a064ce68b040e59fe809cb8','name':'cccccc.com','udid':'d05f4c5d4a064ce68b040e59fe809cb8'}";
		 JSONObject jsond= JSONObject.fromObject(j);  
		 postdata.put("d", jsond);
		 given()
					.params(postdata)
		.when()
					.post(url).prettyPeek()
		.then()
					.spec(publicTest.resFail())
					.body("d", equalTo("用户名格式错误"));
	}
	
	//用户名错误
	@Test
	public void testUserlogin2(){
			String j="{'pwd':'c','capid':'d05f4c5d4a064ce68b040e59fe809cb8','name':'ccc@cccsdfa.com','udid':'d05f4c5d4a064ce68b040e59fe809cb8'}";
			 JSONObject jsond= JSONObject.fromObject(j);  
			 postdata.put("d", jsond);
			 given()
						.params(postdata)
			.when()
						.post(url).prettyPeek()
			.then()
						.spec(publicTest.resFail())
						.body("d", equalTo("用户不存在"));
	}
	
	//密码错误
	@Test
	public void testUserlogin3(){
				String j="{'pwd':'casd','capid':'d05f4c5d4a064ce68b040e59fe809cb8','name':'ccc@ccc.com','udid':'d05f4c5d4a064ce68b040e59fe809cb8'}";
				 JSONObject jsond= JSONObject.fromObject(j);  
				 postdata.put("d", jsond);
				 given()
							.params(postdata)
				.when()
							.post(url).prettyPeek()
				.then()
							.spec(publicTest.resFail())
							.body("d", equalTo("密码错误，请重试"));
	}
	
	
}
