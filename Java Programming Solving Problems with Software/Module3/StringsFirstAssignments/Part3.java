
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    
    public boolean twoOccurrences(String a, String b) {
        int countAppears = 0;
        for(int i = a.length() - 1; i < b.length(); i++) {
            if(b.substring(i - a.length() + 1 , i + 1).equals(a)) {
                countAppears++;
            }
        }
        return (countAppears >= 2);
    }
    
    public String lastPart(String a, String b) {
        for(int i = a.length() - 1; i < b.length(); i++) {
            if(b.substring(i - a.length() + 1, i + 1).equals(a)) {
                return b.substring(i + 1);
            }
        }
        return b;
    }
    
    public void Testing() {
        //System.out.println(twoOccurrences("a" , "banana"));
        System.out.println(lastPart("an", "banana"));
        System.out.println(lastPart("zoo", "forest"));
    }
}
