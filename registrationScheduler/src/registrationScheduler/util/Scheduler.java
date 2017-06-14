package registrationScheduler.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import registrationScheduler.objectPool.Student;

/**
 * This class contains actual logic implementation to allocate courses
 * @author Shraddha
 */
public class Scheduler implements SchedulerInterface {

	StdoutDisplayInterface resObj ;
	
	
	public Scheduler(StdoutDisplayInterface resObjIn) {
		// TODO Auto-generated constructor stub
		Logger.writeMessage("Constructor of Scheduler is called.", Logger.DebugLevel.CONSTRUCTOR);

		resObj= resObjIn;
	}
	
	/**
	 * This method read input add drop file and store output data.
	 * @return None
	 */
	public void addDropCourses(String studentPreference) {
		Student student = null;
		if (studentPreference != null) {
			
			String[] studentPreferencess = studentPreference.split(" ");
			for(Student studentIn:resObj.getStudents()){
				if(studentIn.getStudentName().equalsIgnoreCase(studentPreferencess[0])){
					student=studentIn;
					break;
				}
				
			}
			
			Vector<String> givenCoursePreferences = new Vector<String>();
			if(studentPreferencess[1].equalsIgnoreCase("0")){ 
				//drop course
				for (int i = 2; i < studentPreferencess.length; i++) {
					givenCoursePreferences.add(studentPreferencess[i]);
				}
				student.setGivenCoursePreferences(givenCoursePreferences);
				
			
				dropSpecifiedCourse(student);
			}else{
				//add course
				for (int i = 2; i < studentPreferencess.length; i++) {
					givenCoursePreferences.add(studentPreferencess[i]);
				}
				student.setGivenCoursePreferences(givenCoursePreferences);
				
				
				allocateSpecifiedCourse(student);
			}
			
			
		}
	}


	/**
	 * This method read input files and store input data to List
	 * @return None
	 */
	public void allocateCourses(String studentPreference) {

		Student student = null;

		
		resObj.storeCourseMap("A", 60);
		resObj.storeCourseMap("B", 60);
		resObj.storeCourseMap("C", 60);
		resObj.storeCourseMap("D", 60);
		resObj.storeCourseMap("E", 60);
		resObj.storeCourseMap("F", 60);
		resObj.storeCourseMap("G", 60);
		resObj.storeCourseMap("H", 60);
		
			if (studentPreference != null) {
				student = new Student();
				Vector<String> givenCoursePreferences = new Vector<String>();

				String[] studentPreferencess = studentPreference.split(" ");
				student.setStudentName(studentPreferencess[0]);

				for (int i = 1; i < 6; i++) {

					
					givenCoursePreferences.add(studentPreferencess[i]);
				}
				student.setGivenCoursePreferences(givenCoursePreferences);
				
			}

		//}



		 allocate(student);

		 resObj.storeStudents(student);
		 sortStudentsBasedOnName(resObj.getStudents());
	}



	/**
	 * This method sorts students as per given input to display output in same order as input
	 * @param students
	 * @return List of students 
	 */
	private void sortStudentsBasedOnName(Vector<Student> students) {

		Collections.sort(students, new StudentComparatorEnd());
		

	}
	
	/**
	 * This method read student object and drop specified course from that studnet allcoated courses.
	 * @return None
	 */
	
	public void dropSpecifiedCourse(Student student) {
		if (student != null && student.getAllocatedCourses() != null) {
		// Drop first course to each student
				Vector<String> allocatedCourses = student.getAllocatedCourses();

				String course1 = student.getGivenCoursePreferences().get(0);
				//allocatedCourses = new Vector<String>();
				
				boolean flag1 = checkAvaibilityForDrop(student, course1);
				if (flag1 == true) {
					allocatedCourses.remove(course1);
					student.setAllocatedCourses(allocatedCourses);
					int n = (student.getAvgPreferences());
					student.setAvgPreferences(n-1);
				} else {
					//student.setAvgPreferences(0);
				}
		}
				// Drop remaining courses to each student as request
				for (int i = 1; i < student.getGivenCoursePreferences().size(); i++) {
					
						if (student != null && student.getAllocatedCourses() != null) {
							String course = student.getGivenCoursePreferences().get(i);

							boolean flag = checkAvaibilityForDrop(student, course);
							if (flag == true && (student.getAllocatedCourses()).contains(course)) {
								(student.getAllocatedCourses()).remove(course);
								int n = (student.getAvgPreferences());
								student.setAvgPreferences(n -1);
							} 
						}
						//resObj.storeStudentsInFinalList(student);
					}
	}
	
	
	/**
	 * This method read student object and add specified course in that student allocated courses.
	 * @return None
	 */
	
