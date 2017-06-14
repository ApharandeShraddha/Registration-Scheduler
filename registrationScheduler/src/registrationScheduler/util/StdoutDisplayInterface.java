
package registrationScheduler.util;

import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

import registrationScheduler.objectPool.Student;

public interface StdoutDisplayInterface {
    public void writeScheduleToStdout();
    public void storeStudents(Student studentIn);
    public void storeCourseMap(String studentIn,int capacity);
    public  Vector<Student> getStudents();
	public  ConcurrentHashMap<String, Integer> getCourseMap();
	public void writeSchedulesToFile(String outputFileName);
} 
