# Registration-Scheduler
A Multi-threaded application to assign courses to students based on their preferences.
-------------------------------------------------------------------------------------------------------------------------------------
Project Description:

There are 8 courses (A, B, C, D, E, F, G, H) being offered in the summer session. 
The capacity for each course is 60. The total number of students is 80. Each student is required to provide preference for 5 courses. The student is asked to provide a preference for each of the courses. Top preference is specified as "6", while the lowest preference is specified as "0". A student can occur in the input file multiple times. Each occurrence is either for requesting new courses for registration, or for course(s) that (s)he wishes to drop.

Output file will have average preference_score.
-------------------------------------------------------------------------------------------------------------------------------------
Sample of input files:

reg-preference.txt input file.
 
Student_1 A B E F C
Student_2 C H G D A
...

Student_80 H F A C B

add-drop.txt input file (The digit after the student name is for add/drop: 0 indicates drop, 1 indicates add).

Student_2 0 H A
Student_2 1 B E

Student_1 0 E

-------------------------------------------------------------------------------------------------------------------------------------
Debud levels used:

DEBUG_VALUE=4 Print to stdout everytime a constructor is called
DEBUG_VALUE=3 Print to stdout everytime a thread's run() method is called
DEBUG_VALUE=2 Print to stdout everytime an entry is added to the Results data structure
DEBUG_VALUE=1 Print to stdout the contents of the data structure in the store
DEBUG_VALUE=0 Prints Average preference code

-------------------------------------------------------------------------------------------------------------------------------------
Steps to execute code:

Assuming you are in the directory containing src folder:

## To clean:
ant -buildfile src/build.xml clean

-----------------------------------------------------------------------
## To compile: 
ant -buildfile src/build.xml 

-----------------------------------------------------------------------
## To run by specifying arguments from command line 
ant -buildfile src/build.xml run -Darg0=reg-preference.txt -Darg1=add-drop.txt -Darg2=output.txt  -Darg3=1 -Darg4=0

-----------------------------------------------------------------------

justification for Data Structures used in term of Big O complexity (time and/or space)

ConcurrentHashMap :- Time complexity of ConcurrentHashMap is O(1) for insert ,retrieval and contains which is faster or equal to other data structures.i have used ConcurrentHashMap to store all courses as key and its size as value because each time course allocated i need to access data structure containing courses and retrieve it ,change its value and again store value in it and As i am working in thread environment , I have used ConcurrentHashMap as it is thread safe, Reads can happen very fast while write is done with a lock.

Vector :- Time complexity for Vector is O(1) for add which is faster, O(n) for remove and O(1) for Get. In this assignment i am storing students in Vector,As i need to add each student in to Vector after course allocation and for add time complexity of vector is O(1).Here as i am working in thread environment , Vector methods are all synchronized, So using it from multiple threads is safe.



-----------------------------------------------------------------------
Logic Used To calculate Preference score.

If a student gets her first choice, the preference score is 6. If a student gets her second choice, the preference score is 5, and so on. If a student is assigned a course she did not want, the preference score is 1. If a student is not assigned to a course, then the preference score for that course assignment is 0. The preference is based on just the initial 5 requests. 

The new courses that are added (based on user request or otherwise , From add-drop.txt), the preference for those courses is 1. and if dropped then decrease total preference score by 1 for each course drop request.

