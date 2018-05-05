var URL = "http://193.112.47.88/";
//var URL = "http://localhost:8080/";

var getGroupId;
var getNoteId;
var isGN; //true:group  false:note

var user = JSON.parse($.cookie('data'));

$(function(){
	editormd("test-editormd", {
		width : "100%",
		height : 940,
		path : "/editormd/lib/",
		codeFold : true,
		searchReplace : true,
		toolbar : true,
		emoji : true,
		taskList : true,
		tex : true,
		tocm : true,
		previewCodeHighlight : true,
		saveHTMLToTextarea : true,
		flowChart : true,
		syncScrolling : true,
		sequenceDiagram : true,
		imageUpload : true,
		imageFormats : [ 'jpg', 'jpeg', 'gif', 'png', 'bmp', 'webp' ],
		imageUploadURL : "/uploadimg"
	});	
	
	$("#userName").html(user.userName);
	$("#test-editormd").hide();
	$("#moveBtn").hide();
	$("#newNote").hide();
	$("#saveBtn").hide();
	$("#delBtn").hide();
	$("#paste_catcher").remove();
	findGroupAll();
	
	//搜索匹配功能
	jQuery.expr[':'].Contains = function(a, i, m) {
    return (a.textContent || a.innerText || "").toUpperCase().indexOf(m[3].toUpperCase()) >= 0;
	};
	
	filterList($("#groupid"));
    $('#js-groupId').bind('focus', function() {
        $('#groupid').slideDown();
    }).bind('blur', function() {
        $('#groupid').slideUp();
    })
    $('#groupid').on('click', 'li', function() {
        $('#js-groupId').val($(this).text())
        $('#groupId').val($(this).data('id'))
        getContent($(this).data('id'));
        $('#groupid').slideUp()
    });
})

function filterList(list) {
    $('#js-groupId').bind('input propertychange', function() {
        var filter = $(this).val();
        if (filter) {
            $matches = $(list).find('a:Contains(' + filter + ')').parent();
            $('li', list).not($matches).slideUp();
            $matches.slideDown();
        } else {
            $(list).find("li").slideDown();
        }
    });
}

function showMdEditor() {
	$("#test-editormd").show();
};
 //粘贴上传图片
var clipboard = new ImageClipboard('#test-editormd')
clipboard.onpaste = function(base64) {
	if (base64.indexOf("data:image/png")) {
		$.ajax({
			url : URL + "/uploadBase64",
			dataType : "JSON",
			data : {
				base64Data : base64
			},
			type : "POST",
			success : function(data) {
				if (data.success === 1) {
					var url = "![](" + data.url + ")";
					var old = $("#content").val();
					$("#content").val(old + url);
					 $(function() {
						editormd("test-editormd", {
							
						});
					});
				}
			},
			error : function() {
				console.log("上传失败");
			}
		});
	}

};

function findGroupAll(){
	$.ajax({
		type : "GET",
		url : URL + "group/findGroupAll" + "?noteId=" + user.userId,
		contentType : 'application/json; charset=utf-8',
		success : function(val) {
			if (val.success == 1){
				for(var i = 0; i < val.list.length;i++){	
					//侧栏分组
					$("#groupList").append($('<li />',{'class':'treeview','id':'root'+ val.list[i].groupId +''})
					        .append('<a onclick="funcGetGroupId(\''+ val.list[i].groupId +'\',\''+ val.list[i].groupName +'\')">'
							 +'<i class="fa fa-files-o"></i>'
		            		 +'<span id="'+"span"+ val.list[i].groupId +'">'+ val.list[i].groupName +'</span>'
		            		 +'<span class="pull-right-container">'
		            		 +'<span class="label label-primary pull-right">'+ val.list[i].notes.length +'</span></span>')	
		            		 .append($('<ul />',{'class':'treeview-menu','id':''+ val.list[i].groupId +''})
						))
					//下拉框分组
					$("#move_menu").append($('<li />')
							 .append('<a onclick="moveNote(\''+ val.list[i].groupId +'\')">'
									 +'<span id="'+'pulldown'+val.list[i].groupId+'">'+ val.list[i].groupName +'</span>'))
									 
		            	 for(var y = 0; y < val.list[i].notes.length; y++){
		            		 $("#"+ val.list[i].groupId +"").append('<li>'
		             				 +'<a href="javascript:getContent(\''+ val.list[i].notes[y].noteId +'\')"' 
		             				 +'id="'+ val.list[i].notes[y].noteId +'"><i class="fa fa-circle-o">'
		             				 +'</i>'+ val.list[i].notes[y].title +'</a></li>')
		             		//搜索框
		             		$("#groupid").append($('<li />',{'data-id':''+ val.list[i].notes[y].noteId +''})
		             				.append('<a href="javascript:void(0)"'
		             						+'id="so'+ val.list[i].notes[y].noteId +'">'
		             						+''+ val.list[i].notes[y].title +'</a>')
		             		
		             			 )
		 					}
				}	
			}
		},
		error : function() {
			alert("查询失败");
		}
	});
}

