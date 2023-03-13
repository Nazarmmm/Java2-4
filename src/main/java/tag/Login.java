package tag;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.catalina.User;

import entity.UserList;

public class Login extends SimpleTagSupport {
    // Поле данных для атрибута login
    private String login;
    // Поле данных для атрибута password
    private String password;
    // Метод-сеттер для установки атрибута (вызывается контейнером)
    public void setLogin(String login) {
        this.login = login;
    }
    // Метод-сеттер для установки атрибута (вызывается контейнером)
    public void setPassword(String password) {
        this.password = password;
    }
    public void doTag() throws JspException, IOException {
        // Изначально описание ошибки = null (т.е. ошибки нет)
        String errorMessage = null;
        // Извлечь из контекста приложения общий список пользователей
        UserList userList = (UserList) getJspContext().getAttribute("users", PageContext.APPLICATION_SCOPE);
        if (login==null || login.equals("")) {
            errorMessage = "Логин не может быть пустым!";
        } else {
            // Найти пользователя с таким логином
            User user = (User) userList.findUser(login);
        }
        // Сохранить описание ошибки (текст или null) в сессии
        getJspContext().setAttribute("errorMessage", errorMessage,
                PageContext.SESSION_SCOPE);
    }
}

