<%@ page pageEncoding="UTF-8" %>
<%@ include file="/taglibs.jsp" %>

<s:layout-render name="/layout/standard.jsp" title="Login">
    <s:layout-component name="contents">

        <s:form beanclass="ladder.action.LoginActionBean">
            <table>
                <tr>
                    <td><s:label for="username"/></td>
                    <td><s:text id="username" name="username"/></td>
                </tr>
                <tr>
                    <td><s:label for="password"/></td>
                    <td><s:password id="password" name="password"/></td>
                </tr>
            </table>
            <s:submit name="login">Login</s:submit>
        </s:form>

    </s:layout-component>
</s:layout-render>
