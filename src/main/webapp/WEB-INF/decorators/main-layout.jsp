<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>

<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <decorator:title default="您吃了么" />

	<!--=== CSS ===-->

	<!-- Bootstrap -->
	<link href="<c:url value="./css/bootstrap.min.css" />" rel="stylesheet" type="text/css" />

	<!-- jQuery UI -->
	<!--<link href="plugins/jquery-ui/jquery-ui-1.10.2.custom.css" rel="stylesheet" type="text/css" />-->
	<!--[if lt IE 9]>
		<link rel="stylesheet" type="text/css" href="plugins/jquery-ui/jquery.ui.1.10.2.ie.css"/>
	<![endif]-->

	<!-- Theme -->
	<link href="<c:url value="./css/main.css" />" rel="stylesheet" type="text/css" />
	<link href="<c:url value="./css/plugins.css" />" rel="stylesheet" type="text/css" />
	<link href="<c:url value="./responsive.css" />" rel="stylesheet" type="text/css" />
	<link href="<c:url value="./css/icons.css" />" rel="stylesheet" type="text/css" />

	<!--[if IE 7]>
		<link rel="stylesheet" href="assets/css/fontawesome/font-awesome-ie7.min.css">
	<![endif]-->

	<!--=== JavaScript ===-->
	<script type="text/javascript" src="<c:url value="/resources/assets/js/libs/jquery-1.10.2.min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/plugins/jquery-ui/jquery-ui-1.10.2.custom.min.js" />"></script>

	<script type="text/javascript" src="<c:url value="/resources/bootstrap/js/bootstrap.min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/assets/js/libs/lodash.compat.min.js" />"></script>

	<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
	<!--[if lt IE 9]>
		<script src="assets/js/libs/html5shiv.js"></script>
	<![endif]-->
	<!-- Smartphone Touch Events -->
	<script type="text/javascript" src="<c:url value="resources/plugins/touchpunch/jquery.ui.touch-punch.min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/plugins/event.swipe/jquery.event.move.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/plugins/event.swipe/jquery.event.swipe.js"/>"></script>

	<!-- General -->
	<script type="text/javascript" src="<c:url value="/resources/assets/js/libs/breakpoints.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/plugins/respond/respond.min.js"/>"></script> <!-- Polyfill for min/max-width CSS3 Media Queries (only for IE8) -->
	<script type="text/javascript" src="<c:url value="/resources/plugins/cookie/jquery.cookie.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/plugins/slimscroll/jquery.slimscroll.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/plugins/slimscroll/jquery.slimscroll.horizontal.min.js"/>"></script>

	<!-- Charts -->
	<script type="text/javascript" src="<c:url value="/resources/plugins/sparkline/jquery.sparkline.min.js"/>"></script>

	<script type="text/javascript" src="<c:url value="/resources/plugins/daterangepicker/moment.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/plugins/daterangepicker/daterangepicker.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/plugins/blockui/jquery.blockUI.min.js"/>"></script>

	<!-- Forms -->
	<script type="text/javascript" src="<c:url value="/resources/plugins/uniform/jquery.uniform.min.js"/>"></script> <!-- Styled radio and checkboxes -->
	<script type="text/javascript" src="<c:url value="/resources/plugins/select2/select2.min.js"/>"></script> <!-- Styled select boxes -->
	<script type="text/javascript" src="<c:url value="/resources/plugins/fileinput/fileinput.js"/>"></script>

	<!-- Form Validation -->
	<script type="text/javascript" src="<c:url value="/resources/plugins/validation/jquery.validate.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/plugins/validation/additional-methods.min.js"/>"></script>

	<!-- Noty -->
	<script type="text/javascript" src="<c:url value="/resources/plugins/noty/jquery.noty.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/plugins/noty/layouts/top.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/plugins/noty/themes/default.js"/>"></script>

	<!-- DataTables -->
	<script type="text/javascript" src="<c:url value="/resources/plugins/datatables/jquery.dataTables.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/plugins/datatables/tabletools/TableTools.min.js"/>"></script> <!-- optional -->
	<script type="text/javascript" src="<c:url value="/resources/plugins/datatables/colvis/ColVis.min.js"/>"></script> <!-- optional -->
	<script type="text/javascript" src="<c:url value="/resources/plugins/datatables/columnfilter/jquery.dataTables.columnFilter.js"/>"></script> <!-- optional -->
	<script type="text/javascript" src="<c:url value="/resources/plugins/datatables/DT_bootstrap.js"/>"></script>

	<!-- Bootbox -->
	<script type="text/javascript" src="<c:url value="/resources/plugins/bootbox/bootbox.min.js"/>"></script>

	<!-- App -->
	<script type="text/javascript" src="<c:url value="./js/app.js"/>"></script>
	<script type="text/javascript" src="<c:url value="./js/plugins.js"/>"></script>

  <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
  <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
  <![endif]-->
<script type="text/javascript">
	$(document).ready(function(){
		"use strict";
		App.init(); // Init layout and core plugins
		Plugins.init(); // Init all plugins
		FormComponents.init(); // Init all form-specific plugins
	});
	</script>
	<!-- Demo JS -->
	<script type="text/javascript" src="<c:url value="./js/custom.js"/>"></script>
	<script type="text/javascript" src="<c:url value="./js/demo/form_validation.js"/>"></script>
	<script type="text/javascript" src="<c:url value="./js/demo/modals.js"/>"></script>
</head>
<body>
	<c:import url="/WEB-INF/views/jsp/tags/navbar.jsp"/>
	   	<div id="container">
			<c:import url="/WEB-INF/views/jsp/tags/sidebar.jsp"/>
				<div id="content">
					<div class="container">
		               	<decorator:body />
		           	</div>
				</div>
			</div>
</body>
</html>