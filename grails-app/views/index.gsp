<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>البيئة المصطفوية</title>

    <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />
</head>
<body>
    <content tag="nav">
        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">الغرب <span class="caret"></span></a>
            <ul class="dropdown-menu">
                <li><a href="/family/west">عرض الأسر</a></li>
                <li><a href="/member/west">عرض الأفراد</a></li>
            </ul>
        </li>
        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">شرق آسيا <span class="caret"></span></a>
            <ul class="dropdown-menu">
                    <li><a href="/family/asia">عرض الأسر</a></li>
                <li><a href="/member/asia">عرض الأفراد</a></li>
            </ul>
        </li>
        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">العرب <span class="caret"></span></a>
            <ul class="dropdown-menu">
                    <li><a href="/family/arab">عرض الأسر</a></li>
                <li><a href="/member/arab">عرض الأفراد</a></li>
            </ul>
        </li>
    </content>

    <div class="svg" role="presentation">
        <div class="grails-logo-container">
            <asset:image src="biah.jpg" class="grails-logo"/>
        </div>
    </div>

    <div id="content" role="main">
        <section class="row colset-2-its">
            <h2>
            <div id="controllers" role="navigation">
                <table>
                <tr>
                    <td>
                        الأسرة:
                        <ul>
                            <li class="controller">
                                <g:link controller="${community.Family}" action="create">إضافة أسرة</g:link>
                            </li>
                            <li class="controller">
                                <g:link controller="${community.Family}">جميع الأسر</g:link>
                            </li>                    
                        </ul>
                    </td>
                    <td>
                        أفراد الأسرة:
                        <ul>
                            <li class="controller">
                                <g:link controller="${community.Member}" action="create">إضافة فرد الأسرة</g:link>
                            </li>
                            <li class="controller">
                                <g:link controller="${community.Member}">جميع الأفراد</g:link>
                            </li>                    
                        </ul>
                    </td>
                </tr>
                </table>
            </div>
            </h2>
        </section>
    </div>

</body>
</html>
