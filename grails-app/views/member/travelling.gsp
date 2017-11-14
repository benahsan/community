<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'member.label', default: 'Member')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-member" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="exportExcel"><g:message code="default.excel.label"/></g:link></li>
                <li><a class="list" href="${createLink(uri: '/family')}"><g:message code="default.family.label"/></a></li>
                <li><a class="list" href="${createLink(uri: '/member')}"><g:message code="default.member.label"/></a></li>
                <li><g:link class="list" action="travelling"><g:message code="default.travelling.label"/></g:link></li>
                <li><g:link class="list" action="resident"><g:message code="default.resident.label"/></g:link></li>
                <li><g:link class="list" action="west"><g:message code="default.west.label"/></g:link></li>
                <li><g:link class="list" action="asia"><g:message code="default.asia.label"/></g:link></li>
                <li><g:link class="list" action="arab"><g:message code="default.arab.label"/></g:link></li>              
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="list-member" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <f:table collection="${memberList}" properties="["name", "relationship", "headOfFamily", "nameInPassport", 
            "generalNumber", "gender", "birth", "age", "job", "country", "studies", "travelling", "travel", "deposit"]" />

            <div class="pagination">
                <g:paginate total="${memberCount ?: 0}" />
            </div>
        </div>
    </body>
</html>