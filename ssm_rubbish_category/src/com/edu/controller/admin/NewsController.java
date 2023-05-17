// 
// 
// 

package com.edu.controller.admin;

import java.util.Date;
import org.apache.commons.lang.StringUtils;
import com.edu.entity.admin.Log;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestParam;
import com.edu.page.admin.Page;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import com.edu.service.admin.LogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping({ "/admin/news" })
@Controller
public class NewsController
{
    @Autowired
    private LogService logService;
    
    @RequestMapping(value = { "/list" }, method = { RequestMethod.GET })
    public ModelAndView list(final ModelAndView model) {
        model.setViewName("news/list");
        return model;
    }
    
    @RequestMapping(value = { "/list" }, method = { RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getList(final Page page, @RequestParam(name = "content", required = false, defaultValue = "") final String content) {
        final Map<String, Object> ret = new HashMap<String, Object>();
        final Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("content", content);
        queryMap.put("offset", page.getOffset());
        queryMap.put("pageSize", page.getRows());
        ret.put("rows", this.logService.findList(queryMap));
        ret.put("total", this.logService.getTotal(queryMap));
        return ret;
    }
    
    @RequestMapping(value = { "/add" }, method = { RequestMethod.POST })
    @ResponseBody
    public Map<String, String> add(final Log log) {
        final Map<String, String> ret = new HashMap<String, String>();
        if (log == null) {
            ret.put("type", "error");
            ret.put("msg", "\u8bf7\u586b\u5199\u6b63\u786e\u7684\u65e5\u5fd7\u4fe1\u606f\uff01");
            return ret;
        }
        if (StringUtils.isEmpty(log.getContent())) {
            ret.put("type", "error");
            ret.put("msg", "\u8bf7\u586b\u5199\u65e5\u5fd7\u5185\u5bb9\uff01");
            return ret;
        }
        log.setCreateTime(new Date());
        if (this.logService.add(log) <= 0) {
            ret.put("type", "error");
            ret.put("msg", "\u65e5\u5fd7\u6dfb\u52a0\u5931\u8d25\uff0c\u8bf7\u8054\u7cfb\u7ba1\u7406\u5458\uff01");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "\u65e5\u5fd7\u6dfb\u52a0\u6210\u529f\uff01");
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
        if (this.logService.delete(ids) <= 0) {
            ret.put("type", "error");
            ret.put("msg", "\u65e5\u5fd7\u5220\u9664\u5931\u8d25\uff0c\u8bf7\u8054\u7cfb\u7ba1\u7406\u5458\uff01");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "\u65e5\u5fd7\u5220\u9664\u6210\u529f\uff01");
        return ret;
    }
}
