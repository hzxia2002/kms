package com.article.controller;

import com.article.daoservice.ExaQuestionDbService;
import com.article.daoservice.ExaQuestionOptionsService;
import com.article.daoservice.ExaQuestionService;
import com.article.domain.ExaQuestion;
import com.article.domain.ExaQuestionDb;
import com.article.domain.ExaQuestionOptions;
import com.article.manager.ExaQuestionManager;
import com.comet.core.controller.BaseCRUDActionController;
import com.comet.core.orm.hibernate.Page;
import com.comet.core.orm.hibernate.QueryTranslate;
import com.comet.core.security.util.SpringSecurityUtils;
import com.comet.core.utils.ReflectionUtils;
import com.comet.system.domain.SysDept;
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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @author: System
 */
@Controller
public class ExaQuestionController extends BaseCRUDActionController<ExaQuestion> {
    private static Log log = LogFactory.getLog(ExaQuestionController.class);

    @Autowired
    private ExaQuestionDbService exaQuestionDbService;

    @Autowired
    private ExaQuestionService exaQuestionService;

    @Autowired
    private ExaQuestionManager exaQuestionManager;

    @Autowired
    private ExaQuestionOptionsService exaOptionsService;

    @RequestMapping
    @ResponseBody
    public Page<ExaQuestion> grid(Page page, String condition, String dbId) {
        try {
            page.setAutoCount(true);

            String hql = "from ExaQuestion t where 1=1 " ;

            if(StringUtils.isNotBlank(dbId)) {
                hql += " and t.db = " + dbId;
            }

            QueryTranslate queryTranslate = new QueryTranslate(hql, condition);

            // 查询
            page = exaQuestionService.findByPage(page, queryTranslate.toString());
        } catch (Exception e) {
            log.error("error", e);
        }

        return page;
    }

    @RequestMapping
    public String init(Model model, Long id) throws Exception {
//        try {
//            ExaQuestionDb exaQuestionDb = exaQuestionDbService.get(dbId);
//            model.addAttribute("db", exaQuestionDb);
//            model.addAttribute("bean", new ExaQuestion());
//
//        } catch (Exception e) {
//            log.error("error", e);
//        }

        try {
            if(id != null) {
                ExaQuestion question = exaQuestionService.get(id);

                List<ExaQuestionOptions> optionsList = exaOptionsService.find("from ExaQuestionOptions where questionId=" + id);
                ArrayList arrayList = new ArrayList();
                for (ExaQuestionOptions exaQuestionOptions : optionsList) {
                    HashMap data = new HashMap();
                    data.put("id", exaQuestionOptions.getId());
                    data.put("indexNo", exaQuestionOptions.getOptionKey());
                    data.put("content", exaQuestionOptions.getOptionOption());
                    arrayList.add(data);
                }

                model.addAttribute("bean", question);
                model.addAttribute("options", arrayList);
            }

        } catch (Exception e) {
            log.error("error", e);
        }

        return "view/exa/exaQuestionAdd";
    }

    @RequestMapping
    public String view(Model model, Long id) {
        ExaQuestion exaQuestion = exaQuestionService.get(id);

        model.addAttribute("bean", exaQuestion);
        return "view/exa/exaQuestionView";
    }

