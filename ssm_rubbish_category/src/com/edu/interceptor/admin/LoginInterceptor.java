// 
// 
// 

package com.edu.interceptor.admin;

import java.util.Map;
import net.sf.json.JSONObject;
import java.util.HashMap;
import com.edu.entity.admin.Menu;
import com.edu.util.MenuUtil;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor
{
    public void afterCompletion(final HttpServletRequest arg0, final HttpServletResponse arg1, final Object arg2, final Exception arg3) throws Exception {
    }
    
    public void postHandle(final HttpServletRequest arg0, final HttpServletResponse arg1, final Object arg2, final ModelAndView arg3) throws Exception {
    }
    
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object arg2) throws Exception {
        final String requestURI = request.getRequestURI();
        final Object admin = request.getSession().getAttribute("admin");
        if (admin != null) {
            final String mid = request.getParameter("_mid");
            if (!StringUtils.isEmpty(mid)) {
                final List<Menu> allThirdMenu = MenuUtil.getAllThirdMenu((List<Menu>)request.getSession().getAttribute("userMenus"), Long.valueOf(mid));
                request.setAttribute("thirdMenuList", (Object)allThirdMenu);
            }
            return true;
        }
        System.out.println("\u94fe\u63a5" + requestURI + "\u8fdb\u5165\u62e6\u622a\u5668\uff01");
        final String header = request.getHeader("X-Requested-With");
        if ("XMLHttpRequest".equals(header)) {
            final Map<String, String> ret = new HashMap<String, String>();
            ret.put("type", "error");
            ret.put("msg", "\u767b\u5f55\u4f1a\u8bdd\u8d85\u65f6\u6216\u8fd8\u672a\u767b\u5f55\uff0c\u8bf7\u91cd\u65b0\u767b\u5f55!");
            response.getWriter().write(JSONObject.fromObject((Object)ret).toString());
            return false;
        }
        response.sendRedirect(String.valueOf(request.getServletContext().getContextPath()) + "/system/login");
        return false;
    }
}
