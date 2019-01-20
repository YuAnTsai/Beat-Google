package com.runoob.test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.servlet.ServletException;
import static javax.servlet.SessionTrackingMode.URL;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Clemmy
 */
@WebServlet(name="Search",urlPatterns = {"/Search"})
public class Search extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * 
     */
    
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     /*output result on index.jsp*/
        String query_ = request.getParameter("query");
        
        query_= java.net.URLDecoder.decode(query_, "utf-8");
       
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code.        
                response.setLocale(Locale.TAIWAN);        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");        */
        

        KeywordList ytlist=searchresult(query_,response,request);
        
        out.println("<html><head>");
        out.println("<h1>"+query_+ytlist.size()+"</h1>");
        out.println("<title>KUKUGO</title>");
        out.println("<link href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css\" rel=\"stylesheet\">");
        out.println("<style type=\"text/css\">");
        out.println(".white{color:white;}");
        out.println(".img{ position:relative; top:10px; left:30px;/*right:5px;bottom:5px*/}");
        out.println("body{margin-left:60px;font-size: 1.5em;background: url(http://elgraneropetshop.cl/wp-content/uploads/2018/07/Granero-General.jpg) ;background-position: 550% 0.1%; background-attachment: fixed;"
                + "html {font-size: 100%;}" +
"#wrap {width: 46.25em;margin: 1.5em auto;border: 0.0625em solid #ccc;" +
"}"
                );
        
        out.println("</style>");
        out.println("</head>");
        out.write("<body>");
        out.write("<br />");
        out.write("<br />");
        for(int i=0;i<ytlist.size();i++){
        Node node=ytlist.get(i);
   
        String url=node.getURL();
        String title=node.getTitle();
        String view=node.getView();
        String img=node.getPic();
        String ac=getIn(url,response,request,node);
        FaceBook (ac,response,request,node);
        out.write("<div id=\"wrap\" class=\"white\"> "+title+" ||"+view+"</div>");
        out.println("<span><a href='"+url+"'>"+"<div id=\"wrap\" class=\"img\"><img src='"+img+"' class=\"img\" style=\"box-shadow:3px 3px 40px gray;padding:5px; alt='"+title+"'title='"+title+"' height=\"300\" width=\"545\"></a>"+"</div></span>");
        out.println("<a href='"+ac+"'>"+ac+"</a>");
        
        out.write("<br />");
        out.write("<br />");out.write("<br />");
       
        }
        
        out.write("<body></html>");
       
           
        
    }}


    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
       
        doGet(request, response);
    }
    
