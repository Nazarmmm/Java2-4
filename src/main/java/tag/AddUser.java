package tag;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import entity.User;
import entity.UserList;
import entity.UserList.UserExistsException;
import helper.UserListHelper;

public class AddUser extends SimpleTagSupport {
    // Поле данных для атрибута user
    private User user;
    // Метод-сеттер для установки атрибута (вызывается контейнером)
    public void setUser(User user) {
        this.user = user;
    }
    public void doTag() throws JspException, IOException {
        if (errorMessage==null) {
            try {
                // Непосредственное добавление пользователя делает UserList
                userList.addUser(user);
                // Записать обновлѐнный список пользователей в файл
                UserListHelper.saveUserList(userList);
            } catch (UserExistsException e) {
                // Ошибка - пользователь с таким логином уже существует
                        errorMessage = "Пользователь с таким логином уже существует!";
            }
        }
    }
}

