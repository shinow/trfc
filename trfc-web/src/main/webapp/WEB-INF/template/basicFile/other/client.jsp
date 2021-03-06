<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无人值守-其他档案-其他客户</title>
<!-- 引用公共header部分 -->
<jsp:include page="../../common/base/header_busi.jsp"></jsp:include>

</head>
<body>
	<div class="it_admin">
		<!-- 引用公共left部分 -->
		<jsp:include page="../../common/base/left_busi.jsp"></jsp:include>
		<div class="right">
			<!-- 引用公共right部分 -->
			<jsp:include page="../../common/base/right_head_busi.jsp"></jsp:include>
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
									<label>查询条件：</label> <select class="form-control"
										id="customer_query">
										<option value='name'>名称</option>
										<option value='innercode'>内码</option>
									</select>
								</div>
								<div class="intel_solo">
									<label>关键字：</label> <input type="text" id="customer_keyword">
								</div>
								<div class="intel_solo">
									<div class="intel_sbtn">
										<button class="btn btnblue ">搜索</button>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="intel_opera">
						<!-- <div class="intel_operasolo" id="freshButton">
							<i class="iconfont colorlv">&#xe61b;</i>
							<h5>刷新</h5>
						</div>
						<div class="intel_operasolo" id="showAddCustomer">
							<a data-toggle="modal" data-target="#add"> <i
								class="iconfont coloradd">&#xe627;</i>
								<h5>新增</h5>
							</a>
						</div> -->
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
									<th>操作</th>
								</tr>
							</thead>
							<tbody id="customer_list">

							</tbody>
						</table>
						<!--用户表格end-->
					</div>

					<!--分页效果开始-->
					<div class=" row fr">
						<div class="page_date">
							<label>数据共：</label><i class="colorred" id="customers_total">100</i><label>条</label>
						</div>
						<div class="page_date">
							<label>跳到第：</label> <input type="text" id="jumpPageNo"> <label>页</label>
							<button class="btn btn-default" id="jumpButton">确定</button>
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
				<!--采购申请单end-->

				<!--到货通知单begin-->
				<div class="intel_tabcont hide">2</div>
				<!--到货通知单end-->

				<!--退货通知单begin-->
				<div class="intel_tabcont hide">3</div>
				<!--退货通知单end-->

				<!--到货通知单begin-->
				<div class="intel_tabcont hide">4</div>
				<!--到货通知单end-->
				<!--到货通知单begin-->
				<div class="intel_tabcont hide">5</div>
				<!--到货通知单end-->
				<!--tab切换的内容end-->
			</div>
		</div>
	</div>
	<!--新增begin-->
	<div class="modal fade" id="add" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document" style="width: 750px;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<div class="alt_head">
						<h5>其他客户编辑</h5>
					</div>
				</div>
				<div class="modal-body">
					<div class="alt_edit">
						<div class="alt_edit_div">
							<label>编号：</label> <input type="text" value="121221"
								readonly="true" id="customer_code">
						</div>
						<div class="alt_edit_div">
							<label>内码：</label> <input type="text" value="121221"
								readonly="true" id="customer_innercode">
						</div>
						<div class="alt_edit_div">
							<label>名称：</label> <input type="text" id="customer_name">
						</div>
						<div class="alt_edit_div">
							<label>简称：</label> <input type="text" id="customer_info">
						</div>
						<div class="alt_edit_div">
							<label>所属组织：</label> <input type="text" id="customer_orgname">
						</div>
						<div class="alt_edit_div">
							<label>有效性：</label> <input type="checkbox" id="customer_isvalid"><span>有效</span>
						</div>
						<div class="alt_edit_textarea">
							<label>备注： </label>
							<textarea class="form-control" rows="1" id="customer_remark"></textarea>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" id="customer_add">确定</button>
					<button type="button" class="btn btn-default"
						id="customer_add_hide" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
	<!--新增end-->
	<!--编辑begin-->
	<div class="modal fade" id="edit" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document" style="width: 750px;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<div class="alt_head">
						<h5>修改客户信息</h5>
					</div>
				</div>
				<div class="modal-body">
					<div class="alt_edit">
						<div class="alt_edit_div">
							<label>编号：</label> <input type="text" value="121221"
								readonly="true" id="customer_modify_code">
						</div>
						<div class="alt_edit_div">
							<label>内码：</label> <input type="text" value="121221"
								readonly="true" id="customer_modify_innercode">
						</div>
						<div class="alt_edit_div">
							<label>名称：</label> <input type="text" id="customer_modify_name">
						</div>
						<div class="alt_edit_div">
							<label>简称：</label> <input type="text" id="customer_modify_info">
						</div>
						<div class="alt_edit_div">
							<label>所属组织：</label> <input type="text"
								id="customer_modify_orgname">
						</div>
						<div class="alt_edit_div">
							<label>有效性：</label> <input type="checkbox"
								id="customer_modify_isvalid"><span>有效</span>
						</div>
						<div class="alt_edit_textarea">
							<label>备注： </label>
							<textarea class="form-control" rows="1"
								id="customer_modify_remark"></textarea>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" id="customer_modify">确定</button>
					<button type="button" class="btn btn-default" data-dismiss="modal"
						id="customer_modify_cancel">取消</button>
				</div>
			</div>
		</div>
	</div>
	<!--编辑end-->
	<!--删除begin-->
	<div class="modal fade" id="dele" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document" style="width: 400px;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">提示</h4>
				</div>
				<div class="modal-body">
					<div class="alert_qf">
						<img src="${staticBasePath }/images/tishi.png"><label>注：删除操作不可恢复，您确定要继续么？</label>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" id="customer_delete">确定</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
		<!--删除end-->
		<!-- 引用公共footer部分 -->
		<jsp:include page="../../common/base/footer_busi.jsp"></jsp:include>
	</div>
		<script type="text/javascript"
			src="/javascript/basicFile/other/other-client.js"></script>
</body>

</html>