<%@ page pageEncoding="UTF-8" %>
<%@ include file="/taglibs.jsp" %>

<s:layout-render name="/layout/standard.jsp" title="challenge">
    <s:layout-component name="contents">

        <s:form beanclass="${actionBean.class}">
            M&ouml;chten Sie wirklich ${actionBean.challenged.name} herausfordern?
            <s:submit name="yes" value="Ja"/>
            <s:submit name="no" value="Nein"/>
        </s:form>

    </s:layout-component>
</s:layout-render>
