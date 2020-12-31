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
    private String clientId;

    @Value("${github.client_secret}")
    private String clientSecret;

    @Value("${github.redirect_uri}")
    private String redirectUri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletResponse response) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO()
                .setClient_id(clientId)
                .setClient_secret(clientSecret)
                .setState(state)
                .setCode(code)
                .setRedirect_uri(redirectUri);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        System.out.println(githubUser.toString());
        if (ObjectUtils.isEmpty(githubUser) || ObjectUtils.isEmpty(githubUser.getId())) {
            return "redirect:/";
        }
        String token = UUID.randomUUID().toString();
        User user = new User()
                .setName(githubUser.getName())
                .setToken(token)
                .setBio(githubUser.getBio())
                .setAvatarUrl(githubUser.getAvatar_url())
                .setAccountId(String.valueOf(githubUser.getId()))
                .setGmtCreated(System.currentTimeMillis());
        user.setGmtModified(user.getGmtCreated());
        response.addCookie(new Cookie("token", token));
        userService.saveOrUpdate(user);
        return "redirect:/";
    }
}
