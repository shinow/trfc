<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>物料管理</title>
<meta name="keywords" content=" 天瑞" />
<meta name="description" content="">
<meta name="author" content="">
<link href="/resources/css/bootstrap.css" rel="stylesheet">
<link href="/resources/css/base.css" rel="stylesheet">
<link href="/resources/css/style.css" rel="stylesheet">
<link href="/resources/css/pagination.css" rel="stylesheet">
<script language="javascript" type="text/javascript" src="/resources/js/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
	<div class="left ">
		<div class="user">
			<a href="#" data-toggle="dropdown" data-target="#menu-messages"
				class="dropdown-toggle"> <img src="/resources/images/tx.jpg"
				class="img-circle"> <label>超级管理员</label> <i class="iconfont">&#xe602;</i>
			</a>
			<ul class="dropdown-menu">
				<li><a data-toggle="modal" data-target="#account"><i
						class="iconfont">&#xe60e;</i>个人资料</a></li>
				<li class="divider"></li>
				<li><a data-toggle="modal" data-target="#password"><i
						class="iconfont">&#xe60d;</i> 设置</a></li>
			</ul>
		</div>
		<div class="menu">
			<label>菜单</label> <i class="iconfont fr">&#xe61a;</i>
		</div>
		<ul class="typelist">
			<li><i class="iconfont">&#xe617;</i> <label>采购管理</label></li>
			<li><i class="iconfont">&#xe615;</i> <label>销售管理</label></li>
			<li><i class="iconfont">&#xe614;</i> <label>其他页面</label></li>
			<li class="active"><i class="iconfont">&#xe618;</i> <label>档案管理</label>
			</li>
		</ul>
	</div>
	<div class="leftmini hide">
		<div class="user">
			<a href="#" data-toggle="dropdown" data-target="#menu-messages"
				class="dropdown-toggle"> <img src="/resources/images/tx.jpg"
				class="img-circle">
			</a>
			<ul class="dropdown-menu">
				<li><a data-toggle="modal" data-target="#account"><i
						class="iconfont">&#xe60e;</i>个人资料</a></li>
				<li class="divider"></li>
				<li><a data-toggle="modal" data-target="#password"><i
						class="iconfont">&#xe60d;</i> 设置</a></li>
			</ul>
		</div>
		<div class="menu2">
			<i class="iconfont">&#xe635;</i>
		</div>
		<ul class="typelist">
			<li class="active" data-toggle="tooltip" data-placement="right"
				title="采购管理"><i class="iconfont">&#xe617;</i></li>
			<li data-toggle="tooltip" data-placement="right" title="销售管理"><i
				class="iconfont">&#xe615;</i></li>
			<li data-toggle="tooltip" data-placement="right" title=" 其他"><i
				class="iconfont">&#xe614;</i></li>
			<li><i class="iconfont">&#xe618;</i></li>
			<li><i class="iconfont">&#xe619;</i></li>
			<li><i class="iconfont">&#xe613;</i></li>
			<li><i class="iconfont">&#xe612;</i></li>
			<li><i class="iconfont">&#xe610;</i></li>
			<li><i class="iconfont">&#xe60f;</i></li>
			<li><i class="iconfont">&#xe611;</i></li>
		</ul>
	</div>
	<div class="right">
		<div class="intel_tab">
			<!--tab切换标题-->
			<ul class="intel_menu">
				<li>客户管理</li>
				<li class="select">仓库管理</li>
				<li>供应商管理</li>
				<li>物料管理</li>
			</ul>
			<!--tab切换标题end-->
			<div class="top_opera">
				<a><i class="iconfont" data-toggle="tooltip"
					data-placement="left" title="首页">&#xe605;</i></a> <a><i
					class="iconfont" data-toggle="tooltip" data-placement="left"
					title="控制面板">&#xe606;</i></a> <a><i class="iconfont"
					data-toggle="tooltip" data-placement="left" title="退出">&#xe607;</i></a>
			</div>
		</div>

		<!--tab切换的内容-->
		<div class="intel_tabbox">
			<div id="test" class="intel_tabcont">
				<div class="intel_search">
					<div class="intel_bggray">
						<div class="intel_bgblue"></div>
					</div>
					<div class="intel_sconditon">
						<div class="intel_solo">
							<label>查询条件：</label> 
							<select id="qtp" class="form-control">
								<option value="mc">名称</option>
								<option value="nm">内码</option>
							</select>
						</div>
						<div class="intel_sline">
							<div class="intel_solo">
								<label>关键字：</label> 
								<input id="keyword" type="text">
							</div>
							<div class="intel_solo">
								<label>所属组织：</label> 
								<input id="orgid" type="text">
							</div>
							<div class="intel_solo">
								<div class="intel_sbtn">
									<button id="searchMater" class="btn btnblue">搜索</button>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="intel_opera">
					<div id="refreshMater" class="intel_operasolo">
						<i class="iconfont colorlv">&#xe61b;</i>
						<span>刷新</span>
					</div>
				</div>
				<div class="intel_table">
					<!--用户表格begin-->
					<table class="table table-hover">
						<thead>
							<tr>
					            <th>序号</th>
					            <th>编号</th>
					            <th>内码</th>
					            <th>名称</th>
					            <th>所属组织</th>
					            <th>描述</th>
					        </tr>
						</thead>
						<tbody id="materBody">
						</tbody>
					</table>
					<!--用户表格end-->
				</div>
				<!--分页效果开始-->
				<div class="page">
					<div class="page_date">
						<label>数据共：</label><i id="total" class="colorred">100</i><label>条</label>
					</div>
					<div class="page_date">
						<label>跳到第：</label> 
						<input id="jumpPageNo" type="text"> <label>页</label>
						<button id="jumpPageNoBtn" class="btn btn-default">确定</button>
					</div>
					<div class="page_date">
						<label>每页记录：</label>
						<select id="pageSize" class="form-control">
							<option value="10">10</option>
							<option value="20">20</option>
							<option value="30">30</option>
						</select>
					</div>
					<div class="page_btn" id="pagination"></div>
				</div>
				<!--分页效果结束-->
			</div>
		</div>
	</div>
	<script type="text/javascript" src="/resources/js/jquery-1.11.1.js"></script>
	<script type="text/javascript" src="/resources/js/jquery.pagination.js"></script>
	<script type="text/javascript" src="/resources/js/bootstrap.js"></script>
	<script type="text/javascript" src="/resources/js/layer/layer.js"></script>
	<script type="text/javascript" src="/resources/js/basicFile/nc/warehouse.js"></script>
</body>
</html>