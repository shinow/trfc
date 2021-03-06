(function($, win){
	//请求路径
	var URL = {
			pageUrl:"/trfc/PushSingleAction/page"
	};
	init();
	function init(){
		//初始化页面按钮绑定事件
		bindEvent();
		//初始化页面
		queryData(1);
	}
	function bindEvent(){
		$('#refreshBtn').off('click').on('click',function(){
			queryData(1);
			$('#dataMore').hide();
		});
		$('#searchBtn').off('click').on('click',function(){
			queryData(1);
		});
		$('#clean').off('click').on('click',function(){
			clean();
		});
		$('#jumpPageNoBtn').off('click').on('click',function(){
			var pageNo = $('input#jumpPageNo').val();pageNo = $.trim(pageNo);pageNo = parseInt(pageNo);
			var pageMaxNo = $('input#jumpPageNo').attr('maxpageno');pageMaxNo = $.trim(pageMaxNo);pageMaxNo = parseInt(pageMaxNo);
			if(!pageNo || !$.isNumeric(pageNo) || pageNo < 0 || pageNo > pageMaxNo){
				layer.msg('此处必须为1-'+pageMaxNo+'的数字');
				$('input#jumpPageNo').val('');
			}else{
				$('input#jumpPageNo').val(pageNo);
				queryData(pageNo);
			}
		});
		$('#pageSize').change(function(){
			queryData(1);
		});
	}
	
	function str2Long(dateStr){
		if(dateStr){
			return Date.parseYMD_HMS(dateStr).getTime();
		}
		return '';
	}
	
	function getParams(){
		var params = {};
		var requisitionNum = $('#requisitionNum').val();requisitionNum = $.trim(requisitionNum);
		var noticeNum = $('#noticeNum').val();noticeNum = $.trim(noticeNum);
		var pushStatus = $('#pushStatus').val();pushStatus = $.trim(pushStatus);
		var starttime = $('#starttime').val();starttime = $.trim(starttime);
		var endtime = $('#endtime').val();endtime = $.trim(endtime);
		var desc2 =$("#desc2").val();desc2=$.trim(desc2);
		var pageSize = $('#pageSize').val() || 10;pageSize = $.trim(pageSize);
		return {
			requisitionNum:requisitionNum,
			noticeNum:noticeNum,
			pushStatus:pushStatus,
			starttime:str2Long(starttime),
			endtime:str2Long(endtime),
			desc2:desc2,
			pageSize:pageSize
		};
	}
	
	function clean(){
		 $('#requisitionNum').val("");
		 $('#noticeNum').val("");
		 $('#pushStatus').val("");
		 $('#starttime').val("");
		 $('#endtime').val("");
		 $('#desc2').val("");
//		 queryData(1);
	}
	function queryData(pageNo){
		var index = layer.load(2, {
		  shade: [0.3,'#fff'] //0.1透明度的白色背景
		});
		var params = getParams();
		params.pageNo = pageNo;
		$.ajax({
			url:URL.pageUrl,
			data:params,
			async:true,
			cache:false,
			dataType:'json',
			type:'post',
			success:function(result){
				if(result.code == '000000'){
					renderHtml(result.data);
					var total = result.data.total;
					var pageNo = result.data.pageNo;
					var pageSize = result.data.pageSize;
					$('#total').html(total);
					$('#jumpPageNo').attr('maxPageNo',parseInt((total+pageSize-1)/pageSize));
					$("#pagination").pagination(total, {
					    callback: function(pageNo){
							queryData(pageNo+1);
						},
					    prev_text: '上一页',
					    next_text: '下一页',
					    items_per_page:pageSize,
					    num_display_entries:4,
					    current_page:pageNo-1,
					    num_edge_entries:1,
					    maxentries:total,
					    link_to:"javascript:void(0)"
					});
				}else{
					layer.msg(result.error);
				}
				layer.close(index);
			}
		});
	}
	
	function renderHtml(data){
		$('#dataBody').empty();
		var list = data.list;
		if(list && list.length>0){
			for(var i=0;i<list.length;i++){
				var obj = list[i] || '';
				var requisitionNum = obj.requisitionNum || '';
				var noticeNum = obj.noticeNum || '';
				var desc1 = obj.desc1 || '';
				var reasonFailure = obj.reasonFailure || '';
				var lightCarTime = obj.lightCarTimeStr || '';
				var heavyCarTime = obj.heavyCarTimeStr || '';
				var netWeight = obj.netWeight || '';
				var creator = obj.creator || '';
				var createtime = obj.createtimeStr || '';
				var modifier = obj.modifier || '';
				var modifytime = obj.modifytimeStr || '';
				//设置字体颜色 (LXY)
				var pushStatus = obj.pushStatus || '';
				switch (pushStatus) {
				case '1':
					pushStatus = '推单中';
					break;
				case '2':
					pushStatus = '推单成功';
					break;
				case '3':
					pushStatus = '推单失败';
					break;
				default:
					pushStatus = '';
					break;
				}
				var requisitionType = obj.requisitionType || '';
				switch (requisitionType) {
				case '1':
					requisitionType = '入库单';
					break;
				case '2':
					requisitionType = '申请单';
					break;
				case '3':
					requisitionType = '提货单';
					break;
				case '4':
					requisitionType = '通知单';
					break;
				default:
					requisitionType = '';
					break;
				}
				
				var desc2 = obj.desc2 || '';
				switch (desc2) {
				case '0':
					desc2 = '采购类型';
					break;
				case '1':
					desc2 = '销售类型';
					break;
				default:
					desc2 = '';
					break;
				}
				$('<tr>').append('<td>'+(i+1)+'</td>')
						.append('<td>'+requisitionNum+'</td>')
						.append('<td>'+pushStatus+'</td>')
						.append('<td>'+requisitionType+'</td>')
						.append('<td>'+desc2+'</td>')
						.append('<td '+'title='+reasonFailure+'>'+reasonFailure+'</td>')
						.append('<td>'+lightCarTime+'</td>')
						.append('<td>'+heavyCarTime+'</td>')
						.append('<td>'+netWeight+'</td>')
						.append('<td color:"red">'+createtime+'</td>')
						.data(obj)
						.appendTo('#dataBody');
			}
		}else{
			layer.msg('暂无数据');
			$('#dataMore').hide();
		}
	}
	
})(jQuery, window);