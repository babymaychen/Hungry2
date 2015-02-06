<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./common/pageHeader.jsp" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0" />
	<title>吃货 -- 登录</title>

	<!--=== CSS ===-->
    <c:url value="/" var="baseUrl" />
    <c:url var="loginUrl" value='/loginCheck'/>
	<!-- Bootstrap -->
	<link href="${baseUrl}css/bootstrap.min.css" rel="stylesheet" type="text/css" />

	<!-- Theme -->
	<link href="${baseUrl}css/main.css" rel="stylesheet" type="text/css" />
	<link href="${baseUrl}css/plugins.css" rel="stylesheet" type="text/css" />
	<link href="${baseUrl}css/responsive.css" rel="stylesheet" type="text/css" />
	<link href="${baseUrl}css/icons.css" rel="stylesheet" type="text/css" />

	<!-- Login -->
	<link href="${baseUrl}css/login.css" rel="stylesheet" type="text/css" />

	<link rel="stylesheet" href="${baseUrl}css/font/font-awesome.min.css">
	<!--[if IE 7]>
		<link rel="stylesheet" href="assets/css/fontawesome/font-awesome-ie7.min.css">
	<![endif]-->

	<!--[if IE 8]>
		<link href="assets/css/ie8.css" rel="stylesheet" type="text/css" />
	<![endif]-->

	<!--=== JavaScript ===-->

	<script type="text/javascript" src="${baseUrl}js/libs/jquery-1.10.2.min.js"></script>

	<script type="text/javascript" src="${baseUrl}js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${baseUrl}js/libs/lodash.compat.min.js"></script>

	<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
	<!--[if lt IE 9]>
		<script src="assets/js/libs/html5shiv.js"></script>
	<![endif]-->

	<!-- Beautiful Checkboxes -->
	<script type="text/javascript" src="${baseUrl}js/plugins/uniform/jquery.uniform.min.js"></script>

	<!-- Form Validation -->
	<script type="text/javascript" src="${baseUrl}js/plugins/validation/jquery.validate.min.js"></script>

	<!-- Slim Progress Bars -->
	<script type="text/javascript" src="${baseUrl}js/plugins/nprogress/nprogress.js"></script>

	<!-- App -->
	<script type="text/javascript" src="${baseUrl}js/login.js"></script>
	<script>
	$(document).ready(function(){
		"use strict";

		Login.init(); // Init login JavaScript
	});
	</script>
