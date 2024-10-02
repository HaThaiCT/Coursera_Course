import edu.duke.StorageResource;
import edu.duke.FileResource;
import edu.duke.*;
public class Part3 {

    // Phương thức xử lý các gen
    public void processGenes(StorageResource sr) {
        int countLongerThan60 = 0;
        int longestGeneLength = 0;
        int upper35 = 0;
    
        System.out.println("Số gene : " + (int)sr.size());
        System.out.println("Các chuỗi có độ dài lớn hơn 9 ký tự:");
        for (String gene : sr.data()) {
            if (gene.length() > 60) {
                System.out.println(gene);
                countLongerThan60++;
            } 
            if(cgRatio(gene) > 0.35) {
                upper35++;
            }
            // Tính độ dài gen dài nhất
            if (gene.length() > longestGeneLength) {
                longestGeneLength = gene.length();
            }
        }

        System.out.println("Số lượng chuỗi có độ dài lớn hơn 60 ký tự: " + countLongerThan60);
        System.out.println("Độ dài của gen dài nhất trong sr: " + longestGeneLength);
        System.out.println("Số gen có Rattio > 35 : " + upper35);
        
    }

    // Phương thức kiểm tra processGenes với các chuỗi DNA
    public void testProcessGenes() {
        // Tạo StorageResource từ các chuỗi DNA khác nhau (ví dụ)
 
        //FileResource fr = new FileResource("brca1line.fa");
        URLResource ur = new URLResource("https://users.cs.duke.edu/~rodger/GRch38dnapart.fa");
        String dna = ur.asString();
        System.out.println("Số lần xuất hiện CTG : " + countCTG(dna));

        StorageResource genesFromFile = getAllGenes(dna); // Giả sử phương thức getAllGenes đã được định nghĩa

        processGenes(genesFromFile);
    }

        public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int currIndex = dna.indexOf(stopCodon, startIndex + 3);
        while (currIndex != -1) {
            if ((currIndex - startIndex) % 3 == 0) {
                return currIndex; // Nếu codon dừng hợp lệ, trả về vị trí
            }
            currIndex = dna.indexOf(stopCodon, currIndex + 1); // Tìm codon dừng tiếp theo
        }
        return dna.length(); // Nếu không tìm thấy codon dừng hợp lệ, trả về độ dài của DNA
    }

    // Phương thức tìm gen
    public String findGene(String dna, int where) {
        int startIndex = dna.indexOf("ATG", where);
        if (startIndex == -1) {
            return ""; // Không tìm thấy "ATG", trả về chuỗi rỗng
        }

        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");

        // Tìm vị trí nhỏ nhất trong các codon dừng
        int minIndex = Math.min(taaIndex, Math.min(tagIndex, tgaIndex));

        if (minIndex == dna.length()) {
            return ""; // Không tìm thấy codon dừng hợp lệ
        }

        return dna.substring(startIndex, minIndex + 3); // Trả về gen hợp lệ
    }

    // Phương thức để tìm các gen từ chuỗi DNA
    public StorageResource getAllGenes(String dna) {
        dna = dna.toUpperCase();
        StorageResource genes = new StorageResource();
        int startIndex = 0;
        while (true) {
            // Tìm gen tiếp theo
            String gene = findGene(dna, startIndex);
            if (gene.isEmpty()) {
                break; // Nếu không tìm thấy gen, thoát khỏi vòng lặp
            }
            genes.add(gene); // Thêm gen vào StorageResource
            // Cập nhật startIndex để tìm kiếm sau gen vừa tìm thấy
            startIndex = dna.indexOf(gene, startIndex) + gene.length(); 
        }
        return genes;
    }
    public float cgRatio(String dna) {
        int countC = 0;
        int countG = 0;
        int total = dna.length();

        // Đếm số lần xuất hiện của C và G
        for (int i = 0; i < total; i++) {
            char nucleotide = dna.charAt(i);
            if (nucleotide == 'C') {
                countC++;
            } else if (nucleotide == 'G') {
                countG++;
            }
        }

        // Tính tỷ lệ C và G
        int totalCG = countC + countG;
        if (total == 0) {
            return 0; // Tránh chia cho 0
        }

        // Trả về tỷ lệ dưới dạng số thập phân
        return (float) totalCG / total;
    }

    // Phương thức đếm số lần xuất hiện của codon CTG
    public int countCTG(String dna) {
        int count = 0;
        int index = 0;

        // Tìm codon CTG trong chuỗi dna
        while (index != -1) {
            index = dna.indexOf("CTG", index);
            if (index != -1) {
                count++;
                index += 3; // Di chuyển đến vị trí sau codon CTG đã tìm thấy
            }
        }
        return count;
    }

}
