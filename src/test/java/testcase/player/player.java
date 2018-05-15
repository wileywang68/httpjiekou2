package testcase.player;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import net.sf.json.JSONObject;
import tools.getToken;
import tools.publicTest;

public class player {
	
	public String url="http://192.168.5.111:8590/player";
	
	public static Map <String,Object> postdata = new HashMap<String,Object>();
	
	@BeforeClass
	public static void testsetUp(){		
		 postdata.put("seq", "100");
		 postdata.put("rseq", 100);
		 postdata.put("d", "");
		 postdata.put("t",getToken.readToken()); 
	}
	
	@Test
	public void testPlayer(){
		String j="{'plat':'WindowsEditor','flags':['lang_zh-Hans','font_FZLTH'],'capid':'d05f4c5d4a064ce68b040e59fe809cb8','jsonUpdate':{},'ver':{'editor':2147483647}}";
		 JSONObject jsond= JSONObject.fromObject(j);  
		postdata.put("d", jsond);
		System.out.println(postdata);
		given()
					.params(postdata)
		.when()
					.post(url).prettyPeek()
		.then()
					.spec(publicTest.resSucess())
		        	.body("d.info.name", equalTo("你好"))
					;
	}
	
	@Test
	public void testPlayer1(){
		given()
					.params(postdata)
		.when()
					.post(url).prettyPeek()
		.then()
					.spec(publicTest.resFail())
		        	.body("d", equalTo("设备号错误"))
					;
	}
	
	

}
