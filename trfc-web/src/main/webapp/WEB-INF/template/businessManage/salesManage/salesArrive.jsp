<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>到货通知单</title>
<meta name="keywords" content=" 天瑞" />
<meta name="description" content="">
<meta name="author" content="">
<link href="${basePath }/css/bootstrap.css" rel="stylesheet">
<link href="${basePath }/css/base.css" rel="stylesheet">
<link href="${basePath }/css/style.css" rel="stylesheet">
<link href="${basePath }/css/pagination.css" rel="stylesheet">
<script language="javascript" type="text/javascript"
	src="${basePath }/js/My97DatePicker/WdatePicker.js"></script>

</head>
<body>
	<div class="left ">
		<div class="user">
			<a href="#" data-toggle="dropdown" data-target="#menu-messages"
				class="dropdown-toggle"> <img src="${basePath }/images/tx.jpg"
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
		<div class="menu_collap">
			<ul class="typelist ">
				<a href="#ityewu" data-toggle="collapse" class="menu_collap_tit">
					<label>业务管理</label> <span><i class="iconfont">&#xe604;</i></span>
				</a>
				<div class="in" id="ityewu">
					<li class="active"><a href="../cg/cg_index.html"> <i
							class="iconfont">&#xe617;</i> <label>采购管理</label>
					</a></li>
					<li><a href="../sell/sell_apply.html"> <i class="iconfont">&#xe615;</i>
							<label>销售管理</label>
					</a></li>
					<li><a> <i class="iconfont">&#xe614;</i> <label>其他业务</label>
					</a></li>
					<li><a> <i class="iconfont">&#xe618;</i> <label>质控管理</label>
					</a></li>
				</div>
				<a href="#itdangan" data-toggle="collapse" class="menu_collap_tit">
					<label>基础档案</label> <span><i class="iconfont">&#xe604;</i></span>
				</a>
				<div class="in" id="itdangan">
					<li><a href="../file_nc/client.html"> <i class="iconfont">&#xe617;</i>
							<label>NC档案</label>
					</a></li>
					<li><a href="../file_jil/car.html"> <i class="iconfont">&#xe617;</i>
							<label>计量档案</label>
					</a></li>
					<li><a href="../file-other/car.html"> <i class="iconfont">&#xe617;</i>
							<label>其他档案</label>
					</a></li>
				</div>
			</ul>
		</div>
	</div>
	<div class="leftmini hide">
		<div class="user">
			<a href="#" data-toggle="dropdown" data-target="#menu-messages"
				class="dropdown-toggle"> <img src="${basePath }/images/tx.jpg"
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
				<li>采购申请单</li>
				<li class="select">到货通知单</li>
				<li>退货通知单</li>
				<li>采购车辆状态</li>
				<li>采购划价单</li>
			</ul>
			<!--tab切换标题end-->
			<div class="top_opera">
				<a><i class="iconfont">&#xe605;</i></a> <a><i class="iconfont">&#xe606;</i></a>
				<a><i class="iconfont">&#xe607;</i></a>
			</div>
		</div>
		<!--tab切换的内容-->
		<div class="intel_tabbox">
			<!--采购申请单begin-->
			<div class="intel_tabcont">
				<div class="intel_search">
					<div class="intel_bggray">
						<div class="intel_bgblue"></div>
					</div>
					<div class="intel_sconditon">
						<div class="intel_sline">
							<div class="intel_solo">
								<label>订单号：</label> <input id="billno" type="text">
							</div>
							<div class="intel_solo">
								<label>通知单号：</label> <input id="code" type="text">
							</div>
							<div class="intel_solo">
								<label>供应商：</label> 
								<select id="supplierid" class="form-control">
									<option value="">请选择</option>
									<c:forEach items="${supplier }" var="s">
										<option value="${s.id }">${s.name }</option>
									</c:forEach>
								</select>
							</div>
							<div class="intel_solo">
								<label>车号：</label> 
								<select id="vehicleid" class="form-control">
									<option value="">请选择</option>
									<c:forEach items="${vehicle }" var="v">
										<option value="${v.id }">${v.vehicleno }</option>
									</c:forEach>
								</select>
							</div>
							<div class="intel_solo">
								<label>审核状态：</label> 
								<select id="auditstatus" class="form-control">
									<option value="">请选择</option>
									<option value="0">未审核</option>
									<option value="1">已审核</option>
								</select>
							</div>
							<div class="intel_solo">
								<label>物料：</label> 
								<select id="materielid" class="form-control">
									<option value="">请选择</option>
									<c:forEach items="${materiel }" var="m">
										<option value="${m.id }">${m.name }</option>
									</c:forEach>
								</select>
							</div>
							<div class="intel_solo">
								<label>司机：</label> 
								<select id="driverid" class="form-control">
									<option value="">请选择</option>
									<c:forEach items="${driver }" var="d">
										<option value="${d.id }">${d.name }</option>
									</c:forEach>
								</select>
							</div>
							<div class="intel_solo">
								<label>单据来源：</label> 
								<select id="source" class="form-control">
									<option value="">请选择</option>
									<option value="0">业务平台</option>
									<option value="1">客商平台</option>
									<option value="2">客商APP</option>
								</select>
							</div>
							<div class="intel_solo">
								<label>状态：</label> 
								<select id="status" class="form-control">
									<option value="">请选择</option>
									<option value="0">未入厂</option>
									<option value="1">空车</option>
									<option value="2">重车</option>
									<option value="3">作废</option>
									<option value="4">发卡</option>
									<option value="5">出厂</option>
									<option value="6">入厂</option>
									<option value="7">装车</option>
								</select>
							</div>
							<div class="intel_solo">
								<label>创建时间：</label> <input id="s_starttime" type="text"
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
										class="Wdate" style="width: 160px;" readonly /> <i>-</i> <input
										id="s_endtime" type="text"
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
										class="Wdate" style="width: 160px;" readonly />
							</div>
							<div class="intel_solo">
								<div class="intel_sbtn">
									<button id="searchBtn" class="btn btnblue ">搜索</button>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="intel_opera">
					<div class="intel_operasolo">
						<i class="iconfont colorlv">&#xe61b;</i>
						<h5>刷新</h5>
					</div>
					<div class="intel_operasolo">
						<a href="daohuo_add.html"> <i class="iconfont coloradd">&#xe627;</i>
							<h5>新增</h5>
						</a>
					</div>
					<div class="intel_operasolo">
						<i class="iconfont coloradd">&#xe61c;</i>
						<h5>复制</h5>
					</div>
				</div>
				<div class="intel_table">
					<!--用户表格begin-->
					<table class="table table-hover">
						<thead>
							<tr>
								<th>到货单号</th>
								<th>审核</th>
								<th>来源</th>
								<th>状态</th>
								<th>车号</th>
								<th>订单编号</th>
								<th>供应商</th>
								<th>物料</th>
								<th>制单日期</th>
								<th>订单日期</th>
								<th>备注</th>
								<th>供应商备注</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>CD201601010138</td>
								<td class="colorred">审核中</td>
								<td>客商APP</td>
								<td>未入厂</td>
								<td>豫GA1783</td>
								<td>CD201601010138</td>
								<td>卫辉市润晨商贸有限公司</td>
								<td>粉煤灰1</td>
								<td>2016-01-01 07:59:39</td>
								<td>2016-01-01 07:59:39</td>
								<td>粉煤灰1</td>
								<td>粉煤灰1</td>
								<td><span> <i class="iconfont" data-toggle="tooltip"
										data-placement="left" title="编辑">&#xe600;</i></span> <span> <i
										class="iconfont " data-toggle="tooltip" data-placement="left"
										title="审核">&#xe692;</i></span> <span> <i class="iconfont"
										data-toggle="tooltip" data-placement="left" title=" 作废">&#xe60c;</i></span>
									<span> <i class="iconfont" data-toggle="tooltip"
										data-placement="left" title=" 反审">&#xe651;</i></span> <span> <i
										class="iconfont" data-toggle="tooltip" data-placement="left"
										title=" 出厂">&#xe63c;</i></span></td>
							</tr>

						</tbody>
					</table>
					<!--用户表格end-->
				</div>
				<div class="intel_result" id="ind_tab">
					<div class="cg_tabtit">
						<ul>
							<li class="select">物料信息</li>
							<li>订单信息</li>
							<li>过磅信息</li>
						</ul>
					</div>
					<div class="cg_tabbox">
						<!--tab切换的内容-->
						<div class="cg_tabcont">
							<table class="table table-bordered">
								<thead>
									<tr>
										<th>车号</th>
										<th>到货数量</th>
										<th>司机</th>
										<th>身份证号</th>
										<th>制单日期</th>
										<th>制单人</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>卫辉市天瑞水泥有限公司</td>
										<td>粉煤炭</td>
										<td>方案2</td>
										<td>1000</td>
										<td>豫GA1783</td>
										<td>豫GA1783</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="cg_tabcont hide">
							<table class="table table-bordered">
								<thead>
									<tr>
										<th>订单编号</th>
										<th>供应商</th>
										<th>物料</th>
										<th>组织机构</th>
										<th>订单量</th>
										<th>订单日期</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>卫辉市天瑞水泥有限公司</td>
										<td>粉煤炭</td>
										<td>方案2</td>
										<td>1000</td>
										<td>豫GA1783</td>
										<td>豫GA1783</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="cg_tabcont hide">
							<table class="table table-bordered">
								<thead>
									<tr>
										<th>磅单号</th>
										<th>车号</th>
										<th>毛重</th>
										<th>皮重</th>
										<th>净重</th>
										<th>轻车时间</th>
										<th>重车时间</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>卫辉市天瑞水泥有限公司</td>
										<td>粉煤炭</td>
										<td>方案2</td>
										<td>1000</td>
										<td>豫GA1783</td>
										<td>豫GA1783</td>
										<td>豫GA1783</td>
									</tr>
								</tbody>
							</table>
						</div>
						<!--tab切换的内容end-->
					</div>
				</div>

				<!--分页效果开始-->
				<div class="page">
					<div class="page_date">
						<label>数据共：</label><i id="total" class="colorred">100</i><label>条</label>
					</div>
					<div class="page_date">
						<label>跳到第：</label> <input id="jumpPageNo" type="text" value="asd"> <label>页</label>
						<button id="jumpPageNoBtn" class="btn btn-default">确定</button>
					</div>
					<div class="page_date">
						<label>每页记录：</label> <select id="pageSize" class="form-control">
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
	<!--查看详情begin-->
	<div class="modal fade" id="caigoubill" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document" style="width: 900px;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<div class="alt_head">
						<h5>采购申请单详细信息</h5>
						<img src="${basePath }/images/sh.png">
					</div>
				</div>
				<div class="modal-body">
					<div class="">
						<div class="cg_div">
							<div class="cg_solo">
								<label>单据编号：</label> <input type="text">
							</div>
							<div class="cg_solo">
								<label>单据来源：</label> <input type="text">
							</div>
							<div class="cg_solo">
								<label>订单类型：</label> <input type="text">
							</div>
							<div class="cg_solo">
								<label>订单日期：</label> <input type="text">
							</div>
							<div class="cg_solo">
								<label>供应商：</label> <input type="text">
							</div>
							<div class="cg_solo">
								<label>总数量：</label> <input type="text">
							</div>
							<div class="cg_solo">
								<label>采购员：</label> <input type="text">
							</div>
							<div class="cg_solo">
								<label>制单人： </label> <input type="text">
							</div>
							<div class="cg_solo">
								<label> 制单日期：</label> <input type="text">
							</div>
							<div class="cg_bz">
								<label>备注：</label> <input type="text">
							</div>
						</div>
						<div class="cg_tabtit">
							<ul>
								<li class="select">订单明细</li>
								<li>质检信息</li>
								<li>质检信息</li>
							</ul>
						</div>
						<div class="cg_tabbox">
							<!--tab切换的内容-->
							<div class="cg_tabcont">
								<table class="table table-bordered">
									<thead>
										<tr>
											<th>采购组织</th>
											<th>物料</th>
											<th>质检方案</th>
											<th>数量</th>
											<th>备注</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>卫辉市天瑞水泥有限公司</td>
											<td>粉煤炭</td>
											<td>方案2</td>
											<td>1000</td>
											<td>豫GA1783</td>
										</tr>
										<tr>
											<td>合计</td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
										</tr>
									</tbody>
								</table>
							</div>
							<div class="cg_tabcont hide">1</div>
							<!--tab切换的内容end-->
						</div>
					</div>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary">确定</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
	<!--查看详情end-->
	<script type="text/javascript" src="${basePath }/js/jquery-1.11.1.js"></script>
	<script type="text/javascript" src="${basePath }/js/bootstrap.min.js"></script>
	<script type="text/javascript"
		src="${basePath }/js/jquery.pagination.js"></script>
	<script type="text/javascript" src="${basePath }/js/layer/layer.js"></script>
	<script type="text/javascript"
		src="${basePath }/js/businessManage/salesManage/salesArrive.js"></script>
</body>
</html>