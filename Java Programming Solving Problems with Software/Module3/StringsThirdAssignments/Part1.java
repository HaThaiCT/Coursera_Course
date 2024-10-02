import edu.duke.StorageResource;

public class Part1 {

    // Phương thức tìm codon dừng
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

    // Phương thức để lấy tất cả các gen và trả về StorageResource
    public StorageResource getAllGenes(String dna) {
        StorageResource genes = new StorageResource(); // Khởi tạo StorageResource để lưu gen
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

        return genes; // Trả về StorageResource chứa tất cả các gen
    }

    // Phương thức kiểm tra getAllGenes
    public void testGetAllGenes() {
        String dna = "ATGTAAGATGCCCTAGT";
        StorageResource genes = getAllGenes(dna);
        
        System.out.println("Genes found in DNA: ");
        for (String gene : genes.data()) {
            System.out.println(gene); // In ra các gen đã lưu
        }
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
