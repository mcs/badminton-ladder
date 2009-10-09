<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/taglibs.jsp" %>

<div id="imageHeader">
    <table style="padding: 5px; margin: 0px; width: 100%;">
        <tr>
            <td id="pageHeader">Badminton Liga</td>
            <td id="loginInfo">
                <c:choose>
                    <c:when test="${empty user}">
                        <s:link beanclass="ladder.action.LoginActionBean">
                            <fmt:message key="login"/>
                        </s:link>
                    </c:when>
                    <c:otherwise>
                        ${user.login}
                        |
                        <s:link beanclass="ladder.action.LogoutActionBean">
                            <fmt:message key="logout"/>
                        </s:link>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </table>
    <div id="navLinks">
        <s:link beanclass="ladder.action.IndexActionBean"><fmt:message key="home"/></s:link>
        <s:link beanclass="ladder.action.LadderActionBean"><fmt:message key="ranking"/></s:link>
        &nbsp;
        <a href="http://www.mtv-pattensen.de/">MTV Pattensen</a>
    </div>
    <br/>
    <div>
        <c:if test="${not empty player}">
            <s:form beanclass="${actionBean.class}">
                ausw&auml;hlbare Spieler:
                <s:select name="player.id" onchange="submit()">
                    <s:options-collection collection="${players}" value="id"/>
                </s:select>
                <s:submit name="changeSelectedPlayer" value="Ändern"/>
            </s:form>
        </c:if>
    </div>
</div>