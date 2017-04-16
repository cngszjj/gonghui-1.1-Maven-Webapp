<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/common/cookie.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>系统管理</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css"
	href="easyUI/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="easyUI/themes/icon.css">
<link rel="stylesheet" type="text/css" href="easyUI/demo/demo.css">
<link rel="stylesheet" type="text/css" href="css/style.css">
<script type="text/javascript" src="../easyUI/jquery.min.js"></script>
<script src="easyUI/jquery.min.js"></script>
<script src="//cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
<script type="text/javascript" src="easyUI/jquery.easyui.min.js"></script>
<script type="text/javascript" src="easyUI/locale/easyui-cn.js" ></script>

<script type="text/javascript">
	function formatState(val, row) {

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
</script>

</head>

<body>
	<table class="easyui-datagrid" id="union_tab"
		style="width:100%;height:auto;"
		data-options="rownumbers:true,singleSelect:true,url:'union/list',
			method:'get',toolbar:'#tb',pagination:true,pageSize:10,displayMsg:'当前显示从第{from}条到{to}条 共{total}条记录'">
		<thead>
			<tr>
				<th field="unionCardCode" width="120">机构代码证号码</th>
				<th field="unionName" width="200">机构名称</th>
				<th field="legal" width="200">法人</th>
				<th field="produceDivision" width="120">行政区划</th>
				<th field="state" width="240" formatter="formatState">审核状态</th>
				<th field="id" width="240" formatter="formatShow"></th>
			</tr>
		</thead>
	</table>
	<div id="tb" style="padding:5px;height:auto">

		<div>
			<input class="easyui-textbox" placeholder="请输入关键字进行搜索" name="words"
				id="search_words" > <select class="easyui-combobox"
				id="search_state" panelHeight="auto" name="state"
				style="width:100px">
				<option value="">全部</option>
				<option value="0">未审核</option>
				<option value="1">已审核</option>
				<option value="2">不通过</option>

			</select> <a href="javascript:viod(0)" class="easyui-linkbutton"
				iconCls="icon-search" onclick="doSearch()">查询</a>
		</div>
	</div>
	<div id="win" class="easyui-window" title="详情"
		style="width:1300px;height:600px"
		data-options="iconCls:'icon-save',modal:true,closed:true">
		<div id="unionInfo" style="display:none">
		<form action="/gonghui/union/sava" id="baseInfo" style="display:none" method="post"
			class="form-inline">
			<fieldset>
				<label for="unionName"><span style="color: red;">*</span>工会名称</label>
				<input id="unionName" name="unionName" class="easyui-textbox"
					data-options="required:true,missingMessage:'该输入项为必输项',validType:'NotEmpty'">

				<label for="unionPhone"><span style="color: red;">*</span>联系电话</label>
				<input id="unionPhone" name="unionPhone" type="text"
					data-options="required:true,missingMessage:'该输入项为必输项',validType:'NotEmpty'"
					class="easyui-textbox"> 
					
			</fieldset>
			<fieldset>
				<label for="registerAddress"><span style="color: red;">*</span>住所地址</label>
				<input id="registerAddress" type="text" name="registerAddress"
					class="easyui-textbox"
					data-options="required:true,missingMessage:'该输入项为必输项',validType:'NotEmpty'"
					> <label for="registerZipcode">住所地址邮政编码</label>
				<input id="registerZipcode" name="registerZipcode" type="text"
					class="easyui-textbox">
			</fieldset>
			<fieldset>
				<label for="produceAddress"><span style="color: red;">*</span>办公地址</label>
				<input id="produceAddress" name="produceAddress"
					data-options="required:true,missingMessage:'该输入项为必输项',validType:'NotEmpty'"
					type="text" class="easyui-textbox" style="width: ;"> <label
					for="produceDivision"><span style="color: red;">*</span>办公地址行政区划</label>
				<select name="produceDivision" id="produceDivision"
					class="easyui-combobox"
					data-options="required:true,missingMessage:'该输入项为必输项',validType:'NotEmpty'"
					style="width:152px">
					<option data-value="1">西安市总工会</option>

					<option data-value="2">新城区总工会</option>
					<option data-value="3">碑林区总工会</option>
					<option data-value="4">莲湖区总工会</option>
					<option data-value="5">雁塔区总工会</option>
					<option data-value="6">灞桥区总工会</option>
					<option data-value="7">未央区总工会</option>
					<option data-value="8">阎良区总工会</option>
					<option data-value="9">临潼区总工会</option>
					<option data-value="9">长安区总工会</option>
					<option data-value="10">蓝田县总工会</option>
					<option data-value="11">周至县总工会</option>

					<option data-value="11">机械冶金建材工会</option>
					<option data-value="11">建设交通工会</option>
					<option data-value="11">轻工纺织工会</option>
					<option data-value="11">石化农林工会</option>
					<option data-value="11">财贸工会</option>
					<option data-value="11">教科文卫工会</option>
					<option data-value="11">西电公司工会</option>
					<option data-value="11">市级机关工会</option>
					<option data-value="11">西安银行公会</option>

					<option data-value="11">高新技术开发区工会</option>
					<option data-value="11">曲江新区工会</option>
					<option data-value="11">浐灞生态区工会</option>
					<option data-value="11">航天基地工会</option>
					<option data-value="11">沣东新城工会</option>
					<option data-value="11">国际港务区工会</option>

				</select> <label for="qvregisterZipcode">办公地址邮政编码</label> <input
					id="qvregisterZipcode" name="qvregisterZipcode" type="text"
					class="easyui-textbox">
			</fieldset>
			<fieldset>
				<label for="setupTime" href="#" title="该工会成立日期"
					class="easyui-tooltip"><span style="color: red; ">*</span>成立日期</label>
				<input id='setupTime' name="setupTime" class="easyui-datebox"
					editable="false"
					data-options="required:true,missingMessage:'该输入项为必输项',validType:'NotEmpty'"
					style="width: ;height:26px"> <label for="approvalTime"
					href="#" title="核准日期" class="easyui-tooltip"><span
					style="color: red;">*</span>核准日期</label> <input class="easyui-datebox"
					id='approvalTime' editable="false" name="approvalTime"
					data-options="required:true,missingMessage:'该输入项为必输项',validType:'NotEmpty'"
					style="width: ;height:26px">
			</fieldset>
			<fieldset>
				<label for="workUnit"><span style="color:red">*</span>审批单位</label> <input
					id="workUnit" name="workUnit" type="text"
					data-options="required:true,missingMessage:'该输入项为必输项',validType:'NotEmpty'"
					class="easyui-textbox"> <label for="workUnitCode"><span
					style="color:red">*</span>审批文号</label> <input id="workUnitCode"
					name="workUnitCode"
					data-options="required:true,missingMessage:'该输入项为必输项',validType:'NotEmpty'"
					class="easyui-textbox"> <label for="workersCount"><span
					style="color:red">*</span>现职工人数</label> <input id="workersCount"
					name="workersCount" type="number"
					data-options="required:true,missingMessage:'该输入项为必输项',validType:'NotEmpty'"
					class="easyui-textbox"> <label for="vipCount"><span
					style="color:red">*</span>会员人数</label> <input id="vipCount" name="vipCount"
					type="number"
					data-options="required:true,missingMessage:'该输入项为必输项',validType:'NotEmpty'"
					class="easyui-textbox">

			</fieldset>
			<fieldset>
				<label for="unionWorkersCount"><span style="color:red">*</span>专职工会干部数</label>
				<input id="unionWorkersCount" name="unionWorkersCount"
					data-options="required:true,missingMessage:'该输入项为必输项',validType:'NotEmpty'"
					type="number" class="easyui-textbox"> <label
					for="unitNature">所在单位性质</label> <input id="unitNature"
					name="unitNature" type="text" class="easyui-textbox"> <label
					for="unitType">所在单位行业属性</label> <input id="unitType"
					name="unitType" type="text" class="easyui-textbox"
					>

			</fieldset>
			<fieldset>
				<label for="lastYearSurplus">上年结余累计（万元）</label> <input
					id="lastYearSurplus" name="lastYearSurplus" type="number" value=0
					class="easyui-textbox"> <label for="lastYearVIPSurplus">年会员缴纳会费收入（万元）</label>
				<input id="lastYearVIPSurplus" name="lastYearVIPSurplus"
					type="number" value=0 class="easyui-textbox"> <label
					for="unionFunds">年2%拨交工会经费本级留成收入（万元）</label> <input id="unionFunds"
					name="unionFunds" type="number" value=0 class="easyui-textbox">
				<label for="lastYearOtherSurplus">其他收入（万元）</label> <input
					id="lastYearOtherSurplus" name="lastYearOtherSurplus" value=0
					type="number" class="easyui-textbox">

			</fieldset>
			<fieldset>
				<label for="fixedFunds">固定资金（万元）</label> <input id="fixedFunds"
					name="fixedFunds" type="number" value=0 class="easyui-textbox">
				<label for="flowFunds">流动资金（≥0.6万元）</label> <input id="flowFunds"
					name="flowFunds" type="number" value=0 class="easyui-textbox">
				<label for="otherFunds">其他资金</label> <input id="otherFunds"
					name="otherFunds" type="number" value=0 class="easyui-textbox">
				<label for="countFunds">资金合计</label> <input id="countFunds"
					name="countFunds" type="number" value=0 class="easyui-textbox">

			</fieldset>

			<fieldset>
				<label for="workPlace">办公场所(平方米)㎡</label> <input id="workPlace"
					name="workPlace" type="number" value=0 class="easyui-textbox">
				<label for="sportPlace">活动场所(平方米)㎡</label> <input id="sportPlace"
					name="sportPlace" type="number" value=0 class="easyui-textbox">
				<label for="otherPlace">其他场所(平方米)㎡</label> <input id="otherPlace"
					name="otherPlace" type="number" value=0 class="easyui-textbox">
				<label for="countPlace">场所合计(平方米)㎡</label> <input id="countPlace"
					name="countPlace" type="number" value=0 class="easyui-textbox">

			</fieldset>

			<fieldset>
				<label for="unionCardCode"><span style="color:red">*</span>机构代码证号码(社会信用代码)</label>
				<input id="unionCardCode" name="unionCardCode" type="text"
					data-options="required:true,missingMessage:'该输入项为必输项',validType:'NotEmpty'"
					class="easyui-textbox"> <label for="legalCardCode"><span
					style="color:red">*</span>工会法人资格证书号码</label> <input id="legalCardCode"
					name="legalCardCode" type="text"
					data-options="required:true,missingMessage:'该输入项为必输项',validType:'NotEmpty'"
					class="easyui-textbox"> <label for="civilLiability"><span
					style="color:red">*</span>承担民事责任能力状况</label> <input id="civilLiability"
					name="civilLiability" type="text"
					data-options="required:true,missingMessage:'该输入项为必输项',validType:'NotEmpty'"
					class="easyui-textbox" style="width:;">

			</fieldset>


			<fieldset>
				<label for="legal"><span style="color: red;">*</span>法定代表人</label> <input
					id="legal" name="legal" type="text"
					data-options="required:true,missingMessage:'该输入项为必输项',validType:'NotEmpty'"
					class="easyui-textbox"> <label for="legalSex"><span
					style="color: red;">*</span>性别</label> <select id='legalSex'
					name="legalSex" class="easyui-combobox"
					data-options="required:true,missingMessage:'该输入项为必输项',validType:'NotEmpty'"
					style="width:152px;">
					<option value='1' data-value='1' data-name='000'>男</option>
					<option value='2' data-value='2' data-name='001'>女</option>
				</select> <label for="legalNation"><span style="color: red;">*</span>民族</label>
				<input id="legalNation" name="legalNation" type="text"
					data-options="required:true,missingMessage:'该输入项为必输项',validType:'NotEmpty'"
					class="easyui-textbox"> <label for="legalTime"><span
					style="color: red;">*</span>出生年月</label> <input id='legalTime'
					name="legalTime" class="easyui-datebox" editable="false"
					data-options="required:true,missingMessage:'该输入项为必输项',validType:'NotEmpty'"
					style="width: 153px;height:26px">
			</fieldset>

			<fieldset>
				<label for="culture"><span style="color: red;">*</span>文化程度</label>
				<input id="culture" name="culture" type="text"
					data-options="required:true,missingMessage:'该输入项为必输项',validType:'NotEmpty'"
					class="easyui-textbox"> <label for="politics"><span
					style="color: red;">*</span>政治面貌</label> <input id="politics"
					name="politics" type="text"
					data-options="required:true,missingMessage:'该输入项为必输项',validType:'NotEmpty'"
					class="easyui-textbox"> <label for="unionPost"><span
					style="color: red;">*</span>现任工会职务</label> <input id="unionPost"
					name="unionPost" type="text"
					data-options="required:true,missingMessage:'该输入项为必输项',validType:'NotEmpty'"
					class="easyui-textbox"> <label for="concurrentPost"><span
					style="color: red;">*</span>属兼职或者专职</label> <select id='concurrentPost'
					name="concurrentPost"
					data-options="required:true,missingMessage:'该输入项为必输项',validType:'NotEmpty'"
					class="easyui-combobox" style="width:152px;">
					<option value='1' data-value='1' data-name='000'>专职</option>
					<option value='2' data-value='2' data-name='001'>兼职</option>
				</select>
			</fieldset>

			<fieldset>
				<label for="legalOtherPost"><span style="color: red;">*</span>现任其他职务</label>
				<input id="legalOtherPost" name="legalOtherPost" type="text"
					data-options="required:true,missingMessage:'该输入项为必输项',validType:'NotEmpty'"
					class="easyui-textbox"> <label for="legalCredCode"><span
					style="color: red;">*</span>证件号码</label> <input id="legalCredCode"
					name="legalCredCode" type="text"
					data-options="required:true,missingMessage:'该输入项为必输项',validType:'NotEmpty'"
					class="easyui-textbox"> <label for="legalPhone"><span
					style="color: red;">*</span>手机号码</label> <input id="legalPhone"
					name="legalPhone" type="text"
					data-options="required:true,missingMessage:'该输入项为必输项',validType:'NotEmpty'"
					class="easyui-textbox">
			</fieldset>
			<fieldset>
				<label for="operationBegin" href="#" title="本届任起始时间"
					class="easyui-tooltip"><span style="color: red;">*</span>本届任起始时间</label>
				<input type='text' id='operationBegin' editable="false"
					name="operationBegin" class="easyui-datebox"
					data-options="required:true,missingMessage:'该输入项为必输项',validType:'NotEmpty'"
					style="width: ;" /> <label for="operationTime" href="#"
					title="何时加入工会组织" class="easyui-tooltip"><span
					style="color: red;">*</span>何时加入工会组织</label> <input type='text'
					id='operationTime' editable="false" name="operationTime"
					class="easyui-datebox"
					data-options="required:true,missingMessage:'该输入项为必输项',validType:'NotEmpty'"
					style="width: ;" />
			</fieldset>


			<fieldset>
				<label for="businessScope">业务范围</label> <input id="businessScope"
					name="businessScope" class="easyui-textbox"
					data-options="multiline:true" style="width:980px;height:100px">

			</fieldset>

			<fieldset>
				<label for="operatorName"><span style="color:red">*</span>经办人姓名</label>
				<input id="operatorName" name="operatorName" type="text"
					data-options="required:true,missingMessage:'该输入项为必输项',validType:'NotEmpty'"
					class="easyui-textbox"> <label for="operatorPost"><span
					style="color:red">*</span>职务</label> <input id="operatorPost"
					name="operatorPost" type="text"
					data-options="required:true,missingMessage:'该输入项为必输项',validType:'NotEmpty'"
					class="easyui-textbox"> <label for="operatorCode"><span
					style="color: red;">*</span>证件号码</label> <input id="operatorCode"
					name="operatorCode" type="text"
					data-options="required:true,missingMessage:'该输入项为必输项',validType:'NotEmpty'"
					class="easyui-textbox"> <label for="operatorPhone"><span
					style="color: red;">*</span>手机号码</label> <input id="operatorPhone"
					name="operatorPhone" type="text"
					data-options="required:true,missingMessage:'该输入项为必输项',validType:'NotEmpty'"
					class="easyui-textbox">
			</fieldset>

			<input type="text"  name="id" style="display:none">





		</form>

		<footer>
	    <div>
	         <a href="javascript:void(0)" id="preserveModelView" data-options="size:'large',iconCls:'icon-save'" class="easyui-linkbutton" onclick="submitForm(1)" >通过&nbsp;&nbsp;</a>
	    		<a href="javascript:void(0)" id="preserveModelView" data-options="size:'large',iconCls:'icon-cancel'" class="easyui-linkbutton" onclick="submitForm(2)" >打回&nbsp;&nbsp;</a>
	    </div>
    		</footer>
    		</div>
	</div>
	<script>
		function doSearch() {
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
		
	function submitForm(s){
		$.messager.prompt('Prompt', 'Please enter your name:', function(r){
			if (r){
				alert('Your name is:' + r);
			}
		});
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
						$.messager.alert('提示','更新成功','info');
					}else{
						$('#baseInfo').form('clear');
						$('#win').window('close');
						$.messager.alert('提示',d.error,'info');
					}
					
					
				}
			});
			
		}
	
	$('#union_tab').datagrid({
        tools:[{
            
            text:' 注销 ',
            handler:function(){
			$.cookie('user',null);
			window.location.href="login.html";

            }
        }]
    });
	</script>
	
	
</body>
</html>
