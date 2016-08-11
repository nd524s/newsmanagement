package com.epam.newsmanagement.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by Никита on 8/8/2016.
 */
public class ActionFactory {
    private static final String CONTEXT_PATH = "/Client-context.xml";
    private static final ActionFactory instance = new ActionFactory();
    private ApplicationContext applicationContext = new ClassPathXmlApplicationContext(CONTEXT_PATH);

    private ActionFactory() {}

    public static ActionFactory getInstance() {
        return instance;
    }

    public ActionCommand defineCommand(HttpServletRequest request) {
        String action = request.getParameter("command");
        ActionCommand command = (ActionCommand) applicationContext.getBean(action);
        return command;
    }
}
