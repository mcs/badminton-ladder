<%@ page pageEncoding="UTF-8" %>
<%@ include file="/taglibs.jsp" %>

<s:useActionBean var="matchActionBean" beanclass="ladder.action.admin.MatchActionBean"/>
<s:useActionBean var="playerActionBean" beanclass="ladder.action.admin.PlayerActionBean"/>
<s:useActionBean var="challengeActionBean" beanclass="ladder.action.ChallengeActionBean" event="doNothing"/>

<s:layout-render name="/layout/standard.jsp" title="Ranking">
    <s:layout-component name="contents">

        Aktuelle Rangliste:
        <table border="1px">
            <thead>
                <tr>
                    <th>Rang</th>
                    <th>Spieler</th>
                    <sec:allowed bean="challengeActionBean">
                        <th>Herausfordern</th>
                    </sec:allowed>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${actionBean.ladder.players}" var="player" varStatus="i">
                    <tr>
                        <td><c:out value="${i.count}"/></td>
                        <td><c:out value="${player.name}"/></td>
                            <sec:allowed bean="challengeActionBean">
                            <td>
                                <s:link beanclass="${challengeActionBean.class}">
                                    <s:param name="challenged.id" value="${player.id}"/>
                                    Herausfordern
                                </s:link>
                            </td>
                        </sec:allowed>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <sec:allowed bean="playerActionBean">
            <h2>Adminbereich</h2>
            <s:link beanclass="${playerActionBean.class}" event="addPlayer">
                <s:param name="ladder.id" value="${actionBean.ladder.id}"/>
                Neuen Spieler hinzuf&uuml;gen...
            </s:link>
        </sec:allowed>
    </s:layout-component>
</s:layout-render>
