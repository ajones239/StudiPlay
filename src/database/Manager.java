package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Question;
import model.Quiz;

public class Manager {

	// 
	private Database database;
	private static Manager manager;
	
	public static Manager getInstance() {
		
		if(manager == null) {
			manager = new Manager();
		}
		
		return manager;
		
	}
	
	private Manager() {
		
		database = Database.getInstance();
		
	}
	
	public void register(String type, String name, String username, String password) {
		
		database.execute("INSERT INTO USER(NAME, USERNAME, PASSWORD, TYPE) VALUES('"+name+"','"+username+"','"+password+"','"+type+"');");
		
	}
	
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
	
	public boolean isExists(String type, String username) {
		
		ResultSet result = database.executeQuery("SELECT * FROM USER WHERE USERNAME='"+username+"' AND TYPE='"+type+"'");
		try {
			return result.next();
		} catch (SQLException e) {
			return false;
		}
		
	}
	
	private int getLastInserted() throws Exception {
		
		int id = -1;
		ResultSet result = database.executeQuery("SELECT LAST_INSERT_ID();");
		if(result.next()) {
			id = result.getInt(1);
		}
		return id;
		
	}
	
	public void addQuiz(String quizTitle, String subject, int minutes, String key, int size, int teacher, ArrayList<Question> questions) {
		
		try {
			
			// adding quiz..
			database.execute("INSERT INTO QUIZ(QUIZNAME, SUBJECT, QUIZKEY, TOTALQUESTIONS, TIMELIMIT, TEACHERID) "
					+ "VALUES('"+quizTitle+"','"+subject+"','"+key+"',"+questions.size()+","+minutes+","+teacher+");");
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
		
		return quizes;
		
	}
	
	public ArrayList<Quiz> getAllQuizes() {
		
		ArrayList<Quiz> quizes = new ArrayList<>();
		
		try {
			ResultSet result = database.executeQuery("SELECT * FROM QUIZ");
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
	
	public void addScore(int userid, int quizid, int total, int correct) {
		
		database.execute("INSERT INTO EXAM(USERID, QUIZID, TOTAL, CORRECT) VALUES("+userid+","+quizid+","+total+","+correct+")");
		
	}
	
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
	
	/*public ArrayList<Question> getQuestions(String subject) {
		ArrayList<Question> questions = new ArrayList<>();
		try {
			ResultSet result = database.executeQuery("SELECT question, option1, option2, option3, option4, correctIndex, help FROM quizprogram.question WHERE category='"+subject+"';");
			while(result.next()) {
				String question = result.getString(1);
				String opt1 = result.getString(2);
				String opt2 = result.getString(3);
				String opt3 = result.getString(4);
				String opt4 = result.getString(5);
				int correctIndex = result.getInt(6);
				String hint = result.getString(7);
				questions.add(new Question(subject, question, opt1, opt2, opt3, opt4, correctIndex, hint));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return questions;
	}*/
	
	
}
