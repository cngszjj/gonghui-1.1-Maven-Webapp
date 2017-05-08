	
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
		
//		$("#search_state").combobox({
//			onSelect:function(){
//				doSearchUnion();
//			}
//		}
				
//		)
		

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
		function formatShow(val, row) {
			return "<a href='javascript:void(0)' id='" + val
					+ "' onclick='showUnion(this)'>查看</a>";
		}
		
		function showUnion(that){
			var id = $(that).attr("id");
			$("#win").window("open");
			$.getJSON("union/union",{id:id},function(result,status){
				
				if(result.success){
					var data = result.data;
					$('#baseInfo').form('load', data);
					$("#photo").attr('src',"upload/"+data.photoUrl);
//					$("#photo").attr('src',data.photoUrl);
					
					
				}else{
					if(result.error == "not login"){
						window.location.href="login.html";
					}
				}
				
				
			});
			$("#unionInfo").show();
			$("#baseInfo").show();
		}
		
	function submitUpdateUnionForm(s){
		if(s == 2){
			 $.messager.prompt('Prompt', '请说明打回原因:', function(r){
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
	
	$('#main').tabs({
        tools:[{
            
            text:' 注销 ',
            handler:function(){
			$.cookie('user',null);
			window.location.href="/gonghui/login.html";

            }
        }]
    });