	public void allocateSpecifiedCourse(Student student) {
		// TODO Auto-generated method stub
		
		// Assign first course to each student
		Vector<String> allocatedCourses = student.getAllocatedCourses();

		String course1 = student.getGivenCoursePreferences().get(0);
		//allocatedCourses = new Vector<String>();
		
		boolean flag1 = checkAvaibility(student, course1);
		
		if (flag1 == true ) {
			int n = (student.getAvgPreferences());
			if(allocatedCourses !=null && !(allocatedCourses.contains(course1))){
			allocatedCourses.add(course1);
			student.setAllocatedCourses(allocatedCourses);
			student.setAvgPreferences(n +1);
			}
		} else {
			int n = (student.getAvgPreferences());
			student.setAvgPreferences(n + 0);
		}
		
	// Assign remaining courses to each student as request
		for (int i = 1; i < student.getGivenCoursePreferences().size(); i++) {
			
				if (student != null && student.getAllocatedCourses() != null) {
					String course = student.getGivenCoursePreferences().get(i);

					boolean flag = checkAvaibility(student, course);
					if (flag == true && !(student.getAllocatedCourses()).contains(course)) {
						(student.getAllocatedCourses()).add(course);
						int n = (student.getAvgPreferences());
						student.setAvgPreferences(n +1);
					} else {
						int n = (student.getAvgPreferences());
						student.setAvgPreferences(n + 0);
					}
				}
				//resObj.storeStudentsInFinalList(student);
			}
		
		
		
	}

	/**
	 * This method allocates courses to students
	 * @param students
	 * @param output filename 
	 * @return void
	 */
	private void allocate(Student student ) {


		Vector<String> allocatedCourses = null;

		// Assign first course to each student
		
			if (student != null) {
				String course = student.getGivenCoursePreferences().get(0);
				allocatedCourses = new Vector<String>();
				boolean flag = checkAvaibility(student, course);
				if (flag == true) {
					allocatedCourses.add(course);
					student.setAllocatedCourses(allocatedCourses);
					//student.setAvgPreferences(1);
					student.setAvgPreferences(6);
				} else {
					//student.setAvgPreferences(6);
					student.setAvgPreferences(0);
				}

			}

		

		// Assign remaining courses to each student as request
		for (int i = 1; i < 5; i++) {
			
				if (student != null && student.getAllocatedCourses() != null) {
					String course = student.getGivenCoursePreferences().get(i);

					boolean flag = checkAvaibility(student, course);
					if (flag == true && !(student.getAllocatedCourses()).contains(course)) {
						(student.getAllocatedCourses()).add(course);
						int n = (student.getAvgPreferences());
						student.setAvgPreferences(n +(6- i));
					} else {
						int n = (student.getAvgPreferences());
						student.setAvgPreferences(n + 0);
					}
				}
				//resObj.storeStudentsInFinalList(student);
			}
		

			if(student != null && student.getAllocatedCourses() == null){
				student.setAvgPreferences(0);
			}

	}
	

	
	/**
	 * 
	 * @param student 
	 * @param course
	 * @return boolean value True or False depend on enrollment status
	 */

	private boolean checkAvaibility(Student student, String course) {
		boolean result = false;
		if (resObj.getCourseMap().containsKey(course)) {

			int courseSize = resObj.getCourseMap().get(course);
			if (courseSize > 0) {
				resObj.getCourseMap().put(course, courseSize - 1);
				result = true;
			} else {
				result = false;
			}
		} else {
			System.out.println("course is not offered");
		}
		return result;
	}
	
	/**
	 * 
	 * @param student 
	 * @param course
	 * @return boolean value True or False depend on enrollment status
	 */

	private boolean checkAvaibilityForDrop(Student student, String course) {
		boolean result = false;
		if (resObj.getCourseMap().containsKey(course)) {

			int courseSize = resObj.getCourseMap().get(course);
			
				resObj.getCourseMap().put(course, courseSize + 1);
				result = true;
			
		} else {
			System.out.println("course is not offered");
		}
		return result;
	}

	
	
	
	@Override
	public String toString() {
		return "Scheduler [coursesMap=" + resObj.getCourseMap() + ", students=" + resObj.getStudents() + "]";
	}
}

/*
 *This class is a implementation of Comparator to sort students based on registration time
 */
class StudentComparator implements Comparator<Student> {
	@Override
	public int compare(Student o1, Student o2) {
		return o1.getRegTime() - o2.getRegTime();
	}
}

/*
 *This class is a implementation of Comparator to sort students as per original input sequence
 */
class StudentComparatorEnd implements Comparator<Student> {
	@Override
	public int compare(Student o1, Student o2) {
		String[] arr1=o1.getStudentName().split("_");
		String[] arr2=o2.getStudentName().split("_");
		int first=Integer.parseInt(arr1[1]);
		int second=Integer.parseInt(arr2[1]);
		
		return first - second;
	}
	}

