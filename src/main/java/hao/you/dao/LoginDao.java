package hao.you.dao;

import hao.you.domain.Login;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginDao {
    @Insert("insert into login(user_id, user_name, login_status, login_time) values(#{userId}, #{userName}, #{status}, #{createTime})")
    public int addLoginInfo(Login login);

    @Select("select * from login where user_name = #{user_name}")
    public Login getLoginByName(String user_name);
}
