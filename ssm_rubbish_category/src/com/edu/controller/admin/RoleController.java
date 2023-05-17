// 
// 
// 

package com.edu.controller.admin;

import com.edu.entity.admin.Authority;
import com.edu.entity.admin.Menu;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import com.edu.entity.admin.Role;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestParam;
import com.edu.page.admin.Page;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.edu.service.admin.MenuService;
import com.edu.service.admin.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import com.edu.service.admin.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping({ "/admin/role" })
@Controller
public class RoleController
{
    @Autowired
    private RoleService roleService;
    @Autowired
    private AuthorityService authorityService;
    @Autowired
    private MenuService menuService;
    
    @RequestMapping(value = { "/list" }, method = { RequestMethod.GET })
    public ModelAndView list(final ModelAndView model) {
        model.setViewName("/role/list");
        return model;
    }
    
    @RequestMapping(value = { "/list" }, method = { RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getList(final Page page, @RequestParam(name = "name", required = false, defaultValue = "") String name) {
        final Map<String, Object> ret = new HashMap<String, Object>();
        final Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("name", name);
        queryMap.put("offset", page.getOffset());
        queryMap.put("pageSize", page.getRows());
        ret.put("rows", this.roleService.findList(queryMap));
        ret.put("total", this.roleService.getTotal(queryMap));
        return ret;
    }
    
    @RequestMapping(value = { "/add" }, method = { RequestMethod.POST })
    @ResponseBody
    public Map<String, String> add(final Role role) {
        final Map<String, String> ret = new HashMap<String, String>();
        if (role == null) {
            ret.put("type", "error");
            ret.put("msg", "\u8bf7\u586b\u5199\u6b63\u786e\u7684\u89d2\u8272\u4fe1\u606f\uff01");
            return ret;
        }
        if (StringUtils.isEmpty(role.getName())) {
            ret.put("type", "error");
            ret.put("msg", "\u8bf7\u586b\u5199\u89d2\u8272\u540d\u79f0\uff01");
            return ret;
        }
        if (this.roleService.add(role) <= 0) {
            ret.put("type", "error");
            ret.put("msg", "\u89d2\u8272\u6dfb\u52a0\u5931\u8d25\uff0c\u8bf7\u8054\u7cfb\u7ba1\u7406\u5458\uff01");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "\u89d2\u8272\u6dfb\u52a0\u6210\u529f\uff01");
        return ret;
    }
    
    @RequestMapping(value = { "/edit" }, method = { RequestMethod.POST })
    @ResponseBody
    public Map<String, String> edit(final Role role) {
        final Map<String, String> ret = new HashMap<String, String>();
        if (role == null) {
            ret.put("type", "error");
            ret.put("msg", "\u8bf7\u586b\u5199\u6b63\u786e\u7684\u89d2\u8272\u4fe1\u606f\uff01");
            return ret;
        }
        if (StringUtils.isEmpty(role.getName())) {
            ret.put("type", "error");
            ret.put("msg", "\u8bf7\u586b\u5199\u89d2\u8272\u540d\u79f0\uff01");
            return ret;
        }
        if (this.roleService.edit(role) <= 0) {
            ret.put("type", "error");
            ret.put("msg", "\u89d2\u8272\u4fee\u6539\u5931\u8d25\uff0c\u8bf7\u8054\u7cfb\u7ba1\u7406\u5458\uff01");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "\u89d2\u8272\u4fee\u6539\u6210\u529f\uff01");
        return ret;
    }
    
    @RequestMapping(value = { "/delete" }, method = { RequestMethod.POST })
    @ResponseBody
    public Map<String, String> delete(final Long id) {
        final Map<String, String> ret = new HashMap<String, String>();
        if (id == null) {
            ret.put("type", "error");
            ret.put("msg", "\u8bf7\u9009\u62e9\u8981\u5220\u9664\u7684\u89d2\u8272\uff01");
            return ret;
        }
        try {
            if (this.roleService.delete(id) <= 0) {
                ret.put("type", "error");
                ret.put("msg", "\u5220\u9664\u5931\u8d25\uff0c\u8bf7\u8054\u7cfb\u7ba1\u7406\u5458\uff01");
                return ret;
            }
        }
        catch (Exception e) {
            ret.put("type", "error");
            ret.put("msg", "\u8be5\u89d2\u8272\u4e0b\u5b58\u5728\u6743\u9650\u6216\u8005\u7528\u6237\u4fe1\u606f\uff0c\u4e0d\u80fd\u5220\u9664\uff01");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "\u89d2\u8272\u5220\u9664\u6210\u529f\uff01");
        return ret;
    }
    
    @RequestMapping(value = { "/get_all_menu" }, method = { RequestMethod.POST })
    @ResponseBody
    public List<Menu> getAllMenu() {
        final Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("offset", 0);
        queryMap.put("pageSize", 99999);
        return this.menuService.findList(queryMap);
    }
    
    @RequestMapping(value = { "/add_authority" }, method = { RequestMethod.POST })
    @ResponseBody
    public Map<String, String> addAuthority(@RequestParam(name = "ids", required = true) String ids, @RequestParam(name = "roleId", required = true) final Long roleId) {
        final Map<String, String> ret = new HashMap<String, String>();
        if (StringUtils.isEmpty(ids)) {
            ret.put("type", "error");
            ret.put("msg", "\u8bf7\u9009\u62e9\u76f8\u5e94\u7684\u6743\u9650\uff01");
            return ret;
        }
        if (roleId == null) {
            ret.put("type", "error");
            ret.put("msg", "\u8bf7\u9009\u62e9\u76f8\u5e94\u7684\u89d2\u8272\uff01");
            return ret;
        }
        if (ids.contains(",")) {
            ids = ids.substring(0, ids.length() - 1);
        }
        final String[] idArr = ids.split(",");
        if (idArr.length > 0) {
            this.authorityService.deleteByRoleId(roleId);
        }
        String[] array;
        for (int length = (array = idArr).length, i = 0; i < length; ++i) {
            final String id = array[i];
            final Authority authority = new Authority();
            authority.setMenuId(Long.valueOf(id));
            authority.setRoleId(roleId);
            this.authorityService.add(authority);
        }
        ret.put("type", "success");
        ret.put("msg", "\u6743\u9650\u7f16\u8f91\u6210\u529f\uff01");
        return ret;
    }
    
    @RequestMapping(value = { "/get_role_authority" }, method = { RequestMethod.POST })
    @ResponseBody
    public List<Authority> getRoleAuthority(@RequestParam(name = "roleId", required = true) final Long roleId) {
        return this.authorityService.findListByRoleId(roleId);
    }
}
