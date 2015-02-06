<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="sidebar" class="sidebar-fixed">
	<div id="sidebar-content">
		<!--=== Navigation ===-->
		<ul id="nav">
			<li>
				<a href="javascript:void(0);">
					<i class="icon-edit"></i>
					系统平台
				</a>
				<ul class="sub-menu">
					<li>
						<a href="<c:url value="/resource/index" />">
						<i class="icon-angle-right"></i>
						资源管理
						</a>
					</li>
					<li>
						<a href="<c:url value="/menu/index" />">
						<i class="icon-angle-right"></i>
						菜单管理
						</a>
					</li>
					<li>
						<a href="<c:url value="/organization/index" />">
						<i class="icon-angle-right"></i>
						组织管理
						</a>
					</li>
					<li>
						<a href="<c:url value="/department/index" />">
						<i class="icon-angle-right"></i>
						部门管理
						</a>
					</li>
				</ul>
			</li>
		</ul>
		<!-- /Navigation -->
		<div class="sidebar-widget align-center">
			<div class="btn-group" data-toggle="buttons" id="theme-switcher">
				<label class="btn active">
					<input type="radio" name="theme-switcher" data-theme="bright"><i class="icon-sun"></i> Bright
				</label>
				<label class="btn">
					<input type="radio" name="theme-switcher" data-theme="dark"><i class="icon-moon"></i> Dark
				</label>
			</div>
		</div>
	</div>
	<div id="divider" class="resizeable"></div>
</div>
<!-- /Sidebar -->