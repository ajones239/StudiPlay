package model;

/**
 * This class models a quiz question
 */
public class Question {

	private int id;
	private String question;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	private int correctIndex;
	private String hint;
	
	/**
	 * Constructor with quiz id
	 * @param id unique id of question
	 * @param question question String
	 * @param option1 String of option
	 * @param option2 String of option
	 * @param option3 String of option
	 * @param option4 String of option
	 * @param correctIndex index, 0 - 3, of correct option. index = option - 1
	 * @param hint String to give students hint to the correct answer
	 */
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

	/**
	 * Constructor without quiz id
	 * @param question question String
	 * @param option1 String of option
	 * @param option2 String of option
	 * @param option3 String of option
	 * @param option4 String of option
	 * @param correctIndex index, 0 - 3, of correct option. index = option - 1
	 * @param hint String to give students hint to the correct answer
	 */
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

	/**
	 * @return Question id
	 */
	public int getId() {
		return id;
	}

	/**
	 * sets question's id
	 * @param id new id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return question String
	 */
	public String getQuestion() {
		return question;
	}

	/**
	 * sets question String
	 * @param question new question String
	 */
	public void setQuestion(String question) {
		this.question = question;
	}

	/**
	 * @return option1
	 */
	public String getOption1() {
		return option1;
	}

	/**
	 * set option1
	 * @param option1
	 */
	public void setOption1(String option1) {
		this.option1 = option1;
	}

	/**
	 * @return option2
	 */
	public String getOption2() {
		return option2;
	}

	/**
	 * set option2
	 * @param option2
	 */
	public void setOption2(String option2) {
		this.option2 = option2;
	}

	/**
	 * @return option3
	 */
	public String getOption3() {
		return option3;
	}

	/**
	 * set option4
	 * @param option4
	 */
	public void setOption3(String option3) {
		this.option3 = option3;
	}

	/**
	 * @return option4
	 */
	public String getOption4() {
		return option4;
	}

	/**
	 * set option4
	 * @param option4
	 */
	public void setOption4(String option4) {
		this.option4 = option4;
	}

	/**
	 * @return index of correct question
	 */
	public int getCorrectIndex() {
		return correctIndex;
	}

	/**
	 * sets correct index
	 * @param correctIndex new correct index
	 */
	public void setCorrectIndex(int correctIndex) {
		this.correctIndex = correctIndex;
	}

	/**
	 * @return current hint String
	 */
	public String getHint() {
		return hint;
	}

	/**
	 * sets hintString
	 * @param hint new hint
	 */
	public void setHint(String hint) {
		this.hint = hint;
	}
	
	/**
	 * @return a String array of all Question information
	 */
	public String[] getDataLine() {
		
		return new String[] {question, option1, option2, option3, option4, correctIndex+"", hint};
		
	}
	
}