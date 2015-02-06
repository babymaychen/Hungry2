/*
 * modals.js
 *
 * Demo JavaScript used for modals.
 */

"use strict";

$(document).ready(function(){

	//===== Modals & Dialogs =====//
	$("a.confirm-dialog").click(function(e) {
		e.preventDefault();
		var link = $(this);
		bootbox.confirm({
		    message: '你确定删除吗',
		    buttons: {
		        'cancel': {
		            label: '取消'
		        },
		        'confirm': {
		            label: '确定'
		        }
		    },
		    callback: function(result) {
		        if (result) {
		        	location.href=link.attr("href");
		        }
		    }
		});
	});

});