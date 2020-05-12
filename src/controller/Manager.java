package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Question;
import model.Quiz;

/**
 * The Manager class provides an API to perform specific queries with the database
 */
public class Manager {
	
	private Database database;
	private static Manager manager;
	
	/**
	 * Singleton class. Must use getInstance instead of constructor.
	 * @return this
	 */
	public static Manager getInstance() {

		if(manager == null) {
			manager = new Manager();
		}

		return manager;
	}
	
	/**
	 * Private constructor
	 */
	private Manager() {
		
		database = Database.getInstance();
		
	}
	
	/**
	 * Adds a new user into the database.
	 * @param type should be either "student" or "teacher"
	 * @param name new user's name
	 * @param username new user's username
	 * @param password new user's password
	 */
	public void register(String type, String name, String username, String password) {
		
		database.execute("INSERT INTO USER(NAME, USERNAME, PASSWORD, TYPE) VALUES('"+name+"','"+username+"','"+password+"','"+type+"');");
		
	}
	
	/**
	 * Validates login information and gets userid if valid credentials are given
	 * @param type "teacher" or "student"
	 * @param username user's username
	 * @param password user's password
	 * @return user's id if valid login credentials are given, -1 otherwise
	 */
	public int login(String type, String username, String password) {
		
		ResultSet result = database.executeQuery("SELECT USERID FROM USER WHERE USERNAME='"+username+"' AND PASSWORD='"+password+"' AND TYPE='"+type+"'");
		try {
			if(result.next()) {
				return result.getInt(1);
			}
		} catch (SQLException e) {
		}
		return -1;
		
	}
	
	/**
	 * Checks if user exists
	 * @param type "teacher" or "student"
	 * @param username
	 * @return true if user exists
	 */
	public boolean isExists(String type, String username) {
		
		ResultSet result = database.executeQuery("SELECT * FROM USER WHERE USERNAME='"+username+"' AND TYPE='"+type+"'");
		try {
			return result.next();
		} catch (SQLException e) {
			return false;
		}
		
	}
	
	/**
	 * @return last user id that was inserted into the database
	 * @throws Exception on bad query
	 */
	private int getLastInserted() throws Exception {
		
		int id = -1;
		ResultSet result = database.executeQuery("SELECT LAST_INSERT_ID();");
		if(result.next()) {
			id = result.getInt(1);
		}
		return id;
		
	}
	
