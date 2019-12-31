package com.fzc.service;

import com.fzc.dao.ArticleDAO;
import com.fzc.dao.FriendCmfzDAO;
import com.fzc.dao.UserDAO;
import com.fzc.entity.Article;
import com.fzc.entity.FriendCmfz;
import com.fzc.entity.User;
import com.fzc.entity.UserCmfz;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

/**
 * @ClassName UserServiceImpl
 * @Description
 * @Author
 * @Date 2019-12-20 14:45
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private FriendCmfzDAO friendCmfzDAO;
    @Autowired
    private ArticleDAO articleDAO;

    @Override
    public Map<String, Object> login(User user, HttpServletRequest request) {
        User user1 = userDAO.selectOne(user);
        if (user1 == null) throw new RuntimeException("用户名或密码错误");
        Map<String, Object> map = new HashMap<>();
        map.put("password", user1.getPassword());
        map.put("farmington", user1.getC_dharma());
        map.put("uid", user1.getC_id());
        map.put("nickname", user1.getUsername());
        map.put("gender", user1.getC_sex());
        map.put("phone", user1.getC_phone());
        map.put("location", user1.getC_city());
        map.put("province", user1.getC_province());
        map.put("description", user1.getC_sign());

        //获取图片全路径
        String scheme = request.getScheme();//协议
        InetAddress localHost = null;//获取ip
        try {
            localHost = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String s = localHost.toString();
        String ip = s.split("/")[1];    //截取ip
        int serverPort = request.getServerPort();   //获取端口号 8585
        String contextPath = request.getContextPath();  //获取项目名cmfz
        String url = scheme + "://" + ip + ":" + serverPort + contextPath + "/article/img/" + user1.getC_photo();
        map.put("photo", url);
        /*UserCmfz userCmfz = new UserCmfz();
        userCmfz.setCity(user1.getC_city());
        userCmfz.setDescription(user1.getC_sign());*/
