
/**
 * Write a description of Part2 here.
 * 
 * @author HaThai. 
 * @version 29/09/2024.
 */
public class Part2 {
    
    public String findSimpleGene(String dna, String startCodon, String stopCodon) {
        String result = "";
        String upper_dna = dna.toUpperCase(); 
        int startIndex = upper_dna.indexOf(startCodon);
        if (startIndex == -1) {
            return result;
        }
        int stopIndex = upper_dna.indexOf(stopCodon, startIndex + 3);
        if (stopIndex == -1) {
            return result;
        }
        result = dna.substring(startIndex, stopIndex + 3);
        if(result.length() % 3 == 0) {
            return result;
        }
        return "";
    }
    
    public void testSimpleGene() {
        String dna = "ATGXXXTAA";
        System.out.println("DNA is: " + dna);
        System.out.println("Gene is: " + findSimpleGene(dna , "ATG" , "TAA")); 
        
        dna = "ATTTGTXTGGTA";
        System.out.println("DNA is: " + dna);
        System.out.println("Gene is: " + findSimpleGene(dna , "ATT" , "TAT"));
        
        // dna = "ATAAG";
        // System.out.println("DNA is: " + dna);
        // System.out.println("Gene is: " + findSimpleGene(dna)); 
        
        // dna = "AAAGATGXAGGTTTAA";
        // System.out.println("DNA is: " + dna);
        // System.out.println("Gene is: " + findSimpleGene(dna));     
    }
}
