package com.article.controller;

import com.article.daoservice.ExaPaperSectionService;
import com.article.daoservice.ExaPaperService;
import com.article.domain.ExaPaper;
import com.article.domain.ExaPaperSection;
import com.article.manager.ExaQuestionManager;
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

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @version 1.0
 * @author: System
 */
@Controller
public class ExaPaperController extends BaseCRUDActionController<ExaPaper> {
    private static Log log = LogFactory.getLog(ExaPaperController.class);

    @Autowired
    private ExaPaperSectionService exaPaperSectionService;


    @Autowired
    private ExaPaperService exaPaperService;


    @Autowired
    private ExaQuestionManager exaQuestionManager;



    @RequestMapping
    @ResponseBody
    public Page<ExaPaper> grid(Page page, String condition) {
        try {
            page.setAutoCount(true);

            String hql = "from ExaPaper t where 1=1 ";

            QueryTranslate queryTranslate = new QueryTranslate(hql, condition);

            // 查询
            page = exaPaperService.findByPage(page, queryTranslate.toString());
        } catch (Exception e) {
            log.error("error", e);
        }

        return page;
    }

    @RequestMapping
    public String init(Model model, ExaPaper entity) throws Exception {
        try {
            if(entity != null && entity.getId() != null) {
                entity = exaPaperService.get(entity.getId());

                model.addAttribute("bean", entity);
            }
        } catch (Exception e) {
            log.error("error", e);
        }

        return "view/exa/exaPaperEdit";
    }

    @RequestMapping
    public String view(Model model, Long id) {
        ExaPaper exaPaper = exaPaperService.get(id);

        model.addAttribute("bean", exaPaper);
        return "view/exa/exaPaperView";
    }

    @RequestMapping
    public void save(HttpServletResponse response, Model model, @ModelAttribute("bean") ExaPaper entity)
            throws Exception {
        try {
            String[] columns = new String[]{
                    "id",
                    "paperName",
                    "startTime",
                    "endTime",
                    "paperType",
                    "paperMinute",
                    "totalScore",
                    "questionOrderType",
                    "postTime",
                    "showScoreTime",
                    "isRandPaper",
                    "status",
                    "remark"
            };

            ExaPaper target;
            if (entity.getId() != null) {
                target = exaPaperService.get(entity.getId());

                ReflectionUtils.copyBean(entity, target, columns);
            } else {
                target = entity;
            }

            exaPaperService.save(target);
        } catch (Exception e) {
            log.error("error", e);
            super.processException(response, e);
        }

        sendSuccessJSON(response, "保存成功");
    }

    @RequestMapping
    public void delete(HttpServletResponse response, Long id) throws Exception {
        exaPaperService.delete(id);

        sendSuccessJSON(response, "删除成功");
    }

    @RequestMapping
    public String addQuestion(Model model, Long paperId) throws Exception {
        try {
            ExaPaper exaPaper = exaPaperService.get(paperId);
            model.addAttribute("bean",exaPaper);
            model.addAttribute("paperId",paperId);
        } catch (Exception e) {
            log.error("error", e);
        }

        return "view/exa/addQuestion";
    }

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
    public List<Node> tree(String type, String uid, String id) throws Exception {
        TreeBranch treeBranch = new TreeBranch();
        type = StringUtils.defaultString(type, "");

        if(StringUtils.isEmpty(uid)) {
            ZTreeNode treeNode = new ZTreeNode();
            treeNode.setId("root");
            treeNode.setIsParent(true);
            treeNode.setIsLeaf(false);
            treeNode.setOpen(true);
//            treeNode.setHasChildren(true);
            treeNode.setName("试题章节树");
            treeNode.setText("试题章节树");
            treeNode.setUid("root");
            treeBranch.addTreeNode(treeNode);
        } else {
            String hql = "from ExaPaperSection order by orderNo asc";

            List<ExaPaperSection> exaPaperSections = exaPaperSectionService.findByQuery(hql);

            for (ExaPaperSection exaPaperSection : exaPaperSections) {
                ZTreeNode treeNode = new ZTreeNode();
                treeNode.setId(exaPaperSection.getClass().getSimpleName() + "_" + exaPaperSection.getId());
                treeNode.setIsLeaf(true);
                treeNode.setName(exaPaperSection.getSectionName());
                treeNode.setText(exaPaperSection.getSectionName());
                treeNode.setUid(exaPaperSection.getId().toString());
                treeNode.setType(exaPaperSection.getClass().getSimpleName());
                treeBranch.addTreeNode(treeNode);
            }
        }

        return treeBranch.getTreeNodeList();
    }

    @RequestMapping
    public String selectQuestion(Model model, Long sectionId) throws Exception {
        try {
            model.addAttribute("sectionId",sectionId);
        } catch (Exception e) {
            log.error("error", e);
        }

        return "view/exa/exaQuestionGrid";
    }

    @RequestMapping
    public void addQuestions(HttpServletResponse response, Model model,String questionIds,Long sectionId) throws Exception {
        try {
            exaQuestionManager.saveQuestions(questionIds,sectionId);
            sendSuccessJSON(response, "保存成功");
        } catch (Exception e) {
            sendFailureJSON(response, "保存失败:"+e.getMessage());
        }

    }






}