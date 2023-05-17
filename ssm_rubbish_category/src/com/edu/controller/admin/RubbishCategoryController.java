// 
// 
// 

package com.edu.controller.admin;

import org.apache.commons.lang.StringUtils;
import com.edu.entity.admin.RubbishCategory;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestParam;
import com.edu.page.admin.Page;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import com.edu.service.admin.RubbishCategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping({ "/admin/rubbish_category" })
@Controller
public class RubbishCategoryController
{
    @Autowired
    private RubbishCategoryService rubbishCategoryService;
    
    @RequestMapping(value = { "/list" }, method = { RequestMethod.GET })
    public ModelAndView list(final ModelAndView model) {
        model.setViewName("rubbish_category/list");
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
        ret.put("rows", this.rubbishCategoryService.findList(queryMap));
        ret.put("total", this.rubbishCategoryService.getTotal(queryMap));
        return ret;
    }
    
    @RequestMapping(value = { "/add" }, method = { RequestMethod.POST })
    @ResponseBody
    public Map<String, String> add(final RubbishCategory rubbishCategory) {
        final Map<String, String> ret = new HashMap<String, String>();
        if (rubbishCategory == null) {
            ret.put("type", "error");
            ret.put("msg", "\u8bf7\u586b\u5199\u6b63\u786e\u7684\u5783\u573e\u5206\u7c7b\u4fe1\u606f\uff01");
            return ret;
        }
        if (StringUtils.isEmpty(rubbishCategory.getName())) {
            ret.put("type", "error");
            ret.put("msg", "\u8bf7\u586b\u5199\u5783\u573e\u5206\u7c7b\u540d\uff01");
            return ret;
        }
        if (this.isExist(rubbishCategory.getName(), 0L)) {
            ret.put("type", "error");
            ret.put("msg", "\u8be5\u5783\u573e\u5206\u7c7b\u540d\u5df2\u7ecf\u5b58\u5728\uff0c\u8bf7\u91cd\u65b0\u8f93\u5165\uff01");
            return ret;
        }
        if (this.rubbishCategoryService.add(rubbishCategory) <= 0) {
            ret.put("type", "error");
            ret.put("msg", "\u5783\u573e\u5206\u7c7b\u6dfb\u52a0\u5931\u8d25\uff0c\u8bf7\u8054\u7cfb\u7ba1\u7406\u5458\uff01");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "\u6dfb\u52a0\u6210\u529f\uff01");
        return ret;
    }
    
    @RequestMapping(value = { "/edit" }, method = { RequestMethod.POST })
    @ResponseBody
    public Map<String, String> edit(final RubbishCategory rubbishCategory) {
        final Map<String, String> ret = new HashMap<String, String>();
        if (rubbishCategory == null) {
            ret.put("type", "error");
            ret.put("msg", "\u8bf7\u586b\u5199\u6b63\u786e\u7684\u5783\u573e\u5206\u7c7b\u4fe1\u606f\uff01");
            return ret;
        }
        if (StringUtils.isEmpty(rubbishCategory.getName())) {
            ret.put("type", "error");
            ret.put("msg", "\u8bf7\u586b\u5199\u5783\u573e\u5206\u7c7b\u540d\uff01");
            return ret;
        }
        if (this.isExist(rubbishCategory.getName(), rubbishCategory.getId())) {
            ret.put("type", "error");
            ret.put("msg", "\u8be5\u5783\u573e\u5206\u7c7b\u540d\u5df2\u7ecf\u5b58\u5728\uff0c\u8bf7\u91cd\u65b0\u8f93\u5165\uff01");
            return ret;
        }
        if (this.rubbishCategoryService.edit(rubbishCategory) <= 0) {
            ret.put("type", "error");
            ret.put("msg", "\u5783\u573e\u5206\u7c7b\u6dfb\u52a0\u5931\u8d25\uff0c\u8bf7\u8054\u7cfb\u7ba1\u7406\u5458\uff01");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "\u7f16\u8f91\u6210\u529f\uff01");
        return ret;
    }
    
    @RequestMapping(value = { "/delete" }, method = { RequestMethod.POST })
    @ResponseBody
    public Map<String, String> delete(final Long id) {
        final Map<String, String> ret = new HashMap<String, String>();
        if (id == null) {
            ret.put("type", "error");
            ret.put("msg", "\u9009\u62e9\u8981\u5220\u9664\u7684\u6570\u636e\uff01");
            return ret;
        }
        try {
            if (this.rubbishCategoryService.delete(id) <= 0) {
                ret.put("type", "error");
                ret.put("msg", "\u5783\u573e\u5206\u7c7b\u5220\u9664\u5931\u8d25\uff0c\u8bf7\u8054\u7cfb\u7ba1\u7406\u5458\uff01");
                return ret;
            }
        }
        catch (Exception e) {
            ret.put("type", "error");
            ret.put("msg", "\u8be5\u5783\u573e\u5206\u7c7b\u4e0b\u5b58\u5728\u5783\u573e\uff0c\u8bf7\u5148\u5220\u9664\u5783\u573e\uff01");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "\u5783\u573e\u5206\u7c7b\u5220\u9664\u6210\u529f\uff01");
        return ret;
    }
    
    private boolean isExist(final String name, final Long id) {
        final RubbishCategory rubbishCategory = this.rubbishCategoryService.findByName(name);
        return rubbishCategory != null && rubbishCategory.getId() != (long)id;
    }
}
