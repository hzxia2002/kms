package com.article.controller;

import com.article.daoservice.CmsCatalogService;
import com.article.domain.CmsCatalog;
import com.article.manager.CatalogManager;
import com.article.util.Constants;
import com.comet.core.controller.BaseCRUDActionController;
import com.comet.core.orm.hibernate.Page;
import com.comet.core.orm.hibernate.QueryTranslate;
import com.comet.core.utils.ReflectionUtils;
import com.comet.system.tree.Node;
import com.comet.system.tree.TreeBranch;
import com.comet.system.tree.ZTreeNode;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @version 1.0
 * @author: System
 */
@Controller
public class CmsCatalogController extends BaseCRUDActionController<CmsCatalog> {
    private static Log log = LogFactory.getLog(CmsCatalogController.class);

    @Autowired
	private CmsCatalogService cmsCatalogService;

    @Autowired
    private CatalogManager catalogManager;


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
            String hql = "from CmsCatalog where parent.id is null and type=" + Constants.FILE_VISUAL +" order by treeId";

            if (!StringUtils.equals(uid, "root")) {
                hql = "from CmsCatalog where parent.id = " + uid + " and type=" + Constants.FILE_VISUAL +" order by treeId";
            }

            List<CmsCatalog> cmsCatalogs = cmsCatalogService.findByQuery(hql);

            for (CmsCatalog cmsCatalog : cmsCatalogs) {
                ZTreeNode treeNode = new ZTreeNode();
                treeNode.setId(cmsCatalog.getClass().getSimpleName() + "_" + cmsCatalog.getId());
                if(!cmsCatalog.getIsLeaf()){
                    treeNode.setIsParent(true);
                    treeNode.setOpen(false);
                }else{
                    treeNode.setIsLeaf(true);
                }
                treeNode.setName(cmsCatalog.getName());
                treeNode.setText(cmsCatalog.getName());
                treeNode.setUid(cmsCatalog.getId().toString());
                treeNode.setType(cmsCatalog.getClass().getSimpleName());
                treeNode.setTreeId(cmsCatalog.getTreeId());
                treeBranch.addTreeNode(treeNode);
            }
        }

        return treeBranch.getTreeNodeList();
	}

	@RequestMapping
    @ResponseBody
	public Page<CmsCatalog> grid(Page page, String condition) {
		try {
            page.setAutoCount(true);

            String hql = "from CmsCatalog t where 1=1 and t.type=" + Constants.FILE_VISUAL ;

            QueryTranslate queryTranslate = new QueryTranslate(hql, condition);

            // 查询
            page = cmsCatalogService.findByPage(page, queryTranslate.toString());
		} catch (Exception e) {
            log.error("error", e);
		}

        return page;
	}

    @RequestMapping
    public String init(Model model, CmsCatalog entity) throws Exception {
        try {
            if(entity != null && entity.getId() != null) {
                entity = cmsCatalogService.get(entity.getId());
            } else if(entity != null && entity.getId() == null) {
                entity.setIsValid(Boolean.TRUE);
            }

            model.addAttribute("bean", entity);
        } catch (Exception e) {
            log.error("error", e);
        }

        return "view/cms/cmsCatalogEdit";
    }

    @RequestMapping
    public String view(Model model, Long id) {
        CmsCatalog cmsCatalog = cmsCatalogService.get(id);

        model.addAttribute("bean", cmsCatalog);
        return "view/cms/cmsCatalogView";
    }

    @RequestMapping
    public void save(HttpServletRequest request,HttpServletResponse response, Model model, @ModelAttribute("bean") CmsCatalog entity)
            throws Exception {
        try {
            String[] columns = new String[]{
                    "id",
                    "name",
                    "code",
                    "path",
                    "icon",
                    "isValid",
                    "description"
            };

            CmsCatalog target;
            if (entity.getId() != null) {
                target = cmsCatalogService.get(entity.getId());

                ReflectionUtils.copyBean(entity, target, columns);
                cmsCatalogService.save(target);
            } else {
                target = entity;
                String realPath = request.getSession().getServletContext().getRealPath("");
                catalogManager.saveCmsCatalog(target,realPath);
            }

        } catch (Exception e) {
            log.error("error", e);
            super.processException(response, e);
        }

        sendSuccessJSON(response, "保存成功");
    }

    @RequestMapping
    public void delete(HttpServletResponse response, Long id) throws Exception {
        cmsCatalogService.delete(id);

        sendSuccessJSON(response, "删除成功");
    }
}