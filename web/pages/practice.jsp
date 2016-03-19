
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"  dir="ltr" lang="zh-CN" xml:lang="zh-CN">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglibs.jsp" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <%--<title>经侦辅助办案知识库</title>--%>
    <link href="${ctx}/skin/css/style.css" rel="stylesheet" type="text/css" />

    <script type="text/javascript" src="${ctx}/js/jquery/jquery-1.7.2.js"></script>

    <script type="text/javascript" src="${ctx}/pages/view.js"></script>
    <script type="text/javascript" src="${ctx}/pages/practice.js"></script>

    <style type="text/css">
        .submitCss{background: url("../skin/images/tj.jpg") no-repeat;}
    </style>
    <style>
        #directionContainer ul{
            margin:0px;
            padding: 0px 0px 0px 20px;
        }
        .main{
            width:1024px;
        }

        #directionWrapper{
            padding:15px 7px;
            width:234px;
            border:1px solid #CCC;
        }
        .directionTitle{
            font-weight: bold;
            font-size: 14px;
            padding-bottom:3px;
            border-bottom: 1px dashed #ccc;
        }
        .sectionItem{
            height:20px;
            padding: 4px;
            font-size: 14px;
            font-weight: 500;
            font-family: 微软雅黑,宋体,黑体;
            color: #136ec2;
        }

        .sectionItem a:hover {
            color: #136ec2;
            text-decoration: underline;
        }

        .sectionItem a:visited {
            color: #136ec2;
            text-decoration: none;
        }

        .sectionItem span{
            *zoom:1;
            display:inline-block;
        }

        .itemTitle a {
            color: #136ec2;
        }

        .itemTitle {
            color: #136ec2;
            padding-left: -10px;
        }

        .itemTitle span[style]{
            float:left !important;
            color: #136ec2 !important;
            margin-bottom: 5px !important;
            line-height: 22px !important;
            font-size: 14px !important;
            font-family: 微软雅黑,宋体,黑体 !important;
            font-weight: 500 !important;
        }

        p span {
            color: #333 !important;
            margin-bottom: 5px !important;
            text-indent: 2em !important;
            line-height: 22px !important;
            font-size: 14px !important;
            font-family: 微软雅黑,宋体,黑体 !important;
        }

        /*.fixTop{*/
        /*position: fixed;*/
        /*top: -1px;*/
        /*}*/

        .Header .logo .search .kuang .input11{
            float:left;
            width: 360px;
            border: 2px solid #008eca;
            font-family: "微软雅黑,宋体";
            font-size: 14px;
            color: #444444;
            height: 27px;
            line-height: 27px;
            text-indent: 10px;
        }
    </style>

</head>

<body>
<div class="Header">
    <%@include file="common/nav.jsp"%>
    <div style="display: none;" id="pageType" type="5"></div>
</div>
<div class="clr"></div>
<div class="ContentHome">
    <div style="text-align: center;padding-bottom: 20px">
            <span style="font-size: 24px;font-weight: bold">
                ${paper.paperName}
            </span>

    </div>
    <div style="text-align: center;padding-bottom: 20px">
          <span style="font-size: 12px;font-weight: bold">
            <input type="button" value="查看答案" class="btn_Search" onclick="showAnswer();"/>&nbsp;
         </span>
    </div>


    <ul>
        <c:forEach items="${sections}" var="section" varStatus="sectionStatus">
            <li style="padding-bottom: 20px;clear: both">
                <div style="font-size: 16px;font-family: 宋体;padding-bottom: 8px;">
                    <span style="font-size: 16px;font-weight: bold"> </span>${section.sectionName}
                </div>
                <ul style="padding-left: 20px;padding-right: 5%">
                    <c:forEach items="${details}" var="paperDetail" varStatus="status">
                        <c:set var="question" value="${paperDetail.question}"/>
                        <c:if test="${section.id==paperDetail.sectionId}">
                            <li style="padding-bottom: 20px;clear: both">
                                <div style="font-size: 16px;font-family: 宋体;padding-bottom: 8px;">
                                    <span style="font-size: 16px;font-weight: bold"> ${status.index+1}.</span>${question.content}
                                </div>
                                <ul style="padding-left: 20px;padding-right: 5%">
                                    <c:forEach items="${question.options}" var="option">
                                        <li style="padding-bottom: 5px;float:left;padding-right: 25px;height: 28px;">
                                            <c:choose >
                                                <c:when test="${question.questionType==1}">
                                                    <span style="font-size: 14px;font-weight: bold">${option.optionKey}.</span>&nbsp;<input type="radio" name="question_${question.id}" value="${option.id}">${option.optionOption}
                                                </c:when>
                                                <c:when test="${question.questionType==2}">
                                                    <span style="font-size: 14px;font-weight: bold">${option.optionKey}.</span>&nbsp;<input type="checkbox" name="question_${question.id}" value="${option.id}">${option.optionOption}
                                                </c:when>
                                            </c:choose>
                                        </li>
                                    </c:forEach>
                                </ul>
                                <div style="font-size: 16px;font-family: 宋体;padding-bottom: 8px;display: none;clear: both;" class="answer">
                                    <span style="font-size: 16px;font-weight: bold"> 答案:</span>
                                    <span style="color: red">
                                    <c:if test="${not empty question.skey}">${question.skey}</c:if>
                                    <c:if test="${not empty question.skey}">${question.keyDesc}</c:if>
                                        </span>
                                </div>
                            </li>
                        </c:if>
                    </c:forEach>
                </ul>

            </li>
        </c:forEach>
    </ul>

</div>
<%@include file="common/footer.jsp"%>
</body>
</html>

