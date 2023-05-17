// 
// 
// 

package com.edu.controller.admin;

import java.util.Date;
import java.io.File;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.lang.StringUtils;
import com.edu.entity.admin.User;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import com.edu.page.admin.Page;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.Map;
import java.util.HashMap;
import org.springframework.web.servlet.ModelAndView;
import com.edu.service.admin.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import com.edu.service.admin.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping({ "/admin/user" })
@Controller
public class UserController
{
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    
    @RequestMapping(value = { "/list" }, method = { RequestMethod.GET })
    public ModelAndView list(final ModelAndView model) {
        final Map<String, Object> queryMap = new HashMap<String, Object>();
        model.addObject("roleList", (Object)this.roleService.findList(queryMap));
        model.setViewName("user/list");
        return model;
    }
    
    @RequestMapping(value = { "/list" }, method = { RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getList(final Page page, @RequestParam(name = "username", required = false, defaultValue = "") final String username, @RequestParam(name = "roleId", required = false) final Long roleId, @RequestParam(name = "sex", required = false) final Integer sex) {
        final Map<String, Object> ret = new HashMap<String, Object>();
        final Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("username", username);
        queryMap.put("roleId", roleId);
        queryMap.put("sex", sex);
        queryMap.put("offset", page.getOffset());
        queryMap.put("pageSize", page.getRows());
        ret.put("rows", this.userService.findList(queryMap));
        ret.put("total", this.userService.getTotal(queryMap));
        return ret;
    }
    
    @RequestMapping(value = { "/add" }, method = { RequestMethod.POST })
    @ResponseBody
    public Map<String, String> add(final User user) {
        final Map<String, String> ret = new HashMap<String, String>();
        if (user == null) {
            ret.put("type", "error");
            ret.put("msg", "\u8bf7\u586b\u5199\u6b63\u786e\u7684\u7528\u6237\u4fe1\u606f\uff01");
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
        if (user.getRoleId() == null) {
            ret.put("type", "error");
            ret.put("msg", "\u8bf7\u9009\u62e9\u6240\u5c5e\u89d2\u8272\uff01");
            return ret;
        }
        if (this.isExist(user.getUsername(), 0L)) {
            ret.put("type", "error");
            ret.put("msg", "\u8be5\u7528\u6237\u540d\u5df2\u7ecf\u5b58\u5728\uff0c\u8bf7\u91cd\u65b0\u8f93\u5165\uff01");
            return ret;
        }
        if (this.userService.add(user) <= 0) {
            ret.put("type", "error");
            ret.put("msg", "\u7528\u6237\u6dfb\u52a0\u5931\u8d25\uff0c\u8bf7\u8054\u7cfb\u7ba1\u7406\u5458\uff01");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "\u89d2\u8272\u6dfb\u52a0\u6210\u529f\uff01");
        return ret;
    }
    
    @RequestMapping(value = { "/edit" }, method = { RequestMethod.POST })
    @ResponseBody
    public Map<String, String> edit(final User user) {
        final Map<String, String> ret = new HashMap<String, String>();
        if (user == null) {
            ret.put("type", "error");
            ret.put("msg", "\u8bf7\u586b\u5199\u6b63\u786e\u7684\u7528\u6237\u4fe1\u606f\uff01");
            return ret;
        }
        if (StringUtils.isEmpty(user.getUsername())) {
            ret.put("type", "error");
            ret.put("msg", "\u8bf7\u586b\u5199\u7528\u6237\u540d\uff01");
            return ret;
        }
        if (user.getRoleId() == null) {
            ret.put("type", "error");
            ret.put("msg", "\u8bf7\u9009\u62e9\u6240\u5c5e\u89d2\u8272\uff01");
            return ret;
        }
        if (this.isExist(user.getUsername(), user.getId())) {
            ret.put("type", "error");
            ret.put("msg", "\u8be5\u7528\u6237\u540d\u5df2\u7ecf\u5b58\u5728\uff0c\u8bf7\u91cd\u65b0\u8f93\u5165\uff01");
            return ret;
        }
        if (this.userService.edit(user) <= 0) {
            ret.put("type", "error");
            ret.put("msg", "\u7528\u6237\u6dfb\u52a0\u5931\u8d25\uff0c\u8bf7\u8054\u7cfb\u7ba1\u7406\u5458\uff01");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "\u89d2\u8272\u6dfb\u52a0\u6210\u529f\uff01");
        return ret;
    }
    
    @RequestMapping(value = { "/delete" }, method = { RequestMethod.POST })
    @ResponseBody
    public Map<String, String> delete(String ids) {
        final Map<String, String> ret = new HashMap<String, String>();
        if (StringUtils.isEmpty(ids)) {
            ret.put("type", "error");
            ret.put("msg", "\u9009\u62e9\u8981\u5220\u9664\u7684\u6570\u636e\uff01");
            return ret;
        }
        if (ids.contains(",")) {
            ids = ids.substring(0, ids.length() - 1);
        }
        if (this.userService.delete(ids) <= 0) {
            ret.put("type", "error");
            ret.put("msg", "\u7528\u6237\u5220\u9664\u5931\u8d25\uff0c\u8bf7\u8054\u7cfb\u7ba1\u7406\u5458\uff01");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "\u7528\u6237\u5220\u9664\u6210\u529f\uff01");
        return ret;
    }
    
    @RequestMapping(value = { "/upload_photo" }, method = { RequestMethod.POST })
    @ResponseBody
    public Map<String, String> uploadPhoto(final MultipartFile photo, final HttpServletRequest request) {
        final Map<String, String> ret = new HashMap<String, String>();
        if (photo == null) {
            ret.put("type", "error");
            ret.put("msg", "\u9009\u62e9\u8981\u4e0a\u4f20\u7684\u6587\u4ef6\uff01");
            return ret;
        }
        if (photo.getSize() > 1073741824L) {
            ret.put("type", "error");
            ret.put("msg", "\u6587\u4ef6\u5927\u5c0f\u4e0d\u80fd\u8d85\u8fc710M\uff01");
            return ret;
        }
        final String suffix = photo.getOriginalFilename().substring(photo.getOriginalFilename().lastIndexOf(".") + 1, photo.getOriginalFilename().length());
        if (!"jpg,jpeg,gif,png".toUpperCase().contains(suffix.toUpperCase())) {
            ret.put("type", "error");
            ret.put("msg", "\u8bf7\u9009\u62e9jpg,jpeg,gif,png\u683c\u5f0f\u7684\u56fe\u7247\uff01");
            return ret;
        }
        final String savePath = String.valueOf(request.getServletContext().getRealPath("/")) + "/resources/upload/";
        final File savePathFile = new File(savePath);
        if (!savePathFile.exists()) {
            savePathFile.mkdir();
        }
        final String filename = String.valueOf(new Date().getTime()) + "." + suffix;
        try {
            photo.transferTo(new File(String.valueOf(savePath) + filename));
        }
        catch (Exception e) {
            ret.put("type", "error");
            ret.put("msg", "\u4fdd\u5b58\u6587\u4ef6\u5f02\u5e38\uff01");
            e.printStackTrace();
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "\u7528\u6237\u5220\u9664\u6210\u529f\uff01");
        ret.put("filepath", String.valueOf(request.getServletContext().getContextPath()) + "/resources/upload/" + filename);
        return ret;
    }
    
    private boolean isExist(final String username, final Long id) {
        final User user = this.userService.findByUsername(username);
        return user != null && user.getId() != (long)id;
    }
}
