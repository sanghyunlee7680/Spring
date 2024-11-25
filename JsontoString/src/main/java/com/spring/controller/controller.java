package com.spring.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.spirng.dto.BookDTO;

@Controller
@RequestMapping("/test")
public class controller {
	String json;
	Gson gs;
	
	@GetMapping("/case1")
	public String index() {
		return "index";
	}
	
	// DTO --> JSON
	@ResponseBody
	@GetMapping("/project01")
	public String project01() {
		BookDTO dto = new BookDTO("자바", 21000, "에이콘", 670);
		System.out.println(dto.toString());
		gs = new Gson();
		json = gs.toJson(dto);
		System.out.println(json);
		
		
		return json;
	}
	
	// JSON --> DTO
	@GetMapping("/project02")
	public String project02() {  // .class는 객체의 정보를 담음
		BookDTO dto = gs.fromJson(json, BookDTO.class);
		System.out.println("dto.toString() : "+ dto.toString());
		System.out.println(dto.getTitle()+"\t"+dto.getPrice());
		return "index";
	}
	
	
	// 배열 --> JSON
	// DTO들을 ArrayList로 묶고 --> JSON
	@ResponseBody
	@GetMapping("/project03")
	public String project03() {
		BookDTO dto1 = new BookDTO("자바1", 21000, "에이콘1", 570);
		BookDTO dto2 = new BookDTO("자바2", 31000, "에이콘2", 670);
		BookDTO dto3 = new BookDTO("자바3", 11000, "에이콘3", 370);
		ArrayList<BookDTO> list = new ArrayList<BookDTO>();
		list.add(dto1);
		list.add(dto2);
		list.add(dto3);
		
		gs = new Gson();
		String listJson = gs.toJson(list);
		System.out.println(listJson);
		
		// JSON(ArrayList) --> ArrayList<BookDTO>				Token = 데이터의 단위
		ArrayList<BookDTO> jsontolist = gs.fromJson(listJson, new TypeToken<ArrayList<BookDTO>>() {}.getType());
		
		for (int i=0; i<jsontolist.size(); i++) {
			BookDTO tmp = jsontolist.get(i);
			System.out.println("tmp.toString("+i+") : " + tmp.toString() );
		}
		
		return listJson;
	}
	
	@GetMapping("/project04")
	public String project04() {
		//JSON-JAVA(org.json)
		JSONArray students = new JSONArray();
		JSONObject student1 = new JSONObject();
		student1.put("name", "홍길동");
		student1.put("phone", "010-1111-1111");
		student1.put("address", "서울");
		System.out.println("student1 : " + student1);
		
		JSONObject student2 = new JSONObject();
		student2.put("name",  "나길동");
		student2.put("phone", "010-2222-2222");
		student2.put("address", "광주");
		System.out.println("student2 : " + student2);
		
		
		students.put(student1);
		students.put(student2);
		
		JSONObject object = new JSONObject();
		object.put("students", students);
		System.out.println("object : " + object.toString(2));
		
		return "index";
	}
	
