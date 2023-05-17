// 
// 
// 

package com.edu.controller.admin;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.awt.image.RenderedImage;
import javax.imageio.ImageIO;
import com.edu.util.CpachaUtil;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Iterator;
import com.edu.entity.admin.Role;
import com.edu.entity.admin.Authority;
import org.apache.commons.lang.StringUtils;
import java.util.HashMap;
import java.util.Map;
import com.edu.entity.admin.User;
import org.springframework.web.bind.annotation.RequestMethod;
import com.edu.entity.admin.Menu;
import com.edu.util.MenuUtil;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.edu.service.admin.LogService;
import com.edu.service.admin.MenuService;
import com.edu.service.admin.AuthorityService;
import com.edu.service.admin.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import com.edu.service.admin.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping({ "/system" })
public class SystemController
{
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private AuthorityService authorityService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private LogService logService;
    
    @RequestMapping(value = { "/index" }, method = { RequestMethod.GET })
    public ModelAndView index(final ModelAndView model, final HttpServletRequest request) {
        final List<Menu> userMenus = (List<Menu>)request.getSession().getAttribute("userMenus");
        model.addObject("topMenuList", (Object)MenuUtil.getAllTopMenu(userMenus));
        model.addObject("secondMenuList", (Object)MenuUtil.getAllSecondMenu(userMenus));
        model.setViewName("system/index");
        return model;
    }
    
    @RequestMapping(value = { "/welcome" }, method = { RequestMethod.GET })
    public ModelAndView welcome(final ModelAndView model) {
        model.setViewName("system/welcome");
        return model;
    }
    
    @RequestMapping(value = { "/login" }, method = { RequestMethod.GET })
    public ModelAndView login(final ModelAndView model) {
        model.setViewName("system/login");
        return model;
    }
    
    @RequestMapping(value = { "/login" }, method = { RequestMethod.POST })
    @ResponseBody
    public Map<String, String> loginAct(final User user, final String cpacha, final HttpServletRequest request) {
        final Map<String, String> ret = new HashMap<String, String>();
        if (user == null) {
            ret.put("type", "error");
            ret.put("msg", "\u8bf7\u586b\u5199\u7528\u6237\u4fe1\u606f\uff01");
            return ret;
        }
        if (StringUtils.isEmpty(cpacha)) {
            ret.put("type", "error");
            ret.put("msg", "\u8bf7\u586b\u5199\u9a8c\u8bc1\u7801\uff01");
            return ret;
        }
        if (StringUtils.isEmpty(user.getUsername())) {
            ret.put("type", "error");
            ret.put("msg", "\u8bf7\u586b\u5199\u7528\u6237\u540d\uff01");
            return ret;
        }
        if (StringUtils.isEmpty(user.getPassword())) {
            ret.put("type", "error");
            ret.put("msg", "\u8bf7\u586b\u5199\u5bc6\u7801\uff01");
            return ret;
        }
        final Object loginCpacha = request.getSession().getAttribute("loginCpacha");
        if (loginCpacha == null) {
            ret.put("type", "error");
            ret.put("msg", "\u4f1a\u8bdd\u8d85\u65f6\uff0c\u8bf7\u5237\u65b0\u9875\u9762\uff01");
            return ret;
        }
        if (!cpacha.toUpperCase().equals(loginCpacha.toString().toUpperCase())) {
            ret.put("type", "error");
            ret.put("msg", "\u9a8c\u8bc1\u7801\u9519\u8bef\uff01");
            this.logService.add("\u7528\u6237\u540d\u4e3a" + user.getUsername() + "\u7684\u7528\u6237\u767b\u5f55\u65f6\u8f93\u5165\u9a8c\u8bc1\u7801\u9519\u8bef!");
            return ret;
        }
        final User findByUsername = this.userService.findByUsername(user.getUsername());
        if (findByUsername == null) {
            ret.put("type", "error");
            ret.put("msg", "\u8be5\u7528\u6237\u540d\u4e0d\u5b58\u5728\uff01");
            this.logService.add("\u767b\u5f55\u65f6\uff0c\u7528\u6237\u540d\u4e3a" + user.getUsername() + "\u7684\u7528\u6237\u4e0d\u5b58\u5728!");
            return ret;
        }
        if (!user.getPassword().equals(findByUsername.getPassword())) {
            ret.put("type", "error");
            ret.put("msg", "\u5bc6\u7801\u9519\u8bef\uff01");
            this.logService.add("\u7528\u6237\u540d\u4e3a" + user.getUsername() + "\u7684\u7528\u6237\u767b\u5f55\u65f6\u8f93\u5165\u5bc6\u7801\u9519\u8bef!");
            return ret;
        }
        final Role role = this.roleService.find(findByUsername.getRoleId());
        final List<Authority> authorityList = this.authorityService.findListByRoleId(role.getId());
        String menuIds = "";
        for (final Authority authority : authorityList) {
            menuIds = String.valueOf(menuIds) + authority.getMenuId() + ",";
        }
        if (!StringUtils.isEmpty(menuIds)) {
            menuIds = menuIds.substring(0, menuIds.length() - 1);
        }
        final List<Menu> userMenus = this.menuService.findListByIds(menuIds);
        request.getSession().setAttribute("admin", (Object)findByUsername);
        request.getSession().setAttribute("role", (Object)role);
        request.getSession().setAttribute("userMenus", (Object)userMenus);
        ret.put("type", "success");
        ret.put("msg", "\u767b\u5f55\u6210\u529f\uff01");
        this.logService.add("\u7528\u6237\u540d\u4e3a{" + user.getUsername() + "}\uff0c\u89d2\u8272\u4e3a{" + role.getName() + "}\u7684\u7528\u6237\u767b\u5f55\u6210\u529f!");
        return ret;
    }
    
