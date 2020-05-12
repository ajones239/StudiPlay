package model;

import java.util.*;

import model.Question;

/**
 * This class models a quiz subject
 */
public class Subject {
	ArrayList<Question> questions; // ArrayList of questions with the given subject
	String category;			   // String category description of subject
	
	/**
	 * Constructor
	 * @param category string description of category
	 */
	public Subject(String category) {
		this.category = category;
	}
	
	/**
	 * Checks if there is another question in this subject
	 * @param q current question
	 * @return true if there is another question 
	 */
	public boolean hasNextQuestion(Question q) {
		return questions.indexOf(q) < questions.size() - 1;
	}
	
	/**
	 * Fetches the next question. Can return null if no questions.
	 * @param q current question
	 * @return the next question
	 */
	public Question getNextQuestion(Question q) {
		return questions.get(questions.indexOf(q) + 1);
	}
	
	/**
	 * @return returns the first question
	 */
	public Question getFirstQuestion() {
		return questions.get(0);
	}
	
	/**
	 * @return the category String
	 */
	public String getCategory() {
		return category;
	}
}