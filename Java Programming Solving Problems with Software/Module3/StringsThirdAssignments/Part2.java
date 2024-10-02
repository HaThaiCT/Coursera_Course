
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    // Phương thức tính tỷ lệ C và G
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
