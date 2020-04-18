package model;

import java.util.*;

import database.Manager;

public class Subject {
	public static final String[] SUBJECTS = {"Basic Math", "Algebra", "Science", "Language Arts"};
	ArrayList<Question> questions;
	String category;
	
	public Subject(String category) {
		this.category = category;
		Manager m = Manager.getInstance();
		questions = m.getQuestions(category);
	}
	
	public boolean hasNextQuestion(Question q) {
		return questions.indexOf(q) < questions.size() - 1;
	}
	
	public Question getNextQuestion(Question q) {
		return questions.get(questions.indexOf(q) + 1);
	}
	
	public Question getFirstQuestion() {
		return questions.get(0);
	}
	
	public String getCategory() {
		return category;
	}
}
