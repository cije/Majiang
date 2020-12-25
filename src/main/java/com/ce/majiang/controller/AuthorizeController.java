package com.ce.majiang.controller;

import com.ce.majiang.dto.AccessTokenDTO;
import com.ce.majiang.dto.GithubUser;
import com.ce.majiang.model.User;
import com.ce.majiang.provider.GithubProvider;
import com.ce.majiang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author c__e
 * @date 2020/12/23 16:26
 */
@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Autowired
    private UserService userService;

    @Value("${github.client_id}")
    private String client_id;

    @Value("${github.client_secret}")
    private String client_secret;

    @Value("${github.redirect_uri}")
    private String redirect_uri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletResponse response) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO()
                .setClient_id(client_id)
                .setClient_secret(client_secret)
                .setState(state)
                .setCode(code)
                .setRedirect_uri(redirect_uri);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        if (!ObjectUtils.isEmpty(githubUser)) {
            String token = UUID.randomUUID().toString();
            User user = new User()
                    .setName(githubUser.getName())
                    .setToken(token)
                    .setAccountId(String.valueOf(githubUser.getId()))
                    .setGmtCreated(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreated());
            response.addCookie(new Cookie("token", token));
            userService.save(user);
        }
        return "redirect:/";
    }
}
