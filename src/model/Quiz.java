package model;

import java.util.ArrayList;

public class Quiz {

	private int id;
	private String quizName;
	private String key;
	private int size;
	private int minutes;
	private ArrayList<Question> questions;
	
	public Quiz(int id, String quizName, String key, int size, int minutes, ArrayList<Question> questions) {
	
		this.id = id;
		this.quizName = quizName;
		this.key = key;
		this.size = size;
		this.minutes = minutes;
		this.questions = questions;
	
	}
	
	public Quiz(int id, String quizName, String key, int size, int minutes) {
		
		this.id = id;
		this.quizName = quizName;
		this.key = key;
		this.size = size;
		this.minutes = minutes;
		this.questions = new ArrayList<>();
	
	}
	
	public String[] getTableData() {
		
		return new String[] {quizName, key, size+"", minutes+""};
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuizName() {
		return quizName;
	}

	public void setQuizName(String quizName) {
		this.quizName = quizName;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public ArrayList<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}
	
}
