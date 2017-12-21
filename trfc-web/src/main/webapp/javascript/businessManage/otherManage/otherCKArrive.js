$(function() {

	//请求路径
	var URL = {
			page: '/trfc/otherCKArrive/page',
			addUrl:'/trfc/otherCKArrive/addMain',
			findOne:"/trfc/otherCKArrive/findOne",
			update:"/trfc/otherCKArrive/updateOperation",
			forceOutFactory:"/trfc/otherCKArrive/forceOutFactory",
			updateVeiw:"/trfc/otherCKArrive/editMain",
			customerAutoCompleteSearch: "/trfc/customer/autoCompleteSearch",
			vehicleAutoCompleteSearch: "/trfc/vehicle/autoCompleteSearch",
			warehouseAutoCompleteSearch: "/trfc/warehouse/autoCompleteSearch",
	};
	//业务类型
	var STATUS = {
			'0':'未入厂',
			'1':'一次过磅',
			'2':'二次过磅',
			'3':'作废',
			'4':'发卡',
			'5':'出厂',
			'6':'入厂',
			'7':'装车',
	};
	ShowAction(1);
	initAutoComplete();

	$('#seek').click(function() {
		ShowAction(1);
	});
	//跳转按钮
	$('#jumpButton').click(function() {
		jumpPageAction();
	});
	$('#pageSize').change(function() {
		ShowAction(1);
	});
	//刷新按钮
	$('#refresh').click(function() {
		ShowAction(1);
		layer.closeAll('dialog');
	});
	
	// 表格内容每行单击出来下面的详细信息
	var tabledata = $('#tbody_list');
	tabledata.on("click",'tr', function () {
		$("#ind_tab").css("display", "block");
		loadDetailData(this);
	})
	// 表格内容每行双击出来下面的详细信息
	tabledata.on("dblclick",'tr', function () {
		$('#caigoubill').modal('show');
		var id = $(this).data('obj').id;
		initDetailVeiw(id); 
	});
	// 首页底部的tab切换菜单
	var ind_li = $('#ind_tab ul li');
	ind_li.click(function () {
		$(this).addClass('select').siblings().removeClass('select');
		var index_li = ind_li.index(this);
		$('#ind_tab .cg_tabbox > .cg_tabcont').eq(index_li).show().siblings().hide();
	});

	// 弹出信息的tab切换菜单
	var alt_li = $('#alt_tab ul li');
	alt_li.click(function () {
		$(this).addClass('select').siblings().removeClass('select');
		var index_alt = alt_li.index(this);
		$('#alt_tab .cg_tabbox > .cg_tabcont').eq(index_alt).show().siblings().hide();
	});

	//绑定新增按钮
	$('#addBtn').click(function() {
		window.location.href = URL.addUrl;
	});
	
	//详情模块 读卡功能
	$('#readCardBtn').click(function() {
		readCardAction();
	});

	//绑定列表复制,编辑按钮
	$('#copy').off('click').on('click',function(e) {
		e.stopPropagation();
		var obj = $('table.maintable tbody tr.active').data('obj');
		if(!obj) {layer.msg('需要选中一行才能操作哦！'); return;}
		window.location.href = URL.addUrl + '?id=' + obj.id;
	});
	$('#update').off('click').on('click',function(e) {
		e.stopPropagation();
		var obj = $('table.maintable tbody tr.active').data('obj');
		if(!obj) {layer.msg('需要选中一行才能操作哦！'); return;}
		if(obj.status=='3'){
			layer.msg('已作废,无法进行该操作!');
		}else if(obj.auditstatus == '1'){
			layer.msg('已审核,无法进行编辑操作!');
		}else{
		
			window.location.href = URL.updateVeiw + '?id=' + obj.id;
		}
	});
	//绑定列表审核 反审 作废按钮
	$('#audit').off('click').on('click',function(e) {
		e.stopPropagation();
		var obj = $('table.maintable tbody tr.active').data('obj');
		if(!obj) {layer.msg('需要选中一行才能操作哦！'); return;}
		if(obj.status=='3'){
			layer.msg('已作废,无法进行该操作!');
		}else if(obj.auditstatus!='1'){
			var params = {
					id:obj.id,
					auditstatus:'1'
			}
			var msg = '注：确定要进行审核吗？';
			updateAction(URL.update,params,msg);
		}else{
			layer.msg('已审核,无需重复操作!');
		}
	});
	$('#unaudit').off('click').on('click',function(e) {
		e.stopPropagation();
		var obj = $('table.maintable tbody tr.active').data('obj');
		if(!obj) {layer.msg('需要选中一行才能操作哦！'); return;}
		if(obj.status=='3'){
			layer.msg('已作废,无法进行该操作!');
		}else if(obj.auditstatus!='0'){
			var params = {
					id:obj.id,
					auditstatus:'0'
			}
			var msg = '注：确定要进行反审吗？';
			updateAction(URL.update,params,msg);
		}else{
			layer.msg('未审核,无能进行反审操作!');
		}
	});
	$('#invalid').off('click').on('click',function(e) {
		e.stopPropagation();
		var obj = $('table.maintable tbody tr.active').data('obj');
		if(!obj) {layer.msg('需要选中一行才能操作哦！'); return;}
		if(obj.status!='3'){
			var params = {
					id:obj.id,
					status:'3'
			}
			var msg = '注：确定要作废吗？';
			updateAction(URL.update,params,msg);
		}else{
			layer.msg('已作废,无需重复操作!');
		}
	});
	$('#outfactory').off('click').on('click',function(e) {
		e.stopPropagation();
		var obj = $('table.maintable tbody tr.active').data('obj');
		if(!obj) {layer.msg('需要选中一行才能操作哦！'); return;}
		if(obj.status=='2'){
			var params = {
					id:obj.id
			}
			var msg = '注：确定强制出厂吗？';
			updateAction(URL.forceOutFactory,params,msg);
		}else if(obj.status=='5'){
			layer.msg('已出厂,无法进行此操作!');
		}else{
			layer.msg('未进行二次过磅,无法进行此操作');
		}
	});

	function updateAction(url, params,msg) {
		if(params){
			var index =	layer.confirm(msg, {
				area: '600px',
				btn: ['确认','取消'] //按钮
			}, function(){
				$.ajax({
					url:url,
					data:params,
					async:true,
					cache:false,
					dataType:'json',
					type:'post',
					success:function(result){
						if(result.code == '000000'){
							ShowAction(1);
						}else{
							layer.msg(result.error, {icon: 5});
						}
						layer.close(index);
					}
				});
			});
		}
	}

	//展示读卡信息
	function readCardAction (){

		if(initCardReader()) {
			//打开读卡器
			readerOpen();
			//开打卡片获取卡号
			var cardno = openCard();
			if(cardno){
				//蜂鸣
				readerBeep();
				try{
					var obj = readObjFromCard();
					$('#detail_cardno').val(cardno);
					$('#detail_card_vehiclecode').val(obj.vehicleobj.code);
					$('#detail_card_vehicleno').val(obj.vehicleno);
					$('#detail_card_customer').val(obj.customerobj.name);
					$('#detail_card_materiel').val(obj.materielname);
					$('#detail_card_bussnesstype').val(obj.status);
					$('#detail_card_note').val(obj.notice);
					$('#detail_card_status').val(obj.packagetype);
					$('#detail_card_count').val(obj.takeamount);
				} catch (e) {
					layer.msg(e.Message);
				}
			}
			//关闭读卡器
			readerClose();
			}else{
				layer.msg('当前游览器不支持!(只兼容IE游览器)');
			}
	}
	
	function initDetailVeiw(id) {
		$.ajax({
			url:URL.findOne,
			data:{id:id},
			async:true,
			cache:false,
			dataType:'json',
			type:'post',
			success:function(result){
				if(result.code == '000000'){
					var obj = result.data;
					switch (obj.auditstatus) {
					case '0':
						$('#shImg').attr('src','/resources/images/un_sh.png');
						break;
					case '1':
						$('#shImg').attr('src','/resources/images/sh.png');
					default:

					}

					$('#detail_code').val(obj.code);
					$('#detail_customer').val(obj.customername);
					$('#detail_datasource').val(obj.datasource);
					$('#detail_materiel').val(obj.materielname);
					$('#detail_cargo').val(obj.cargo);
					$('#detail_receivedepartment').val(obj.senddepartmentname);
					$('#detail_vehicleno').val(obj.vehicleno);
					$('#detail_warehouse').val(obj.warehousename);
					$('#detail_driver').val(obj.drivername);
					$('#detail_rfid').val(obj.rfid);
					$('#detail_identityno').val(obj.driveridentityno);
					$('#detail_count').val(obj.count);
					$('#detail_creator').val(obj.creatorname);
					$('#detail_createtime').val(getNowFormatDate(true, obj.createtime));
					$('#detail_status').val(obj.forceOutFactory == '1' ? '强制出厂' : STATUS[obj.status]);
					$('#detail_remark').val(obj.remark);
				}else{
					layer.msg(result.error, {icon: 5});
				}
			}
		});
	}

	//加载详情信息
	function loadDetailData(tr){
		var obj = $(tr).data('obj');
		var vehicle_tbody = $('#tbody_vehicle').empty();
		var tr = '<tr>'
			+'<td>1</td>'
			+'<td>'+(obj.vehicleno || '')+'</td>'
			+'<td>'+(obj.count || '')+'</td>'
			+'<td>'+(obj.drivername || '')+'</td>'
			+'<td>'+(obj.driveridentityno || '')+'</td>'
			+'<td>'+(obj.creatorname || '')+'</td>'
			+'</tr>';
		vehicle_tbody.append(tr);
		var pound_tbody = $('#tbody_pound').empty();
		if(obj.poundDetail){
			var tr = '<tr>'
				+'<td>'+(obj.poundDetail.code || '')+'</td>'
				+'<td>'+(obj.poundDetail.vehicleno || '')+'</td>'
				+'<td>'+(obj.poundDetail.grossweight || '')+'</td>'
				+'<td>'+(obj.poundDetail.tareweight || '')+'</td>'
				+'<td>'+(obj.poundDetail.netweight || '')+'</td>'
				+'<td>'+(getNowFormatDate(true,obj.poundDetail.lighttime) || '')+'</td>'
				+'<td>'+(getNowFormatDate(true,obj.poundDetail.weighttime) || '')+'</td>'
				+'</tr>';
			pound_tbody.append(tr);
		}
	}

	//加载下拉框
	function initAutoComplete(){
		var cache = {};
		$("#seek_customer").autocomplete({
			source: function( request, response ) {
				var term = request.term;
				var customer = cache['customer'] || {};
				if ( term in customer ) {
					response( customer[ term ] );
					return;
				}
				$.post( URL.customerAutoCompleteSearch, request, function( data, status, xhr ) {
					customer[ term ] = data;
					response( data );
				});
			},
			response: function( event, ui ) {
				if(ui.content && ui.content.length > 0){
					ui.content.forEach(function(x,i,a){
						x.label = x.name;
						x.value = x.id;
					});
				}
			},
			select: function( event, ui ) {
				$(this).val(ui.item.name).attr('customerid', ui.item.id).attr('select',true);
				return false;
			}
		}).off('click').on('click',function(){
			$(this).autocomplete('search',' ');
		}).on('input propertychange',function(){
			$(this).removeAttr('customerid');
		}).change(function(){
			if(!$(this).attr('customerid')){
				$(this).val('');
			}
		});
		$("#seek_warehouse").autocomplete({
			source: function( request, response ) {
				var term = request.term;
				var warehouse = cache['warehouse'] || {};
				if ( term in warehouse ) {
					response( warehouse[ term ] );
					return;
				}
				$.post( URL.warehouseAutoCompleteSearch, request, function( data, status, xhr ) {
					warehouse[ term ] = data;
					response( data );
				});
			},
			response: function( event, ui ) {
				if(ui.content && ui.content.length > 0){
					ui.content.forEach(function(x,i,a){
						x.label = x.name;
						x.value = x.id;
					});
				}
			},
			select: function( event, ui ) {
				$(this).val(ui.item.name).attr('warehouseid', ui.item.id);
				return false;
			}
		}).off('click').on('click',function(){
			$(this).autocomplete('search',' ');
		}).on('input propertychange',function(){
			$(this).removeAttr('warehouseid');
		}).change(function(){
			if(!$(this).attr('warehouseid')){
				$(this).val('');
			}
		});

		$("#seek_vehicleno").autocomplete({
			source: function( request, response ) {
				var term = request.term;
				var vehicle = cache['vehicle'] || {};
				if ( term in vehicle ) {
					response( vehicle[ term ] );
					return;
				}
				$.post( URL.vehicleAutoCompleteSearch, request, function( data, status, xhr ) {
					vehicle[ term ] = data;
					response( data );
				});
			},
			response: function( event, ui ) {
				if(ui.content && ui.content.length > 0){
					ui.content.forEach(function(x,i,a){
						x.label = x.vehicleno;
						x.value = x.id;
					});
				}
			},
			select: function( event, ui ) {
				$(this).val(ui.item.vehicleno).attr('vehicleid', ui.item.id);
				return false;
			}
		}).off('click').on('click',function(){
			$(this).autocomplete('search',' ');
		}).on('input propertychange',function(){
			$(this).removeAttr('vehicleid');
		}).change(function(){
			if(!$(this).attr('vehicleid')){
				$(this).val('');
			}
		});
	}

	//将日期字符串转变为时间戳
	function str2Long(dateStr){
		if(dateStr){
			return Date.parseYMD_HMS(dateStr).getTime();
		}
		return '';
	}

//	页面跳转
	function jumpPageAction() {
		//获取总页数
		var maxpageno = $('#jumpPageNo').attr('maxpageno');
		//获取当前页
		var pageno = $('#jumpPageNo').val();
		//判断跳转值是否在符合规范
		if(!pageno || !$.isNumeric(pageno) || pageno<=0 || pageno>maxpageno){
			layer.msg('输入的数字必须在1~'+maxpageno+'之间');
		}else{
			//加载指定的列表数据
			ShowAction(pageno);
		}
	}
//	前进一页
	function pageCallback(pageNo) {
		ShowAction(pageNo+1);
	}

	//获取查询条件
	function getSeekData(){
		var customerid = $('#seek_customer').attr('customerid');
		var cargo = $('#seek_cargo').val();cargo = $.trim(cargo);
		var warehouse = $('#seek_warehouse').attr('warehouseid');
		var vehicleid = $('#seek_vehicleno').attr('vehicleid');
		var code = $('#seek_code').val();code = $.trim(code);
		var auditstatus = $('#seek_auditstatus').val();
		//获取当前页面记录数
		var pageSize = $('#pageSize').val();pageSize = $.trim(pageSize);
		var starttime = $('#seek_starttime').val();starttime = $.trim(starttime);
		var endtime = $('#seek_endtime').val();endtime = $.trim(endtime);

		return {
			customerid:customerid,
			cargo:cargo,
			warehouse:warehouse,
			vehicleid:vehicleid,
			code:code,
			auditstatus:auditstatus,
			pageSize:pageSize,
			starttime:str2Long(starttime),
			endtime:str2Long(endtime),
		};
	}


//	展示数据列表
	function ShowAction(pageNo) {
		//启动缓冲动画
		var index = layer.load(2, {
			shade: [0.3,'#fff'] //0.1透明度的白色背景
		});

		//获取查询条件
		var params = getSeekData();
		//获得当前页面标记
		params.pageNo = pageNo;
		$.post(URL.page,params,function(result) {
			if(result.code=='000000'){
				var list = result.data.list;
				var pageNo = result.data.pageNo;
				var pageSize = result.data.pageSize;
				var total = result.data.total;
				//添加数据总数
				$('#total').html(result.data.total);
				//绑定 一个maxpageno属性
				$('#jumpPageNo').attr('maxPageNo',parseInt((total+pageSize-1)/pageSize));
				//分页插件
				$("#pagination").pagination(total, {
					callback: pageCallback,
					prev_text: '上一页',
					next_text: '下一页',
					items_per_page:pageSize,
					num_display_entries:4,
					current_page:pageNo-1,
					num_edge_entries:1,
					maxentries:total,
					link_to:"javascript:void(0)"
				});
				$('#tbody_list').empty();
				if(list && list.length>0){
					showPageData(list,pageSize,pageNo);
				}else{
					layer.msg('暂无数据');
				}
				//关闭缓冲图标
				layer.close(index);
			}else{
				layer.msg(result.error,{icon:5});
				//关闭缓冲图标
				layer.close(index);
			}
		});
	}
//	展示列表
	function showPageData(list,pageSize,pageNo) {




		//加载时清空列表和跳转值
		$('#jumpPageNo').val('');
		var tbody = $('#tbody_list');
		for(var i=0;i<list.length;i++){
			var obj = list[i];
			var auditstatus = '';
			var color1 = '';
			switch(obj.auditstatus){
			case '0':
				auditstatus = '未审核';
				color1 = 'class="colorred"';
				break;
			case '1':
				auditstatus = '已审核';
			}
			var color2 = '';
			if(obj.status=='3'){
				color2 = 'class="colorred"';
			}

			var tr = '<tr>'
				+'<td>'+((pageNo-1)*pageSize+i+1)+'</td>'
				+'<td>'+(obj.code || '')+'</td>'
				+'<td '+color1+'>'+(auditstatus || '')+'</td>'
				+'<td '+color2+'>'+(STATUS[obj.status] || '')+'</td>'
				+'<td>'+(obj.customername || '')+'</td>'
				+'<td>'+(obj.datasource || '')+'</td>'
				+'<td>'+(obj.materielname || '')+'</td>'
				+'<td>'+(obj.cargo || '')+'</td>'
				+'<td>'+(obj.warehousename || '')+'</td>'
				+'<td>'+(obj.senddepartmentname || '')+'</td>'
				+'<td>'+(getNowFormatDate(true, obj.createtime) || '')+'</td>'
				+'<td>'+(obj.remark || '')+'</td>'
				+'</tr>';
			//转换为jquery对象
			tr=$(tr);
			//追加
			tbody.append(tr);
			//将数据绑定到tr上
			tr.data('obj',obj);
		}
	}

	//获取时间 param(true:返回yyyy-MM-dd hh:mm:ss fasle:返回yyyy-MM-dd)
	//time(获取指定时间的字符串) 默认返回当前时间
	function getNowFormatDate(param,time) {
		var date ;
//		判断time参数是否存在
		if(time){
			date = new Date(time);
		}else{
			return '';
		}
		var seperator1 = "-";
		var seperator2 = ":";
//		获取月份
		var month = date.getMonth() + 1;
//		获取日期
		var strDate = date.getDate();
//		月或者日 为个位数时前面加'0'
		if (month >= 1 && month <= 9) {
			month = "0" + month;
		}
		if (strDate >= 0 && strDate <= 9) {
			strDate = "0" + strDate;
		}
		var hours = date.getHours();
		var minutes = date.getMinutes();
		var seconds = date.getSeconds();
		if (hours >= 0 && hours <= 9) {
			hours = "0" + hours;
		}
		if (minutes >= 0 && minutes <= 9) {
			minutes = "0" + minutes;
		}
		if (seconds >= 0 && seconds <= 9) {
			seconds = "0" + seconds;
		}
//		判断返回结果
		if(param){
			var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
			+ " " + hours + seperator2 + minutes
			+ seperator2 + seconds;
		}else{
			var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
		}
		return currentdate;
	}





});