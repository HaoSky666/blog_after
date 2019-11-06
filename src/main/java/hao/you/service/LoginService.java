package hao.you.service;

import hao.you.domain.Login;

public interface LoginService {
    public int addLoginInfo(Login login);

    public Login getLoginByName(String user_name);
}
