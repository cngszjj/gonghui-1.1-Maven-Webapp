
window.onload = function(){
	var thisuser  = JSON.parse($.cookie("user"));
//	<option value="西安市总工会">西安市总工会</option>
//	<option value="新城区总工会">新城区总工会</option>
//	<option value="碑林区总工会">碑林区总工会</option>
//	<option value="莲湖区总工会">莲湖区总工会</option>
//	<option value="雁塔区总工会">雁塔区总工会</option>
//	<option value="灞桥区总工会">灞桥区总工会</option>
//	<option value="未央区总工会">未央区总工会</option>
//	<option value="阎良区总工会">阎良区总工会</option>
//	<option value="临潼区总工会">临潼区总工会</option>
//	<option value="长安区总工会">长安区总工会</option>
//	<option value="蓝田县总工会">蓝田县总工会</option>
//	<option value="周至县总工会">周至县总工会</option>
//
//	<option value="机械冶金建材工会">机械冶金建材工会</option>
//	<option value="建设交通工会">建设交通工会</option>
//	<option value="轻工纺织工会">轻工纺织工会</option>
//	<option value="石化农林工会">石化农林工会</option>
//	<option value="财贸工会">财贸工会</option>
//	<option value="教科文卫工会">教科文卫工会</option>
//	<option value="西电公司工会">西电公司工会</option>
//	<option value="市级机关工会">市级机关工会</option>
//	<option value="西安银行公会">西安银行公会</option>
//
//	<option value="高新技术开发区工会">高新技术开发区工会</option>
//	<option value="曲江新区工会">曲江新区工会</option>
//	<option value="浐灞生态区工会">浐灞生态区工会</option>
//	<option value="航天基地工会">航天基地工会</option>
//	<option value="沣东新城工会">沣东新城工会</option>
//	<option value="国际港务区工会">国际港务区工会</option>
//	if(thisuser.state != 3){
//		$("#pd_words").html("<option value=''>西安市总工会</option>")
//	}
	
};
		function submitForm() {
			$.messager.progress(); // display the progress bar
			$('#add_user').form('submit', {
				url : 'user/add',
				onSubmit : function() {
					var isValid = $(this).form('validate');
					if (!isValid) {
						$.messager.progress('close'); // hide progress bar while the form is invalid
					}
					return isValid; // return false will stop the form submission
				},
				success : function(data) {
					$.messager.progress('close'); // hide progress bar while submit successfully
					var d = JSON.parse(data);
					if (d.success) {
						$('#add_user').form('clear');
						$('#add_user_dlg').dialog('close');
						$('#user_data').datagrid('reload');
						$.messager.alert('提示', '添加成功', 'info');
					} else {
						$.messager.alert('提示', d.error, 'info');
					}

				}
			});
		}
		function submitEditForm() {

			$.messager.progress(); // display the progress bar
			$('#edit_user').form('submit', {
				url : 'user/update',
				onSubmit : function() {
					var isValid = $(this).form('validate');
					if (!isValid) {
						$.messager.progress('close'); // hide progress bar while the form is invalid
					}
					return isValid; // return false will stop the form submission
				},
				success : function(data) {
					$.messager.progress('close'); // hide progress bar while submit successfully
					var d = JSON.parse(data);
					if (d.success) {
						$('#edit_user').form('clear');
						$('#edit_user_dlg').dialog('close');
						$('#user_data').datagrid('reload');
						$.messager.alert('提示', '操作成功', 'info');
					} else {
						$.messager.alert('提示', d.error, 'info');
					}

				}
			});

		}

		function clearEditForm() {
			$('#edit_user').form('clear');
			$('#edit_user_dlg').dialog('close');
		}
		function clearForm() {
			$('#add_user').form('clear');
			$('#add_user_dlg').dialog('close');
		}
		function editUser(item) {
			var id = $(item).attr("id");
			$('#edit_user_dlg').dialog('open');
			var d;
			$.get('user/' + id, function(data) {

				$('#edit_user').form('load', {
					id : data.id,
					username : data.username,
					state : data.state,
					produceDivision : data.produceDivision,
					password : data.password

				});
			});
		}
		function formatEdit(val, row) {

			return "<a href='javascript:void(0)' id='" + val
					+ "' onclick='editUser(this)'>编辑</a>";
		}
		function formatDate(val, row) {
			var d = new Date(val)
			return d.Format("yyyy-MM-dd hh:mm:ss");

		}
		function formatDateToDay(val, row) {
			if(val){
				var d = new Date(val);
				return d.Format("yyyy-MM-dd");
			}else{
				return "";
			}
			

		}
		function formatState(val, row) {
			if (val == 1) {
				return "普通用户";
			}
			if (val == 2) {
				return "操作员";
			}
		}
		
		function formatUnionState(val, row) {

			if (0 == val) {
				return "<span style='color:blue'>待审核</span>";
			}
			if (1 == val) {
				return "<span style='color:green'>已通过</span>";
			}
			if (2 == val) {
				return "<span style='color:red'>不通过</span>";
			}

		}

		function doSearch() {
			$('#user_data').datagrid('load', {
				words : $('#search_user_words').val()
			});
		}
		
		
		//union
		function doSearchUnion() {
			$('#union_tab').datagrid('load', {
				words : $('#search_words').val(),
				state : $('#search_state').val(),
				pd_words:$("#pd_words").val(),
				dstart:$("#dstart").val(),
				dend:$("#dend").val()
			});
		}
		function doExport() {
			 var queryParams = $('#union_tab').datagrid('options');
			    var form = $("<form>");
			    form.attr('style', 'display:none');
			    form.attr('target', '');
			    form.attr('method', 'get'); //请求方式
			    form.attr('action', 'union/exportUnion');//请求地址

			    var input1 = $('<input>');//将你请求的数据模仿成一个input表单
			    var input2 = $('<input>');
			    var input3 = $('<input>');
			    var input4 = $('<input>');
			    var input5 = $('<input>');
			    var input6 = $('<input>');
			    var input7 = $('<input>');
			    input1.attr('type', 'hidden');
			    input1.attr('name', 'words');//该输入框的name
			    input1.attr('value',$('#search_words').val());//该输入框的值
			    
			    input2.attr('type', 'hidden');
			    input2.attr('name', 'state');//该输入框的name
			    input2.attr('value',$('#search_state').val());//该输入框的值

			    input3.attr('type', 'hidden');
			    input3.attr('name', 'pd_words');//该输入框的name
			    input3.attr('value',$("#pd_words").val());//该输入框的值
			    
			    input4.attr('type', 'hidden');
			    input4.attr('name', 'dstart');//该输入框的name
			    input4.attr('value',$("#dstart").val());//该输入框的值
			    
			    input5.attr('type', 'hidden');
			    input5.attr('name', 'dend');//该输入框的name
			    input5.attr('value',$("#dend").val());//该输入框的值
			    
			    input6.attr('type', 'hidden');
			    input6.attr('name', 'page');//该输入框的name
			    input6.attr('value',queryParams.pageNumber);//该输入框的值
			    
			    input7.attr('type', 'hidden');
			    input7.attr('name', 'rows');//该输入框的name
			    input7.attr('value',queryParams.pageSize);//该输入框的值

			    $('body').append(form);
			    form.append(input1);
			    form.append(input2);
			    form.append(input3);
			    form.append(input4);
			    form.append(input5);
			    form.append(input6);
			    form.append(input7);
			    
			    form.submit();
			    form.remove();

			 
			 
			
		}		
		function formatShow(val, row) {
			var chakan = "<a href='javascript:void(0)' id='" + val
			+ "' onclick='showUnion(this)'>查看</a>";
			var daochu = "<span style='margin-left:10px;'><a href='javascript:void(0)' id='" + val
			+ "' onclick='exportDoc(this)'>导出</a></span>";
			return chakan + daochu;
		}
		
		function showUnion(that){
			var id = $(that).attr("id");
			$("#win").window("open");
			$.getJSON("union/union",{id:id},function(result,status){
				
				if(result.success){
					var data = result.data;
					$('#baseInfo').form('load', data);
					$("#photo").attr('src',"upload/"+data.photoUrl);		
				}else{
					if(result.error == "not login"){
						window.location.href="login.html";
					}
				}
				
				
			});
			$("#unionInfo").show();
			$("#baseInfo").show();
			$("#preserveModelView").show();
			$("#preserveback").show();
			$("#preservesave").hide();
		}
		
		function exportDoc(that){
			var id = $(that).attr("id");
			console.log(id);
			 var form = $("<form>");
			    form.attr('style', 'display:none');
			    form.attr('target', '');
			    form.attr('method', 'get'); //请求方式
			    form.attr('action', 'union/exportDocx');//请求地址

			    var input1 = $('<input>');//将你请求的数据模仿成一个input表单
			    input1.attr('type', 'hidden');
			    input1.attr('name', 'exdataId');//该输入框的name
			    input1.attr('value',id);//该输入框的值
			    
			    $('body').append(form);
			    form.append(input1);
			    
			    form.submit();
			    form.remove();
		}
		
	function submitUpdateUnionForm(s){
		if(s == 2){
			 $.messager.prompt('Prompt', '请说明打回原因:', function(r){
				 if(typeof(r)=="undefined"){
					 return;
				 }
					$.messager.progress();	// display the progress bar
					$('#baseInfo').form('submit', {
						url: 'union/update',
						onSubmit: function(param){
							param.state = s;
							param.info = r;
							var isValid = $(this).form('validate');
							if (!isValid){
								$.messager.progress('close');	// hide progress bar while the form is invalid
							}
							return isValid;	// return false will stop the form submission
						},
						success: function(data){
							$.messager.progress('close');	// hide progress bar while submit successfully
							var d = JSON.parse(data);
							if(d.success){
								$('#baseInfo').form('clear');
								$('#win').window('close');
								$('#union_tab').datagrid('reload');
								$.messager.alert('提示','操作成功','info');
							}else{
								$('#baseInfo').form('clear');
								$('#win').window('close');
								$.messager.alert('提示',d.error,'info');
							}
							
							
						}
					});
				}); 
		}else{
			$.messager.progress();	// display the progress bar
			$('#baseInfo').form('submit', {
				url: 'union/update',
				onSubmit: function(param){
					param.state = s;
					var isValid = $(this).form('validate');
					if (!isValid){
						$.messager.progress('close');	// hide progress bar while the form is invalid
					}
					return isValid;	// return false will stop the form submission
				},
				success: function(data){
					$.messager.progress('close');	// hide progress bar while submit successfully
					var d = JSON.parse(data);
					if(d.success){
						$('#baseInfo').form('clear');
						$('#win').window('close');
						$('#union_tab').datagrid('reload');
						$.messager.alert('提示','操作成功','info');
					}else{
						$('#baseInfo').form('clear');
						$('#win').window('close');
						$.messager.alert('提示',d.error,'info');
					}
					
					
				}
			});
		}	
		}
	
	
	function addUnion(){
		$("#win").window("open");
		$('#baseInfo').form('clear');
		$("#baseInfo").show();
		$("#unionInfo").show();
		
		$("#preserveModelView").hide();
		$("#preserveback").hide();
		$("#preservesave").show();
	}
	function submitAddUnion(){
		$.messager.progress();	// display the progress bar
		$('#baseInfo').form('submit', {
			url: 'union/save',
			onSubmit: function(param){
				var isValid = $(this).form('validate');
				if (!isValid){
					$.messager.progress('close');	// hide progress bar while the form is invalid
				}
				return isValid;	// return false will stop the form submission
			},
			success: function(data){
				$.messager.progress('close');	// hide progress bar while submit successfully
				var d = JSON.parse(data);
				if(d.success){
					$('#baseInfo').form('clear');
					$('#win').window('close');
					$('#union_tab').datagrid('reload');
					$.messager.alert('提示','操作成功','info');
				}else{
					$('#baseInfo').form('clear');
					$('#win').window('close');
					$.messager.alert('提示',d.error,'info');
				}
				
				
			}
		});
	}
	
	$('#main').tabs({
        tools:[{
            text:' 注销 ',
            handler:function(){
			$.cookie('user',null,{date:new Date,path:'/gonghui'});
			window.location.reload(true);

            }
        }]
    });
	console.log($.cookie("user"));