import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class WordCounter {
	private String urlStr;
	private String content;
	
	public WordCounter(String urlStr) {
		this.urlStr = urlStr;
	}
	
	private String fetchContent() throws IOException{
		URL url = new URL(this.urlStr); //將url轉成URL格式
		URLConnection conn = url.openConnection();
		InputStream in = conn.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		
		String retVal = "";
		String line = null;
		
		while((line = br.readLine())!=null) {
			retVal = retVal + line + "\n";
		}
		
		return retVal;
	}
	
	public int countKeyword(String keyword) throws IOException {
		if (content == null) {
			content = fetchContent();
		}
		content = content.toUpperCase();
		keyword = keyword.toUpperCase();
		
		int i = content.indexOf(keyword);
		int count = 0;
		while(i != -1) {
			count++;
			content = content.substring(i+keyword.length());
			i = content.indexOf(keyword);
		}
		return count;
		
		/*int count = 0;
		int start = 0;
		
		//TO DO : indexOf(keyword)
		while (content.indexOf(keyword, start) >= 0 && start < content.length()) {
			count++;
			start = content.indexOf(keyword, start) + keyword.length();
			}
		return count;
		return 0;*/
	}
	
}

