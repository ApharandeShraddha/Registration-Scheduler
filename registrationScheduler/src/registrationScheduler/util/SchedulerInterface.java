package registrationScheduler.util;

import registrationScheduler.objectPool.Student;

/**
 * Results     This interface is responsible for allocating coureses
 * @author    Shraddha
 */
public interface SchedulerInterface {
	
	/**
	 * 
	 * @param sbRegTime is a stringBuilder containing input data of student and corresponding registration time
	 * @param sbStudentPreferences is a stringBuilder containing input data of student and corresponding registration time
	 * @param outputFilename is a stringBuilder containing input data of student and corresponding registration time
	 * @return void
	 */
	public void allocateCourses( String sbStudentPreferences );
	public void addDropCourses(String studentPreference) ;
	public void dropSpecifiedCourse(Student student);
	public void allocateSpecifiedCourse(Student student);

}
