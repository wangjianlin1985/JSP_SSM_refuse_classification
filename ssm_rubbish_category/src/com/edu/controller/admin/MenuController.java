// 
// 
// 

package com.edu.controller.admin;

import org.apache.commons.lang.StringUtils;
import java.util.ArrayList;
import java.io.File;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ResponseBody;
import com.edu.entity.admin.Menu;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestParam;
import com.edu.page.admin.Page;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import com.edu.service.admin.MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping({ "/admin/menu" })
@Controller
public class MenuController
{
    @Autowired
    private MenuService menuService;
    
    @RequestMapping(value = { "/list" }, method = { RequestMethod.GET })
    public ModelAndView list(final ModelAndView model) {
        model.addObject("topList", (Object)this.menuService.findTopList());
        model.setViewName("menu/list");
        return model;
    }
    
    @RequestMapping(value = { "/list" }, method = { RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getMenuList(final Page page, @RequestParam(name = "name", required = false, defaultValue = "") String name) {
        final Map<String, Object> ret = new HashMap<String, Object>();
        final Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("name", name);
        final List<Menu> findList = this.menuService.findList(queryMap);
        ret.put("rows", findList);
        ret.put("total", findList.size());
        return ret;
    }
    
    @RequestMapping(value = { "/get_icons" }, method = { RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getIconList(final HttpServletRequest request) {
        final Map<String, Object> ret = new HashMap<String, Object>();
        final String realPath = request.getServletContext().getRealPath("/");
        final File file = new File(String.valueOf(realPath) + "\\resources\\admin\\easyui\\css\\icons");
        final List<String> icons = new ArrayList<String>();
        if (!file.exists()) {
            ret.put("type", "error");
            ret.put("msg", "\u6587\u4ef6\u76ee\u5f55\u4e0d\u5b58\u5728\uff01");
            return ret;
        }
        final File[] listFiles = file.listFiles();
        File[] array;
        for (int length = (array = listFiles).length, i = 0; i < length; ++i) {
            final File f = array[i];
            if (f != null && f.getName().contains("png")) {
                icons.add("icon-" + f.getName().substring(0, f.getName().indexOf(".")).replace("_", "-"));
            }
        }
        ret.put("type", "success");
        ret.put("content", icons);
        return ret;
    }
    
    @RequestMapping(value = { "/add" }, method = { RequestMethod.POST })
    @ResponseBody
    public Map<String, String> add(final Menu menu) {
        final Map<String, String> ret = new HashMap<String, String>();
        if (menu == null) {
            ret.put("type", "error");
            ret.put("msg", "\u8bf7\u586b\u5199\u6b63\u786e\u7684\u83dc\u5355\u4fe1\u606f!");
            return ret;
        }
        if (StringUtils.isEmpty(menu.getName())) {
            ret.put("type", "error");
            ret.put("msg", "\u8bf7\u586b\u5199\u83dc\u5355\u540d\u79f0!");
            return ret;
        }
        if (StringUtils.isEmpty(menu.getIcon())) {
            ret.put("type", "error");
            ret.put("msg", "\u8bf7\u586b\u5199\u83dc\u5355\u56fe\u6807\u7c7b!");
            return ret;
        }
        if (menu.getParentId() == null) {
            menu.setParentId(0L);
        }
        if (this.menuService.add(menu) <= 0) {
            ret.put("type", "error");
            ret.put("msg", "\u6dfb\u52a0\u5931\u8d25\uff0c\u8bf7\u8054\u7cfb\u7ba1\u7406\u5458!");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "\u6dfb\u52a0\u6210\u529f!");
        return ret;
    }
    
    @RequestMapping(value = { "/edit" }, method = { RequestMethod.POST })
    @ResponseBody
    public Map<String, String> edit(final Menu menu) {
        final Map<String, String> ret = new HashMap<String, String>();
        if (menu == null) {
            ret.put("type", "error");
            ret.put("msg", "\u8bf7\u9009\u62e9\u6b63\u786e\u7684\u83dc\u5355\u4fe1\u606f!");
            return ret;
        }
        if (StringUtils.isEmpty(menu.getName())) {
            ret.put("type", "error");
            ret.put("msg", "\u8bf7\u586b\u5199\u83dc\u5355\u540d\u79f0!");
            return ret;
        }
        if (StringUtils.isEmpty(menu.getIcon())) {
            ret.put("type", "error");
            ret.put("msg", "\u8bf7\u586b\u5199\u83dc\u5355\u56fe\u6807\u7c7b!");
            return ret;
        }
        if (menu.getParentId() == null) {
            menu.setParentId(0L);
        }
        if (this.menuService.edit(menu) <= 0) {
            ret.put("type", "error");
            ret.put("msg", "\u4fee\u6539\u5931\u8d25\uff0c\u8bf7\u8054\u7cfb\u7ba1\u7406\u5458!");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "\u4fee\u6539\u6210\u529f!");
        return ret;
    }
    
    @RequestMapping(value = { "/delete" }, method = { RequestMethod.POST })
    @ResponseBody
    public Map<String, String> delete(@RequestParam(name = "id", required = true) final Long id) {
        final Map<String, String> ret = new HashMap<String, String>();
        if (id == null) {
            ret.put("type", "error");
            ret.put("msg", "\u8bf7\u9009\u62e9\u8981\u5220\u9664\u7684\u83dc\u5355\u4fe1\u606f!");
            return ret;
        }
        final List<Menu> findChildernList = this.menuService.findChildernList(id);
        if (findChildernList != null && findChildernList.size() > 0) {
            ret.put("type", "error");
            ret.put("msg", "\u8be5\u5206\u7c7b\u4e0b\u5b58\u5728\u5b50\u5206\u7c7b\uff0c\u4e0d\u80fd\u5220\u9664!");
            return ret;
        }
        if (this.menuService.delete(id) <= 0) {
            ret.put("type", "error");
            ret.put("msg", "\u5220\u9664\u5931\u8d25\uff0c\u8bf7\u8054\u7cfb\u7ba1\u7406\u5458!");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "\u5220\u9664\u6210\u529f!");
        return ret;
    }
}
