package controllers;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Task;
import utils.DBUtil;
/**
 * 編集機能を実装するためのサーブレット
 */
@WebServlet("/edit")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//エンティティマネージャーを生成
		EntityManager em = DBUtil.createEntityManager();
		//getParameterで取得したidを引きDBで検索
        Task m = em.find(Task.class, Integer.parseInt(request.getParameter("id")));

        em.close();

        request.setAttribute("task", m);
        request.setAttribute("_token", request.getSession().getId());
        request.getSession().setAttribute("task_id", m.getId());

        //フォワード
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/tasks/edit.jsp");
        rd.forward(request, response);
	}

}
