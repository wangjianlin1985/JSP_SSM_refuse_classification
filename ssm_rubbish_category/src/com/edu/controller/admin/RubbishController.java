// 
// 
// 

package com.edu.controller.admin;

import org.apache.commons.lang.StringUtils;
import com.edu.entity.admin.Rubbish;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.HashMap;
import org.springframework.web.bind.annotation.RequestParam;
import com.edu.page.admin.Page;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.Map;
import org.springframework.web.servlet.ModelAndView;
import com.edu.service.admin.RubbishService;
import org.springframework.beans.factory.annotation.Autowired;
import com.edu.service.admin.RubbishCategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping({ "/admin/rubbish" })
@Controller
public class RubbishController
{
    @Autowired
    private RubbishCategoryService rubbishCategoryService;
    @Autowired
    private RubbishService rubbishService;
    
    @RequestMapping(value = { "/list" }, method = { RequestMethod.GET })
    public ModelAndView list(final ModelAndView model) {
        model.addObject("rubbishCategoryList", (Object)this.rubbishCategoryService.findList(null));
        model.setViewName("rubbish/list");
        return model;
    }
    
    @RequestMapping(value = { "/list" }, method = { RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getList(final Page page, @RequestParam(name = "name", required = false, defaultValue = "") String name, final Long categoryId) {
        final Map<String, Object> ret = new HashMap<String, Object>();
        final Map<String, Object> queryMap = new HashMap<String, Object>();
        if (categoryId != null) {
            queryMap.put("categoryId", categoryId);
        }
        queryMap.put("name", name);
        queryMap.put("offset", page.getOffset());
        queryMap.put("pageSize", page.getRows());
        ret.put("rows", this.rubbishService.findList(queryMap));
        ret.put("total", this.rubbishService.getTotal(queryMap));
        return ret;
    }
    
    @RequestMapping(value = { "/add" }, method = { RequestMethod.POST })
    @ResponseBody
    public Map<String, String> add(final Rubbish rubbish) {
        final Map<String, String> ret = new HashMap<String, String>();
        if (rubbish == null) {
            ret.put("type", "error");
            ret.put("msg", "\u8bf7\u586b\u5199\u6b63\u786e\u7684\u5783\u573e\u4fe1\u606f\uff01");
            return ret;
        }
        if (StringUtils.isEmpty(rubbish.getName())) {
            ret.put("type", "error");
            ret.put("msg", "\u8bf7\u586b\u5199\u5783\u573e\u540d\uff01");
            return ret;
        }
        if (this.rubbishService.add(rubbish) <= 0) {
            ret.put("type", "error");
            ret.put("msg", "\u5783\u573e\u6dfb\u52a0\u5931\u8d25\uff0c\u8bf7\u8054\u7cfb\u7ba1\u7406\u5458\uff01");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "\u6dfb\u52a0\u6210\u529f\uff01");
        return ret;
    }
    
    @RequestMapping(value = { "/edit" }, method = { RequestMethod.POST })
    @ResponseBody
    public Map<String, String> edit(final Rubbish rubbish) {
        final Map<String, String> ret = new HashMap<String, String>();
        if (rubbish == null) {
            ret.put("type", "error");
            ret.put("msg", "\u8bf7\u586b\u5199\u6b63\u786e\u7684\u5783\u573e\u4fe1\u606f\uff01");
            return ret;
        }
        if (StringUtils.isEmpty(rubbish.getName())) {
            ret.put("type", "error");
            ret.put("msg", "\u8bf7\u586b\u5199\u5783\u573e\u540d\uff01");
            return ret;
        }
        if (this.rubbishService.edit(rubbish) <= 0) {
            ret.put("type", "error");
            ret.put("msg", "\u5783\u573e\u6dfb\u52a0\u5931\u8d25\uff0c\u8bf7\u8054\u7cfb\u7ba1\u7406\u5458\uff01");
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
            if (this.rubbishService.delete(id) <= 0) {
                ret.put("type", "error");
                ret.put("msg", "\u5783\u573e\u5220\u9664\u5931\u8d25\uff0c\u8bf7\u8054\u7cfb\u7ba1\u7406\u5458\uff01");
                return ret;
            }
        }
        catch (Exception e) {
            ret.put("type", "error");
            ret.put("msg", "\u8be5\u5783\u573e\u4e0b\u5b58\u5728\u5783\u573e\uff0c\u8bf7\u5148\u5220\u9664\u5783\u573e\uff01");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "\u5783\u573e\u5220\u9664\u6210\u529f\uff01");
        return ret;
    }
}
