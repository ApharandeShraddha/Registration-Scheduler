
package registrationScheduler.util;

import java.io.BufferedReader;
import java.lang.String;



/**
 * Results     This class is responsible for writing output file
 * @author    Shraddha
 */
public class FileProcessor {
	
    private BufferedReader refFile;
    private BufferedReader addDrop;

    
    public FileProcessor(BufferedReader refFileIn, BufferedReader addDropIn){
	Logger.writeMessage("In FileProcessor, BufferedReader constructor", Logger.DebugLevel.CONSTRUCTOR);
	// code to handle argument
		this.refFile=refFileIn;
		this.addDrop=addDropIn;

    }
    public FileProcessor(String outFileNameIn){
    	Logger.writeMessage("In FileProcessor, String Parameter constructor", Logger.DebugLevel.CONSTRUCTOR);
    	// code to handle argument
    }
    
    /**
     * @return String
     */
    public synchronized String readLineFromRefFile() throws Exception{
    	    	return refFile.readLine();
    }
    
    /**
     * @return String
     */
    public synchronized String readLineFromAddDrop() throws Exception{
    	    	return addDrop.readLine();
    }
    
    // similarly a method to writeLineToFile().
    // other helper methods
}
