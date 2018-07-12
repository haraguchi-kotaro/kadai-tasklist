package controllers;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Task;
import utils.DBUtil;
/**
 * タスクの削除処理を持つサーブレット
 * edit.jspから来ることを想定
 * "_token"にはidが格納されている
 */
@WebServlet("/destroy")
public class DestroyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DestroyServlet() {
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
            //EditServletで格納した削除対象のtask_idを取得
            Task m = em.find(Task.class, (Integer)(request.getSession().getAttribute("task_id")));

            em.getTransaction().begin();
            em.remove(m);
            em.getTransaction().commit();
            em.close();

            request.getSession().removeAttribute("task_id");

            response.sendRedirect(request.getContextPath() + "/index");
        }
	}

}
