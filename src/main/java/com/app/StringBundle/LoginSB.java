package com.app.StringBundle;

/**
 * Created by ayaroslavtsev on 31.01.2017.
 */
public class LoginSB {
    private static Object[][] contents = {
            {"loginScreen.title", "Вход"},
            {"loginScreen.login", "Логин"},
            {"loginScreen.password", "Пароль"},
            {"loginScreen.error", "Неверный логин или пароль"},
            {"loginScreen.inactive", "Ваш пользователь заблокирован"}
    };

    public static Object[][] getContents() {
        return contents;
    }
}
