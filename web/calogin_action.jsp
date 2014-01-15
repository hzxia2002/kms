<%@ page contentType="text/html;charset=GBK" %>
<%@ page import="com.jit.attr.JitAcComp"%>
<%@page import="java.security.cert.X509Certificate" %>
<%@ page import="org.springframework.security.web.authentication.AbstractProcessingFilter"%>
<%! String username="";
    String password="";
    String bmdm="";
    String sfz="";
%>
<%
    request.setCharacterEncoding("GBK");
    String myinfo = "";
    try{
        X509Certificate[] certs=(X509Certificate[])request.getAttribute("javax.servlet.request.X509Certificate");
        if(certs==null){
            out.println("错误！请提交证书！");
            return;
        }
        else
        {
            JitAcComp jitaccomp = new JitAcComp();
            X509Certificate gaX509Cert=null;
            gaX509Cert=certs[0];

            //添加审计信息
            jitaccomp.setBaseDN("c=cn");
            jitaccomp.setPrivilegeSetType(0);
            jitaccomp.setPKICertificate(gaX509Cert);
            jitaccomp.setParameter("10.15.34.62,10.1.1.103", "5056,389");//设置CA目录服务器IP及端口号

            //客户端IP获取及设置审计参数,不设定则不执行审计操作。
            String ip = request.getRemoteAddr();
            jitaccomp.setClientIP(ip);

            jitaccomp.setAuditMethod("udp");                   //设置推审计信息使用http协议
            jitaccomp.setAuditParameter("10.15.34.59", "3000");  //推审计信息

            //×××上语句中第二个IP 10.1.1.103是中央LDAP服务器，必须加上，第二个端口389是中央LDAP的端口。
            jitaccomp.isCheckCRL(true); //不设定则不做Crl的验证
            jitaccomp.isCheckCertPath(true);//不设定则不做证书链的验证

            //获取证书主题，包括姓名、身份证号、组织机构代码。
            String dn = gaX509Cert.getSubjectDN().toString();
            int x = dn.indexOf("OU=");
            String ou1 = dn.substring(x+3,x+5);
            x = dn.indexOf("OU=",x+1);
            String ou2 = dn.substring(x+3,x+5);

            x = dn.indexOf("O=");
            String o = dn.substring(x+2,x+4);

            x = dn.indexOf("L=");
            String l1 = dn.substring(x+2,x+4);

            x = dn.indexOf("L=",x+1);
            String l2 = dn.substring(x+2,x+4);
            x = dn.indexOf("ST=");
            String st = dn.substring(x+3,x+5);

            username = dn.substring(dn.indexOf("=")+1, dn.indexOf(" "));
            //   String password = dn.substring(dn.indexOf(" ")+1,dn.indexOf(","));
            bmdm = st+l2+l1+o+ou2+ou1;
            x = dn.indexOf("CN=");
            username = dn.substring(x+3, dn.indexOf(" ",x));

            int index_sfz=dn.indexOf(" ",x)+1;
            //sfz=dn.substring(index_sfz, index_sfz+18);
            String[] infomation = dn.split(" ");
            sfz = infomation[1].substring(0,infomation[1].length() - 1);
            x = dn.indexOf(" ",x);
            password = dn.substring(x+1,dn.indexOf(",",x));
            username = dn.substring(dn.indexOf("=")+1, dn.indexOf(" "));

            //password = dn.substring(dn.indexOf(" ")+1,dn.indexOf(","));
            request.setAttribute("cert",gaX509Cert);
        }
    }
    catch(Exception e){
    }
%>
<body class="userlogin_body">
<div style="width:100%"  align="center">
    <div class="login_bg_div">
        <div style="padding-top:150px;padding-left:0px">
            <%
                if (session.getAttribute(AbstractProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY) != null) {
            %>
            <p>
                ${SPRING_SECURITY_LAST_EXCEPTION.message}
            </p>
            <%
                }
            %>
            <br />
            <br />
            <br />
            <br />
            <br />
            <table width="367" border="0" align="left" cellpadding="0" cellspacing="0">
                <tr>
                    <td width="42%" nowrap>
                        <a href="https://10.15.69.238/calogin_action.jsp"><img src="${ctx}/skin/images/login.png" border="0" width="99" height="36" /></a>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>

<form action="<%=request.getContextPath()%>/j_spring_security_check" method="post" name="loginForm">
    <input id="j_username" name="j_username" type="password" size="15" value='<%=sfz%>' style='display:none'>
    <input id="j_password" name="j_password" type="hidden" size="15" value='<%=username%>' style='display:none'>
</form>
</body>
<script>loginForm.submit();</script>
