package controller;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helper.TextQuestion;
import model.entity.Person;
import model.entity.question.Answer;
import model.entity.question.Question;
import model.entity.question.QuestionType;

/**
 * Servlet implementation class ChangeResponseTypeYN
 */
@WebServlet("/ChangeResponseTypeYN")
public class ChangeResponseTypeYN extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeResponseTypeYN() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		int authorId = Integer.parseInt(request.getParameter("authorId"));
		Question question = TextQuestion.getQuestion(id);
		List<Answer> answers = question.getAnswers();
		ArrayList<Integer> ids = new ArrayList<Integer>();
		
		for (Answer a : answers) {
			if (a.isCorrect()) {
			Question newQuestion = new Question();
			newQuestion.setPhrasing(a.getText());
			newQuestion.setType(QuestionType.YN);
			newQuestion.setId(Question.questionCount++);
			Person author = new Person();
			author.setId(authorId);
			newQuestion.setAuthor(author);
			ids.add(newQuestion.getId());
			}
		}
		request.setAttribute("id", ids);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
