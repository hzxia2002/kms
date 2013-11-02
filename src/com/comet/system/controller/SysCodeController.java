package com.comet.system.controller;

import com.comet.system.daoservice.SysCodeDetailService;
import com.comet.system.daoservice.SysCodeService;
import com.comet.system.domain.SysCode;
import com.comet.system.domain.SysCodeDetail;
import com.comet.system.manager.SysCodeManager;
import com.comet.core.controller.BaseCRUDActionController;
import com.comet.core.orm.hibernate.Page;
import com.comet.core.orm.hibernate.QueryTranslate;
import com.comet.core.utils.ReflectionUtils;
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
public class SysCodeController extends BaseCRUDActionController<SysCode> {
    @Autowired
	private SysCodeService sysCodeService;

    @Autowired
    private SysCodeDetailService sysCodeDetailService;

    @Autowired
    private SysCodeManager sysCodeManager;

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
            treeNode.setName("系统代码树");
            treeNode.setText("系统代码树");
            treeNode.setUid("root");
            treeBranch.addTreeNode(treeNode);
        } else {
            String hql = "";

            if(StringUtils.equals(uid, "root")) {
                hql = "from SysCode where parent.id is null order by treeId";

                List<SysCode> sysCodes = sysCodeService.findByQuery(hql);
                treeBranch = buildMainTree(sysCodes);

                return treeBranch.getTreeNodeList();
            }

            if(StringUtils.equals(type, "SysCode")) {
                // 根据type进行处理
                SysCode bean = sysCodeService.get(Long.parseLong(uid));

                if(bean != null) {
                    if(bean.getIsLeaf() != null && !bean.getIsLeaf()) {
                        // 如果不是叶子节点，查询主表
                        hql = "from SysCode where parent.id = " + uid + " order by treeId";

                        List<SysCode> sysCodes = sysCodeService.findByQuery(hql);
                        treeBranch = buildMainTree(sysCodes);

                        return treeBranch.getTreeNodeList();
                    } else {
                        // 如果是叶子节点，查询详细表
                        hql = "from SysCodeDetail where sysCode.id = " + uid + " and parent.id is null order by treeId";

                        List<SysCodeDetail> sysCodeList = sysCodeDetailService.findByQuery(hql);
                        treeBranch = buildDetailTree(sysCodeList);

                        return treeBranch.getTreeNodeList();
                    }
                }
            } else if(StringUtils.equals(type, "SysCodeDetail")) {
                // 如果是叶子节点，查询详细表
                hql = "from SysCodeDetail where parent.id = " + uid + " order by treeId";

                List<SysCodeDetail> sysCodeList = sysCodeDetailService.findByQuery(hql);
                treeBranch = buildDetailTree(sysCodeList);

                return treeBranch.getTreeNodeList();
            }
        }

        return treeBranch.getTreeNodeList();
	}

    /**
     * 取得代码详细列表信息
     *
     * @param code
     * @return
     */
    @RequestMapping
    @ResponseBody
    public List<SysCodeDetail> getCodeDetailList(String code) {
        try {
            return sysCodeManager.getCodeDetailListByCode(code);
        } catch (Exception e) {
            log.error("error", e);

            return null;
        }
    }

	@RequestMapping
    @ResponseBody
	public Page<SysCode> grid(Page page, String condition) {
		try {
            page.setAutoCount(true);

            String hql = "from SysCode t where 1=1 " + page.getOrderByString("t.treeId asc");

            QueryTranslate queryTranslate = new QueryTranslate(hql, condition);

            // 查询
            page = sysCodeService.findByPage(page, queryTranslate.toString());
		} catch (Exception e) {
            log.error("error", e);
		}

        return page;
	}

    @RequestMapping
    public String initAdd(Model model, SysCode entity) throws Exception {
        try {
            if(entity == null) {
                entity = new SysCode();
            }

            model.addAttribute("bean", entity);
        } catch (Exception e) {
            log.error("error", e);
        }

        return "view/sys_new/sysCodeEdit";
    }

    @RequestMapping
    public String init(Model model, SysCode entity) throws Exception {
        try {
            if(entity != null && entity.getId() != null) {
                entity = sysCodeService.get(entity.getId());

                model.addAttribute("bean", entity);
            }
        } catch (Exception e) {
            log.error("error", e);
        }

        return "view/sys_new/sysCodeEdit";
    }

    @RequestMapping
    public String view(Model model, Long id) {
        SysCode sysCode = sysCodeService.get(id);

        model.addAttribute("bean", sysCode);
        return "view/sys_new/sysCodeView";
    }

    @RequestMapping
    public void save(HttpServletResponse response, Model model, @ModelAttribute("bean") SysCode entity)
            throws Exception {
        try {
            String[] columns = new String[]{
//                    "id",
//                    "parent",
                    "code",
                    "name",
                    "isReserved",
//                    "isLeaf",
//                    "treeId",
                    "description"
            };

            SysCode target;
            if (entity.getId() != null) {
                target = sysCodeService.get(entity.getId());

                ReflectionUtils.copyBean(entity, target, columns);
            } else {
                target = entity;
            }

            sysCodeService.save(target);
            sendSuccessJSON(response, "保存成功");
        } catch (Exception e) {
            log.error("error", e);
            super.processException(response, e);
        }
    }

    @RequestMapping
    public void delete(HttpServletResponse response, Long id) throws Exception {
        sysCodeService.delete(id);

        sendSuccessJSON(response, "删除成功");
    }

    protected TreeBranch buildMainTree(List<SysCode> list) {
        TreeBranch treeBranch = new TreeBranch();

        for (SysCode sysCode : list) {
            ZTreeNode treeNode = new ZTreeNode();
            treeNode.setId(sysCode.getClass().getSimpleName() + "_" + sysCode.getId());

//            if(sysCode.getIsLeaf() != null && !sysCode.getIsLeaf()){
                treeNode.setIsParent(true);
                treeNode.setOpen(false);
//            }else{
//                treeNode.setIsLeaf(true);
//            }

            treeNode.setName(sysCode.getName());
            treeNode.setText(sysCode.getName());
            treeNode.setUid(sysCode.getId().toString());
            treeNode.setType(sysCode.getClass().getSimpleName());
            treeNode.setTreeId(sysCode.getTreeId());
            treeBranch.addTreeNode(treeNode);
        }

        return treeBranch;
    }

    protected TreeBranch buildDetailTree(List<SysCodeDetail> list) {
        TreeBranch treeBranch = new TreeBranch();

        for (SysCodeDetail detail : list) {
            ZTreeNode treeNode = new ZTreeNode();
            treeNode.setId(detail.getClass().getSimpleName() + "_" + detail.getId());
            if(detail.getIsLeaf() != null && !detail.getIsLeaf()){
                treeNode.setIsParent(true);
                treeNode.setOpen(false);
            }else{
                treeNode.setIsLeaf(true);
            }
            treeNode.setName(detail.getName());
            treeNode.setText(detail.getName());
            treeNode.setUid(detail.getId().toString());
            treeNode.setType(detail.getClass().getSimpleName());
            treeNode.setTreeId(detail.getTreeId());
            treeBranch.addTreeNode(treeNode);
        }

        return treeBranch;
    }
}