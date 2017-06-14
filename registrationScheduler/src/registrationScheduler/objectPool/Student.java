package registrationScheduler.objectPool;

import java.util.List;
import java.util.Vector;


/**
 * This class have student information
 * @author Shraddha
 *
 */
public class Student {
	
	
	private String studentName;
	
	private int regTime;
	
	private List<String> givenCoursePreferences;
	
	private Vector<String> allocatedCourses;
	
	private int avgPreferences;
	

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public int getRegTime() {
		return regTime;
	}

	public void setRegTime(int regTime) {
		this.regTime = regTime;
	}

	public List<String> getGivenCoursePreferences() {
		return givenCoursePreferences;
	}

	public void setGivenCoursePreferences(List<String> givenCoursePreferences) {
		this.givenCoursePreferences = givenCoursePreferences;
	}

	public Vector<String> getAllocatedCourses() {
		return allocatedCourses;
	}

	public void setAllocatedCourses(Vector<String> allocatedCourses) {
		this.allocatedCourses = allocatedCourses;
	}
	
	public int getAvgPreferences() {
		return avgPreferences;
	}

	public void setAvgPreferences(int avgPreferences) {
		this.avgPreferences = avgPreferences;
	}

 

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();

		if (getAllocatedCourses() != null && !(getAllocatedCourses().isEmpty())) {
			
			for (String course : getAllocatedCourses()) {
				if (course != null && course != " ") {
					sb.append(course);
					sb.append(" ");
				}
			}
		}
		return studentName + " " + sb.toString() + " " + avgPreferences + "\n";
	}

	
	
}
