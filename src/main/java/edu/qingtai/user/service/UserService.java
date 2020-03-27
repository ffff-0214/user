package edu.qingtai.user.service;

import net.sf.json.JSONObject;

public interface UserService {
    String saveUser(JSONObject userInfo, String userName, String userImage);

    void test();
}
