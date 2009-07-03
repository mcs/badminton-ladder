<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/taglibs.jsp" %>

<div id="imageHeader">
    <table style="padding: 5px; margin: 0px; width: 100%;">
        <tr>
            <td id="pageHeader">Badminton Liga</td>
            <td id="loginInfo">
                <c:if test="${not empty user}">
                    ${user.login}
                    |
                    <s:link beanclass="ladder.action.LogoutActionBean">
                        <fmt:message key="site.logout"/>
                    </s:link>
                </c:if>
            </td>
        </tr>
    </table>
    <div id="navLinks">
        <s:link beanclass="ladder.action.IndexActionBean">Startseite</s:link>
        <c:if test="${empty user}">
            <s:link beanclass="ladder.action.LoginActionBean">Anmelden</s:link>
        </c:if>
        <s:link beanclass="ladder.action.LadderActionBean">Rangliste</s:link>
        &nbsp;
        <a href="http://www.mtv-pattensen.de/">MTV Pattensen</a>
    </div>
</div>