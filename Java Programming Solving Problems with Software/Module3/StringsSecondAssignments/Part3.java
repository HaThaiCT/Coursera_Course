public class Part3 {

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

    // Phương thức đếm số gen
    public int countGenes(String dna) {
        int count = 0;
        int startIndex = 0;

        while (true) {
            // Tìm gen tiếp theo
            String gene = findGene(dna, startIndex);
            if (gene.isEmpty()) {
                break; // Nếu không tìm thấy gen, thoát khỏi vòng lặp
            }
            count++; // Tăng số lượng gen
            // Cập nhật startIndex để tìm kiếm sau gen vừa tìm thấy
            startIndex = dna.indexOf(gene, startIndex) + gene.length(); // Cập nhật vị trí để tìm gen tiếp theo
        }

        return count; // Trả về số lượng gen đã tìm thấy
    }

    // Phương thức kiểm tra countGenes
    public void testCountGenes() {
        String dna1 = "ATGTAAGATGCCCTAGT";
        System.out.println("Number of genes in ATGTAAGATGCCCTAGT: " + countGenes(dna1)); // Dự kiến: 2

        String dna2 = "ATGCCCTAAATGTAGATGAAATGCTGATGA";
        System.out.println("Number of genes in ATGCCCTAAATGTAGATGAAATGCTGATGA: " + countGenes(dna2)); // Dự kiến: 3

        String dna3 = "ATGCCCTAA";
        System.out.println("Number of genes in ATGCCCTAA: " + countGenes(dna3)); // Dự kiến: 1

        String dna4 = "ATGCGTAGTGAGATGATGTAA";
        System.out.println("Number of genes in ATGCGTAGTGAGATGATGTAA: " + countGenes(dna4)); // Dự kiến: 2

        String dna5 = "TGAATGTAG";
        System.out.println("Number of genes in TGAATGTAG: " + countGenes(dna5)); // Dự kiến: 1
    }

}
