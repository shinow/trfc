<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>采购采样管理</title>
<jsp:include page="../../common/base/header_busi.jsp"></jsp:include>
</head>
<body>

	<div class="it_admin">
		<jsp:include page="../../common/base/left_busi.jsp"></jsp:include>
		<div class="right">
			<jsp:include page="../../common/base/right_head_busi.jsp"></jsp:include>
			<div class="intel_tab">
				<!--tab切换标题-->
				<ul class="intel_menu">
					<li><a href="/trfc/quality/sales/batchnum/main">销售批号维护</a></li>
					<li><a href="/trfc/quality/sales/report/main">销售化验报告</a></li>
					<li class="select">采购采样管理</li>
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
				<!--采购申请单begin-->
				<div class="intel_tabcont">
					<div class="intel_search">
						<div class="intel_bggray">
							<div class="intel_bgblue"></div>
						</div>
						<div class="intel_sconditon">
							<div class="intel_sline">
								<div class="intel_solo">
									<label>开始时间：</label> <input type="text" id="seek_starttime"
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"
										class="Wdate" style="width: 160px" />
								</div>
								<div class="intel_solo">
									<label>结束时间：</label> <input type="text" id="seek_endtime"
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"
										class="Wdate" style="width: 160px" />
								</div>
								<div class="intel_solo">
									<label>化验类型：</label> <input type="text" id="seek_assaytype" class="assaySel"> 
								</div>
								<div class="intel_solo">
									<label>单据编号：</label> <input type="text" id="seek_code">
								</div>
								<div class="intel_solo">
									<button class="btn btnblue" id="seek">搜索</button>
								</div>
								<div class="clear"></div>
							</div>
						</div>
					</div>
					<div class="intel_opera">
						<div class="intel_operasolo" id="fresh">
							<i class="iconfont colorlv">&#xe61b;</i>
							<h5>刷新</h5>
						</div>
						<div class="intel_operasolo" id="addBtn">
							<a data-toggle="modal" data-target="#add"> <i
								class="iconfont coloradd">&#xe627;</i>
								<h5>新增</h5>
							</a>
						</div>
					</div>
					<div class="intel_table">
						<!--用户表格begin-->
						<table class="table table-hover">
							<thead>
								<tr>
									<th>行号</th>
									<th>单据编号</th>
									<th>采样日期</th>
									<th>化验类型</th>
									<th>制单人</th>
									<th>制单日期</th>
									<th>备注</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody id="list">
								<tr>
									<td>1</td>
									<td>2016-02-01</td>
									<td>粉煤灰1</td>
									<td>粉煤灰1</td>
									<td>10</td>
									<td>2016-07-1216:45:21</td>
									<td>XX做</td>
									<td><span> <a data-toggle="modal"
											data-target="#edit"><i class="iconfont"
												data-toggle="tooltip" data-placement="left" title="编辑">&#xe600;</i></a>
									</span> <span> <a data-toggle="modal" data-target="#dele"><i
												class="iconfont" data-toggle="tooltip" data-placement="left"
												title="删除">&#xe63d;</i></a>
									</span></td>
								</tr>
							</tbody>
						</table>
						<!--用户表格end-->
					</div>
					<div class="intel_result">
						<div class="cg_tabbox">
							<!--tab切换的内容-->
							<div class="cg_tabcont">
								<table class="table table-bordered">
									<thead>
										<tr>
											<th>采样单号</th>
											<th>派车单号</th>
											<th>供应商</th>
											<th>物料</th>
											<th>矿点</th>
											<th>车辆</th>
											<th>备注</th>
										</tr>
									</thead>
									<tbody id="detail_list">
										<tr>
											<td>卫辉市天瑞水泥有限公司</td>
											<td>粉煤炭</td>
											<td>方案2</td>
											<td>1000</td>
											<td>豫GA1783</td>
											<td>1000</td>
											<td>豫GA1783</td>
										</tr>
									</tbody>
								</table>
							</div>
							<!--tab切换的内容end-->
						</div>
					</div>
					<!--分页效果开始-->
									<div class=" row fr">
						<div class="page_date">
							<label>数据共：</label><i class="colorred" id="total">100</i><label>条</label>
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
				<!--到货通知单end-->
				<!--tab切换的内容end-->
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
							<h5>物料方案新增</h5>
						</div>
					</div>
					<div class="modal-body">
						<div class="alt_opera mb10">
							<ul>
								<li id="add_fresh"><i class="iconfont colorlv">&#xe61b;</i>
									<h5>刷新</h5></li>
							</ul>
						</div>
						<div class="alt_edit">
							<div class="alt_edit_div">
								<label>单据编号：</label> <input type="text" id="add_code" readonly="readonly">
							</div>
							<div class="alt_edit_div">
								<label>采样日期：</label> <input type="text" id="add_samplingtime" readonly="readonly"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"
									class="Wdate_cg" style="width: 220px" />
							</div>
							<div class="alt_edit_div">
								<label>制单人：</label> <input type="text" readonly="readonly" id="add_creator">
							</div>
							<div class="alt_edit_div">
								<label>制单日期：</label> <input type="text" readonly="readonly" id="add_createtime">
							</div>

							<div class="alt_edit_div">
								<label> 化验类型：</label> <input type="text" id="add_assaytype" class="assaySel">
							</div>
							<div class="alt_edit_div" style="width: 100%">
                        <label>备注： </label>
                        <input type="text" style="width: 450px;" id="add_remark">
                        <button class="btn btn_duka" id="add_readBtn">读卡</button>
                        <em class="colorred em_duka">请读卡！</em>
                    </div>
						</div>
						<div>
							<div class="cg_tabtit">
								<ul>
									<li class="select">采样车辆</li>
								</ul>
							</div>
							<div class="cg_tabbox">
								<!--tab切换的内容-->
								<div class="cg_tabcont">
									<table class="table table-bordered">
										<thead>
											<tr>
											<th></th>
												<th>采样编号</th>
												<th>派车单号</th>
												<th>供应商</th>
												<th>物料</th>
												<th>矿口</th>
												<th>车辆</th>
												<th>备注</th>
											</tr>
										</thead>
										<tbody id="add_list">
											<tr>
												<td>1</td>
												<td>100</td>
												<td>100</td>
												<td>100</td>
												<td>100</td>
												<td>100</td>
												<td>100</td>
											</tr>
										</tbody>
									</table>
								</div>
								<!--tab切换的内容end-->
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" id="add_sure">确定</button>
						<button type="button" class="btn btn-default" data-dismiss="modal" id="add_cancel">取消</button>
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
							<h5>物料方案编辑</h5>
						</div>
					</div>
					<div class="modal-body">
						<div class="alt_edit">
						<input type = "hidden" id="edit_id">
							<div class="alt_edit_div">
								<label>单据编号：</label> <input type="text" id="edit_code" readonly="readonly">
							</div>
							<div class="alt_edit_div">
								<label>采样日期：</label> <input type="text" id="edit_samplingtime" readonly="readonly"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"
									class="Wdate_cg" style="width: 220px" />
							</div>
							<div class="alt_edit_div">
								<label>制单人：</label> <input type="text" readonly="readonly" id="edit_creator">
							</div>
							<div class="alt_edit_div">
								<label>制单日期：</label> <input type="text" readonly="readonly" id="edit_createtime">
							</div>

							<div class="alt_edit_div">
								<label> 化验类型：</label> <input type="text" id="edit_assaytype" class="assaySel">
							</div>
							<div class="alt_edit_div" style="width: 100%">
                        <label>备注： </label>
                        <input type="text" style="width: 450px;" id="edit_remark">
                        <button class="btn btn_duka" id="edit_readBtn">读卡</button>
                        <em class="colorred em_duka">请读卡！</em>
							</div>
						</div>
						<div>
							<div class="cg_tabtit">
								<ul>
									<li class="select">采样车辆</li>
								</ul>
							</div>
							<div class="cg_tabbox">
								<!--tab切换的内容-->
								<div class="cg_tabcont">
									<table class="table table-bordered">
										<thead>
											<tr>
												<th></th>
												<th>采样编号</th>
												<th>派车单号</th>
												<th>供应商</th>
												<th>物料</th>
												<th>矿口</th>
												<th>车辆</th>
												<th>备注</th>
											</tr>
										</thead>
										<tbody id="edit_list">
											<tr>
												<td>1</td>
												<td>100</td>
												<td>100</td>
												<td>100</td>
												<td>100</td>
												<td>100</td>
												<td>100</td>
											</tr>
										</tbody>
									</table>
								</div>
								<!--tab切换的内容end-->
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" id="edit_sure">确定</button>
						<button type="button" class="btn btn-default" id="edit_cancel" data-dismiss="modal">取消</button>
					</div>
				</div>
			</div>
		</div>
		<!--编辑end-->
		<!--查看详情begin-->
		<div class="modal fade" id="caigoubill" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document" style="width: 750px;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<div class="alt_head">
							<h5>物料方案详情</h5>
						</div>
					</div>
					<div class="modal-body">
						
						<div class="alt_edit">
							<div class="alt_edit_div">
								<label>单据编号：</label> <input type="text" id="detail_code" readonly="readonly">
							</div>
							<div class="alt_edit_div">
								<label>采样日期：</label> <input type="text" id="detail_samplingtime"  readonly="readonly"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"
									class="Wdate_cg" style="width: 220px" />
							</div>
							<div class="alt_edit_div">
								<label>制单人：</label> <input type="text" id="detail_creator" readonly="readonly">
							</div>
							<div class="alt_edit_div">
								<label>制单日期：</label> <input type="text" id="detail_createtime" readonly="readonly">
							</div>

							<div class="alt_edit_div">
								<label> 化验类型：</label> <input type="text" id="detail_assaytype" readonly="readonly">
							</div>
							<div class="alt_edit_div">
								<label>备注： </label> <input type="text" id="detail_remark" readonly="readonly">
							</div>
						</div>
						<div>
							<div class="cg_tabtit">
								<ul>
									<li class="select">采样车辆</li>
								</ul>
							</div>
							<div class="cg_tabbox">
								<!--tab切换的内容-->
								<div class="cg_tabcont">
									<table class="table table-bordered">
										<thead>
											<tr>
												<th></th>
												<th>采样编号</th>
												<th>派车单号</th>
												<th>供应商</th>
												<th>物料</th>
												<th>矿口</th>
												<th>车辆</th>
												<th>备注</th>
											</tr>
										</thead>
										<tbody  id = "vehicle_list">
											<tr>
												<td>1</td>
												<td>100</td>
												<td>100</td>
												<td>100</td>
												<td>100</td>
												<td>100</td>
												<td>100</td>
											</tr>
										</tbody>
									</table>
								</div>
								<!--tab切换的内容end-->
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					</div>
				</div>
			</div>
		</div>
		<!-- 引用公共footer部分 -->
		<jsp:include page="../../common/base/footer_busi.jsp"></jsp:include>
		<script type="text/javascript"
			src="/javascript/quality/purchase/cardReader.js"></script>
		<script type="text/javascript"
			src="/javascript/quality/purchase/sampling.js"></script>
	</div>
</body>
</html>
