package model;

public class Question {

	// Attributes..
	private int id;
	private String question;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	private int correctIndex;
	private String hint;
	
	public Question(int id, String question, String option1, String option2, String option3, String option4,
			int correctIndex, String hint) {
	
		this.id = id;
		this.question = question;
		this.option1 = option1;
		this.option2 = option2;
		this.option3 = option3;
		this.option4 = option4;
		this.correctIndex = correctIndex;
		this.hint = hint;
	
	}
	
	public Question(String question, String option1, String option2, String option3, String option4,
			int correctIndex, String hint) {
	
		this.question = question;
		this.option1 = option1;
		this.option2 = option2;
		this.option3 = option3;
		this.option4 = option4;
		this.correctIndex = correctIndex;
		this.hint = hint;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getOption1() {
		return option1;
	}

	public void setOption1(String option1) {
		this.option1 = option1;
	}

	public String getOption2() {
		return option2;
	}

	public void setOption2(String option2) {
		this.option2 = option2;
	}

	public String getOption3() {
		return option3;
	}

	public void setOption3(String option3) {
		this.option3 = option3;
	}

	public String getOption4() {
		return option4;
	}

	public void setOption4(String option4) {
		this.option4 = option4;
	}

	public int getCorrectIndex() {
		return correctIndex;
	}

	public void setCorrectIndex(int correctIndex) {
		this.correctIndex = correctIndex;
	}

	public String getHint() {
		return hint;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}
	
	public String[] getDataLine() {
		
		return new String[] {question, option1, option2, option3, option4, correctIndex+"", hint};
		
	}
	
}