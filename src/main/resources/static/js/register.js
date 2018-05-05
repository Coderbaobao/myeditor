//var URL = "http://193.112.47.88/";
var URL = "http://localhost:8080/";

var messageInfo = $('#message-info');
var message = $('#message');
var registerBtn = $('#register-button');

$(function() {
	// 将错误信息提示框设为不可见
	messageInfo.css('display', 'none');
	// 监听回车事件
	$('body').keydown(function(e) {
		if (e.keyCode === 13) {
			doRegister();
			}
		});
	
	// 监听登录按钮事件
	registerBtn.bind('click', function() {
		if(checkPaw()){
			doRegister();
		}
		
	});
});

function checkCode(){
	
}
function checkPaw(){
	if($('#password').val() != $('#password2').val()){
		messageInfo.css('display', 'block');
		message.text("两次密码不正确！");
		return false;
	}
	return true;
}

//登录逻辑
var doRegister = function() {
	registerBtn.text("注册中...");
	registerBtn.attr("disabled", true);
	$.ajax({
		type : 'post', // 请求发送方式
		url : URL + 'user/register', // 请求地址
		data : JSON.stringify({
			'userName' : $('#userName').val(),
			'userPaw' : $('#password').val()
		}),// 请求数据，用户名和密码
		async : true,// 异步，不写默认为True
		dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		// 请求成功后的回调
		success : function(val) {
			// 登录成功状态码为 1
			if (val["success"] === 1) {
				$.cookie('data', JSON.stringify(val.data), { expires: 7 });
				// 隐藏错误信息提示框
				messageInfo.css('display', 'none');
				// 设置成功提示信息
				message.text(val["message"]);
				// 跳转到主页
				location.href = "/mdEditor?#";
			} else if (val["success"] === 0) {
				// 登录失败状态码为 0
				registerBtn.text("登录");
				registerBtn.attr("disabled", false);
				// 设置错误提示信息
				message.text(val["message"]);
				// 显示错误提示框
				messageInfo.css('display', 'block');
			}
		},
		error : function(errorMessage) {
			registerBtn.text("注册");
			registerBtn.attr("disabled", false);
			// 其它错误信息
			messageInfo.text(errorMessage);
			messageInfo.css('display', 'block');
		}
	});
}