public KeywordList searchresult (String query, HttpServletResponse response, HttpServletRequest request)throws IOException {
        /*Get the raw searched result from youtube ,organize them in certain format(依序排好:(url,title,picture,view,parent node)) and add them into keywordlist*/
        System.out.println(query);
        query= new String(request.getParameter("query").toString().getBytes("utf-8"), "utf-8");
        
        ArrayList<String>array1=new ArrayList<String>();
        String query11="";
        KeywordList ytlist=new KeywordList();
        //http://i.ytimg.com/vi/(please input your hash)/0.jpg # 

        System.out.println(getEncoding(query));
        //search_query關鍵字->keyword為中文的話,出現keyword+搞笑狗
        if(query.getBytes().length!=query.length()){query11=query+"好笑"+"搞笑"+"+"+"狗";}
        //serch_query關鍵字->keyword為英文的話,出現keyword+funny dog videos
        else{query11=query+"funny"+"+"+"dog";}


       
        

    
        int count=1;
        String url   = "https://www.youtube.com/results?sp=EgIYAQ%253D%253D&search_query="+query11;
        System.out.println(url);
       
        url= java.net.URLDecoder.decode(url, "utf-8");

      while(true){
         if(count==10){
             
              break;
              }
          if(count!=1&&count<7){ url= array1.get(count-1); }
        /*因為c首頁只有2-6的原因
          所以出現此式
          */
         /* System.out.println("-"+url+" ");*/
         

       Document  doc = Jsoup.connect(url)
      .userAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36")
      .timeout(2700)
      .get();
                  
            

       
             if(count>6){  
            for (Element a : doc.select(".yt-lockup-video")){
         /*   System.out.println("https://www.youtube.com"+a.attr("href") + " "+a.attr("title") );.yt-lockup-title > a[title] .attr("href")*/
            String pic=a.select("a[rel='spf-prefetch']").attr("href");pic = pic.replace("/watch?v=","http://i.ytimg.com/vi/");  
            pic=pic+"/0.jpg";
            String views=a.select("span").attr("aria-label");
            int s=views.indexOf("觀看次數：");
            if(views.length()!=0){
            char aa=(views.charAt(views.length()-1));
            String st=Character.toString(aa);
            views=views.replace("觀看次數：","");
            if((st.contains("次"))){views=views.substring(s,views.length()-1);}
            views=views.trim();
            String str2="";
            if(views != null && !"".equals(views)){
            for(int i=0;i<views.length();i++){
            if(views.charAt(i)>=48 && views.charAt(i)<=57){
            str2+=views.charAt(i);
            }
            }}    views=str2.replace(",", "");
            views=views.replace(" ", "");}
            int view;
            if(views!=""){view=Integer.parseInt(views);}else{ view=0;}
            if(!a.select("a[rel='spf-prefetch']").attr("title").contains("IPHONE")&&(!a.select("a[rel='spf-prefetch']").attr("title").contains("搞笑动画"))&&(!a.select("a[rel='spf-prefetch']").attr("title").contains("目"))&&(!a.select("a[rel='spf-prefetch']").attr("title").contains("advertisement"))&&(!a.select("a[rel='spf-prefetch']").attr("title").contains("Ads"))&&(!a.select("a[rel='spf-prefetch']").attr("title").contains("Cowardly Dog"))&&(!a.select("a[rel='spf-prefetch']").attr("title").contains("Dog shaped"))&&(!a.select("a[rel='spf-prefetch']").attr("title").contains("tv game"))&&(!a.select("a[rel='spf-prefetch']").attr("title").contains("Funny Baby"))  ){
            ytlist.add(new Node("https://www.youtube.com"+a.select("a[rel='spf-prefetch']").attr("href"),a.select("a[rel='spf-prefetch']").attr("title"),pic,view,query));}
            System.out.println(a.select("a[rel='spf-prefetch']").attr("title"));
            
            }
           count++; }
          if(count==1){
            /*第一次 ,並把2-6頁加入*/
             for (Element a : doc.select(".yt-lockup-video")){
         /*   System.out.println("https://www.youtube.com"+a.attr("href") + " "+a.attr("title") );.yt-lockup-title > a[title] .attr("href")*/

            String pic=a.select("a[rel='spf-prefetch']").attr("href");pic = pic.replace("/watch?v=","http://i.ytimg.com/vi/");  
            pic=pic+"/0.jpg";
            String views=a.select("span").attr("aria-label");
            int s=views.indexOf("觀看次數：");
            if(views.length()!=0){
            char aa=(views.charAt(views.length()-1));
            
            String st=Character.toString(aa);
            views=views.replace("觀看次數：","");
            if((st.contains("次"))){views=views.substring(s,views.length()-1);}
            views=views.trim();
            String str2="";
            if(views != null && !"".equals(views)){
            for(int i=0;i<views.length();i++){
            if(views.charAt(i)>=48 && views.charAt(i)<=57){
            str2+=views.charAt(i);
            }
            }}
            

            views=str2.replace(",", "");
            views=views.replace(" ", "");}
            int view;
            if(views!=""){view=Integer.parseInt(views);}else{ view=0;}
            
           
            
            }
            for (Element a : doc.select("a")) {
           /* System.out.println("https://www.youtube.com"+a.attr("href") + " "+a.attr("aria-label") ); String pic=a.attr("href");pic = pic.replace("/watch?v=","http://i.ytimg.com/vi/");  
            pic=pic+"/0.jpg";*/
           
           /* System.out.println(pic);*/
            if(a.attr("aria-label").contains("前往")){
                String o="https://www.youtube.com"+(a.attr("href"));      
                        array1.add(o);
            }
            
        }
            if(array1.size()==0){break;}
        }
          if(array1.size()!=0&&count<7){
             /*分析每一頁,  
              */
            for (Element a : doc.select(".yt-lockup-video")){
         /*   System.out.println("https://www.youtube.com"+a.attr("href") + " "+a.attr("title") );.yt-lockup-title > a[title] .attr("href")*/

            String pic=a.select("a[rel='spf-prefetch']").attr("href");pic = pic.replace("/watch?v=","http://i.ytimg.com/vi/");  
            pic=pic+"/0.jpg";
            String views=a.select("span").attr("aria-label");
            int s=views.indexOf("觀看次數：");
            if(views.length()!=0){
            char aa=(views.charAt(views.length()-1));
            views=views.replace("觀看次數：","");
            String st=Character.toString(aa);
            if((st.contains("次"))){views=views.substring(s,views.length()-1);}
            views=views.trim();
            String str2="";
            if(views != null && !"".equals(views)){
            for(int i=0;i<views.length();i++){
            if(views.charAt(i)>=48 && views.charAt(i)<=57){
            str2+=views.charAt(i);
            }
            }}

            views=str2.replace(",", "");
            views=views.replace(" ", "");}
            int view;
            if(views!=""){view=Integer.parseInt(views);}else{ view=0;}
               if((!a.select("a[rel='spf-prefetch']").attr("title").contains("iPhone"))&&(!a.select("a[rel='spf-prefetch']").attr("title").contains("搞笑动画"))&&(!a.select("a[rel='spf-prefetch']").attr("title").contains("目"))&&(!a.select("a[rel='spf-prefetch']").attr("title").contains("advertisement"))&&(!a.select("a[rel='spf-prefetch']").attr("title").contains("Ads"))&&(!a.select("a[rel='spf-prefetch']").attr("title").contains("Cowardly Dog"))&&(!a.select("a[rel='spf-prefetch']").attr("title").contains("Dog shaped"))&&(!a.select("a[rel='spf-prefetch']").attr("title").contains("tv game"))&&(!a.select("a[rel='spf-prefetch']").attr("title").contains("Funny Baby")) ){
            ytlist.add(new Node("https://www.youtube.com"+a.select("a[rel='spf-prefetch']").attr("href"),a.select("a[rel='spf-prefetch']").attr("title"),pic,view,query));}
            System.out.println("https://www.youtube.com"+a.select("a[rel='spf-prefetch']").attr("href"));
            
            }
                count++;
                continue;             
            }if(count>7){
            String check="前往第 "+count+" 頁";
            for (Element a : doc.select("a")){
            if(a.attr("aria-label").contains(check)){
                System.out.println(check);
                String o="https://www.youtube.com"+(a.attr("href"));
                url=o;break;}
        }
            continue;}              
          }return ytlist;
    
}
	public static String getEncoding(String str) {
		/*測試編碼用*/
		String encode[] = new String[]{
				"UTF-8",
				"ISO-8859-1",
				"GB2312",
				"GBK",
				"GB18030",
				"Big5",
				"Unicode",
				"ASCII"
		};
		for (int i = 0; i < encode.length; i++){
			try {
				if (str.equals(new String(str.getBytes(encode[i]), encode[i]))) {
					return encode[i];
				}
			} catch (Exception ex) {
			}
		}
		
		return "";
	}
