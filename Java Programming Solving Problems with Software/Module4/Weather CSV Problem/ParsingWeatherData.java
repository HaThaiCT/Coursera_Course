import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;

/**
 * Write a description of ParsingWeatherData here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ParsingWeatherData {
    CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord coldestRecord = null;
        for(CSVRecord currentRecord : parser) {
            String currentTemp = currentRecord.get("TemperatureF");
            double current = Double.parseDouble(currentTemp);
            if(!currentTemp.equals("-9999")) {
                if(coldestRecord == null) {
                    coldestRecord = currentRecord;
                }
                else {
 
                    if(current < Double.parseDouble(coldestRecord.get("TemperatureF"))) {
                        coldestRecord = currentRecord;
                    }
                }
            }
        }
        return coldestRecord;
    }

    public void testColdestHourInFile() {
        FileResource fr = new FileResource();  // Mở hộp thoại để chọn file CSV
        CSVParser parser = fr.getCSVParser();  // Tạo CSVParser từ file được chọn
        CSVRecord coldest = coldestHourInFile(parser);  // Tìm nhiệt độ thấp nhất

        // In thông tin về nhiệt độ thấp nhất
        System.out.println("Coldest temperature was " + coldest.get("TemperatureF") +
                           " at " + coldest.get("DateUTC"));
    }
    
    public String fileWithColdestTemperature(){
        String coldestFile = null;
        double coldestTemp = Double.MAX_VALUE;
        DirectoryResource dr = new DirectoryResource();
        
        for(File file : dr.selectedFiles()) {
            FileResource fr = new FileResource(file);
            CSVParser parser = fr.getCSVParser();
            CSVRecord coldest = coldestHourInFile(parser);
            double currentTemp = Double.parseDouble(coldest.get("TemperatureF"));
            if(coldestFile == null) {
                coldestTemp = currentTemp;
                coldestFile = file.getName(); 
            }
            else {
                if(currentTemp < coldestTemp) {
                    coldestTemp = currentTemp;
                    coldestFile = file.getName();
                }
            }
        }
        return coldestFile;
    }
    
    public void testFileWithColdestTemperature() {
        String coldestFile = fileWithColdestTemperature();
        System.out.println("Coldest day was in file " + coldestFile);
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord lowestHumiditySoFar = null; // Biến lưu record có độ ẩm thấp nhất

        // Duyệt qua từng dòng trong file CSV
        for (CSVRecord currentRow : parser) {
            // Bỏ qua các giá trị "N/A"
            if (!currentRow.get("Humidity").equals("N/A")) {
                lowestHumiditySoFar = getLowestHumidityOfTwo(currentRow, lowestHumiditySoFar);
            }
        }
        return lowestHumiditySoFar; // Trả về record có độ ẩm thấp nhất
    }

    public CSVRecord getLowestHumidityOfTwo(CSVRecord currentRow, CSVRecord lowestHumiditySoFar) {
    if (lowestHumiditySoFar == null) {
        return currentRow;
    }
    
    // Chuyển đổi giá trị độ ẩm thành số để so sánh
    double currentHumidity = Double.parseDouble(currentRow.get("Humidity"));
    double lowestHumidity = Double.parseDouble(lowestHumiditySoFar.get("Humidity"));
    
    // So sánh và trả về record có độ ẩm thấp nhất
    if (currentHumidity < lowestHumidity) {
        return currentRow;
    }
    return lowestHumiditySoFar;
}

public void testLowestHumidityInFile() {
    FileResource fr = new FileResource();  // Mở hộp thoại để chọn file CSV
    CSVParser parser = fr.getCSVParser();  // Tạo CSVParser từ file được chọn
    CSVRecord lowestHumidity = lowestHumidityInFile(parser);  // Tìm độ ẩm thấp nhất
    
    // In thông tin về độ ẩm thấp nhất
    System.out.println("Lowest Humidity was " + lowestHumidity.get("Humidity") +
                       " at " + lowestHumidity.get("DateUTC"));
}

        public CSVRecord lowestHumidityInManyFiles() {
        CSVRecord lowestHumiditySoFar = null; // Biến lưu record có độ ẩm thấp nhất

        DirectoryResource dr = new DirectoryResource(); // Chọn nhiều file từ hệ thống

        // Duyệt qua từng file CSV được chọn
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f); // Mở file
            CSVParser parser = fr.getCSVParser(); // Tạo CSVParser để đọc file

            // Tìm độ ẩm thấp nhất trong file hiện tại
            CSVRecord currentLowest = lowestHumidityInFile(parser);

            // So sánh với độ ẩm thấp nhất toàn cục
            lowestHumiditySoFar = getLowestHumidityOfTwo(currentLowest, lowestHumiditySoFar);
        }
        
        return lowestHumiditySoFar; // Trả về record có độ ẩm thấp nhất trong tất cả các file
    }

    public void testLowestHumidityInManyFiles() {
        // Tìm bản ghi có độ ẩm thấp nhất từ nhiều file CSV
        CSVRecord lowestHumidity = lowestHumidityInManyFiles();

        // In thông tin về độ ẩm thấp nhất và thời gian ghi nhận
        System.out.println("Lowest Humidity was " + lowestHumidity.get("Humidity") +
                           " at " + lowestHumidity.get("DateUTC"));
    }

    public double averageTemperatureInFile(CSVParser parser) {
    double totalTemperature = 0; // Tổng nhiệt độ
    int count = 0; // Số lượng bản ghi

    // Duyệt qua từng record trong file CSV
    for (CSVRecord currentRow : parser) {
        double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
        totalTemperature += currentTemp; // Cộng nhiệt độ vào tổng
        count++;
    }
    return totalTemperature / count; // Trả về nhiệt độ trung bình
}

public void testAverageTemperatureInFile() {
    FileResource fr = new FileResource();  // Mở hộp thoại để chọn file CSV
    CSVParser parser = fr.getCSVParser();  // Tạo CSVParser từ file được chọn
    double averageTemp = averageTemperatureInFile(parser);  // Tính nhiệt độ trung bình
    System.out.println("Average temperature in file is " + averageTemp);
}

    // Phương thức tính nhiệt độ trung bình trong file CSV với độ ẩm >= giá trị chỉ định
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        double totalTemperature = 0; // Tổng nhiệt độ
        int count = 0; // Đếm số bản ghi có độ ẩm >= giá trị chỉ định

        // Duyệt qua từng bản ghi trong file CSV
        for (CSVRecord currentRow : parser) {
            // Kiểm tra cột độ ẩm (bỏ qua các giá trị "N/A")
            if (!currentRow.get("Humidity").equals("N/A")) {
                int currentHumidity = Integer.parseInt(currentRow.get("Humidity"));

                // Kiểm tra độ ẩm có lớn hơn hoặc bằng giá trị chỉ định không
                if (currentHumidity >= value) {
                    double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                    totalTemperature += currentTemp; // Cộng nhiệt độ vào tổng
                    count++; // Tăng số lượng bản ghi
                }
            }
        }
        
        // Kiểm tra nếu không có bản ghi nào đáp ứng điều kiện
        if (count == 0) {
            return Double.NaN; // Trả về NaN nếu không có bản ghi phù hợp
        }
        
        return totalTemperature / count; // Trả về nhiệt độ trung bình
    }

    // Phương thức kiểm tra `averageTemperatureWithHighHumidityInFile`
    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource(); // Mở hộp thoại để chọn file CSV
        CSVParser parser = fr.getCSVParser(); // Tạo CSVParser từ file được chọn
        double avgTemp = averageTemperatureWithHighHumidityInFile(parser, 80); // Gọi phương thức với giá trị độ ẩm là 80

        // Kiểm tra và in ra kết quả
        if (Double.isNaN(avgTemp)) {
            System.out.println("No temperatures with that humidity");
        } else {
            System.out.println("Average temperature when humidity is high is " + avgTemp);
        }
    }


    

}
