
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {

    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int stopIndex = dna.indexOf(stopCodon, startIndex + 3);
        while(stopIndex != -1) {
            if ((stopIndex - startIndex) % 3 == 0) {
                return stopIndex;
            }
            else {
                stopIndex = dna.indexOf(stopCodon, stopIndex + 1);
            }
        }
        return dna.length();
    }
    
    public String findGene(String dna) {
    int startIndex = dna.indexOf("ATG");
    if (startIndex == -1) {
        return ""; // Không có ATG
    }
    
    int taaIndex = findStopCodon(dna, startIndex, "TAA");
    int tagIndex = findStopCodon(dna, startIndex, "TAG");
    int tgaIndex = findStopCodon(dna, startIndex, "TGA");
    
    int minIndex = Math.min(taaIndex, Math.min(tagIndex, tgaIndex));
    
    if (minIndex == dna.length()) {
        return ""; 
    }
    return dna.substring(startIndex, minIndex + 3); // Trả về chuỗi gen
}
    
    public void testFindGene() {
    String dna1 = "ATGxxxyyyzzzTAAxxxyyyzzz";
    String dna2 = "xxxyyyzzzTAGxxxyyyzzz";
    String dna3 = "ATGxxxyyyzzzTAGTGA";
    String dna4 = "ATGxxxyyyzzz";
    
    System.out.println("DNA: " + dna1 + " => Gene: " + findGene(dna1));
    System.out.println("DNA: " + dna2 + " => Gene: " + findGene(dna2));
    System.out.println("DNA: " + dna3 + " => Gene: " + findGene(dna3));
    System.out.println("DNA: " + dna4 + " => Gene: " + findGene(dna4));
}

    
    public void testFindStopCodon() {
        String dna = "xxxyyyzzzTAAxxxyyyzzzTGA";
        int startIndex = 0;
    
        int stopIndex = findStopCodon(dna, startIndex, "TAA");
        System.out.println("TAA at: " + stopIndex); // In ra vị trí của TAA
    
        stopIndex = findStopCodon(dna, startIndex, "TAG");
        System.out.println("TAG at: " + stopIndex); // In ra vị trí của TAG (nếu có)
    
        stopIndex = findStopCodon(dna, startIndex, "TGA");
        System.out.println("TGA at: " + stopIndex); // In ra vị trí của TGA
}

    public void printAllGenes(String dna) {
    int startIndex = 0;
    
    while (true) {
        String gene = findGene(dna);
        if (gene.isEmpty()) {
            break;
        }
        
        System.out.println("Gene found: " + gene);
        startIndex = dna.indexOf(gene, startIndex) + gene.length();
        dna = dna.substring(startIndex); // Cập nhật DNA để tiếp tục tìm
    }
}


    public void testPrintAllGenes() {
    String dna = "ATGxxxyyyzzzTAAATGxxxyyyTAGTGAATGyyyzTGA";
    printAllGenes(dna);
    }

}
