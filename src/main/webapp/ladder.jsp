<%@ page pageEncoding="UTF-8" %>
<%@ include file="/taglibs.jsp" %>

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

        <s:link beanclass="ladder.action.admin.MatchActionBean">
            Match eintragen...
        </s:link>
    </s:layout-component>
</s:layout-render>
