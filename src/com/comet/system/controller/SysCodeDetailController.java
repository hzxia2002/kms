package com.comet.system.controller;

import com.comet.core.controller.BaseCRUDActionController;
import com.comet.core.orm.hibernate.Page;
import com.comet.core.orm.hibernate.QueryTranslate;
import com.comet.core.utils.ReflectionUtils;
import com.comet.system.daoservice.SysCodeDetailService;
import com.comet.system.domain.SysCodeDetail;
import com.comet.system.tree.Node;
import com.comet.system.tree.TreeBranch;
import com.comet.system.tree.ZTreeNode;
import org.apache.commons.lang.StringUtils;
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
public class SysCodeDetailController extends BaseCRUDActionController<SysCodeDetail> {
    @Autowired
	private SysCodeDetailService sysCodeDetailService;


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
            String hql = "from SysCodeDetail where parent.id is null order by treeId";

            if (!StringUtils.equals(uid, "root")) {
                hql = "from SysCodeDetail where parent.id = " + uid + " order by treeId";
            }

            List<SysCodeDetail> sysCodeDetails = sysCodeDetailService.findByQuery(hql);

            for (SysCodeDetail sysCodeDetail : sysCodeDetails) {
                ZTreeNode treeNode = new ZTreeNode();
                treeNode.setId(sysCodeDetail.getClass().getSimpleName() + "_" + sysCodeDetail.getId());
                if(!sysCodeDetail.getIsLeaf()){
                    treeNode.setIsParent(true);
                    treeNode.setOpen(false);
                }else{
                    treeNode.setIsLeaf(true);
                }
                treeNode.setName(sysCodeDetail.getName());
                treeNode.setText(sysCodeDetail.getName());
                treeNode.setUid(sysCodeDetail.getId().toString());
                treeNode.setType(sysCodeDetail.getClass().getSimpleName());
                treeNode.setTreeId(sysCodeDetail.getTreeId());
                treeBranch.addTreeNode(treeNode);
            }
        }

        return treeBranch.getTreeNodeList();
	}

    @RequestMapping
    @ResponseBody
    public List<Node> treeData(String type, String uid, String id, String mainCode) throws Exception {
        TreeBranch treeBranch = new TreeBranch();
        type = StringUtils.defaultString(type, "");
        String hql = "";

        // 先判断mainCode是否有值，如果有，则取sysCode值
        if(StringUtils.isNotEmpty(mainCode) && StringUtils.isEmpty(uid)) {
                hql = "from SysCodeDetail t where sysCode.code = '" + mainCode + "' and t.parent is null order by code asc";
        } else {
            hql = "from SysCodeDetail t where t.parent.id = " + uid + " order by code asc";
        }

        List<SysCodeDetail> sysCodeDetails = sysCodeDetailService.findByQuery(hql);

        for (SysCodeDetail sysCodeDetail : sysCodeDetails) {
            ZTreeNode treeNode = new ZTreeNode();
            treeNode.setId(sysCodeDetail.getClass().getSimpleName() + "_" + sysCodeDetail.getId());
            if(!sysCodeDetail.getIsLeaf()){
                treeNode.setIsParent(true);
                treeNode.setOpen(false);
            }else{
                treeNode.setIsLeaf(true);
            }

            treeNode.setName(sysCodeDetail.getName());
            treeNode.setText(sysCodeDetail.getName());
            treeNode.setUid(sysCodeDetail.getId().toString());
            treeNode.setType(sysCodeDetail.getClass().getSimpleName());
            treeNode.setTreeId(sysCodeDetail.getTreeId());
            treeBranch.addTreeNode(treeNode);
        }

        return treeBranch.getTreeNodeList();
    }

	@RequestMapping
    @ResponseBody
	public Page<SysCodeDetail> grid(Page page, String condition) {
		try {
            page.setAutoCount(true);

            String hql = "from SysCodeDetail t where 1=1 " + page.getOrderByString("t.treeId asc");

            QueryTranslate queryTranslate = new QueryTranslate(hql, condition);

            // 查询
            page = sysCodeDetailService.findByPage(page, queryTranslate.toString());
		} catch (Exception e) {
            log.error("error", e);
		}

        return page;
	}

    @RequestMapping
    public String initAdd(Model model, SysCodeDetail entity) throws Exception {
        try {
            if(entity == null) {
                entity = new SysCodeDetail();
            }

            if(entity.getParent() != null) {
                entity.setSysCode(entity.getParent().getSysCode());
            }

            entity.setIsValid(Boolean.TRUE);
            model.addAttribute("bean", entity);
        } catch (Exception e) {
            log.error("error", e);
        }

        return "view/sys_new/sysCodeDetailEdit";
    }

    @RequestMapping
    public String init(Model model, SysCodeDetail entity) throws Exception {
        try {
            if(entity != null && entity.getId() != null) {
                entity = sysCodeDetailService.get(entity.getId());

                model.addAttribute("bean", entity);
            }
        } catch (Exception e) {
            log.error("error", e);
        }

        return "view/sys_new/sysCodeDetailEdit";
    }

    @RequestMapping
    public String view(Model model, Long id) {
        SysCodeDetail sysCodeDetail = sysCodeDetailService.get(id);

        model.addAttribute("bean", sysCodeDetail);
        return "view/sys_new/sysCodeDetailView";
    }

    @RequestMapping
    public void save(HttpServletResponse response, Model model, @ModelAttribute("bean") SysCodeDetail entity)
            throws Exception {
        try {
            String[] columns = new String[]{
//                    "id",
                    "sysCode",
//                    "parent",
                    "code",
                    "name",
//                    "isLeaf",
//                    "treeId",
                    "isReserved",
                    "tag",
                    "isValid",
                    "description"
            };

            SysCodeDetail target;
            if (entity.getId() != null) {
                target = sysCodeDetailService.get(entity.getId());

                ReflectionUtils.copyBean(entity, target, columns);
            } else {
                target = entity;
            }

            sysCodeDetailService.save(target);
        } catch (Exception e) {
            log.error("error", e);
            super.processException(response, e);
        }

        sendSuccessJSON(response, "保存成功");
    }

    @RequestMapping
    public void delete(HttpServletResponse response, Long id) throws Exception {
        sysCodeDetailService.delete(id);

        sendSuccessJSON(response, "删除成功");
    }
}