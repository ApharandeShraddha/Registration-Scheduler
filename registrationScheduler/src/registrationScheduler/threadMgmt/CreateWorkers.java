
package registrationScheduler.threadMgmt;


import registrationScheduler.util.FileProcessor;
import registrationScheduler.util.Logger;
import registrationScheduler.util.StdoutDisplayInterface;

/**
 * Results     This class is responsible for creating threads and joining them
 * @author    Shraddha
 */
public class CreateWorkers  {

	

	FileProcessor fpObj;
	StdoutDisplayInterface resObj;

    public CreateWorkers(FileProcessor fpIn, StdoutDisplayInterface resIn) {
		// TODO Auto-generated constructor stub
    	Logger.writeMessage("Constructor of CreateWorkers is called.", Logger.DebugLevel.CONSTRUCTOR);
		this.fpObj=fpIn;
		this.resObj=resIn;
	
	}

	/**
	   *  This will start threads
	   *  @param  number of threads
	   *  @return void
	   */ 
	public void startWorkers(int noOfThreads){


		WorkerThread worker = new WorkerThread(fpObj, resObj);
		Thread[] threadList = new Thread[noOfThreads];
		for(int i=0;i<noOfThreads;i++){
			Thread t=new Thread(worker);
			threadList[i]=t;
			t.start();

		}
		
		for(int j=0;j<noOfThreads;++j){
			 try {
				 threadList[j].join();
			 } catch (InterruptedException e) {
				 System.out.println("Thread interrupted.");
			 	System.exit(1);
		     }finally{
		    	 
		     }
		}

	
    }
}
