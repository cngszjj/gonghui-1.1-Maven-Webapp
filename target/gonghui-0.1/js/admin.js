	
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
				state : $('#search_state').val()
			});
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
		/* $.messager.prompt('Prompt', 'Please enter your name:', function(r){
			if (r){
				alert('Your name is:' + r);
			}
		}); */
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
	
	$('#main').tabs({
        tools:[{
            
            text:' 注销 ',
            handler:function(){
			$.cookie('user',null);
			window.location.href="login.html";

            }
        }]
    });