	@GetMapping("/project05")
	public String project05() {
		String client_id = "81cfjhb7pz";
		String client_secret = "dB1AQY37PcbCLxQfMm1VXf5mw9yrQciVBk5BjYnO";
		// 키보드를 통하여 InputStreamReader 클래스를 통해 바이트 단위로 입력받고 라인단위로 읽기위해서 버퍼리더를 연결함.
		BufferedReader io = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			// Step 1 : 파라미터 확보하기 ( 검색할 주소를 콘솔로 입력 ) 
			System.out.println("주소를 입력하세요 : ");
			String address = io.readLine();
			// 입력받는 문자열의 공백으로 인해서 데이터를 끝으로 인식하므로 UTF-8로 변경하면 %20으로 변환됨 즉, 데이터 토큰이 공백을 통해서 판단됨
			String addr = URLEncoder.encode(address, "UTF-8");
			
			// Step 2 : URL 작성하기
			String reqUrl="https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode?query="+addr;
			
			// Step 3 : 작성된 URL을 이용하여 요청을 발생 시킴
			// URL을 URL클래스에 담아서 openConnection()을 통해서 네이버 서버와 연결을 시도한다
			URL url = new URL(reqUrl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			// 필요한 아이디와 비밀번호를 헤더에 파라미터로 삽입한다.
			con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", client_id);
			con.setRequestProperty("X-NCP-APIGW-API-KEY", client_secret);
			
			// Step 4 : 요청 후 응답데이터 수신하기       Buffer를 사용하면 데이터를 안깨지고 더 빠르게 받을 수 있다. inputStream의 데이터 손실을 막아줌 
			BufferedReader br;// = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));;
			// 200 OK 얻는 코드
			int responseCode = con.getResponseCode(); 
			// 정상적인 응답코드인 200이면 코드를 실행
			if(responseCode == 200) {
				// 커넥션을 통해서 한글이 깨지지않도록 UTF-8 형식으로 문자단위로 읽어서 라인단위로 변환함
				br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
			}
			else {
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			
			// Step 5 : 수신한 데이터를 문자열 데이터로 변환하기
			String line;
			StringBuffer response = new StringBuffer(); // JSON
			// JSON을 한줄씩 읽어서 응답클래스 안에 한줄씩 입력함
			while((line = br.readLine()) != null) {
				response.append(line);
			}
			br.close();
			System.out.println(response);
			
			// Step 6 : JSON 객체로 변환하기
			// 데이터 단위를 인식시키기위해서 필요
			JSONTokener tok = new JSONTokener(response.toString());
			// { } 인식
			JSONObject obj = new JSONObject(tok);
			System.out.println("obj.toString() : " + obj.toString());
			System.out.println("obj.get() : " + obj.get("status"));
			// meta {}를 인식
			JSONObject meta = obj.getJSONObject("meta");
			System.out.println("meta : " + meta);
			int totalCount = meta.getInt("totalCount");
			System.out.println("totalCount : " + totalCount);
			
			// addresses {} 를 인식
			JSONArray arr = obj.getJSONArray("addresses");
			JSONObject first = (JSONObject)arr.get(0);
			String x = first.getString("x");
			String y = first.getString("y");
			System.out.println("x : " + x);
			System.out.println("y : " + y);
			
			JSONArray element = first.getJSONArray("addressElements");
			JSONObject name = (JSONObject)element.get(7);
			String longName = name.getString("longName");
			String shortName = name.getString("shortName");
			System.out.println("longName : " + longName);
			System.out.println("shortName : " + shortName);
			getImage(x,y,addr);
		} catch (IOException e) {e.printStackTrace();}
		return "index";
	}
	
	public void getImage(String x, String y, String addr) {
		// Step 1 : URL 작성
		//curl --location --request GET 'https://naveropenapi.apigw.ntruss.com/map-static/v2/raster?
		// w=300&
		// h=300&
		// center=127.1054221,37.3591614&
		// level=16' \
		// --header 'x-ncp-apigw-api-key-id: {81cfjhb7pz}' \
		// --header 'x-ncp-apigw-api-key: {dB1AQY37PcbCLxQfMm1VXf5mw9yrQciVBk5BjYnO}'
		String client_id = "81cfjhb7pz";
		String client_secret = "dB1AQY37PcbCLxQfMm1VXf5mw9yrQciVBk5BjYnO";
		try {
			String url = "https://naveropenapi.apigw.ntruss.com/map-static/v2/raster?";
			url += "w=300&h=300&";
			url += "level=16&";
			url += "center="+x+","+y+"&";
			String pos = URLEncoder.encode(x+" "+y,"UTF-8");
			url += "&markers=type:t|size:mid|pos:"+pos+"|label:"+URLEncoder.encode(addr, "UTF-8");
			
			// Step 2 : 요청발생
			URL ur = new URL(url); 
			HttpURLConnection con = (HttpURLConnection) ur.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", client_id);
			con.setRequestProperty("X-NCP-APIGW-API-KEY", client_secret);
			
			// Step 3 : 데이터 수신
			InputStream is = con.getInputStream();
			// **이미지는 바이트단위이기 때문에 바이트 배열을 사용한다.
			byte[] bytes = new byte[1024]; 
			// 파일이름 짓기
			/*
				Date dt = new Date();
				Long lt = dt.getTime();
				String img = lt.toString();
			*/
			String imgname = Long.valueOf(new Date().getTime()).toString();
			// 파일 생성
			File file = new File("c://logs//"+imgname+".jpg");
			file.createNewFile();
			int read=0;
			FileOutputStream outputstream = new FileOutputStream(file);
			while( (read=is.read(bytes) ) != -1) { // 이미지기 때문에 숫자로 비교 int
				outputstream.write(bytes,0,read);
			}
			is.close();
			outputstream.close();
		} catch (Exception e) {e.printStackTrace();}
	}
	
}
