// 
// 
// 

package com.edu.util;

import java.util.Iterator;
import java.util.ArrayList;
import com.edu.entity.admin.Menu;
import java.util.List;

public class MenuUtil
{
    public static List<Menu> getAllTopMenu(final List<Menu> menuList) {
        final List<Menu> ret = new ArrayList<Menu>();
        for (final Menu menu : menuList) {
            if (menu.getParentId() == 0L) {
                ret.add(menu);
            }
        }
        return ret;
    }
    
    public static List<Menu> getAllSecondMenu(final List<Menu> menuList) {
        final List<Menu> ret = new ArrayList<Menu>();
        final List<Menu> allTopMenu = getAllTopMenu(menuList);
        for (final Menu menu : menuList) {
            for (final Menu topMenu : allTopMenu) {
                if (menu.getParentId() == topMenu.getId()) {
                    ret.add(menu);
                    break;
                }
            }
        }
        return ret;
    }
    
    public static List<Menu> getAllThirdMenu(final List<Menu> menuList, final Long secondMenuId) {
        final List<Menu> ret = new ArrayList<Menu>();
        for (final Menu menu : menuList) {
            if (menu.getParentId() == secondMenuId) {
                ret.add(menu);
            }
        }
        return ret;
    }
}
