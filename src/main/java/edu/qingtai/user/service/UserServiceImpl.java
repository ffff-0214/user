package edu.qingtai.user.service;

import edu.qingtai.user.domain.User;
import edu.qingtai.user.mapper.UserMapper;
import edu.qingtai.user.util.RedisUtils;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService{
    private UserMapper userMapper;

    private RedisUtils redisUtils;

    @Autowired
    public UserServiceImpl(final UserMapper userMapper,
                           final RedisUtils redisUtils){
        this.userMapper = userMapper;
        this.redisUtils=redisUtils;
    }

    @Override
    public String saveUser(JSONObject userInfo, String userName, String userImage){
        String rd3session = DigestUtils.md5DigestAsHex((userInfo.getString("openid")+
                userInfo.getString("session_key")).getBytes());

        String openid = userInfo.getString("openid");

        //看redis
        if(redisUtils.get(rd3session) == null){
            redisUtils.set(rd3session, openid, 1, TimeUnit.DAYS);
            //看数据库
            if(userMapper.selectByPrimaryKey(openid) == null){
                User user = new User();
                user.setOpenid(openid);
                user.setUsername(userName);
                user.setUserimage(userImage);
                userMapper.insert(user);
            }

            return rd3session;
        }
        else{
            redisUtils.set(rd3session, openid, 1, TimeUnit.DAYS);
            return rd3session;
        }
    }

    @Override
    public void test(){
        redisUtils.set("yhf", "19031211368", 1, TimeUnit.DAYS);
        System.out.println(redisUtils.get("yhf"));
        System.out.println(redisUtils.get("yhf").getClass().toString());
//        if(redisUtils.get("sessionid") != null){
//            System.out.println("yhf");
//        }else{
//        System.out.println(redisUtils.get("sessionid").getClass().toString());}
    }
}
