<%@ page pageEncoding="UTF-8" %>
<%@ include file="/taglibs.jsp" %>

<s:useActionBean var="matchActionBean" beanclass="ladder.action.admin.MatchActionBean"/>

<s:layout-render name="/layout/standard.jsp" title="DAR-Ladder">
    <s:layout-component name="contents">

        Aktuelle Rangliste:
        <table border="1px">
            <thead>
            <tr>
                <th>Rang</th>
                <th>Spieler</th>
            </tr>
            </thead>
            <tbody>
                <c:forEach items="${actionBean.ladder.players}" var="player" varStatus="i">
                    <tr>
                        <td><c:out value="${i.count}"/></td>
                        <td><c:out value="${player.name}"/></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <sec:allowed bean="matchActionBean">
            <s:link beanclass="${matchActionBean.class}">
                Match eintragen...
            </s:link>
        </sec:allowed>
    </s:layout-component>
</s:layout-render>
