<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="i18n/messages"/>

<html>
<jsp:include page="/view/fragments/head.jsp">
    <jsp:param name="title" value="Login • Dreamfit"/>
</jsp:include>
<body>
<jsp:include page="/view/fragments/navbar.jsp"/>

<main id="main" >
    <div class="site-section pb-0" id="bg" style="height: 100%">
        <div class="container" id="cont" style=" height: 100%">
            <div class="d-inline-block" style="height: 100%; width: 100%">
                <div class="d-inline-block" data-aos="fade-zoom-in" data-aos-offset="0"
                     style="height: 100%; width: 100%">
                    <div class="maincontent d-flex flex-nowrap p-lg-3" style="height:100%; background: #eeeeee">
                        <img class="pagepic" src="static/img/loginpic.jpg" alt="">
                        <div class="mr-auto">
                            <div class="row d-flex justify-content-start form">
                                <div class=" mb-5 mb-md-0">
                                    <%--                            <div class="alert alert-info col-md-11 mb-3" role="alert" th:if="${logout}">--%>
                                    <%--                                You've been logged out--%>
                                    <%--                                successfully.--%>
                                    <%--                            </div>--%>
                                    <%--                            <div class="alert alert-danger col-md-11 mb-3" role="alert" th:if="${error}">--%>
                                    <%--                                Invalid Username or--%>
                                    <%--                                Password!--%>
                                    <%--                            </div>--%>
                                    <%--                            <div class="alert alert-success col-md-11 mb-3" role="alert" th:if="${signedup}">--%>
                                    <%--                                Signed up successfully!--%>
                                    <%--                            </div>--%>
                                    <form class="form" method="post" action="log-in">
                                        <div class="col-md-11 form-group">
                                            <label for="email">E-mail</label>
                                            <input class="form-control"
                                                   data-msg="Incorrect email"
                                                   data-rule="email"
                                                   id="email"
                                                   name="username"/>
                                            <div class="validate"></div>
                                        </div>
                                        <div class="col-md-11 form-group">
                                            <label for="password">Password</label>
                                            <input class="form-control"
                                                   data-msg="Enter a valid password <br>((8+ digit or latin characters)"
                                                   data-rule="pwrd"
                                                   id="password"
                                                   name="password"
                                                   type="password">
                                            <div class="validate"></div>
                                        </div>

                                        <%--                                <div class="col-md-11 mb-3" style="background: #eeeeee">--%>
                                        <%--                                    <div class="loading">Loading</div>--%>
                                        <%--                                    <div class="error-message"></div>--%>
                                        <%--                                    <div class="sent-message">fifler</div>--%>
                                        <%--                                </div>--%>


                                        <div class="col-md-6 form-group">
                                            <input class="pretty-button d-block w-100 submit-button" <%--disabled="disabled"--%> type="submit"
                                                   value="SIGN IN">
                                        </div>

                                        <div class="col-md-12 form-group">
                                            <span>Don't have an account? <a style="color:#34ce57" href="registration">SIGN UP</a></span>
                                        </div>
                                        <%--                                <div class="col-md-11 mb-2" style="color: #a55444; font-size: 0.8em">--%>
                                        <%--                                    <div class="alert alert-danger col-md-12 mb-3" role="alert"--%>
                                        <%--                                         th:if="${error}">Invalid Username or Password!</div>--%>
                                        <%--                                </div>--%>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>

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



