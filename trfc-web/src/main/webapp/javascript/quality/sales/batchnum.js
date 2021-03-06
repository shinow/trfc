$(function(){



	//加载页面
	batchnumShowAction(1);
	materialSelect();
	//跳转页面
	$('#jumpButton').click(jumpPageAction);
	//$('.material_select2').select2();
	//绑定搜索按钮
	$('#seek').click(function(){batchnumShowAction(1);});
	
//	$('#add').off('click').on('click',function(e) {
//		e.stopPropagation();
//		window.location.href="/trfc/quality/sales/batchnum/addMain";
//	});
	$("#add").click(function(){
		href="/trfc/quality/sales/batchnum/addMain";
		window.location.href=href;
		 });
	//绑定删除按钮
	$('#delete').off('click').on('click',function(e) {
		e.stopPropagation();
		deleteAction();
	});
	//绑定审查按钮
	$('#audit').off('click').on('click',function(e) {
		e.stopPropagation();
		updateAudit(this,"1");
	});
	//绑定反审按钮
	$('#unaudit').off('click').on('click',function(e) {
		e.stopPropagation();
		updateAudit(this,'0');
	});

	//绑定停用按钮
	$('#stop').off('click').on('click',function(e) {
		e.stopPropagation();
		updateStopState(this);
	});
	//绑定复制按钮
	$('#copy').off('click').on('click',function(e) {
		e.stopPropagation();
		copyAction();
	});
	//监听每页记录 事件
	$('#pageSize').change(function(){batchnumShowAction(1);});
	//绑定编辑按钮
	$('#update').off('click').on('click',function(e) {
		e.stopPropagation();
		aditAction();
	});
	//双击 显示详情
	$('#list').on('dblclick','tr',function(){
		var id = $(this).data('batchnum_obj').id;
		window.location.href = URL.detailUrl+"?id="+id;
	});
	//绑定刷新按钮
	$('#fresh').click(function() {
		batchnumShowAction(1);
		layer.closeAll('dialog');
	});
	
	
	//获取用户id
	var user = $('.user').attr("userid");
	$('#seek_billsstate').change(function(){batchnumShowAction(1);});

	//--------------------------------------------------------------------------------
	//编辑
	function aditAction(){
		var obj = $('table.maintable tbody tr.active').data();
		if(!obj) {layer.msg('需要选中一行才能操作哦！'); return;}
		//判断是否已经停用
		if(obj.billsstate == 0){
			layer.msg('该数据已停用,无法进行该操作!');
			return;
		}
		//判断是否已审核
		if(obj.auditstate == "1"){
			layer.msg('该数据已审核,无法进行该操作!');
			return;
		}
		//跳转到编辑页面
		window.location.replace(URL.editUrl+"?id="+obj.id);
	}
	//复制
	function copyAction(){
		var obj = $('table.maintable tbody tr.active').data();
		if(!obj) {layer.msg('需要选中一行才能操作哦！'); return;}
		//判断是否已经停用
		if(obj.billsstate == 0){
			layer.msg('该数据已停用,无法进行该操作!');
			return;
		}
		//跳转到新增页面
		window.location.replace(URL.addUrl+"?id="+obj.id);
	}

	//停用
	function updateStopState(){
		var obj = $('table.maintable tbody tr.active').data();
		if(!obj) {layer.msg('需要选中一行才能操作哦！'); return;}
		//判断是否已经停用
		if(obj.billsstate == 0){
			layer.msg('该数据已停用,无需重复操作!');
			return;
		}
		var id = obj.id;
		var index = layer.confirm('你确定要停用吗?', {
			area: '600px', 
			btn: ['确定','取消'] //按钮
		}, function(){
			//获取id
			var param = {id:id,
					billsstate:'0',
					user:user};
			//提交数据到服务器
			$.post(URL.updateUrl,param,function(result){
				if(result.code=='000000'){
					//加载页面
					batchnumShowAction(1);
				}else{
					layer.msg(result.error,{icon:5});
				}
			});
			//关闭对话框
			layer.close(index);
		}, function(){
		});


	}


	//审核(bt:当前元素 auditstate:数据)
	function updateAudit(bt,auditstate){
		var obj = $('table.maintable tbody tr.active').data();
		if(!obj) {layer.msg('需要选中一行才能操作哦！'); return;}
		var auditState = obj.auditstate;
		var billsstate = obj.billsstate;
		if(billsstate == '0'){
			layer.msg('该数据已停用,无法执行该操作!')
			return;
		}
		
		var msg = '';
		//是否是审核
		if(auditstate == '1'){
			msg = '你确定要审核吗?';
			if(auditState == '1'){
				layer.msg('该数据已审核,无需重复操作!')
				return;
			}
		}
		//是否是反审
		if(auditstate == '0'){
			msg = '你确定要反审吗?';
			if(auditState == '0'){
				layer.msg('该数据未审核,不能进行反审操作!')
				return;
			}
		}
		var id = obj.id;
		var index = layer.confirm(msg, {
			area: '600px', 
			btn: ['确定','取消'] //按钮
		}, function(){
			//获取id
			var param = {id:id,
					auditstate:auditstate,
					user:user
			};
			//更新数据到服务器
			$.post(URL.updateUrl,param,function(result){
				if(result.code=='000000'){
					//加载页面
					batchnumShowAction(1);
				}else{
					layer.msg(result.error, {icon:5});
				}
			});
			//关闭对话框
			layer.close(index);
		}, function(){
		});

	}
	//删除action
	function deleteAction(){
		var obj = $('table.maintable tbody tr.active').data();
		if(!obj) {layer.msg('需要选中一行才能操作哦！'); return;}
		//弹出删除确认框
		var index = layer.confirm('你确定要删除吗?', {
			area: '600px', 
			btn: ['确定','取消'] //按钮
		}, function(){
			//提交删除的数据
			submitDelete(obj.id);
			//关闭对话框
			layer.close(index);
		}, function(){
		});
	}
	//提交删除信息
	function submitDelete(id){
		$.post(URL.deleteUrl,{id:id},function(result){
			if(result.code=='000000'){
				//重新加载当前页面
				batchnumShowAction(1);
			}else{
				layer.msg(result.error, {icon:5});
			}
		});
	}

	//获取下拉框数据并填充
	function materialSelect(){
		var cache={};
		$("#seek_material").autocomplete({
			//数据源
			source: function( request, response ) {
				var term = request.term;
				var material = cache['material'] || {};
				if ( term in material ) {
					response( material[ term ] );
					return;
				}
				$.post( URL.materialAutoCompleteSearch, request, function( data, status, xhr ) {
					material[ term ] = data;
					response( data );
				});
			},
			//显示下拉框
			response: function( event, ui ) {
				if(ui.content && ui.content.length > 0){
					//展示下拉框
					ui.content.forEach(function(x,i,a){
						x.label = x.name;
						x.value = x.id;
					});
				}
			},
			//选定,显示结果到输入框
			select: function( event, ui ) {
				$(this).val(ui.item.name).attr('materialid', ui.item.id);
				return false;
			}
		}).off('click').on('click',function(){
			$(this).autocomplete('search',' ');
		}).change(function(){
			$(this).val('').removeAttr('materialid');
		});
	};
	//填充数据
	function fillContent(list){
		var select = $('#seek_material').empty();
		//设置默认值
		select.append("<option></option>");
		if(list){
			for(var i=0;i<list.length;i++){
				var obj = list[i];
				var msg = obj.name;
				if(obj.spec){
					msg = obj.name+' | '+obj.spec;
				}
				var option = '<option value='+obj.id+'>'+msg+'</option>';
				//追加数据
				select.append(option);
			}
		}
	}
	//页面跳转
	function jumpPageAction(){
		//获取总页数
		var maxpageno = $('#jumpPageNo').attr('maxpageno');
		//获取当前页
		var pageno = $('#jumpPageNo').val();
		//判断跳转值是否在符合规范
		if(!pageno || !$.isNumeric(pageno) || pageno<=0 || pageno>maxpageno){
			layer.msg('输入的数字必须在1~'+maxpageno+'之间');
		}else{
			//加载指定的列表数据
			batchnumShowAction(pageno);
		}
	}
	//前进一页
	function pageCallback(pageNo){
		batchnumShowAction(pageNo+1);
	}

	//展示数据列表
	function batchnumShowAction(pageNo){
		var index = layer.load(2, {
			shade: [0.3,'#fff'] //0.1透明度的白色背景
		});
		var url = URL.pageUrl;
		//获取当前页面记录数
		var pageSize = $('#pageSize').val();
		//获取查询条件
		var starttime = $('#seek_starttime').val();
		if(starttime){
			starttime = new Date(starttime);
			starttime = starttime.getTime();
		}
		var endtime = $('#seek_endtime').val();
		if(endtime){
			endtime = new Date(endtime);
			endtime = endtime.getTime();
		}
		if(starttime && endtime){
			if(starttime>endtime){
				layer.msg('开始时间不能大于结束时间!',{icon:5});
				layer.close(index);
				return;
			}
		}
		var code = $('#seek_code').val();
		var factorycode = $('#seek_factorycode').val();
		var material = $('#seek_material').attr('materialid');
		var billsstate = $('#seek_billsstate').val();

		var params = {
				pageSize:pageSize,
				starttime:starttime,
				endtime:endtime,
				code:code,
				factorycode:factorycode,
				material:material,
				billsstate:billsstate
		};
		//获得当前页面标记
		params.pageNo = pageNo;
		$.post(url,params,function(result){
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
				if(list){
					showPageData(list,pageSize,pageNo);
				}
				layer.close(index);
			}else{
				layer.msg(result.error);
			}
		});
	}
	//展示列表
	function showPageData(list,pageSize,pageNo){
		var AUDIT = {
				0:"待审核",
				1:"已审核"
		};
		var AUDITCOL = {
				1:'',
				0:'class="colorred"'
		}
		var BILLS = {
				0:"停用",
				1:"启用"	
		};
		var TEST = {
				0:"未化验",
				1:"已化验"
		};

		//加载时清空列表和跳转值
		$('#jumpPageNo').val('');
		var tbody = $('#list').empty();
		for(var i=0;i<list.length;i++){
			var obj = list[i];

			var assaytime = getNowFormatDate(false,obj.assaytime);
			var starttime = getNowFormatDate(false,obj.starttime);
			var endtime = getNowFormatDate(false,obj.endtime);
			var audittime = null;
			if(obj.audittime){
				audittime = getNowFormatDate(true,obj.audittime);
			}
			var tr = '<tr>'
				+'<td>'+((pageNo-1)*pageSize+i+1)+'</td>'
				+'<td>'+(obj.code || '')+'</td>'
				+'<td '+AUDITCOL[obj.auditstate]+'>'+(AUDIT[obj.auditstate] || '')+'</td>'
				+'<td>'+(BILLS[obj.billsstate] || '')+'</td>'
				+'<td>'+(TEST[obj.teststate] || '')+'</td>'
				+'<td>'+(obj.material || '')+'</td>'
				+'<td>'+(obj.factorycode || '')+'</td>'
				+'<td>'+(obj.count || '')+'</td>'
				+'<td>'+(obj.weighed || '')+'</td>'
				+'<td>'+(obj.margin || '')+'</td>'
				+'<td>'+(assaytime || '')+'</td>'
				+'<td>'+(obj.assayer || '')+'</td>'
				+'<td>'+(starttime || '')+'</td>'
				+'<td>'+(endtime || '')+'</td>'
				+'<td>'+(audittime || '')+'</td>'
				+'<td>'+(obj.auditer || '')+'</td>'
				+'</tr>';
			//转换为jquery对象
			tr=$(tr).data(obj);
			//追加
			tbody.append(tr);
			//将数据绑定到tr上
			tr.data('batchnum_obj',obj);
		}
	}



});