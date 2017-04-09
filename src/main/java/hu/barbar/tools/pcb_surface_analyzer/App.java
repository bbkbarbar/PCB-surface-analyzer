package hu.barbar.tools.pcb_surface_analyzer;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import hu.barbar.util.FileHandler;

public class App{

	
	static App me = null;
	
	public static final String DEFAULT_INPUT_FILE = "input.png";
	
	
    public static void main( String[] args ){
        if(args.length < 1){
        	System.out.println("Run with default input file: " + DEFAULT_INPUT_FILE);
        	me = new App(DEFAULT_INPUT_FILE);
        }else{
        	String filePath = args[0];
        	System.out.println("Run with input file: " + filePath);
        	if( !FileHandler.fileExists(filePath) ){
        		System.out.println("Input file missing: \"" + filePath + "\"");
        		return;
        	}
        	me = new App(filePath);
        }
    }
    
    
    public App(String inputFile){
    	
        BufferedImage image;
		try {
			image = ImageIO.read(new File(inputFile));
		} catch (IOException e) {
			System.out.println("Exception while try to load image: " + inputFile);
			e.printStackTrace();
			return;
		}    
        
		int w = image.getWidth();
        int h = image.getHeight();
        System.out.println("Image size: " + w + "*" + h + "\n");
        
        long lightPixels = 0;
        
        for(int y = 0; y < h; y++) {
            for(int x = 0; x < w; x++) {
                if(!isPixelDark(image.getRGB(x, y))){
                	lightPixels++;
                }
            }
        }
        
        System.out.println("Light pixels: " + lightPixels);
        System.out.println("Total pixels: " + (w*h));
        float millingPercentage = ((lightPixels*100f)/(w*h));
        System.out.println("Milling ratio: " + String.format("%.2f", millingPercentage) + "%");
        
    	
    }
    
    static int getColorComponentSum(Color c){
    	int result = c.getBlue();
    	result += c.getRed();
    	result += c.getGreen();
    	return result;
    }
    
    private boolean isPixelDark(int px){
    	Color c = new Color(px);
    	if(getColorComponentSum(c) <= 382){
    		return true;
    	}else{
    		return false;
    	}
    }
    
}