</head>
<c:url var="loginUrl" value='/loginCheck'/>
<body class="login">
	<!-- Logo -->
	<div class="logo">
		<img src="${baseUrl}images/logo.png" alt="logo" />
		<strong>吃</strong>货
	</div>
	<!-- /Logo -->

	<!-- Login Box -->
	<div class="box">
		<div class="content">
			<!-- Login Formular -->
			<form:form id="loginForm" modelAttribute="loginModel" action="${loginUrl}" class="form-vertical login-form" method="post">
				<!-- Title -->
				<h3 class="form-title">吃货登陆</h3>

				<!-- Error Message -->
				<div class="alert fade in alert-danger" style="display: none;">
					<i class="icon-remove close" data-dismiss="alert"></i>
					请输入你的用户名和密码.
				</div>

				<!-- Input Fields -->
				<div class="form-group">
					<!--<label for="username">Username:</label>-->
					<div class="input-icon">
						<i class="icon-user"></i>
						<input type="text" name="username" class="form-control" placeholder="用户名" autofocus="autofocus" data-rule-required="true" data-msg-required="请输入用户名."/>
					</div>
				</div>
				<div class="form-group">
					<!--<label for="password">Password:</label>-->
					<div class="input-icon">
						<i class="icon-lock"></i>
						<input type="password" name="password" class="form-control" placeholder="密码" data-rule-required="true" data-msg-required="请输入密码." />
					</div>
				</div>
				<!-- /Input Fields -->

				<!-- Form Actions -->
				<div class="form-actions">
					<label class="checkbox pull-left"><input type="checkbox" class="uniform" name="remember"> 记住我</label>
					<button type="submit" class="submit btn btn-primary pull-right">
						登陆 <i class="icon-angle-right"></i>
					</button>
				</div>
			</form:form>
			<!-- /Login Formular -->

			<!-- Register Formular (hidden by default) -->
			<form:form class="form-vertical register-form" action="index.html" method="post" style="display: none;">
				<!-- Title -->
				<h3 class="form-title">免费注册</h3>

				<!-- Input Fields -->
				<div class="form-group">
					<div class="input-icon">
						<i class="icon-user"></i>
						<input type="text" name="username" class="form-control" placeholder="用户名" autofocus="autofocus" data-rule-required="true" data-msg-required="请输入用户名."/>
					</div>
				</div>
				<div class="form-group">
					<div class="input-icon">
						<i class="icon-lock"></i>
						<input type="password" name="password" class="form-control" placeholder="密码" id="register_password" data-rule-required="true" data-msg-required="请输入密码." data-rule-rangelength="[6, 12]" data-msg-rangelength="输入的密码长度必须在{0}和{1}之间."/>
					</div>
				</div>
				<div class="form-group">
					<div class="input-icon">
						<i class="icon-ok"></i>
						<input type="password" name="password_confirm" class="form-control" placeholder="确认密码" data-rule-required="true" data-msg-required="请输入确认密码." data-rule-equalTo="#register_password" data-msg-equalTo="输入的密码不一致."/>
					</div>
				</div>
				<div class="form-group">
					<div class="input-icon">
						<i class="icon-envelope"></i>
						<input type="text" name="Email" class="form-control" placeholder="邮箱地址" data-rule-required="true" data-msg-required="请输入邮箱地址." data-rule-email="true" data-msg-email="格式不正确."/>
					</div>
				</div>
				<div class="form-group spacing-top">
					<label class="checkbox"><input type="checkbox" class="uniform" name="remember" data-rule-required="true" data-msg-required="Please accept ToS first."> I agree to the <a href="javascript:void(0);">Terms of Service</a></label>
					<label for="remember" class="has-error help-block" generated="true" style="display:none;"></label>
				</div>
				<!-- /Input Fields -->

				<!-- Form Actions -->
				<div class="form-actions">
					<button type="button" class="back btn btn-default pull-left">
						<i class="icon-angle-left"></i> 返回</i>
					</button>
					<button type="submit" class="submit btn btn-primary pull-right">
						注册 <i class="icon-angle-right"></i>
					</button>
				</div>
			</form:form>
			<!-- /Register Formular -->
		</div> <!-- /.content -->

		<!-- Forgot Password Form -->
		<div class="inner-box">
			<div class="content">
				<!-- Close Button -->
				<i class="icon-remove close hide-default"></i>

				<!-- Link as Toggle Button -->
				<a href="#" class="forgot-password-link">忘了密码?</a>

				<!-- Forgot Password Formular -->
				<form class="form-vertical forgot-password-form hide-default" action="login.html" method="post">
					<!-- Input Fields -->
					<div class="form-group">
						<!--<label for="email">Email:</label>-->
						<div class="input-icon">
							<i class="icon-envelope"></i>
							<input type="text" name="email" class="form-control" placeholder="Enter email address" data-rule-required="true" data-rule-email="true" data-msg-required="Please enter your email." />
						</div>
					</div>
					<!-- /Input Fields -->

					<button type="submit" class="submit btn btn-default btn-block">
						重新设置密码
					</button>
				</form>
				<!-- /Forgot Password Formular -->

				<!-- Shows up if reset-button was clicked -->
				<div class="forgot-password-done hide-default">
					<i class="icon-ok success-icon"></i> <!-- Error-Alternative: <i class="icon-remove danger-icon"></i> -->
					<span>好！我们会给你发邮件的.</span>
				</div>
			</div> <!-- /.content -->
		</div>
		<!-- /Forgot Password Form -->
	</div>
	<!-- /Login Box -->

	<!-- Single-Sign-On (SSO) -->
	<div class="single-sign-on">
		<span>or</span>

		<button class="btn btn-facebook btn-block">
			<i class="icon-facebook"></i> Sign in with Facebook
		</button>

		<button class="btn btn-twitter btn-block">
			<i class="icon-twitter"></i> Sign in with Twitter
		</button>

		<button class="btn btn-google-plus btn-block">
			<i class="icon-google-plus"></i> Sign in with Google
		</button>
	</div>
	<!-- /Single-Sign-On (SSO) -->

	<!-- Footer -->
	<div class="footer">
		<a href="#" class="sign-up">Don't have an account yet? <strong>Sign Up</strong></a>
	</div>
	<!-- /Footer -->
</body>
</html>