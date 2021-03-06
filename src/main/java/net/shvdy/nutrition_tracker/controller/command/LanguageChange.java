package net.shvdy.nutrition_tracker.controller.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * 24.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class LanguageChange implements ActionCommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getSession().setAttribute("lang",
                Objects.requireNonNullElse(request.getParameter("lang"), "en"));
        CommandEnum.REDIRECT_HOME.getActionCommand().execute(request, response);
    }

}