package tools;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import io.restassured.response.Response;
import net.sf.json.JSONObject;

public class getToken {
	
	public static  String[] userLogin(){
		  String url="http://192.168.5.116:8590/user/login";
		  String s="{'pwd':'c','capid':'d05f4c5d4a064ce68b040e59fe809cb8','name':'ccc@ccc.com','udid':'d05f4c5d4a064ce68b040e59fe809cb8'}";
		  JSONObject json_test = JSONObject.fromObject(s);  
	      Map <String,Object> map = new HashMap<String,Object>();
		  map.put("seq", "1");
		  map.put("rseq", 1);
		  map.put("d", json_test);
		  Response response=given()
					.params(map)
		  .when()
					.post(url).prettyPeek()
		  .then()
					.spec(publicTest.resSucess())
					.body("d.uinfo.capstoneID", equalTo("5adc929ae13823789172bae3")).extract().response()
		  ;
		  
		  String token1=response.path("d.uinfo.token");
		  String cuid=response.path("d.cuid");
		  String capstoneid=response.path("d.uinfo.capstoneID");
		  String[] arr={token1,cuid,capstoneid};
		  return arr;

	}
	
	public static String getUserToken(){
		String[] arr=userLogin();
		String url="http://192.168.5.111:8590/a/login";
		String j="{'uinfo': {'token':'"+arr[0]+"','capstoneID':'"+arr[1]+"'},'channel':'capstone','bichannel':'capstone','cuid':'"+arr[2]+"','capid':'d05f4c5d4a064ce68b040e59fe809cb8','pf':'capstone','udid':'d05f4c5d4a064ce68b040e59fe809cb8'}";
		JSONObject json_test = JSONObject.fromObject(j);  
	    Map <String,Object> map = new HashMap<String,Object>();
		map.put("seq", "2");
		map.put("rseq", 2);
		map.put("d", json_test);
		String token=
		  given()
					.params(map)
		  .when()
					.post(url).prettyPeek()
		  .then()
					.spec(publicTest.resSucess())
					.body("d.servers[0].player.name", equalTo("你好")).extract().path("d.servers[0].player.token")
		  ;
		return token;
	}
	
	
	public void savetoken() throws Exception{
		String token=getUserToken();
	    File file =new File("token.txt");
	    if(!file.exists()){
	       file.createNewFile();
	    }
	      FileWriter fileWritter = new FileWriter(file.getName(),false);
	      BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
	      bufferWritter.write(token);
	      bufferWritter.close();
	      System.out.println(file.getAbsolutePath());
	     System.out.println("Done");
	}
	
	/**
     * 以行为单位读取文件，常用于读面向行的格式化文件
     */
    public static String readToken() {
        File file = new File("token.txt");
        BufferedReader reader = null;
        String token = "";
        String token1="";
        try {
            reader = new BufferedReader(new FileReader(file));
            while ((token1 = reader.readLine()) != null) {
            		token+=token1;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return token;
    }
	
	public static void main(String args[]) throws Exception{
	getToken g=	new getToken();
	g.savetoken();
	}

}
