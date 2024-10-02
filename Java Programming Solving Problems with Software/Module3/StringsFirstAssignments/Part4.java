import edu.duke.*;
/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part4 {
    public void readWebPage() {
        URLResource ur = new URLResource("https://www.dukelearntoprogram.com/course2/data/manylinks.html");
        for (String link : ur.lines()) {
            String lowerLink = link.toLowerCase();
            int index = lowerLink.indexOf("youtube.com");
            if(index != -1) {
                int start = link.lastIndexOf("\"", index);
                int end = link.indexOf("\"", index + 1);
                if(start != -1 && end != -1) {
                    System.out.println(link.substring(start + 1 , end));
                }
            }
        }
    }
    
}
