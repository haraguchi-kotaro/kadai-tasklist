package controllers;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Task;
import utils.DBUtil;

/**
 * フォームからの値を受け取り、DBへ挿入するサーブレット
 */
@WebServlet("/create")
public class CreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String _token = (String)request.getParameter("_token");
	    if(_token != null && _token.equals(request.getSession().getId())) {
	    	//エンティティマネージャーを生成
	    	EntityManager em = DBUtil.createEntityManager();

	        Task m = new Task();

	        //フォームから入力された値を受け取り、変数mに格納
	        String content = request.getParameter("content");
	        m.setContent(content);

	        //日付を取得し、変数mに格納
	        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
	        m.setCreated_at(currentTime);
	        m.setUpdated_at(currentTime);

	        em.getTransaction().begin();
	        em.persist(m);
	        em.getTransaction().commit();
	        em.close();

	        //リダイレクト
	        response.sendRedirect(request.getContextPath() + "/index");
	    }
	}
}