function funcGetGroupId(id,name){
	$("#title").val(name);
	$("#newNote").show();
	$("#moveBtn").hide();
	$("#saveBtn").show();
	$("#delBtn").show();
	if(getGroupId != id){
		getGroupId = id;
	}
	if(isGN != true){
		isGN = true;
	}		
}

function guid() {
    function S4() {
       return (((1+Math.random())*0x10000)|0).toString(16).substring(1);
    }
    return (S4()+S4()+"-"+S4()+"-"+S4()+"-"+S4()+"-"+S4()+S4()+S4());
}

//判断一个字符串是否包含一个字符串 返回true和false
function isContains(str, substr) {
    return str.indexOf(substr) >= 0;
}
//点击获取笔记本内容
function getContent(id) { 
	$.ajax({
		type : "GET",
		url : URL + "note/findById" + "?noteId=" + id,
		contentType : 'application/json; charset=utf-8',
		success : function(data) {
			if (data.success == 1){
				
				if(isGN != false){
					isGN = false;
				}	
				showMdEditor();
				getNoteId = id;
				$("#content").val(data.data.content);
				$("#title").val(data.data.title);
				$("#moveBtn").show();
				$("#newNote").show();
				$("#saveBtn").show();
				$("#delBtn").show();
				$(function() {
					editormd("test-editormd", {
					});
				});
			}		
		},
		error : function() {
			alert("查询失败");
		}
	});
};
function moveNote(id){
	$.ajax({
		type : "POST",
		url : URL + "note/moveNote", 
		data : JSON.stringify({
			group_id   :  id,
			noteId    :  getNoteId
		}),
		dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		success : function(data) {
			if (data.success == 1){
				var movetitle = $("#title").val();
				$("#"+ getNoteId +"").remove();
				$("#title").val("");
				
				$("#"+ id +"").append('<li>'
         				 +'<a href="javascript:getContent(\''+ getNoteId +'\')"'
         				 +'id="'+ getNoteId +'"><i class="fa fa-circle-o">'
         				 +'</i>'+ movetitle +'</a></li>')
				alert("移动成功！");
			}		
		},
		error : function() {
			alert("移动失败");
		}
	});
}

$("#newGroup").click(function(groupName) {
	var groupName = $("#groupName").val();
	getGroupId = guid();
	
	if(groupName == ""){
		groupName = "新建分组";
	}
	$.ajax({
		type : "POST",
		url : URL + "group/addGroup",
		data : JSON.stringify({
			groupId   :  getGroupId,
			groupName :  groupName,
			user_id   :  user.userId
		}),
		dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		success : function(val) {
			if (val.success == 1){
				$("#groupList").append($('<li />',{'class':'treeview','id':'root'+ getGroupId +''})
				        .append('<a onclick="funcGetGroupId(\''+ getGroupId +'\',\''+ groupName +'\')")">'
						 +'<i class="fa fa-files-o"></i>'
	            		 +'<span id="'+"span"+ getGroupId +'">'+ groupName +'</span>'
	            		 +'<span class="pull-right-container">'
	            		 +'<span class="label label-primary pull-right">0</span></span>')	
	            		 .append($('<ul />',{'class':'treeview-menu','id':''+ getGroupId +'' })
					))
					$("#move_menu").append($('<li />')
							 .append('<a onclick="moveNote(\''+ getGroupId +'\')">'
									 +'<span id="'+'pulldown'+ getGroupId +'">'+ groupName +'</span>'))
					$("#groupName").val("");
					
			}
		},
		error : function(error) {
			console.log(error);
			alert("创建失败");
		}
	});
});


