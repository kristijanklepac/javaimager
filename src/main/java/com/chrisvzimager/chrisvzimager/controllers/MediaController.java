/**
 * 
 */
package com.chrisvzimager.chrisvzimager.controllers;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.ArrayList;
import java.util.List;
import java.util.function.IntPredicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.chrisvzimager.chrisvzimager.exceptions.ApiResponse;
import com.chrisvzimager.chrisvzimager.services.Post;
import com.chrisvzimager.chrisvzimager.services.StorageProperties;
import com.chrisvzimager.chrisvzimager.services.StorageService;
import com.chrisvzimager.chrisvzimager.utils.CreateDirectory;
import com.chrisvzimager.chrisvzimager.utils.Slug;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;


/**
 * @author Kristijan Klepač
 * @email kristijan.klepac@gmail.com
 */
@RestController
public class MediaController {

	private final StorageService storageService;
	private final Path rootPath;

	@Autowired
	public MediaController(StorageService storageService, StorageProperties properties) {
		this.storageService = storageService;
		this.rootPath = Paths.get(properties.getLocation());
	}
	
	
	@CrossOrigin(origins = "http://localhost:8080")
	@PostMapping("/media-with-crop/{album_name}")
	public ResponseEntity<?> mediaWithCrop(
			HttpServletRequest request, 
			//@PathVariable(value = "user_id", required = true) String user_id, 
			@PathVariable(value = "album_name", required = true) String album_name,
			@RequestParam("files") MultipartFile[] files) throws NoSuchAlgorithmException, KeyManagementException, IOException {

		System.out.println(files);
		
		System.out.println(request.getHeader("token"));
		
		if(request.getHeader("token") == null) {
			
			return ResponseEntity.badRequest().body(new ApiResponse(false,"No token present in header"));
		}
		
		// check if token is valid
		// curl link from asp.net app and get valid response about token
		// postavi link prema aplikaciji gdje se validira userToken
		// link vraća JSON u kojem je ime foldera (GUID) koji koristimo kao glavni image folder
		// unutar kojega imamo podfoldere (albume)
        String pathd = "https://localhost:5001/api/externaltoken"; //This is the base url of the API tested
        
        // vjeruj svim certifikatima (must be on localhost testing for https://localhost)
        // not best practice on production sites
        TrustManager[] trustAllCerts = new TrustManager[] {
        	       new X509TrustManager() {
        	          public java.security.cert.X509Certificate[] getAcceptedIssuers() {
        	            return null;
        	          }

        	          public void checkClientTrusted(X509Certificate[] certs, String authType) {  }

        	          public void checkServerTrusted(X509Certificate[] certs, String authType) {  }

        	       }
        	    };

        	    SSLContext sc = SSLContext.getInstance("SSL");
        	    sc.init(null, trustAllCerts, new java.security.SecureRandom());
        	    HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

        	    // Create all-trusting host name verifier
        	    HostnameVerifier allHostsValid = new HostnameVerifier() {
        	        public boolean verify(String hostname, SSLSession session) {
        	          return true;
        	        }
        	    };
        	    // Install the all-trusting host verifier
        	    HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        	    String user_id;
				/*
        	     * end of the fix
        	     */
                // pokušaj spajanja na link za validaciju tokena
        	    try {
					URL url = new URL(pathd);
					URLConnection con = url.openConnection();
					
					//con.setRequestMethod("GET");
					con.addRequestProperty("Accept", "application/json");
					con.addRequestProperty("Connection", "close");
					//con.addRequestProperty("Content-Encoding", "gzip"); // We gzip our request
					//con.setRequestProperty("Content-Type", "application/json"); // We send our data in JSON format
					con.setRequestProperty("User-Agent", "ChrisVz-Server/" );
					con.setRequestProperty("token", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJrcmlzdGlqYW4ua2xlcGFjQGdtYWlsZW4uY29tIiwianRpIjoiY2UzYTYzYmQtOTlkZC00M2ZhLTgxNWUtMjY0NTE2YTZhNzhmIiwibmJmIjoxNTM2NzU3NjM2LCJleHAiOjE1NDE5NDE2MzYsImlzcyI6Imlzc3VlciIsImF1ZCI6ImF1ZGllbmNlIn0.KZMPuAUvGht7047mCHf3iJhq2DpFye5Psvl02_zm5rw" );
				
					//con.setRequestProperty("token", request.getHeader("token")); // token iz requesta upućenog iz react applikacije
					
					ObjectMapper mapperx = new ObjectMapper();

					JsonNode node = mapperx.readTree(con.getInputStream());
					// Grab id with node.get("id").intValue()
					// Grab TokenValue with node.get("tokenValue").textValue()	
					
					// user_id postaje folder dobiven iz JSONA
					user_id = node.get("userFolder").textValue();
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					return ResponseEntity.badRequest().body(e);
				}
        
        
		List<String> listfiles = new ArrayList<>();
	    
		// sanitize album_name 
		album_name = album_name.replaceAll("[^A-Za-z0-9.()]", "");
		// sanitize user_id if needed
		//user_id = user_id.replaceAll("[^A-Za-z0-9.()]", "");

		// create dir user_id if not exists

		Path path = Paths.get(rootPath.toString(), user_id);
		String userDir = String.valueOf(path); //This is Null Safe
		CreateDirectory.createDir(userDir);

		// create dir album name if not exists

		Path path1 = Paths.get(userDir, album_name);
		String albumDir = String.valueOf(path1); //This is Null Safe
		CreateDirectory.createDir(albumDir);
		
		for (MultipartFile file : files) {

			String tempfile = storageService.store(file, user_id, album_name);
			
			File filePath = new File(tempfile);
			String abspath = filePath.getAbsolutePath();
			
			Resource resource = storageService.loadAsResource(tempfile) ;
			
			String fullRes = resource.toString();
			
			listfiles.add(abspath);
			
			
			
			System.out.println(resource);
			
		}
		
		// crop image and insert in album dir
		// in this case we will use max crop sizes that we defined in application.properties
		
		// vrati sve dobivene linkove u aplikaciju (.NET)
		// tamo će se zapisati putanje u bazu
		// total count of records is important to know for pagination in react 
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=UTF-8");
        /*headers.add("Access-Control-Expose-Headers", "X-Total-Count");
        headers.add("X-Total-Count","2");*/
        headers.add("Access-Control-Expose-Headers", "Content-Range");
        headers.add("Content-Range","2");
        return (new ResponseEntity<List<String> >(listfiles, headers, HttpStatus.OK));
		

		//return path.toString();
	}

	static String stripChars(String s, IntPredicate include) {
        return s.codePoints().filter(include::test).collect(StringBuilder::new,
                StringBuilder::appendCodePoint, StringBuilder::append).toString();
    }

}
