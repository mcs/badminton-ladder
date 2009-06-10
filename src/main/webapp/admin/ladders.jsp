<%@ page pageEncoding="UTF-8" %>
<%@ include file="/taglibs.jsp" %>

<s:layout-render name="/layout/standard.jsp" title="Rangliste">
    <s:layout-component name="contents">

        vorhandene Ligen:
        <ul>
            <c:forEach items="${actionBean.ladders}" var="ladder">
                <li><c:out value="${ladder}"/></li>
            </c:forEach>
        </ul>
        <hr/>
        <s:link beanclass="ladder.action.admin.LadderActionBean">
            Neue Liga anlegen...
        </s:link>
        
    </s:layout-component>
</s:layout-render>
