package com.article.controller;

import com.article.daoservice.CmsCollectCatagoryService;
import com.article.domain.CmsCollectCatagory;
import com.comet.core.controller.BaseCRUDActionController;
import com.comet.core.orm.hibernate.Page;
import com.comet.core.orm.hibernate.QueryTranslate;
import com.comet.core.security.user.BaseUser;
import com.comet.core.security.util.SpringSecurityUtils;
import com.comet.core.utils.ReflectionUtils;
import com.comet.system.manager.SysUserManager;
import com.comet.system.tree.Node;
import com.comet.system.tree.TreeBranch;
import com.comet.system.tree.ZTreeNode;
import com.comet.system.utils.PrivilegeUtils;
import com.comet.system.utils.UserSessionUtils;
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
import java.util.List;

/**
 * @version 1.0
 * @author: System
 */
@Controller
public class CmsCollectCatagoryController extends BaseCRUDActionController<CmsCollectCatagory> {
    private static Log log = LogFactory.getLog(CmsCollectCatagoryController.class);

    @Autowired
	private CmsCollectCatagoryService cmsCollectCatagoryService;


	/**
	 * 取得树数据
	 *
	 * @param type
	 *            节点类型
	 * @param uid
	 *            节点ID
	 * @throws Exception
	 *             异常
	 */
    @RequestMapping
    @ResponseBody
    public List<Node> tree(String type,String uid, String id) throws Exception {
        TreeBranch treeBranch = new TreeBranch();
        type = StringUtils.defaultString(type, "");

        if(StringUtils.isEmpty(uid)) {
            ZTreeNode treeNode = new ZTreeNode();
            treeNode.setId("root");
            treeNode.setIsParent(true);
            treeNode.setIsLeaf(false);
            treeNode.setOpen(true);
//            treeNode.setHasChildren(true);
            treeNode.setName("根节点");
            treeNode.setText("根节点");
            treeNode.setUid("root");
            treeBranch.addTreeNode(treeNode);
        } else {
            String hql = "from CmsCollectCatagory where parent.id is null order by treeId";

            if (!StringUtils.equals(uid, "root")) {
                hql = "from CmsCollectCatagory where parent.id = " + uid + " order by treeId";
            }

            List<CmsCollectCatagory> cmsCollectCatagorys = cmsCollectCatagoryService.findByQuery(hql);

            for (CmsCollectCatagory cmsCollectCatagory : cmsCollectCatagorys) {
                ZTreeNode treeNode = new ZTreeNode();
                treeNode.setId(cmsCollectCatagory.getClass().getSimpleName() + "_" + cmsCollectCatagory.getId());
                if(!cmsCollectCatagory.getIsLeaf()){
                    treeNode.setIsParent(true);
                    treeNode.setOpen(false);
                }else{
                    treeNode.setIsLeaf(true);
                }
                treeNode.setName(cmsCollectCatagory.getName());
                treeNode.setText(cmsCollectCatagory.getName());
                treeNode.setUid(cmsCollectCatagory.getId().toString());
                treeNode.setType(cmsCollectCatagory.getClass().getSimpleName());
                treeNode.setTreeId(cmsCollectCatagory.getTreeId());
                treeBranch.addTreeNode(treeNode);
            }
        }

        return treeBranch.getTreeNodeList();
	}

	@RequestMapping
    @ResponseBody
	public Page<CmsCollectCatagory> grid(Page page, String condition) {
		try {
            page.setAutoCount(true);

            String hql = "from CmsCollectCatagory t where 1=1 " + page.getOrderByString("t.treeId asc");

            QueryTranslate queryTranslate = new QueryTranslate(hql, condition);

            // 查询
            page = cmsCollectCatagoryService.findByPage(page, queryTranslate.toString());
		} catch (Exception e) {
            log.error("error", e);
		}

        return page;
	}

    @RequestMapping
    public String init(Model model, CmsCollectCatagory entity) throws Exception {
        try {
            if(entity != null && entity.getId() != null) {
                entity = cmsCollectCatagoryService.get(entity.getId());

            }
            model.addAttribute("bean", entity);
        } catch (Exception e) {
            log.error("error", e);
        }

        return "view/cms/cmsCollectCatagoryEdit";
    }

    @RequestMapping
    public String view(Model model, Long id) {
        CmsCollectCatagory cmsCollectCatagory = cmsCollectCatagoryService.get(id);

        model.addAttribute("bean", cmsCollectCatagory);
        return "view/cms/cmsCollectCatagoryView";
    }

    @RequestMapping
    public void save(HttpServletResponse response, Model model, @ModelAttribute("bean") CmsCollectCatagory entity)
            throws Exception {
        try {
            String[] columns = new String[]{
                    "id",
                    "name",
                    "code",
                    "type",
                    "parent"
            };

            CmsCollectCatagory target;
            if (entity.getId() != null) {
                target = cmsCollectCatagoryService.get(entity.getId());

                ReflectionUtils.copyBean(entity, target, columns);
            } else {
                target = entity;
                if(!PrivilegeUtils.isSysAdmin()){
                    entity.setUserId(SpringSecurityUtils.getCurrentUser().getId().toString());
                    entity.setType("1");
                }else {
                    entity.setType("0");
                }
            }

            cmsCollectCatagoryService.save(target);
        } catch (Exception e) {
            log.error("error", e);
            super.processException(response, e);
        }

        sendSuccessJSON(response, "保存成功");
    }

    @RequestMapping
    public void delete(HttpServletResponse response, Long id) throws Exception {
        cmsCollectCatagoryService.delete(id);

        sendSuccessJSON(response, "删除成功");
    }
}