package net.shvdy.nutrition_tracker.controller.command.admin;

import net.shvdy.nutrition_tracker.controller.command.ActionCommand;
import net.shvdy.nutrition_tracker.controller.command.CommandEnum;
import net.shvdy.nutrition_tracker.dto.ArticleDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 24.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class Feed implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		List<ArticleDTO> articles = new ArrayList<>();
		try {
			articles = CommandEnum.getArticleService().findPaginated();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.getSession().setAttribute("paginatedArticles", articles);
		return "/view/feed.jsp";
	}
}
