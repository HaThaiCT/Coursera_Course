import edu.duke.*;
import java.io.*;
/**
 * Write a description of Assignment1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Assignment2 {
    public ImageResource makeInverse(ImageResource inImage) {
                ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
                for (Pixel pixel : outImage.pixels()) {
                    Pixel inPixel = inImage.getPixel(pixel.getX() , pixel.getY());
                    int average = (inPixel.getRed() + inPixel.getBlue() + inPixel.getGreen()) / 3;
                    pixel.setRed(255 - inPixel.getRed());
                    pixel.setBlue(255 - inPixel.getBlue());
                    pixel.setGreen(255 - inPixel.getGreen());
                }
        
                return outImage;
    }
    
        public void doSaveAndInverse() {
            DirectoryResource dr = new DirectoryResource();
            for (File f : dr.selectedFiles()) {
            ImageResource image = new ImageResource(f);
            ImageResource inverse = makeInverse(image);
            String fname = image.getFileName();
            String newName = "copy-" + fname;
            inverse.setFileName(newName);
            inverse.draw();
            inverse.save();
        }
    }

    
}
