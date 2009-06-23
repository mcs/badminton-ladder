<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/taglibs.jsp" %>

<div id="imageHeader">
    <table style="padding: 5px; margin: 0px; width: 100%;">
        <tr>
            <td id="pageHeader">otr java mirror</td>
            <td id="loginInfo">
                <c:if test="${not empty user}">
                    Welcome, ${user.login}
                    |
<%--
                    <s:link beanclass="otr.mirror.web.action.LogoutActionBean">
                        <fmt:message key="site.logout"/>
                    </s:link>
--%>
                </c:if>
            </td>
        </tr>
    </table>
    <div id="navLinks">
    <s:link beanclass="ladder.action.IndexActionBean">Startseite</s:link>
    <s:link beanclass="ladder.action.LadderActionBean">Rangliste</s:link>
    &nbsp;
    <a href="http://www.mtv-pattensen.de/">MTV Pattensen</a>
        <c:if test="${not empty user}">
            <s:link beanclass="otr.mirror.web.action.admin.SettingsActionBean">
                <fmt:message key="site.admin_settings"/>
            </s:link>
        </c:if>
    </div>
</div>