	/**
	 * Adds a new quiz into the database
	 * @param quizTitle title of quiz
	 * @param subject subject of quiz
	 * @param minutes allowed time for the quiz in minutes
	 * @param key quiz key. Must be unique for exams, can be anything for practice quizzes
	 * @param size number of questions
	 * @param teacher ID of teacher who created it. Can be anything for practice quizzes
	 * @param questions ArrayList of questions to add
	 * @param practice true if this is a practice quiz
	 */
	public void addQuiz(String quizTitle, String subject, int minutes, String key, int size, int teacher, ArrayList<Question> questions
			, boolean practice) {
		
		try {
			
			int value = (practice ? 1 : 0);
			System.out.println(practice+" ,"+value);
			// adding quiz..
			database.execute("INSERT INTO QUIZ(QUIZNAME, SUBJECT, QUIZKEY, TOTALQUESTIONS, TIMELIMIT, TEACHERID, PRACTICE) "
					+ "VALUES('"+quizTitle+"','"+subject+"','"+key+"',"+questions.size()+","+minutes+","+teacher+","+value+");");
			int quizId = getLastInserted();
			if(quizId != -1) {
				
				for(Question question: questions) {
					
					database.execute("INSERT INTO QUESTION(QUESTION, OPTION1, OPTION2, OPTION3, OPTION4, CORRECTINDEX, HELP) VALUES("
							+ "'"+question.getQuestion()+"','"+question.getOption1()+"','"+question.getOption2()+"','"+question.getOption3()+"',"
							+ "'"+question.getOption4()+"',"+question.getCorrectIndex()+",'"+question.getHint()+"');");
					int questionId = getLastInserted();
					database.execute("INSERT INTO QUIZQUESTIONHELPER(QUIZID, QUESTIONID) VALUES("+quizId+","+questionId+");");
				
				}
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Gets quizzes created by a specific teacher. Can be null
	 * @param teacherid to get quizzes of
	 * @return ArrayList of quizzes
	 */
	public ArrayList<Quiz> getAllQuizes(int teacherid) {
		
		ArrayList<Quiz> quizes = new ArrayList<>();
		
		try {
			ResultSet result = database.executeQuery("SELECT * FROM QUIZ WHERE TEACHERID="+teacherid);
			while(result.next()) {
				int id = result.getInt(1);
				String name = result.getString(2);
				String subject = result.getString(3);
				String key = result.getString(4);
				int size = result.getInt(5);
				int minutes = result.getInt(6);
				quizes.add(new Quiz(id, name, subject, key, size, minutes));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return quizes; // can be null
		
	}
	
	/**
	 * Returns all quizzes based on whether they are practice or not
	 * @param isPractice true if you are asking for practice quizzes. False otherwise
	 * @return an ArrayList of all quizzes matching the isPractice value
	 */
	public ArrayList<Quiz> getAllQuizesByPractice(int isPractice) {
		
		ArrayList<Quiz> quizes = new ArrayList<>();
		
		try {
			ResultSet result = database.executeQuery("SELECT * FROM QUIZ WHERE PRACTICE="+isPractice);
			while(result.next()) {
				int id = result.getInt(1);
				String name = result.getString(2);
				String subject = result.getString(3);
				String key = result.getString(4);
				int size = result.getInt(5);
				int minutes = result.getInt(6);
				quizes.add(new Quiz(id, name, subject, key, size, minutes));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return quizes;
		
	}
	
	/**
	 * Returns an id of the quiz with the given key 
	 * @param key key to get quiz id of
	 * @return quiz id. -1 if the quiz is not found
	 */
	public int getQuizByKey(String key) {
		
		try {
			ResultSet result = database.executeQuery("SELECT QUIZID FROM QUIZ WHERE QUIZKEY='"+key+"'");
			while(result.next()) {
				return result.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}	
		return -1;
		
	}
	
	/**
	 * Gets a quiz by id. returns null if quiz is not found
	 * @param id id of quiz to retrieve
	 * @return Quiz with given id
	 */
	public Quiz getQuiz(int id) {
		
		try {
			ArrayList<Question> questions = new ArrayList<>();
			ResultSet result = database.executeQuery("SELECT * FROM QUIZ WHERE QUIZID="+id);
			if(result.next()) {
				
				String name = result.getString(2);
				String subject = result.getString(3);
				String key = result.getString(4);
				int size = result.getInt(5);
				int minutes = result.getInt(6);
				
				// reading quiz..
				result = database.executeQuery("SELECT question,option1,option2,option3,option4,"
						+ "correctIndex,help FROM quizprogram.quizQuestionHelper JOIN quizprogram."
						+ "question WHERE quizQuestionHelper.questionId=question.questionId AND "
						+ "quizQuestionHelper.quizId="+id+";");
				while(result.next()) {

					String question = result.getString(1);					
					String option1 = result.getString(2);
					String option2 = result.getString(3);
					String option3 = result.getString(4);
					String option4 = result.getString(5);
					int index = result.getInt(6);
					String hint = result.getString(7);
					questions.add(new Question(question, option1, option2, option3, option4, index, hint));
					
				}
				
				return new Quiz(id, name, subject, key, size, minutes, questions);
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}	
		
		return null;
		
	}
	
	/**
	 * Adds a students exam score to the database.
	 * @param userid user of exam taker
	 * @param quizid exam they took
	 * @param total total number of questions
	 * @param correct total number correct
	 */
	public void addScore(int userid, int quizid, int total, int correct) {
		
		database.execute("INSERT INTO EXAM(USERID, QUIZID, TOTAL, CORRECT) VALUES("+userid+","+quizid+","+total+","+correct+")");
		
	}
	
	/**
	 * Fetches student names and scores of a quiz with the given id
	 * @param quizId quiz to get scores and students for
	 * @return 2D String array of students name, total, correct
	 */
	public String[][] getTeacherStudentData(int quizId){
		
		ArrayList<String[]> data = new ArrayList<>();
		
		try {
			
			ResultSet result = database.executeQuery("SELECT NAME,TOTAL,CORRECT FROM QUIZPROGRAM.EXAM JOIN QUIZPROGRAM.User WHERE QUIZPROGRAM.USER.USERID=QUIZPROGRAM.EXAM.USERID AND QUIZPROGRAM.EXAM.QUIZID="+quizId+";");
			while(result.next()) {
				
				String name = result.getString(1);
				int total = result.getInt(2);
				int correct = result.getInt(3);
				int wrong = total - correct;
				data.add(new String[] {name, total+"", correct+"",wrong+""});
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return data.toArray(new String[][] {});
		
	}
	
	/**
	 * Deletes quiz with given key
	 * @param key of quiz to delete
	 */
	public void deleteQuiz(String key) {
		int id = this.getQuizByKey(key);
		try {
			database.execute("DELETE FROM quiz WHERE quizid="+id+";");
			database.execute("DELETE FROM quizquestionhelper WHERE quizid="+id+";");
		}
		catch (Exception e) {}
	}
	
	public void setDatabaseCredentials(String host, String user, String password) {
		
	}
}
