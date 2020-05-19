<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="i18n/messages"/>
<%--<fmt:setBundle basename="messages"/>--%>
<nav class="navbar navbar-light custom-navbar" id="navbar2">
    <div class="container px-0">
        <div class="align-self-start d-inline-flex mt-sm-1">
                <span class="font-rubick navbar-light p-lg-2 mr-lg-5 "
                      style="color: #3a2332; font-weight: lighter; font-size: 1em !important;  background: rgb(178,183,223);
background: radial-gradient(circle, rgba(178,183,223,1) 0%, rgba(232,253,203,0.7245097868248862) 0%, rgba(178,183,223,1) 100%);  font-size: 1.2em">${localizedDate}</span>
        </div>
        <div class="d-flex flex-grow-1 justify-content-center align-items-end " style="position: relative; left: -2%;">
            <a href="${pageContext.request.contextPath}"><h1
                    style="font-family:'Run Medium',serif;font-weight:normal;font-size:42px">dreamfit</h1></a>
        </div>
        <%--        <a class="navbar-brand" id="justatest"></a>--%>
        <div class="align-self-start d-inline-flex mt-sm-1">
            <ul class="list-inline  navbar-right mr-4">
                <c:if test="${role == 'GUEST'}">
                    <li class=" list-inline-item">
                        <div>
                            <a class="pretty-button bg" href="/login"><fmt:message key="sign-in"/></a>
                            <a class="pretty-button bg signupButton" href="javascript:void(0)"
                               href="${pageContext.request.contextPath}/registration"><fmt:message
                                    key="create-account"/></a>
                        </div>
                    </li>
                </c:if>
                <c:if test="${role != 'GUEST'}">
                    <li class="list-inline-item">
                        <ul class="list-inline mt-0" style="margin-top: 0;">
                            <li class="list-inline-item" style="font-size: 1.1em; max-width: 560px">
                                <span class="logged-in-as mt-sm-1" >${userFirstName} ${userLastName}</span>
                            </li>
                            <li class="list-inline-item" id="logout-link">
                                <a href="${pageContext.request.contextPath}/logout">
                                    <button class="pretty-button bg signupButton">
                                        <fmt:message key="logout"/>
                                    </button>
                                </a>
                            </li>
                        </ul>
                    </li>
                </c:if>
            </ul>
        </div>
        <a class="burger" data-target="#main-navbar" data-toggle="collapse" href="#">
            <span></span>
        </a>
    </div>
</nav>