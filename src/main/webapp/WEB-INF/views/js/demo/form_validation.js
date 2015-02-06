/*
 * form_validation.js
 *
 * Demo JavaScript used on Validation-pages.
 */

"use strict";

$(document).ready(function(){
	

	//===== Validation =====//
	// @see: for default options, see assets/js/plugins.form-components.js (initValidation())
	var id = $("#id");
	$.extend( $.validator.defaults, {
		invalidHandler: function(form, validator) {
			var errors = validator.numberOfInvalids();
			if (errors) {
				var message = errors == 1
				? '对不起 ，你有 1 个错误.'
				: '对不起，你有 ' + errors + ' 个错误.';
				noty({
					text: message,
					type: 'error',
					timeout: 2000
				});
			}
		}
	});
	// new method check if match some regex
	$.validator.addMethod("regex", function(value, element) {
		  return this.optional(element) || /^\/[\w\-_]+(\/[\w\-_]+)+$/.test(value);
		},
		"Please specify the correct domain for your documents"
	);
	
	/**
	 * ===================resource=========================
	 */
	$("#validate-resource-add").validate({
        // Specify the validation rules

        rules: {
        	name: {
        		required: true,
        		remote: {
                     url: location.href.substring(0,location.href.lastIndexOf('/'))+"/resourceName/check/exists",
                     type: "GET"
                  }
        	},
        	url: {
                required: true,
                regex: true,
                remote: {
                    url: location.href.substring(0,location.href.lastIndexOf('/'))+"/resourceUrl/check/exists",
                    type: "GET"
                 }
            },
            menu_id: "required"
        },
        
        // Specify the validation error messages
        messages: {
        	name: {
                required: "请输入资源名称",
                remote: "对不起，该资源名已存在"
            },
        	url: {
                required: "请输入该资源的url",
                regex: "对不起，您输入的url格式不正确（例：/resource/index）",
                remote: "对不起，该url已存在"
            },
            menu_id:"请选择菜单平台"
        },
        
        submitHandler: function(form) {
            form.submit();
        }
    });
	
	$("#validate-resource-edit").validate({
        // Specify the validation rules
        rules: {
        	
        	name: {
        		required: true,
        		remote: {
        			url: location.href.substring(0,location.href.lastIndexOf('/'))+"/resourceName/check/existsExceptSelf?id="+$("#id").val(),
                    type: "GET"
                        
                  }
        	},
        	url: {
                required: true,
                regex: true,
                remote: {
                    url: location.href.substring(0,location.href.lastIndexOf('/'))+"/resourceUrl/check/existsExceptSelf?id="+$("#id").val(),
                    type: "GET"
                 }
            },
            menu_id: "required"
        },
        
        // Specify the validation error messages
        messages: {
        	name: {
                required: "请输入资源名称",
                remote: "对不起，该资源名已存在"
            },
        	url: {
                required: "请输入该资源的url",
                regex: "对不起，您输入的url格式不正确（例：/resource/index）",
                remote: "对不起，该url已存在"
            },
            menu_id:"请选择菜单平台"
        },
        
        submitHandler: function(form) {
            form.submit();
        }
    });

	/**
	 * ===================menu=========================
	 */
	$("#validate-menu-add").validate({
        // Specify the validation rules

        rules: {
        	name: {
        		required: true,
        		remote: {
                     url: location.href.substring(0,location.href.lastIndexOf('/'))+"/menuName/check/exists",
                     type: "GET"
                  }
        	}
        },
        
        // Specify the validation error messages
        messages: {
        	name: {
                required: "请输入菜单名称",
                remote: "对不起，该菜单名已存在"
            }
        },
        
        submitHandler: function(form) {
            form.submit();
        }
    });
	
	$("#validate-menu-edit").validate({
        // Specify the validation rules
        rules: {
        	
        	name: {
        		required: true,
        		remote: {
        			url: location.href.substring(0,location.href.lastIndexOf('/'))+"/menuName/check/existsExceptSelf?id="+$("#id").val(),
                    type: "GET"
                        
                  }
        	}
        },
        
        // Specify the validation error messages
        messages: {
        	name: {
                required: "请输入菜单名称",
                remote: "对不起，该菜单名已存在"
            }
        },
        
        submitHandler: function(form) {
            form.submit();
        }
    });
	
	/**
	 * ===================org=========================
	 */
	$("#validate-org-add").validate({
        // Specify the validation rules

        rules: {
        	name: {
        		required: true,
        		remote: {
                     url: location.href.substring(0,location.href.lastIndexOf('/'))+"/orgName/check/exists",
                     type: "GET"
                  }
        	}
        },
        
        // Specify the validation error messages
        messages: {
        	name: {
                required: "请输入组织名称",
                remote: "对不起，该组织名已存在"
            }
        },
        
        submitHandler: function(form) {
            form.submit();
        }
    });
	
	$("#validate-org-edit").validate({
        // Specify the validation rules
        rules: {
        	
        	name: {
        		required: true,
        		remote: {
        			url: location.href.substring(0,location.href.lastIndexOf('/'))+"/orgName/check/existsExceptSelf?id="+$("#id").val(),
                    type: "GET"
                        
                  }
        	}
        },
        
        // Specify the validation error messages
        messages: {
        	name: {
                required: "请输入组织名称",
                remote: "对不起，该组织名已存在"
            }
        },
        
        submitHandler: function(form) {
            form.submit();
        }
    });
	
	/**
	 * ===================dep=========================
	 */
	$("#validate-dep-add").validate({
        // Specify the validation rules

        rules: {
        	name: {
        		required: true,
        		remote: {
                     url: location.href.substring(0,location.href.lastIndexOf('/'))+"/depName/check/exists",
                     type: "GET"
                  }
        	}
        },
        
        // Specify the validation error messages
        messages: {
        	name: {
                required: "请输入部门名称",
                remote: "对不起，该部门名已存在"
            }
        },
        
        submitHandler: function(form) {
            form.submit();
        }
    });
	
	$("#validate-dep-edit").validate({
        // Specify the validation rules
        rules: {
        	
        	name: {
        		required: true,
        		remote: {
        			url: location.href.substring(0,location.href.lastIndexOf('/'))+"/depName/check/existsExceptSelf?id="+$("#id").val(),
                    type: "GET"
                        
                  }
        	}
        },
        
        // Specify the validation error messages
        messages: {
        	name: {
                required: "请输入部门名称",
                remote: "对不起，该部门名已存在"
            }
        },
        
        submitHandler: function(form) {
            form.submit();
        }
    });
	
});