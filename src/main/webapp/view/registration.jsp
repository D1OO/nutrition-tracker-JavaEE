<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="i18n/messages"/>

<html>
<jsp:include page="/view/fragments/head.jsp">
    <jsp:param name="title" value="Sign Up • Dreamfit"/>
</jsp:include>
<body>
<jsp:include page="/view/fragments/navbar.jsp"/>

<div class="maincontent d-flex flex-nowrap p-lg-3" style="height:100%; background: #eeeeee">
    <img class="pagepic" src="static/img/signuppic.jpg" alt="">
    <div class="mr-auto">
        <div class="row d-flex justify-content-start form">
            <div class=" mb-5 mb-md-0">
                <form class="form" method="post" action="sign-up">
                    <%--                    <p class="error-validation"--%>
                    <%--                       th:each="error : ${#fields.errors('global')}"--%>
                    <%--                       th:if="${#fields.hasGlobalErrors()}"--%>
                    <%--                       th:text="${error}">Validation error</p>--%>
                    <div class="form-group col-md-11">
                        <label class="control-label" for="username"><fmt:message key="login.email"/></label>
                        <input class="form-control"
                               data-msg="Incorrect email"
                               data-rule="email" id="username"
                               name="username"/>
                    </div>
                    <div class="form-group col-md-11">
                        <label class="control-label" for="password"><fmt:message key="login.password"/></label>
                        <input class="form-control"
                               data-msg="Enter a valid password <br>(8+ digit or latin characters)"
                               data-rule="pwrd" id="password"
                               name="password"
                               type="password"/>
                        <div class="validate"></div>
                    </div>
                    <div class="form-group col-md-11">
                        <label class="control-label" for="userProfile.firstName"><fmt:message
                                key="login.first-name"/></label>
                        <input class="form-control"
                               data-msg="Enter a valid first name"
                               data-rule="firstnameexp" id="userProfile.firstName"
                               name="firstName"/>
                        <div class="validate"></div>
                    </div>
                    <div class="form-group col-md-11">
                        <label class="control-label" for="userProfile.lastName"><fmt:message
                                key="login.last-name"/></label>
                        <input class="form-control"
                               data-msg="Enter a valid last name"
                               data-rule="lastnameexp" id="userProfile.lastName"
                               name="lastName"/>
                        <div class="validate"></div>
                    </div>

                    <div class="form-group col-md-5">
                        <button class="pretty-button submit-button d-block w-100" <%--disabled="disabled"--%> type="submit">
                            <fmt:message key="login.sign-up"/></button>
                    </div>
                    <div class="form-group col-md-12 ">
                        <span class="mr-2"><fmt:message key="login.already-signed-up"/></span>
                        <a style="color:#34ce57" href="login"><fmt:message key="login.login"/></a>
                    </div>

                    <%--                    <div class="alert alert-info col-md-12 mb-3" role="alert" th:if="${logout}">You've been logged--%>
                    <%--                        out--%>
                    <%--                        successfully.--%>
                    <%--                    </div>--%>
                    <%--                    <div class="alert alert-danger col-md-12 mb-3" role="alert" th:if="${error}">Invalid Username or--%>
                    <%--                        Password!--%>
                    <%--                    </div>--%>
                    <%--                    <div class="col-md-12 mb-3" style="background: #eeeeee">--%>
                    <%--                        <div class="loading" style="background: #eeeeee">Loading</div>--%>
                    <%--                        <div class="error-message"></div>--%>
                    <%--                        <div class="sent-message">filler</div>--%>
                    <%--                    </div>--%>
                </form>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/view/fragments/footer.jsp"/>
<div>
    <!-- Vendor JS Files -->
    <script src="static/vendor/jquery/jquery.min.js"></script>
    <script src="static/vendor/jquery/jquery-migrate.min.js"></script>
    <script src="static/vendor/jquery/jquery-form-plugin.js"></script>
    <script src="static/vendor/bootstrap/js/bootstrap.min.js"></script>
    <script src="static/vendor/easing/easing.min.js"></script>
    <script src="static/vendor/isotope/isotope.pkgd.min.js"></script>
    <script src="static/vendor/aos/aos.js"></script>
    <script src="static/vendor/owlcarousel/owl.carousel.min.js"></script>

    <!-- Template Main JS File -->
    <script src="static/js/main.js"></script>
    <script src="static/js/login-form-validation.js"></script>

    <script id="change-lang-param" type="text/javascript">

        $(document).ready(function () {
            AOS.init({
                easing: 'ease',
                duration: 1000,
            });

            $(".locale").click(function (event) {
                var selectedOption = event.target.id;
                if (selectedOption != '') {
                    location.replace('?lang=' + selectedOption);
                }
            });

            $('.slink-container').hover(function () {
                $(this).css("text-decoration", "underline");
            }, function () {
                // on mouseout, reset the background colour
                $(this).css("text-decoration", "none");
            });

            $('.form-control').keyup(function () {
                if (allFilled()) {
                    $('.pretty-button').removeAttr('disabled')
                } else {
                    $('.pretty-button').prop("disabled", true);
                }
            });

            function allFilled() {
                var filled = true;
                $('.form-control').each(function () {
                    if ($(this).val() == '') filled = false;
                });
                return filled;
            }

            $('.submit-button').hover(function () {
                if (allFilled()) {
                    $('.submit-button').removeAttr('disabled')
                } else {
                    $('.submit-button').prop("disabled", true);
                }
            });
        });
    </script>
</div>

</body>
</html>