//        userCmfz.setFarmington(user1.getC_dharma());
//        userCmfz.setGender(user1.getC_sex());
//        userCmfz.setLocaltion(user1.getC_city());
//        userCmfz.setCity(user1.getC_city());
//        userCmfz.setNickname(user1.getUsername());
//        userCmfz.setUid(user1.getC_id());
//        userCmfz.setPassword(user1.getPassword());
//        userCmfz.setPhone(user1.getC_phone());
        //获取图片全路径
        /*String scheme = request.getScheme();//协议
        InetAddress localHost = null;//获取ip
        try {
            localHost = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String s = localHost.toString();
        String ip = s.split("/")[1];    //截取ip
        int serverPort = request.getServerPort();   //获取端口号 8585
        String contextPath = request.getContextPath();  //获取项目名cmfz
        String url=scheme+"://"+ip+":"+serverPort+contextPath+"/article/img/"+user1.getC_photo();
        userCmfz.setPhoto(url);*/
        return map;
    }

    @Override
    public Map<String, Object> regist(User user) {
        User user1 = new User();
        user1.setC_phone(user.getC_phone());
        User user2 = userDAO.selectOne(user1);
        if (user2 != null) throw new RuntimeException("该号码已存在");
        String id = UUID.randomUUID().toString();
        user.setC_id(id);
        user.setC_status("激活");
        user.setC_create_date(new Date());
        userDAO.insertSelective(user);
        Map<String, Object> map = new HashMap<>();
        map.put("password", user.getPassword());
        map.put("uid", id);
        map.put("phone", user.getC_phone());
        return map;
    }

    @Override
    public Map<String, Object> update(User user) {
        HashMap<String, Object> map = new HashMap<>();
        int i = userDAO.updateByPrimaryKeySelective(user);
        if (i == 1) {
            User user1 = userDAO.selectOne(user);
            System.out.println("修改之后" + user1);
            map.put("password", user1.getPassword());
            map.put("farmington", user1.getC_dharma());
            map.put("uid", user1.getC_id());
            map.put("nickname", user1.getUsername());
            map.put("gender", user1.getC_sex());
            map.put("phone", user1.getC_phone());
            map.put("location", user1.getC_city());
            map.put("province", user1.getC_province());
            map.put("description", user1.getC_sign());
            return map;
        } else {
            map.put("error", "-200");
            map.put("error_msg", "该手机号码已存在");
            return map;
        }

    }

    @Override
    public List<UserCmfz> showAllFriend(User user, HttpServletRequest request) {
        FriendCmfz friendCmfz = new FriendCmfz();
        friendCmfz.setC_id(user.getC_id());
        List<FriendCmfz> friendCmfzs = friendCmfzDAO.select(friendCmfz);
        List<UserCmfz> users = new ArrayList<>();
        for (FriendCmfz cmfz : friendCmfzs) {
            User usersearch = new User();
            System.out.println("cmfz" + cmfz);
            usersearch.setC_id(cmfz.getMy_id());
            User user1 = userDAO.selectOne(usersearch);


            UserCmfz userCmfz = new UserCmfz();
            userCmfz.setCity(user1.getC_city());
            userCmfz.setDescription(user1.getC_sign());
            userCmfz.setFarmington(user1.getC_dharma());
            userCmfz.setGender(user1.getC_sex());
            userCmfz.setLocaltion(user1.getC_city());
            userCmfz.setCity(user1.getC_city());
            userCmfz.setNickname(user1.getUsername());
            userCmfz.setUid(user1.getC_id());
            userCmfz.setPassword(user1.getPassword());
            userCmfz.setPhone(user1.getC_phone());
            //获取图片全路径
            String scheme = request.getScheme();//协议
            InetAddress localHost = null;//获取ip
            try {
                localHost = InetAddress.getLocalHost();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            String s = localHost.toString();
            String ip = s.split("/")[1];    //截取ip
            int serverPort = request.getServerPort();   //获取端口号 8585
            String contextPath = request.getContextPath();  //获取项目名cmfz
            String url = scheme + "://" + ip + ":" + serverPort + contextPath + "/article/img/" + user1.getC_photo();
            userCmfz.setPhoto(url);
            users.add(userCmfz);
        }
        return users;
    }

    @Override
    public List<Article> showAllArticleByUp(User user) {
        User user1 = userDAO.selectOne(user);
        Article article = new Article();
        article.setU_id(user1.getU_id());
        List<Article> articles = articleDAO.select(article);

        return articles;
    }

    @Override
    public Map<String, Object> showAllUser(Integer page, Integer rows) {
        Map<String, Object> map = new HashMap<>();
        User user = new User();
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        List<User> users = userDAO.selectByRowBounds(user, rowBounds);
        if (users.size() == 0 && page > 1) {
            page--;
            rowBounds = new RowBounds((page - 1) * rows, rows);
            users = userDAO.selectByRowBounds(user, rowBounds);
        }
        int records = userDAO.selectCount(user);
        int total = records % rows == 0 ? records / rows : records / rows + 1;
        map.put("rows", users);
        map.put("page", page);
        map.put("total", total);
        map.put("records", records);
        return map;
    }

    @Override
    public void updateUser(User user) {
        int i = userDAO.updateByPrimaryKeySelective(user);
        if (i == 0) {
            throw new RuntimeException("用户状态未修改");
        }
    }

    @Override
    public List<User> showAll() {
        List<User> users = userDAO.selectAll();
        return users;
    }

    @Override
    public List<Integer> showGirlCount() {
        List<Integer> list = new ArrayList<>();
        Integer first = userDAO.queryGirlPerviousFirst();
        Integer second = userDAO.queryGirlPerviousSecond();
        Integer third = userDAO.queryGirlPerviousThird();
        list.add(first);
        list.add(second);
        list.add(third);
        return list;
    }

    @Override
    public List<Integer> showBoyCount() {
        List<Integer> list = new ArrayList<>();
        Integer first = userDAO.queryBoyPerviousFirst();
        Integer second = userDAO.queryBoyPerviousSecond();
        Integer third = userDAO.queryBoyPerviousThird();
        list.add(first);
        list.add(second);
        list.add(third);
        return list;
    }
}
