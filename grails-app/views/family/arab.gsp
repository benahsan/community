<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'family.label', default: 'Family')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>                 
    </head>
    <body>
        <a href="#list-family" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="exportExcel"><g:message code="default.excel.label"/></g:link></li>
                <li><a class="list" href="${createLink(uri: '/family')}"><g:message code="default.family.label"/></a></li>
                <li><a class="list" href="${createLink(uri: '/member')}"><g:message code="default.member.label"/></a></li>
                <li><g:link class="list" action="west"><g:message code="default.west.label"/></g:link></li>
                <li><g:link class="list" action="asia"><g:message code="default.asia.label"/></g:link></li>
                <li><g:link class="list" action="arab"><g:message code="default.arab.label"/></g:link></li>  
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div
        <div class="nav" role="navigation">
            <tr>
                كشف
                <td><g:link class="list" action="index">جميع الحقول</g:link>
                أو
                <td><g:link class="list" action="country">البلاد</g:link>
                أو
                <td><g:link class="list" action="contact">الاتصال</g:link>
                أو
                <td><g:link class="list" action="responsible">المسؤول</g:link>
                أو
                <td><g:link class="list" action="address">العنوان</g:link>
                أو
                <td><g:link class="list" action="members">الأفراد</g:link>
                أو
                <td><g:link class="list" action="notes">ملاحظات</g:link>
            </tr>
        </div>
        <div id="list-family" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <f:table collection="${familyList}" />
            <div class="pagination">
                <g:paginate total="${familyCount ?: 0}" />
            </div>
        </div>
    </body>
</html>