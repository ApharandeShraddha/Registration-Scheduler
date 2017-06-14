package registrationScheduler.objectPool;


import registrationScheduler.util.Logger;
import registrationScheduler.util.Scheduler;

/**
 * Results     This class is singleton object pool which should be used to allocate courses
 * @author    Shraddha
 */
public class ObjectPool {


	
	
	//create object of objectpool
	private static final ObjectPool instance = new ObjectPool();
	
	//constructor is private so ObjectPool can not be instantiated.
	private ObjectPool(){
		Logger.writeMessage("Constructor of ObjectPool is called.", Logger.DebugLevel.CONSTRUCTOR);
	};
	
	public synchronized static ObjectPool getInstance(){
		return instance;
	}
	
	
	public void allocateCourses(String sbStudentPreferences,Scheduler scheduler){
		scheduler.allocateCourses(sbStudentPreferences);
		
		
	}
	public void addDropCourses(String addDropLine,Scheduler scheduler){
		scheduler.addDropCourses(addDropLine);
		
		
	}
	
	
	
	
}
