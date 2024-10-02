
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public int howMany(String stringa, String stringb) {
        int count = 0;
        int startIndex = 0;
        
        while (true) {
            int index = stringb.indexOf(stringa, startIndex);
            
            if (index == -1) {
                break; 
            }
            
            count++;
            startIndex = index + stringa.length();
        }     
        return count;
    }

    public void testHowMany() {
        String stringa1 = "GAA";
        String stringb1 = "ATGAACGAATTGAATC";
        System.out.println("Occurrences of GAA in ATGAACGAATTGAATC: " + howMany(stringa1, stringb1)); // Kết quả dự kiến: 3

        String stringa2 = "AA";
        String stringb2 = "ATAAAA";
        System.out.println("Occurrences of AA in ATAAAA: " + howMany(stringa2, stringb2)); // Kết quả dự kiến: 2
        
        String stringa3 = "A";
        String stringb3 = "ATAAA";
        System.out.println("Occurrences of A in ATAAA: " + howMany(stringa3, stringb3)); // Kết quả dự kiến: 4

        String stringa4 = "BB";
        String stringb4 = "ABABABAB";
        System.out.println("Occurrences of BB in ABABABAB: " + howMany(stringa4, stringb4)); // Kết quả dự kiến: 0
    }
}
