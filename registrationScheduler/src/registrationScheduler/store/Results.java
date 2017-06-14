
package registrationScheduler.store;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

import registrationScheduler.objectPool.Student;
import registrationScheduler.util.FileDisplayInterface;
import registrationScheduler.util.Logger;
import registrationScheduler.util.StdoutDisplayInterface;

/**
 * Results     This class is used for storing results and writing output to file
 * @author    Shraddha
 */
public class Results implements StdoutDisplayInterface {

	
	public ConcurrentHashMap<String, Integer> coursesMap = new ConcurrentHashMap<String, Integer>();

	public Vector<Student> students = new Vector<Student>();
	

	public void storeStudents(Student studentIn){
		Logger.writeMessage("Entry added to Results data sturcture is : "+studentIn, Logger.DebugLevel.IN_RESULTS);
		students.add(studentIn);	
		}
	
	
	
	public  Vector<Student> getStudents(){
		return students;
	}
	
	public void storeCourseMap(String studentIn,int capacity){
		coursesMap.put(studentIn,capacity);
	}
	
	public  ConcurrentHashMap<String, Integer> getCourseMap(){
		return coursesMap;
	}
	
	
	/**
	 * This Method writes output to console
	 * @param 
	 * @return void
	 */
    public void writeScheduleToStdout() {
    	StringBuilder sb = new StringBuilder();
    	float averageScore = calculateAverageScore(students);
    	
    	for (Student student : getStudents()) {
			if(student !=null){
			sb.append(student.toString());
			}
		}

		sb.append("\n");
		sb.append("Average preference_score is: " + averageScore);
		
		System.out.println(sb.toString());

    }
	/**
	 * This Method calculates average of preference score
	 * @param List of students
	 * @return float value of average preference score
	 */

	private float calculateAverageScore(List<Student> students) {
		float averageScore = 0;
		float sum = 0;
		if (students != null) {
			for (Student s : students) {
				sum = sum + s.getAvgPreferences();
			}
			averageScore = sum / (students.size());
		}
		return averageScore;
	}
	
	 
    
	/**
	 * This Method writes output to flie
	 * @param file name
	 * @return void
	 */

    public void writeSchedulesToFile(String outFileName){
    	StringBuilder sb = new StringBuilder();
    	float averageScore = calculateAverageScore(students);
    	
    	for (Student student : getStudents()) {
			if(student !=null){
			sb.append(student.toString());
			}
		}

		sb.append("\n");
		
		Logger.writeMessage("Contents of the data structure is : "+sb, Logger.DebugLevel.FROM_RESULTS);
		
		//sb.append("Average preference_score is: " + averageScore);

		Logger.writeMessage("Average preference_score is: " + averageScore, Logger.DebugLevel.RELEASE);
		

    	BufferedWriter bufferedWriter = null;
		
		try {
			
			File file = new File(outFileName);

		    //File read code taken from web (below 1 line) 
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));


			if (bufferedWriter!=null)
				bufferedWriter.write(sb.toString());
		} catch (IOException e) {
			System.err.println("Error while writing into File.");
			System.exit(1);
		} finally {

			try {
				if (bufferedWriter!=null)
					bufferedWriter.close();
			} catch (IOException e) {
				System.err.println("Failed while closing buffer reader.");
				System.exit(1);
			}
		}
    	
    }

}
