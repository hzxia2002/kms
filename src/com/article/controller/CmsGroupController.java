package com.article.controller;

import com.article.daoservice.CmsGroupService;
import com.article.daoservice.CmsGroupUserService;
import com.article.domain.CmsGroup;
import com.article.domain.CmsGroupUser;
import com.comet.core.controller.BaseCRUDActionController;
import com.comet.core.orm.hibernate.Page;
import com.comet.core.orm.hibernate.QueryTranslate;
import com.comet.core.utils.ReflectionUtils;
import com.comet.system.daoservice.SysUserService;
import com.comet.system.domain.SysUser;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @author: System
 */
@Controller
public class CmsGroupController extends BaseCRUDActionController<CmsGroup> {
    private static Log log = LogFactory.getLog(CmsGroupController.class);

    @Autowired
    private CmsGroupService cmsGroupService;

    @Autowired
    private CmsGroupUserService cmsGroupUserService;

    @Autowired
    private SysUserService sysUserService;


    @RequestMapping
    @ResponseBody
    public Page<CmsGroup> grid(Page page, String condition) {
        try {
            page.setAutoCount(true);

            String hql = "from CmsGroup t where 1=1 " ;

            QueryTranslate queryTranslate = new QueryTranslate(hql, condition);

            // 查询
            page = cmsGroupService.findByPage(page, queryTranslate.toString());
        } catch (Exception e) {
            log.error("error", e);
        }

        return page;
    }

    @RequestMapping
    @ResponseBody
    public Page<CmsGroup> userGrid(Page page, String condition,Long groupId) {
        try {
            page.setAutoCount(true);

            String hql = "select t.user from CmsGroupUser t where 1=1 and t.group.id="+groupId ;

            QueryTranslate queryTranslate = new QueryTranslate(hql, condition);

            // 查询
            page = cmsGroupService.findByPage(page, queryTranslate.toString());
        } catch (Exception e) {
            log.error("error", e);
        }

        return page;
    }

    @RequestMapping
    public String init(Model model, CmsGroup entity) throws Exception {
        try {
            if(entity != null && entity.getId() != null) {
                entity = cmsGroupService.get(entity.getId());

                model.addAttribute("bean", entity);
            }
        } catch (Exception e) {
            log.error("error", e);
        }

        return "view/cms/cmsGroupEdit";
    }

    @RequestMapping
    public String initAddUser(Model model, CmsGroup entity) throws Exception {
        try {
            if(entity != null && entity.getId() != null) {
                entity = cmsGroupService.get(entity.getId());

                model.addAttribute("bean", entity);
            }
        } catch (Exception e) {
            log.error("error", e);
        }

        return "view/cms/cmsGroupAddUser";
    }

    @RequestMapping
    public String view(Model model, Long id) {
        CmsGroup cmsGroup = cmsGroupService.get(id);

        model.addAttribute("bean", cmsGroup);
        return "view/cms/cmsGroupView";
    }


    @RequestMapping
    public void addUsers(HttpServletResponse response,String userIds,Long groupId) {
        if(StringUtils.isNotEmpty(userIds)){

            String[] ids = userIds.split(",");
            CmsGroup cmsGroup = cmsGroupService.get(groupId);
            for (String id : ids) {
                String hql = "from CmsGroupUser where group.id="+groupId+" and user.id="+id;
                List<CmsGroupUser> cmsGroupUsers = cmsGroupUserService.find(hql);
                if(cmsGroupUsers.size()>0){
                    continue;
                }
                SysUser sysUser = sysUserService.get(Long.valueOf(id));
                CmsGroupUser cmsGroupUser = new CmsGroupUser();
                cmsGroupUser.setGroup(cmsGroup);
                cmsGroupUser.setUser(sysUser);
                cmsGroupUserService.save(cmsGroupUser);
            }
        }

        sendSuccessJSON(response);
    }

    @RequestMapping
    public void save(HttpServletResponse response, Model model, @ModelAttribute("bean") CmsGroup entity)
            throws Exception {
        try {
            String[] columns = new String[]{
                    "id",
                    "name",
                    "code"
            };

            CmsGroup target;
            if (entity.getId() != null) {
                target = cmsGroupService.get(entity.getId());

                ReflectionUtils.copyBean(entity, target, columns);
            } else {
                target = entity;
            }

            cmsGroupService.save(target);
        } catch (Exception e) {
            log.error("error", e);
            super.processException(response, e);
        }

        sendSuccessJSON(response, "保存成功");
    }

    @RequestMapping
    public void delete(HttpServletResponse response, Long id) throws Exception {
        cmsGroupService.delete(id);

        sendSuccessJSON(response, "删除成功");
    }

    @RequestMapping
    public void deleteUser(HttpServletResponse response, Long id,Long groupId) throws Exception {
        String hql = "from CmsGroupUser where group.id="+groupId+" and user.id="+id;
        List<CmsGroupUser> cmsGroupUsers = cmsGroupUserService.find(hql);
        if(cmsGroupUsers.size()>0){
            for (CmsGroupUser cmsGroupUser : cmsGroupUsers) {
                cmsGroupUserService.delete(cmsGroupUser);
            }
        }

        sendSuccessJSON(response, "删除成功");
    }

    @RequestMapping
    @ResponseBody
    public List getUsers(HttpServletResponse response, String groupIds) throws Exception {
        List<Map> arrayList = new ArrayList<Map>();
        if(StringUtils.isNotEmpty(groupIds)){
            String hql = "select g.user from CmsGroupUser g where g.group.id in("+groupIds+")";
            List<SysUser> sysUsers = sysUserService.find(hql);
            for (SysUser sysUser : sysUsers) {
                HashMap hashMap = new HashMap();
                hashMap.put("id",sysUser.getId());
                hashMap.put("displayName",sysUser.getDisplayName());
                arrayList.add(hashMap);
            }

        }
        return arrayList;
    }

    @RequestMapping
    @ResponseBody
    public List getAllUsers(HttpServletResponse response, String groupIds) throws Exception {
        List<Map> arrayList = new ArrayList<Map>();
        String hql = "from SysUser";
        List<SysUser> sysUsers = sysUserService.find(hql);
        for (SysUser sysUser : sysUsers) {
            HashMap hashMap = new HashMap();
            hashMap.put("id",sysUser.getId());
            hashMap.put("displayName",sysUser.getDisplayName());
            arrayList.add(hashMap);
        }

        return arrayList;
    }
}