    @RequestMapping
    public void save(HttpServletResponse response, Model model, @ModelAttribute("bean") ExaQuestion entity)
            throws Exception {
        try {
            String[] columns = new String[]{
                    "id",
                    "db",
                    "questionType",
                    "questionLevel",
                    "questionFrom",
                    "status",
                    "content",
                    "postTime",
                    "skey",
                    "keyDesc"
            };

            ExaQuestion target;
            if (entity.getId() != null) {
                target = exaQuestionService.get(entity.getId());

                ReflectionUtils.copyBean(entity, target, columns);
            } else {
                target = entity;
            }

            exaQuestionService.save(target);
        } catch (Exception e) {
            log.error("error", e);
            super.processException(response, e);

            return;
        }

        sendSuccessJSON(response, "保存成功");
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
            treeNode.setName("题库树");
            treeNode.setText("题库树");
            treeNode.setUid("root");
            treeBranch.addTreeNode(treeNode);
        } else {
            String hql = "from ExaQuestionDb";

            List<ExaQuestionDb> exaQuestionDbs = exaQuestionDbService.findByQuery(hql);

            for (ExaQuestionDb exaQuestionDb : exaQuestionDbs) {
                ZTreeNode treeNode = new ZTreeNode();
                treeNode.setId(exaQuestionDb.getClass().getSimpleName() + "_" + exaQuestionDb.getId());
                treeNode.setIsLeaf(true);
                treeNode.setName(exaQuestionDb.getDbName());
                treeNode.setText(exaQuestionDb.getDbName());
                treeNode.setUid(exaQuestionDb.getId().toString());
                treeNode.setType(exaQuestionDb.getClass().getSimpleName());
                treeBranch.addTreeNode(treeNode);
            }
        }

        return treeBranch.getTreeNodeList();
    }


    @RequestMapping
    public void delete(HttpServletResponse response, Long id) throws Exception {
        exaQuestionService.delete(id);

        sendSuccessJSON(response, "删除成功");
    }

    @RequestMapping
    public void saveData(HttpServletResponse response, Model model, @ModelAttribute("bean") ExaQuestion entity,
                         String option,String opIndexNo,String opIds,Integer flag,Long dbId)
            throws Exception {
        try {
            String[] columns = new String[]{
                    "id",
                    "content",
                    "skey",
                    "keyDesc",
                    "questionType"
            };

            ExaQuestion target;
            if (entity.getId() != null&&flag==1) {
                target = exaQuestionService.get(entity.getId());
                target.setUpdateUser(SpringSecurityUtils.getCurrentUser().getLoginName());
                target.setUpdateTime(new Timestamp(System.currentTimeMillis()));

                ReflectionUtils.copyBean(entity, target, columns);
            } else {
                target = entity;
                target.setCreateUser(SpringSecurityUtils.getCurrentUser().getLoginName());
                target.setCreateTime(new Timestamp(System.currentTimeMillis()));
                target.setId(null);
            }
            ExaQuestionDb exaQuestionDb = exaQuestionDbService.get(dbId);
            target.setDb(exaQuestionDb);
            //保存题目及选项
            ArrayList arrayList = new ArrayList();
            String[] options = option.split(",");
            String[] indexNos = opIndexNo.split(",");
            String[] ids = opIds.split(",");
            for (int i = 0; i < indexNos.length; i++) {
                ExaQuestionOptions exaQuestionOption = new ExaQuestionOptions();
                String no = indexNos[i];
                String opt = options[i];
                String id = ids[i];
                exaQuestionOption.setOptionKey(no);
                exaQuestionOption.setOptionOption(opt);
                if(org.apache.commons.lang3.StringUtils.isNotEmpty(id.trim())&&flag==1){
                    exaQuestionOption.setId(Long.valueOf(id));
                }

                arrayList.add(exaQuestionOption);
            }
            exaQuestionManager.saveQuestion(target,arrayList);
        } catch (Exception e) {
            log.error("error", e);
            super.processException(response, "保存试题失败");

            return;
        }

        sendSuccessJSON(response, "保存成功");
    }


    @RequestMapping
    @ResponseBody
    public Map getQuestion(Long questionId) {
        HashMap hashMap = new HashMap();
        try {
            hashMap.put("question",exaQuestionService.get(questionId));
            List<ExaQuestionOptions> optionsList = exaOptionsService.find("from ExaQuestionOptions where questionId=" + questionId);
            ArrayList arrayList = new ArrayList();
            for (ExaQuestionOptions exaQuestionOptions : optionsList) {
                HashMap data = new HashMap();
                data.put("id",exaQuestionOptions.getId());
                data.put("indexNo",exaQuestionOptions.getOptionKey());
                data.put("content",exaQuestionOptions.getOptionOption());
                arrayList.add(data);
            }
            hashMap.put("options", arrayList);

            // 查询
        } catch (Exception e) {
            log.error("error", e);
        }

        return hashMap;
    }
}