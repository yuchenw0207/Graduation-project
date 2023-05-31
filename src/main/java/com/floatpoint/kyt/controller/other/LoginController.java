package com.floatpoint.kyt.controller.other;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.floatpoint.kyt.common.result.Result;
import com.floatpoint.kyt.common.result.ResultCode;
import com.floatpoint.kyt.common.utils.WxUtil;
import com.floatpoint.kyt.entity.dataobject.User;
import com.floatpoint.kyt.entity.params.add.UserAddPA;
import com.floatpoint.kyt.security.validate.ThirdLoginAuthenticationToken;
import com.floatpoint.kyt.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Api(description = "登录")
@RestController
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;

    @PostMapping("/wxLogin")
    @ResponseBody
    public Result login(String code, HttpServletRequest request) {
        String openId = WxUtil.getOpenId(code);
        User user = userService.getById(openId);
        System.out.println(openId+"******************");
        System.out.println(code+"******************");
        if(user == null){
            // 发起访客注册
            UserAddPA addVO = new UserAddPA();
            addVO.setUserId(openId);
            boolean add = userService.add(addVO);
            if(!add){
                return Result.fail(ResultCode.USER_NOT_LOGIN);
            }
            user = userService.getById(openId);
        }
        // 注入框架
        ThirdLoginAuthenticationToken token = new ThirdLoginAuthenticationToken(openId);
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String JSessionId = request.getSession().getId();
        Map<String,Object> map = new HashMap<>();
        map.put("sessionId",JSessionId);
        map.put("roleId",user.getRoleId().getValue());
        return Result.success(map);
    }

    @GetMapping("/webLogin")
    public Result webLogin(){
        ThirdLoginAuthenticationToken token = new ThirdLoginAuthenticationToken("123456");
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return Result.success();
    }

}
