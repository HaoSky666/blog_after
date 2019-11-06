package hao.you.enums;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum LoginCheckEnum {
    ACCOUNT_VERIFICATION(1,"账号或密码不能为空！"),
    USER_EXIST(2,"用户已存在！"),
    REGISTERED_SUCCESS(3,"注册成功！"),
    ACCOUNT_NOT_EXIST(4,"账户不存在！"),
    PASSWORD_MISTAKE(5,"密码错误！"),
    LOGIN_SUCCESS(6,"登录成功");

    private static Map<Integer, String> typeAndNameMap = Collections.synchronizedMap(new HashMap<Integer, String>());

    private final int type;
    private final String name;

    LoginCheckEnum(int type,String name){
        this.type=type;
        this.name=name;
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public static Map<Integer, String> getMethodNameMap() {
        if (typeAndNameMap.size() == 0) {
            for (LoginCheckEnum bizType : LoginCheckEnum.values()) {
                typeAndNameMap.put(bizType.type, bizType.name);
            }
        }
        return typeAndNameMap;
    }

}
