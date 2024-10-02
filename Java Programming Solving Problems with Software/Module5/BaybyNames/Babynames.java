import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;

/**
 * Write a description of Babynames here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Babynames {
    public void totalBirths() {
        FileResource fr = new FileResource();
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        for(CSVRecord rec : fr.getCSVParser(false)){
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if(rec.get(1).equals("M")){
                totalBoys += numBorn;
            }
            else {
                totalGirls += numBorn;
            }
        }
        System.out.println("total births = " + totalBirths);
        System.out.println("total girls = " + totalGirls);
        System.out.println("total boys = " + totalBoys);
    }
    
    public int getRank(int year, String name, String gender) {
        int rank = 0;
        String fileName = "yob" + year + ".csv";
        FileResource fr = new FileResource(fileName);
        for(CSVRecord rec : fr.getCSVParser(false)) {
            String currentGender = rec.get(1);
            if(currentGender.equals(gender)) {
                rank++;
                String currentName = rec.get(0);
                if(currentName.equals(name)) {
                    return rank;
                }
            }
        }
        return -1;
    }
    
    public String getName(int year, int rank, String gender) {
        String fileName = "yob" + year + ".csv";
        FileResource fr = new FileResource(fileName);
        int currentRank = 0;
        for(CSVRecord rec : fr.getCSVParser(false)) {
            String currentGender = rec.get(1);
            if(currentGender.equals(gender)) {
                currentRank++;
                String currentName = rec.get(0);
                if(currentRank == rank) {
                    return currentName;
                }
            }    
        }
        return "NO NAME";
    }
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender) {
        int rank = getRank(year, name, gender);
        String newName = getName(newYear, rank, gender);
        String gd = "she";
        if(gender == "M") {
            gd = "he";
        }
        System.out.println(name + " born in " + year + " would be " + newName + " if " + gd + " was born in " + newYear + ",");
    }
    
    int yearOfHighestRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        int highestRank = -1;
        int yearOfHighestRank = -1;
        for(File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            String fileName = f.getName();
            int year = Integer.parseInt(fileName.substring(3,7));
            
            int currentRank = getRank(year, name, gender);
            if(currentRank != -1) {
                if(yearOfHighestRank == -1 || currentRank < highestRank) {
                    highestRank = currentRank;
                    yearOfHighestRank = year;
                }
            }
        }
        return yearOfHighestRank;
    }
    
    public double getAverageRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        int sumRank = 0;
        int countName = 0;
        for(File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            String fileName = f.getName();
            int year = Integer.parseInt(fileName.substring(3,7));
            int currentRank = getRank(year, name, gender);
            if(currentRank != -1) {
                sumRank += currentRank;
                countName++;
            }
        }
        if(countName > 0) {
            return (double) sumRank / countName;
        }
        return -1.0;
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        int minRank = getRank(year, name, gender);
        String fileName = "yob" + year + ".csv";
        FileResource fr = new FileResource(fileName);
        int currentRank = 0;
        int totalBirths = 0;
        for(CSVRecord rec : fr.getCSVParser(false)){
            String currentGender = rec.get(1);
            if(currentGender.equals(gender)) {
                currentRank++;
                if(currentRank == minRank) return totalBirths;
                totalBirths += Integer.parseInt(rec.get(2));
            }
        }
        return totalBirths;
    }

}
