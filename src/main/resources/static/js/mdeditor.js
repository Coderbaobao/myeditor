var URL = "http://localhost:8080/";

var getGroupId;
var getNoteId;
var isGN; //true:group  false:note

var user = JSON.parse($.cookie('data'));

$(function() {
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
		imageUploadURL : "/uploadimg",
	});	
	$("#userName").html(user.userName);
	console.log(user.userName);
	findGroupAll();

});
function findGroupAll(){
	$.ajax({
		type : "GET",
		url : URL + "group/findGroupAll" + "?noteId=" + user.userId,
		contentType : 'application/json; charset=utf-8',
		success : function(val) {
			if (val.success == 1){
				for(var i = 0; i < val.list.length;i++){	
					$("#groupList").append($('<li />',{'class':'treeview','id':'root'+ val.list[i].groupId +''})
					        .append('<a onclick="funcGetGroupId(\''+ val.list[i].groupId +'\',\''+ val.list[i].groupName +'\')">'
							 +'<i class="fa fa-files-o"></i>'
		            		 +'<span id="'+"span"+ val.list[i].groupId +'">'+ val.list[i].groupName +'</span>'
		            		 +'<span class="pull-right-container">'
		            		 +'<span class="label label-primary pull-right">'+ val.list[i].notes.length +'</span></span>')	
		            		 .append($('<ul />',{'class':'treeview-menu','id':''+ val.list[i].groupId +''})
						))
		            	 for(var y = 0; y < val.list[i].notes.length; y++){
		            		 $("#"+ val.list[i].groupId +"").append('<li>'
		             				 +'<a href="javascript:getContent(\''+ val.list[i].notes[y].noteId +'\')"' 
		             				 +'id="'+ val.list[i].notes[y].noteId +'"><i class="fa fa-circle-o">'
		             				 +'</i>'+ val.list[i].notes[y].title +'</a></li>')
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
					$("#groupName").val("");
			}
		},
		error : function(error) {
			console.log(error);
			alert("创建失败");
		}
	});
});

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
				
				getNoteId = id;
				$("#content").val(data.data.content);
				$("#title").val(data.data.title);
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


$("#newNote").click(function() {
	$("#title").val("");
	getNoteId = guid();
	var newtitle = "新建笔记";
	$.ajax({
		type : "POST",
		url : URL + "note/addNote",
		data : JSON.stringify({
			noteId   : getNoteId,
			group_id : getGroupId
		}),
		dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		success : function(data) {
			if (data.success == 1){
				 $("#"+ getGroupId +"").append('<li>'
         				 +'<a href="javascript:getContent(\''+ getNoteId +'\')"'
         				 +'id="'+ getNoteId +'"><i class="fa fa-circle-o">'
         				 +'</i>'+ newtitle +'</a></li>')
         		$("#content").val("");
         		$(function() {
        			editormd("test-editormd", {
        			});
        		});
			   }
				alert("新建成功");	
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
					$("#span"+ getGroupId +"").append(''+ title +'')
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
					$("#"+ getNoteId +"").append($('<i \>',{'class':'fa fa-circle-o'}))
					$("#"+ getNoteId +"").append(''+ title +'')
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
				if (data.success == 1){
					$("#root"+ getGroupId +"").remove();
					$("#title").val("");
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
