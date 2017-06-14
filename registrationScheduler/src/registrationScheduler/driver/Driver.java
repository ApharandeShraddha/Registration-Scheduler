
package registrationScheduler.driver;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import registrationScheduler.store.Results;
import registrationScheduler.threadMgmt.CreateWorkers;
import registrationScheduler.util.FileProcessor;
import registrationScheduler.util.Logger;
import registrationScheduler.util.StdoutDisplayInterface;

/**
 * Results     This class is main driver which will start the program
 * @author    Shraddha
 */
public class Driver{
	
	int noOfThreads=0;
	int debugValue=0;
	String regPreferenceFileName="";
	
	String addDropFileName="";
	String outputFileName="";


	public static void main(String args[]) {
	
			 Driver dr = new Driver();
			 BufferedReader brRegPref=null; // for registration preference input file 
			 BufferedReader brAddDrop=null; // add-drop input file
			 StdoutDisplayInterface res= new Results();
			 try {
		
				
					
			    dr.validateArgs(args);
			   
			    Logger.setDebugValue(dr.getDebugValue());
			    brRegPref=new BufferedReader(new FileReader(dr.getRegPreferenceFileName()));
			    brAddDrop=new BufferedReader(new FileReader(dr.getAddDropFileName()));

				FileProcessor fp=new FileProcessor(brRegPref,brAddDrop);
			 
				CreateWorkers workers=new CreateWorkers(fp, res);
				workers.startWorkers(dr.getNoOfThreads());
				res.writeSchedulesToFile(dr.getOutputFileName());
			
		} catch (FileNotFoundException ex) {
			System.err.println("File " + dr.getRegPreferenceFileName() + " or " + dr.getAddDropFileName() + " doesn't exist.");
			System.exit(1);
		} catch (Exception e) {
			System.err.println("Your request can not be processed. Please try after some time.");
			System.exit(1);
		}finally{
			try {
				if(brRegPref!=null)
					brRegPref.close();
				if(brAddDrop!=null)
					brAddDrop.close();
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				
			}
		}
	   
	  

	} // end main(...)
	
	

    
	/**
	   *  Validate input arguments
	   *  @param args 
	   *  @return void
	   */ 
	private void validateArgs(String args[]){
		
      
		
		if(args.length==5){
		   
			regPreferenceFileName=args[0];
			addDropFileName=args[1];
			outputFileName=args[2];

			try{
			   
				noOfThreads=Integer.valueOf(args[3]);
				debugValue=Integer.valueOf(args[4]);
				
				if(noOfThreads < 1 || noOfThreads >3){
					System.err.println("No. of threads value should be between 1 and 3.");
					System.exit(1);
				}else if(debugValue < 0 || debugValue >4){
					System.err.println("Debug value should be between 0 and 4.");
					System.exit(1);
				}
				

			}catch(IllegalArgumentException ex){
				System.err.println("Debug level or thread number should be an Integer");
				System.exit(1);
			}finally{
				
			}
		}else{
			System.err.println("Invalid number of arguments. Expected : registration preference input file, add-drop input file, output file, No. of threads and debug value .");
			System.exit(1);
		}
	}

    
	public int getNoOfThreads() {
		return noOfThreads;
	}

	public void setNoOfThreads(int noOfThreads) {
		this.noOfThreads = noOfThreads;
	}

	public int getDebugValue() {
		return debugValue;
	}

	public void setDebugValue(int debugValue) {
		this.debugValue = debugValue;
	}
	public String getRegPreferenceFileName() {
		return regPreferenceFileName;
	}

	public void setRegPreferenceFileName(String regPreferenceFileName) {
		this.regPreferenceFileName = regPreferenceFileName;
	}

	public String getAddDropFileName() {
		return addDropFileName;
	}

	public void setAddDropFileName(String addDropFileName) {
		this.addDropFileName = addDropFileName;
	}
	public String getOutputFileName() {
		return outputFileName;
	}

	public void setOutputFileName(String outputFileName) {
		this.outputFileName = outputFileName;
	}
	
} // end public class Driver