$("#newNote").click(function() {
	showMdEditor();
	$("#title").val("");
	getNoteId = guid();
	var newtitle = "新建笔记";
	$.ajax({
		type : "POST",
		url : URL + "note/addNote",
		data : JSON.stringify({
			noteId   : getNoteId,
			group_id : getGroupId,
			title    : newtitle
		}),
		dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		success : function(data) {
			if (data.success == 1){
				 $("#"+ getGroupId +"").append('<li>'
         				 +'<a href="javascript:getContent(\''+ getNoteId +'\')"'
         				 +'id="'+ getNoteId +'"><i class="fa fa-circle-o">'
         				 +'</i>'+ newtitle +'</a></li>')
				 //搜索框
         		$("#groupid").append($('<li />',{'data-id':''+ getNoteId +''})
         				.append('<a href="javascript:void(0)">'
         						+''+ newtitle +'</a>')
         		
         			 )
         		$("#content").val("");
         		
         		getContent(getNoteId);
         	
        		alert("新建成功");
			   }		
		},
		error : function() {
			alert("新建失败");
		}
	});
});

$("#saveBtn").click(function() {
	var title = $("#title").val();
	if(isGN){
		$.ajax({
			type : "POST",
			url : URL + "group/saveGroup",
			data : JSON.stringify({
				groupId : getGroupId,
				groupName : title
			}),
			dataType : 'json',
			contentType : 'application/json; charset=utf-8',
			success : function(data) {
				if (data.success == 1){
					//删除<span>标签内容  在追加<a>标签内容
					$("#span"+ getGroupId +"").html("");
					$("#span"+ getGroupId +"").append(''+ title +'');
					$("#pulldown"+ getGroupId +"").html(title);
					alert("保存成功");
				}
					
			},
			error : function() {
				alert("保存失败");
			}
		});
	}else{
		var content = $("#content").val();
		if(title == null){
			title = "新建笔记本";
		}
		$.ajax({
			type : "POST",
			url : URL + "note/saveNote",
			data : JSON.stringify({
				noteId  : getNoteId,
				title   : title,
				content : content
			}),
			dataType : 'json',
			contentType : 'application/json; charset=utf-8',
			success : function(data) {
				if (data.success == 1){
					//删除<a>标签内容  在追加<a>标签内容
					$("#"+ getNoteId +"").html("");
					$("#so"+ getNoteId +"").html("");
					$("#"+ getNoteId +"").append($('<i \>',{'class':'fa fa-circle-o'}));
					$("#"+ getNoteId +"").append(''+ title +'');
					$("#so"+ getNoteId +"").append(''+ title +'');
					alert("保存成功");
				}
					
			},
			error : function() {
				alert("保存失败");
			}
		});
	}	
});
 
$("#delBtn").click(function() {
	if(isGN){
		$.ajax({
			type : "GET",
			url : URL + "group/delGroupbyId"+ "?groupId=" + getGroupId,
			contentType : 'application/json; charset=utf-8',
			success : function(data) {
				console.log(data);
				if (data.success == 1){
					$("#root"+ getGroupId +"").remove();
					$("#title").val("");
					$("#editorContent").hide();
					alert("删除成功");
				}		
			},
			error : function() {
				alert("删除失败");
			}
		});
		
	}else{
		$.ajax({
			type : "GET",
			url : URL + "note/delNotebyId"+ "?noteId=" + getNoteId,
			contentType : 'application/json; charset=utf-8',
			success : function(data) {
				if (data.success == 1){
					$("#"+ getNoteId +"").remove();
					$("#so"+ getNoteId +"").remove();
					$("#title").val("");
					alert("删除成功");
				}
					
			},
			error : function() {
				alert("删除失败");
			}
		});
	}
});
