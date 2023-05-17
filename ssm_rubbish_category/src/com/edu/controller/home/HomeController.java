// 
// 
// 

package com.edu.controller.home;

import java.util.List;
import com.edu.entity.admin.RubbishCategory;
import com.edu.entity.admin.Rubbish;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.edu.service.admin.RubbishService;
import org.springframework.beans.factory.annotation.Autowired;
import com.edu.service.admin.RubbishCategoryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping({ "/home" })
public class HomeController
{
    @Autowired
    private RubbishCategoryService rubbishCategoryService;
    @Autowired
    private RubbishService rubbishService;
    
    @RequestMapping(value = { "/index" }, method = { RequestMethod.GET })
    public ModelAndView index(final ModelAndView model) {
        model.setViewName("home/index");
        return model;
    }
    
    @RequestMapping(value = { "/search" }, method = { RequestMethod.GET })
    public ModelAndView search(@RequestParam(name = "k", required = false, defaultValue = "") String name, final ModelAndView model) {
        RubbishCategory rubbishCategory = null;
        if (!StringUtils.isEmpty(name)) {
            final List<Rubbish> rubbishList = this.rubbishService.findByName(name);
            if (rubbishList != null && rubbishList.size() > 0) {
                rubbishCategory = rubbishList.get(0).getRubbishCategory();
            }
            else {
                final List<RubbishCategory> rubbishCategoryList = this.rubbishCategoryService.findByCommon(name);
                if (rubbishCategoryList != null && rubbishCategoryList.size() > 0) {
                    rubbishCategory = rubbishCategoryList.get(0);
                }
            }
        }
        model.addObject("k", (Object)name);
        model.addObject("rubbishCategory", (Object)rubbishCategory);
        model.setViewName("home/search");
        return model;
    }
}
