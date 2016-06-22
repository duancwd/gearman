package Task;


 
  
import java.io.BufferedReader;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.InputStreamReader;  
import java.net.HttpURLConnection;  
import java.net.MalformedURLException;  
import java.net.URL;  
import java.net.URLConnection;  
  
public class PageStatic {  
    public static void convert2Html(String sFilePath,String sSavePath,String sHtmlFile) throws IOException  
    {  
        PageStatic ru = new PageStatic();   
        String baseHref="<base href='"+sFilePath+"'></base>";  
        String filePath = ru.getClass().getResource("/richin/reflect/PageStatic.class").getPath().toString(); //取得项目根目录  
        filePath = filePath.substring(1, filePath.indexOf("WEB-INF"));   
      
        int HttpResult;   
        String SavePath = filePath + sSavePath; //保存路径   
        URL url=new URL(sFilePath);   
        URLConnection urlconn=url.openConnection(); //抽象类 URLConnection 是所有类的超类，它代表应用程序和 URL 之间的通信链接，通过在 URL 上调用 openConnection 方法创建连接对象   
        urlconn.addRequestProperty("Accept-Language", "zh-cn");  
        urlconn.connect(); //使用 connect 方法建立到远程对象的实际连接   
        HttpURLConnection httpconn=(HttpURLConnection)urlconn; //每个 HttpURLConnection 实例都可用于生成单个请求，但是其他实例可以透明地共享连接到 HTTP 服务器的基础网络   
  
        HttpResult=httpconn.getResponseCode(); //getResponseCode可以从 HTTP 响应消息获取状态码   
  
        if(HttpResult!=HttpURLConnection.HTTP_OK) {   
        } else {   
            String charset=httpconn.getContentType();  
            //System.out.println(charset);  
            String charType=charset.substring(charset.lastIndexOf("=")+1);  
            //System.out.println(charType);  
            if("text/html".equals(charType))  
                charType="GBK";  
        InputStreamReader isr = new InputStreamReader(httpconn.getInputStream(),charType);   
        BufferedReader in = new BufferedReader(isr);   
        String inputLine;   
        if(!SavePath.endsWith("/")) {   
        SavePath+="/";   
        }   
        FileOutputStream fout = new FileOutputStream(SavePath+sHtmlFile);   
        while ((inputLine = in.readLine()) != null)   
        {   
        //System.out.println(inputLine);  
        if(inputLine.toLowerCase().equals("<head>"))  
        inputLine=inputLine+System.getProperty("line.separator")+baseHref;//加入basehref  
        inputLine=inputLine+System.getProperty("line.separator");  //换行  
        fout.write(inputLine.getBytes());   
        }   
        in.close();   
        fout.close();   
        }   
  
    }  
    /** 
     * @param args 
     * @throws IOException  
     */  
    public static void main(String[] args) throws IOException {  
        // TODO Auto-generated method stub  
        convert2Html("http://www.51cto.com/art/200807/79080.htm","/","a4.htm");   
    }  
  
}  