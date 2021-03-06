<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="i18n/messages"/>


<div class="d-flex flex-grow-1 mt-2" data-aos="fade-zoom-in" data-aos-offset="0"
     style="height: 100%; width: 100%; background: #eeeeee; border: 1px solid #b8b8b8; border-radius: 0.5rem;">
    <div class="maincontent p-lg-3 d-flex flex-grow-1"
         style="border: 7px solid rgba(31,171,0,0.27); border-radius: 0.5rem; background: rgba(194,175,215,0.34) ">
        <div class="d-flex flex-grow-1 flex-column mb-5 mb-md-0 align-content-md-start"
             style="margin-top: 10%">
            <h1 style="text-align: center">404 Not Found</h1>
            <h2 style="text-align: center">Sorry, this resource isn't present here anymore :(</h2>
        </div>
    </div>
</div>