package hson.test.image;

import org.imgscalr.Scalr;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

/**
 * hson
 * 5/5/21
 */
public class TestImgsCal {



    @Test
    public void testImgsCal(){

        compressImage("pexels-maxime-francis-2246476.jpg");
        compressImage("image.png");
        compressImage("roadmap.png");

        compressImageBySize("pexels-maxime-francis-2246476.jpg",300);
        compressImageBySize("image.png",300);
    }



    public void compressImage(String fileName){
        try {
            URL url = this.getClass().getClassLoader().getResource(fileName);
            BufferedImage image = ImageIO.read(
                    url);
            int w = image.getWidth();
            int h = image.getHeight();
            BufferedImage resizedImage = resizeImage(image,w/2,h/2);

            Path resourceDirectory = Paths.get("src","test","resources","result");
            String absolutePath = resourceDirectory.toFile().getAbsolutePath();

            File outputfile = new File(absolutePath+File.separator+"imageCompressed"+fileName +".jpg");
            ImageIO.write(resizedImage, "jpg", outputfile);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void compressImageBySize(String fileName,int size){
        try {
            URL url = this.getClass().getClassLoader().getResource(fileName);
            BufferedImage image = ImageIO.read(
                    url);
            int w = image.getWidth();
            int h = image.getHeight();
            BufferedImage resizedImage = resizeImageBySize(image,size);

            Path resourceDirectory = Paths.get("src","test","resources","result");
            String absolutePath = resourceDirectory.toFile().getAbsolutePath();

            File outputfile = new File(absolutePath+File.separator+"imageCompressedBySize"+fileName +".jpg");
            ImageIO.write(resizedImage, "jpg", outputfile);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        return Scalr.resize(originalImage, Scalr.Method.AUTOMATIC, Scalr.Mode.AUTOMATIC, targetWidth, targetHeight, Scalr.OP_ANTIALIAS);
    }


    public BufferedImage resizeImageBySize(BufferedImage originalImage, int size) {
        return Scalr.resize(originalImage,size);
    }


}
