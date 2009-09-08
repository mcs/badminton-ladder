<%@ page pageEncoding="UTF-8" %>
<%@ include file="/taglibs.jsp" %>

<s:layout-render name="/layout/standard.jsp" title="DAR-Ladder">
    <s:layout-component name="contents">

        <s:form beanclass="ladder.action.admin.MatchActionBean">
            <table>
                <tr>
                    <td>Gewinner:</td>
                    <td>
                        <s:select name="winner.id">
                            <s:options-collection collection="${actionBean.ladder.players}" label="name" value="id"/>
                        </s:select>

                    </td>
                </tr>
                <tr>
                    <td>Verlierer:</td>
                    <td>
                        <s:select name="loser.id">
                            <s:options-collection collection="${actionBean.ladder.players}" label="name" value="id"/>
                        </s:select>

                    </td>
                </tr>
            </table>
            <s:submit name="setResult" value="Ergebnis übernehmen"/>
        </s:form>

    </s:layout-component>
</s:layout-render>
