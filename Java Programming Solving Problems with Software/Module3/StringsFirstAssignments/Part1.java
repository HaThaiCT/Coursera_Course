
/**
 * Write a description of Part1 here.
 * 
 * @author HaThai. 
 * @version 29/09/2024.
 */
public class Part1 {
    
    public String findSimpleGene(String dna) {
        String result = "";
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1) {
            return result;
        }
        int stopIndex = dna.indexOf("TAA", startIndex + 3);
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
        String dna = "ATGXXXTTAA";
        System.out.println("DNA is: " + dna);
        System.out.println("Gene is: " + findSimpleGene(dna)); 
        
        dna = "ATGGTA";
        System.out.println("DNA is: " + dna);
        System.out.println("Gene is: " + findSimpleGene(dna));
        
        dna = "ATAAG";
        System.out.println("DNA is: " + dna);
        System.out.println("Gene is: " + findSimpleGene(dna)); 
        
        dna = "AAAGATGXAGGTTTAA";
        System.out.println("DNA is: " + dna);
        System.out.println("Gene is: " + findSimpleGene(dna));     
    }
}
