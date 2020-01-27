package com.fund.util;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.net.ssl.HttpsURLConnection;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fund.exception.CustomException;
import com.fund.exception.ExceptionCode;

import ch.qos.logback.classic.Logger;

@Component
public class UrlUtil {
   
   private static final Logger logger = (Logger) LoggerFactory.getLogger(UrlUtil.class);
   
   private static final String boundary = "^-----^";// multipart request 구분자
   private static final String LINE_FEED = "\r\n";
   
   public File covertToFile(String imgUrl) {
      URL url = null;
      File file = null;
      try {
         url = new URL(imgUrl);
         BufferedImage bi = ImageIO.read(url);
         file = new File("downloaded.jpg");
         ImageIO.write(bi, "jpg", file);
      } catch (IOException e) {
    	  throw new CustomException(ExceptionCode.FILE_IO_EXCEPTION);
      } 
      
      return file;
   }
   
   
   public HttpURLConnection makeGETConnection(String apiUrl) throws IOException {
      URL url = new URL(apiUrl);
      HttpURLConnection con = (HttpURLConnection) url.openConnection();
      con.setRequestMethod("GET");
      return con;
   }
   
   public HttpsURLConnection sendPost(String url, String parameters, String header) throws IOException {
	   
	   URL obj = new URL(url);
       HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

       //add reuqest header
       if(header != null) {
    	   con.setRequestProperty("Authorization", header);
       }
       con.setRequestMethod("POST");
       con.setConnectTimeout(10000);       //컨텍션타임아웃 10초
       con.setReadTimeout(5000);           //컨텐츠조회 타임아웃 5총
       // Send post request
       con.setDoOutput(true);              //항상 갱신된내용을 가져옴.
     
       if(parameters != null) {
    	   logger.info("url ==> " + url + ", params => " + parameters);
    	   DataOutputStream wr = new DataOutputStream(con.getOutputStream());
           wr.writeBytes(parameters);
           wr.flush();
           wr.close();   
       }
       
       return con;
   }
   
public HttpURLConnection sendPost(String url, String parameters) throws IOException {
	   
	   URL obj = new URL(url);
       HttpURLConnection con = (HttpURLConnection) obj.openConnection();

       con.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
       con.setRequestMethod("POST");
       con.setConnectTimeout(10000);       //컨텍션타임아웃 10초
       con.setReadTimeout(5000);           //컨텐츠조회 타임아웃 5총
       // Send post request
       con.setDoOutput(true);              //항상 갱신된내용을 가져옴.
     
       if(parameters != null) {
    	   logger.info("url ==> " + url + ", params => " + parameters);
    	   DataOutputStream wr = new DataOutputStream(con.getOutputStream());
           wr.writeBytes(parameters);
           wr.flush();
           wr.close();   
       }
       
       return con;
   }
   
   public void addFilePart(OutputStream outputStream, PrintWriter writer, String fieldName, File file) throws IOException {
      String fileName = file.getName();
      writer.append("--" + boundary).append(LINE_FEED);
        writer.append("Content-Disposition: form-data; name=\"" + fieldName + "\"; filename=\"" + fileName + "\"").append(LINE_FEED);
        writer.append("Content-Type: " + URLConnection.guessContentTypeFromName(fileName)).append(LINE_FEED);
        writer.append("Content-Transfer-Encoding: binary").append(LINE_FEED);
        writer.append(LINE_FEED);
        writer.flush();
        
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] buffer = new byte[4096];
        int bytesRead = -1;
        while((bytesRead = fileInputStream.read(buffer)) != -1) {
           outputStream.write(buffer, 0, bytesRead);
        }
        outputStream.flush();
        fileInputStream.close();
        
        writer.append(LINE_FEED);
        writer.flush();
   }
   
   public void addFormField(PrintWriter writer, String name, String value) {
      writer.append("--" + boundary).append(LINE_FEED);
        writer.append("Content-Disposition: form-data; name=\"" + name + "\"").append(LINE_FEED);
        writer.append("Content-Type: text/plain; charset=UTF-8").append(LINE_FEED);
        writer.append(LINE_FEED);
        writer.append(value).append(LINE_FEED);
        writer.flush();
   }
   
   public void addHeaderField(HttpURLConnection con, String name, String value) {
      con.setRequestProperty(name, value);
   }
   
   public void showAllRequestPropertyBeforeConnect(HttpURLConnection con) {
      logger.info("Printing Request Properties...");
      for (Map.Entry<String, List<String>> entries : con.getRequestProperties().entrySet()) {    
          String values = "";
          for (String value : entries.getValue()) {
              values += value + ",";
          }
          logger.info("Key: " + entries.getKey() + ", Value: " + values);
      }
   }
   
   public void showAllHeaderFieldsBeforeDisconnect(HttpURLConnection con) {
      logger.info("Printing Response Header...");
      for (Map.Entry<String, List<String>> entries : con.getHeaderFields().entrySet()) {    
          String values = "";
          for (String value : entries.getValue()) {
              values += value + ",";
          }
          logger.info("Key: " + entries.getKey() + ", Value: " + values);
      }
   }
   
   public List<String> getResponse(HttpURLConnection con, PrintWriter writer) throws IOException {
      List<String> response = new ArrayList<String>();
      
      writer.append(LINE_FEED).flush();
      writer.append("--" + boundary + "--").append(LINE_FEED);
      logger.info(writer.toString());
      writer.close();
      
      int status = con.getResponseCode();
      logger.info("Server returned status of write post api : " + status);
      if(status == HttpURLConnection.HTTP_OK) {
         BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
         String line = null;
         while((line = reader.readLine()) != null) {
            response.add(line);
         }
         reader.close();
         con.disconnect();
      }
      
      return response;
   }
   public String getResponseMessage(HttpURLConnection con) {
      StringBuffer result = null;
      try {
         int responseCode = con.getResponseCode();
         BufferedReader br = null;
         if(responseCode == 200) {
            br = new BufferedReader(new InputStreamReader(con.getInputStream()));
         }
         else {
            br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
         }
         
         String inputLine = null;
         result = new StringBuffer();
         while((inputLine=br.readLine()) != null) {
            result.append(inputLine);
         }
         br.close();
      } catch (IOException e) {
    	  logger.error(e.getMessage());
    	  throw new CustomException(ExceptionCode.FILE_IO_EXCEPTION);
      }
      
      return result.toString();
   }
   
   public String encode(String url) {
      String encodedRedirectUrl = null;
      try {
         encodedRedirectUrl =  URLEncoder.encode(url, "UTF-8");
      } catch (UnsupportedEncodingException e) {
    	  throw new CustomException(ExceptionCode.URL_ENCODING_EXCEPTION);
      }
      return encodedRedirectUrl;
   }
   
   
}
