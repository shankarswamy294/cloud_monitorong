<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
    <head>
    </head>
    <body>
        <h3>Welcome, Enter The Employee Details</h3>
        <form:form method="POST" action="addClient" modelAttribute="NewClientDetails">
             <table>
                <tr>
                    <td><form:label path="subscription">Subscription</form:label></td>
                    <td><form:input path="subscription"/></td>
                </tr>
                <tr>
                    <td><form:label path="client">client</form:label></td>
                    <td><form:input path="client"/></td>
                </tr>
                <tr>
                    <td><form:label path="key">key</form:label></td>
                    <td><form:input path="key"/></td>
                </tr>
                
                <tr>
                    <td><form:label path="tenant">tenant</form:label></td>
                    <td><form:input path="tenant"/></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Submit"/></td>
                </tr>
            </table>
        </form:form>
    </body>
</html>