    @RequestMapping(value = { "/logout" }, method = { RequestMethod.GET })
    public String logout(final HttpServletRequest request) {
        final HttpSession session = request.getSession();
        session.setAttribute("admin", (Object)null);
        session.setAttribute("role", (Object)null);
        request.getSession().setAttribute("userMenus", (Object)null);
        return "redirect:login";
    }
    
    @RequestMapping(value = { "/edit_password" }, method = { RequestMethod.GET })
    public ModelAndView editPassword(final ModelAndView model) {
        model.setViewName("system/edit_password");
        return model;
    }
    
    @RequestMapping(value = { "/edit_password" }, method = { RequestMethod.POST })
    @ResponseBody
    public Map<String, String> editPasswordAct(final String newpassword, final String oldpassword, final HttpServletRequest request) {
        final Map<String, String> ret = new HashMap<String, String>();
        if (StringUtils.isEmpty(newpassword)) {
            ret.put("type", "error");
            ret.put("msg", "\u8bf7\u586b\u5199\u65b0\u5bc6\u7801\uff01");
            return ret;
        }
        final User user = (User)request.getSession().getAttribute("admin");
        if (!user.getPassword().equals(oldpassword)) {
            ret.put("type", "error");
            ret.put("msg", "\u539f\u5bc6\u7801\u9519\u8bef\uff01");
            return ret;
        }
        user.setPassword(newpassword);
        if (this.userService.editPassword(user) <= 0) {
            ret.put("type", "error");
            ret.put("msg", "\u5bc6\u7801\u4fee\u6539\u5931\u8d25\uff0c\u8bf7\u8054\u7cfb\u7ba1\u7406\u5458\uff01");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "\u5bc6\u7801\u4fee\u6539\u6210\u529f\uff01");
        this.logService.add("\u7528\u6237\u540d\u4e3a{" + user.getUsername() + "}\uff0c\u7684\u7528\u6237\u6210\u529f\u4fee\u6539\u5bc6\u7801!");
        return ret;
    }
    
    @RequestMapping(value = { "/get_cpacha" }, method = { RequestMethod.GET })
    public void generateCpacha(@RequestParam(name = "vl", required = false, defaultValue = "4") final Integer vcodeLen, @RequestParam(name = "w", required = false, defaultValue = "100") final Integer width, @RequestParam(name = "h", required = false, defaultValue = "30") final Integer height, @RequestParam(name = "type", required = true, defaultValue = "loginCpacha") final String cpachaType, final HttpServletRequest request, final HttpServletResponse response) {
        final CpachaUtil cpachaUtil = new CpachaUtil(vcodeLen, width, height);
        final String generatorVCode = cpachaUtil.generatorVCode();
        request.getSession().setAttribute(cpachaType, (Object)generatorVCode);
        final BufferedImage generatorRotateVCodeImage = cpachaUtil.generatorRotateVCodeImage(generatorVCode, true);
        try {
            ImageIO.write(generatorRotateVCodeImage, "gif", (OutputStream)response.getOutputStream());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
