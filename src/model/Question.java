package model;

/*
 * This class is used to model questions that users will be asked. Specific subjects 
 * 	(ie AlgebraQuestion) should extend this class.
 */
public abstract class Question 
{
	protected String text;		
	protected Object answer;	// If answer is numeric, use equivalent wrapper class (ie Integer)
	
	/*
	 * @param text text the question asks
	 * @param answer the answer to the question
	 */
	public Question(String text, Object answer) {
		this.text = text;
		this.answer = answer;
	}
	
	/*
	 * @param attempted answer to the question
	 * @return true if the attempt matches the answer
	 */
	public boolean checkAnswer(Object attempt) {
		return attempt.equals(answer);
	}

}
