import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class urlFetcher{
	public String getYouTubeId (String youTubeUrl) {
	    String pattern = "(?<=youtu.be/|watch\\?v=|/videos/|embed\\/)[^#\\&\\?]*";
	    Pattern compiledPattern = Pattern.compile(pattern);
	    Matcher matcher = compiledPattern.matcher(youTubeUrl);
	    if(matcher.find()){
	        return matcher.group();
	    } else {
	        return "error";  
	    }
	}
	public static String getVideoUrl( String videoId) {
        return "http://youtu.be/" + videoId;
    }
}