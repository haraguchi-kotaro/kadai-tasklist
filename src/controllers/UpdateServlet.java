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
 * 更新処理を実装するためのサーブレット
 */
@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
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

            Task m = em.find(Task.class, (Integer)(request.getSession().getAttribute("task_id")));


            String content = request.getParameter("content");
            m.setContent(content);

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            m.setUpdated_at(currentTime);

            em.getTransaction().begin();
            em.getTransaction().commit();
            em.close();

            request.getSession().removeAttribute("task_id");

            response.sendRedirect(request.getContextPath() + "/index");
        }
	}

}
