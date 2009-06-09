<%@ page pageEncoding="UTF-8" %>
<%@ include file="/taglibs.jsp" %>

<s:layout-definition>
    <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
    <html>
        <head>
            <title>Badminton-Ladder - ${title}</title>
            <link rel="stylesheet"
                  type="text/css"
                  href="${pageContext.request.contextPath}/ladder.css"/>
        </head>
        <body>
            <div id="root">
                <div id="kopf">
                    <%@ include file="/layout/header.jsp" %>
                </div>
                
                <div id="links">
                    <%@ include file="/layout/globalnavigation.jsp" %>
                </div>
                
                <div id="inhalt">
                    <s:messages/><s:errors/>
                    <s:layout-component name="contents"/>
                </div>
                
                <div id="fuss">
                    &copy; 2009 Marcus Kra&szlig;mann
                </div>
            </div>
        </body>
    </html>
</s:layout-definition>