import edu.duke.*;
import org.apache.commons.csv.*;
/**
 * Write a description of ParsingExportData here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ParsingExportData {
    public void tester() {
        FileResource fr = new FileResource("exportdata.csv");
        CSVParser parser = fr.getCSVParser();
        //listExportersTwoProducts(parser, "cotton", "flowers");
        //System.out.println(countryInfo(parser,"cocoa"));
        bigExporters(parser, "$999,999,999,999");
        //System.out.println(numberOfExporters(parser, "cocoa"));
    }
    
    public String countryInfo(CSVParser parser, String country) {
        for(CSVRecord record : parser) {
            String countryName = record.get("Country");
            if(countryName.equals(country)) {
                String exports = record.get("Exports");
                String value = record.get("Value (dollars)");
                String result = country + ": " + exports + ": " + value;
                
                return result;
            }
        }
        return "NOT FOUND";
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        for(CSVRecord record : parser) {
            String listItems = record.get("Exports");

            if(listItems.contains(exportItem1) && listItems.contains(exportItem2)) {
                System.out.println(record.get("Country"));
            }
        }
    }
    
    int numberOfExporters(CSVParser parser, String exportItem) {
        int countCountries = 0;
        for(CSVRecord record : parser) {
            String listItems = record.get("Exports");
            if(listItems.contains(exportItem)) {
                countCountries++;
            }
        }
        return countCountries;
    }
    
    public void bigExporters(CSVParser parser, String amount) {
        for(CSVRecord record : parser) {
            String value = record.get("Value (dollars)");
            if(value.length() > amount.length()) {
                String country = record.get("Country");
                System.out.println(country + " " + value);
            }
        }
    }
    
    
}
