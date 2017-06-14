
package registrationScheduler.threadMgmt;


import registrationScheduler.objectPool.ObjectPool;
import registrationScheduler.util.FileProcessor;
import registrationScheduler.util.Logger;
import registrationScheduler.util.Scheduler;
import registrationScheduler.util.StdoutDisplayInterface;

/**
 * Results     This class is responsible for thread's run method and processing line by line
 * @author    Shraddha
 */
public class WorkerThread implements Runnable  {
	FileProcessor fpObj;
	StdoutDisplayInterface resObj;
	ObjectPool obj=ObjectPool.getInstance();
    // public WorkerThread(??h) {
    // }
	Scheduler scheduler ;
    
    
    public WorkerThread(FileProcessor fpIn, StdoutDisplayInterface resIn) {
		// TODO Auto-generated constructor stub
    	Logger.writeMessage("Constructor of WorkerThread is called.", Logger.DebugLevel.CONSTRUCTOR);
		this.fpObj=fpIn;
		this.resObj=resIn;
		scheduler = new Scheduler(resObj);
		
	}

	public void run()  {

		
		try {
    		Logger.writeMessage("Thread's run() method is called", Logger.DebugLevel.IN_RUN);
			String studentPreference=fpObj.readLineFromRefFile();
			
					
			while (studentPreference != null) {

				obj.allocateCourses(studentPreference, scheduler);

				studentPreference = fpObj.readLineFromRefFile();
			}
			
			String addDropLine = fpObj.readLineFromAddDrop();
			while (addDropLine != null) {

				obj.addDropCourses(addDropLine, scheduler);

				addDropLine = fpObj.readLineFromAddDrop();
			}
		} catch (Exception e) {
			
			
		}finally{
			
		}
	
    }
    

}
