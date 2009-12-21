<%@ page pageEncoding="UTF-8" %>
<%@ include file="/taglibs.jsp" %>

<s:layout-render name="/layout/standard.jsp" title="Spieler herausfordern">
    <s:layout-component name="contents">

        <table>
            <tr>
                <td>
                    Herausforderer:
                </td>
                <td>
                    ${player.name} (Rang ${player.rank})
                </td>
                <td>
                    Gegner:
                </td>
                <td>
                    ${actionBean.challenged.name} (Rang ${actionBean.challenged.rank})
                </td>
            </tr>
        </table>
        Möchten Sie ${actionBean.challenged.name} wirklich herausfordern?
        <s:form beanclass="${actionBean.class}">
            <s:submit name="confirm">Ja</s:submit>
            <s:submit name="abort">Nein</s:submit>
        </s:form>
    </s:layout-component>
</s:layout-render>
