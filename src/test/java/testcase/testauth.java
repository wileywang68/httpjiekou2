package testcase;

import org.junit.Test;
import static io.restassured.RestAssured.*;
public class testauth {
	
//	@Test
//	public void testhttpauth(){
//		given()
//				.auth().basic("hogwarts","123456")
//		.when()
//				.get("http://101.132.159.87:9002/baidu.html").prettyPeek()
//		.then().statusCode(200)
//		;
//	}
	
	@Test
	public void testAuth2(){
		useRelaxedHTTPSValidation();
		String token="7c031f3bfb6b2d71d0250d83a9bd04170c8af2f6";
		given().auth().oauth2(token).when().get("https://api.github.com/user").prettyPeek().then().statusCode(200);
	}

}
