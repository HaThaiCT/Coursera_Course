import edu.duke.*;
import java.io.*;
/**
 * Write a description of Assignment1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Assignment1 {
    public ImageResource makeGray(ImageResource inImage) {
                ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
                for (Pixel pixel : outImage.pixels()) {
                    Pixel inPixel = inImage.getPixel(pixel.getX() , pixel.getY());
                    int average = (inPixel.getRed() + inPixel.getBlue() + inPixel.getGreen()) / 3;
                    pixel.setRed(average);
                    pixel.setBlue(average);
                    pixel.setGreen(average);
                }
        
                return outImage;
    }
    
        public void doSaveAndGetGray() {
            DirectoryResource dr = new DirectoryResource();
            for (File f : dr.selectedFiles()) {
            ImageResource image = new ImageResource(f);
            ImageResource gray = makeGray(image);
            String fname = image.getFileName();
            String newName = "copy-" + fname;
            gray.setFileName(newName);
            gray.draw();
            gray.save();
        }
    }

    
}