public String getIn (String url,HttpServletResponse response,HttpServletRequest request,Node parent) throws IOException{
    /*進入一個一個youtube影片內,抓取facebook連結*/
    String c="";
    String relHref="";
   List<Node>fblist2=new ArrayList<Node>();
  Document  doc2 = Jsoup.connect(url)
      .userAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36")
      .timeout(2700)
      .get();
     ArrayList<String>al=new ArrayList();
Elements links = doc2.select("a[href]");

for(Element link:links){
   int count1=0;
    relHref = link.text(); // == "/"
    count1=count1+1;

    if(relHref.contains("https")){
        
        if(relHref.contains("facebook")&&(!relHref.contains("播放時間"))){
        relHref.replace("\n", "");
        c=relHref;
        Node fbb=new Node(c,"",parent);
        fblist2.add(fbb);
        parent.setChildrenItems(fblist2);
     
        
  }
       
    }

}
              
 return c;
}
public void FaceBook (String fburl,HttpServletResponse response,HttpServletRequest request,Node parent) throws IOException{
/*進入facebook並且進入其中搜尋是否有po文,若有,則抓取其中兩則,最後建立一個一個完整的樹並print出來(呼叫PrintTree())*/
Btree bt=new Btree();
    int count=0;

ArrayList <Node> fblist=new ArrayList<Node>();
    String relHref2="";
try{    
             
List <Node> items=parent.getChildrenItems();
for(int i=0;i<items.size();i++){
Node fb=items.get(i);
Document  doc3 = Jsoup.connect(fb.fbURL)
      .userAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36")
      .timeout(2700)
      .get(); 
Elements links = doc3.select("a"); 
for(Element link:links){
    relHref2 = link.attr("href");
    if((relHref2.contains("posts/")||relHref2.contains("photos/"))&&(count<2)&&(!relHref2.contains("page"))){ 
        count++;
        relHref2="www.facebook.com"+relHref2;
        fblist.add(new Node(relHref2,"",fb));
}
}
fb.setChildrenItems(fblist);
List<Node>ch=fb.getChildrenItems();
for(int n=0;n<ch.size();n++){
Node nn=ch.get(n);
			}
}
	
}catch(Exception ex){   
}
System.out.println("Print tree");
bt.BuildTree(parent);
bt.PrintTree();
}
}
