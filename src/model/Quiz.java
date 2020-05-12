package model;

import java.util.ArrayList;

/**
 * The class models a quiz
 */
public class Quiz {
	private int id;
	private String quizName;
	private String category;
	private String key;
	private int size;
	private int minutes;
	private ArrayList<Question> questions; 
	
	/**
	 * Constructor with default questions
	 * @param id unique quiz id
	 * @param quizName name of quiz
	 * @param category quizzes subject as a string
	 * @param key unique key to identify quizzes
	 * @param size number of questions
	 * @param minutes allowed time
	 * @param questions ArrayList of Questions
	 */
	public Quiz(int id, String quizName, String category, String key, int size, int minutes, ArrayList<Question> questions) {
	
		this.id = id;
		this.category = category;
		this.quizName = quizName;
		this.key = key;
		this.size = size;
		this.minutes = minutes;
		this.questions = questions;
	
	}
	
	/**
	 * Constructor with no default questions
	 * @param id unique quiz id
	 * @param quizName name of quiz
	 * @param category quizzes subject as a string
	 * @param key unique key to identify quizzes
	 * @param size number of questions
	 * @param minutes allowed time
	 */
	public Quiz(int id, String quizName, String category, String key, int size, int minutes) {
		
		this.id = id;
		this.category = category;
		this.quizName = quizName;
		this.key = key;
		this.size = size;
		this.minutes = minutes;
		this.questions = new ArrayList<>();
	
	}
	
	/**
	 * @return quiz data for teacher's screen
	 */
	public String[] getTableData() {
		
		return new String[] {quizName, category, key, size+"", minutes+""};
		
	}

	/**
	 * @return unique quiz id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets id
	 * @param id new id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return quizzes name
	 */
	public String getQuizName() {
		return quizName;
	}

	/**
	 * sets quizname
	 * @param quizName new quizname
	 */
	public void setQuizName(String quizName) {
		this.quizName = quizName;
	}

	/**
	 * @return quiz's key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * sets quiz's key
	 * @param key mew key
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return number of questions in this quiz
	 */
	public int getSize() {
		return size;
	}

	/**
	 * sets quiz's size
	 * @param size new size
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * @return allowed time for quiz in minutes 
	 */
	public int getMinutes() {
		return minutes;
	}

	/**
	 * sets allowed time for quiz 
	 * @param minutes new time in minutes
	 */
	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	/**
	 * @return ArrayList of questions
	 */
	public ArrayList<Question> getQuestions() {
		return questions;
	}

	/**
	 * sets ArrayList of questions
	 * @param questions new ArrayList of questions
	 */
	public void setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}

	/**
	 * @return quiz's category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * set's quiz's category
	 * @param category new category String
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	
}
