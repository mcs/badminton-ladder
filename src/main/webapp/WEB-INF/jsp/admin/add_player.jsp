<%@ page pageEncoding="UTF-8" %>
<%@ include file="/taglibs.jsp" %>

<s:layout-render name="/layout/standard.jsp" title="DAR-Ladder">
    <s:layout-component name="contents">

        <h1>Neuer Spieler</h1>
        <p>Hier kann ein neuer Spieler zur Liga hinzugef&uuml;gt werden.</p>

        <s:form>
            <s:hidden name="ladder.id"/>
            <s:label for="player.name">Name des neuen Spielers:</s:label>
            <s:text id="player.name" name="player.name" size="20"/>
            <br/>
            <s:submit name="saveNewPlayer">Spieler anlegen</s:submit>
        </s:form>
        
    </s:layout-component>
</s:layout-render>
