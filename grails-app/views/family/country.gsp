                <td><g:link class="list" action="notes">الأفراد</g:link>
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
                <li><a class="list" href="${createLink(uri: '/family')}">عرض الأسر</a></li>
                <li><a class="list" href="${createLink(uri: '/member')}">عرض الأفراد</a></li>
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
            <table>
                <td>
                    <ul>
                        <li><% counter=0 %><g:findAll in="${familyList}" expr="${it.country.region == "العرب"}" ><% counter++ %></g:findAll>العرب: <%= counter %></li>
                        <li><% counter=0 %><g:findAll in="${familyList}" expr="${it.country.region == "شرق آسيا"}" ><% counter++ %></g:findAll>شرڤ آسيا: <%= counter %></li>
                        <li><% counter=0 %><g:findAll in="${familyList}" expr="${it.country.region == "الغرب"}" ><% counter++ %></g:findAll>الغرب: <%= counter %></li>
                    </ul>
                </td>
                <td>
                    <ul>
                        <% [ "اليمن",
                            "إندونيسيا",
                            "ماليزيا",
                            "بريطانيا",
                            "أستراليا",
                            "جنوب أفريقيا",
                            "أمريكا",
                            "مصر",
                            "سوريا",
                            "كندا",
                            "السويد",
                            "إثيوبيا",
                            "ألمانيا",
                            "الأردن",
                            "السودان",
                            "الصومال",
                            "المكسيك",
                            "نميبيا",
                            "بلجيكا",
                            "تايلندا",
                            "جزر القمر",
                            "سنغافورا",
                            "كرغستان",
                            "باكستان",
                            "الهند",
                            "بروناي",
                            "فيليبين" ].each { country -> %>             
                                <% counter=0 %>
                                <g:findAll in="${familyList}" expr="${it.country.country == country }" >
                                    <% counter++ %>
                                </g:findAll>
                                <g:if test="${counter!=0}"><li><%= "${country}: ${counter}" %></li></g:if>
                        <% } %>
                        
                    </ul>
                </td>
            </table>
            <f:table collection="${familyList}" properties="['fileNumber', 'headOfFamily', 'country', 'member' ]" />

            <div class="pagination">
                <g:paginate total="${familyCount ?: 0}" />
            </div>
        </div>
    </body>
</html>