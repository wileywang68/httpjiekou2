package test2;

import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.config.SessionConfig;
import io.restassured.filter.session.SessionFilter;

import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.Map;

public class testsession {
	
	@Test
	public void testsession(){
		RestAssured.config=RestAssured.config().sessionConfig(new SessionConfig().sessionIdName("JSESSIONID.03e51c1d"));
		SessionFilter sessionfilter=new SessionFilter();
		Map <String,Object> map = new HashMap<String,Object>();
		map.put("Submit", "登录");
		map.put("j_username", "wiley.wang");
		map.put("j_password", "wiley123");
		map.put("from", "/");
	      			
		given()
					.filter(sessionfilter)
					.params(map)
//					.queryParam("Submit", "登录")
//					.queryParam("j_username", "wiley.wang")
//					.queryParam("j_password", "wiley123")
//					.queryParam("from", "/")
					
		.when()
					.log().all()
					.post("http://192.168.125.77:8080/j_acegi_security_check")
					.prettyPeek()
		.then()
					.log().all()
					.statusCode(302);
		
	}

}
