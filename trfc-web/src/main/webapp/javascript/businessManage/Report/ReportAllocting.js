(function($, win){
	//请求路径
	var URL = {
			commonUrl:"/trfc/allotPound/allotPage",		//场内倒运
			commonListUrl:"/trfc/allotPound/allotList",		
			allotMaterUrl:"/trfc/allotPound/allotMaterPage",//调入堆场 
			allotMaterListUrl:"/trfc/allotPound/allotMaterList",
			allotMaterDCUrl:"/trfc/allotPound/allotMaterDCPage",//调出堆场
			allotMaterDCListUrl:"/trfc/allotPound/allotMaterDCList",
			allotMaterVeUrl:"/trfc/allotPound/allotMaterVePage",//物料车号
			allotMaterVeListUrl:"/trfc/allotPound/allotMaterVeList",
			allotMaterDrDcUrl:"/trfc/allotPound/allotMaterDrDcPage",//调入调出
			allotMaterDrDcListUrl:"/trfc/allotPound/allotMaterDrDcList",
				
	};
	init();
	function init(){

		//初始化默认查询当天的数据
			var array = getTodayStr();
			$('#clock1').val(array[0]);
			$('#clock2').val(array[1]);
		bindEvent();
		queryData();
		$(".wuliao_tabcont").hide();
		$(".hide_buyCar").show();
	}
	$('#buyCar').off('click').on('click',function(){
		$('input#jumpPageNo').val('');
		$(".wuliao_tabcont").hide();
		$(".hide_buyCar").show();
		$("#tag_display_leave").removeClass("displayNone");
		$("#tag_display_enter").removeClass("displayNone");
		queryData(1);
		
	});
	$('#thing').off('click').on('click',function(){
		$('input#jumpPageNo').val('');
		$(".wuliao_tabcont").hide();
		$(".hide_thing").show();
		$("#tag_display_leave").addClass("displayNone");
		$("#tag_display_enter").removeClass("displayNone");
		queryData2(1);

	});
	$('#receive').off('click').on('click',function(){
		$('input#jumpPageNo').val('');
		$(".wuliao_tabcont").hide();
		$(".hide_receive").show();
		$("#tag_display_leave").removeClass("displayNone");
		$("#tag_display_enter").addClass("displayNone");
		queryData3(1);
		
	});
	$('#unit').off('click').on('click',function(){
		$('input#jumpPageNo').val('');
	    $(".wuliao_tabcont").hide();
		$(".hide_unit").show();
		$("#tag_display_leave").removeClass("displayNone");
		$("#tag_display_enter").removeClass("displayNone");
		queryData4(1);
		
	});
	$('#yard').off('click').on('click',function(){
		$('input#jumpPageNo').val('');
	    $(".wuliao_tabcont").hide();
		$(".hide_yard").show();
		$("#tag_display_leave").removeClass("displayNone");
		$("#tag_display_enter").removeClass("displayNone");
		queryData5(1);	
	});
	
	$("#allExport1").off('click').on('click',function(){
		commonList();
		method('.tableExcelA');
	})
	$("#allExport2").off('click').on('click',function(){
		commonList4();
		method('.tableExcelB');
	})
	$("#allExport3").off('click').on('click',function(){
		commonList3();
		method('.tableExcelC');
	})
	$("#allExport4").off('click').on('click',function(){
		commonList2();
		method('.tableExcelD');
	})
	$("#allExport5").off('click').on('click',function(){
		commonList5();
		method('.tableExcelE');
	})
	
//	// 物料的四个tab切换菜单
	var wl_li = $('.wuliao_tab ul li');
	wl_li.click(function () {
	    $(this).addClass('select').siblings().removeClass('select');
	    var index = wl_li.index(this);
//	    $('.wuliao_tabbox > .wuliao_tabcont').eq(index).show().siblings().hide();
	});
	
	//调入调出堆场
	function commonList5(){
		$.ajax({
            url:URL.allotMaterDrDcListUrl,
            async:false,
            cache:false,
            dataType:'json',
            type:'post',
            success:function(result){
                if(result.code == '000000'){
                console.log(result.data)           	
                	$('#RMgE').empty();
        	        var list = result.data||[];
        	            for(var i=0;i<list.length;i++){
        	            	$('<tr>').append('<td>'+(list[i].materialname|"")+'</td>')
							.append('<td>'+(list[i].enteryardname||"")+'</td>')
							.append('<td>'+(list[i].leaveyardname||"")+'</td>')
							.append('<td>'+(list[i].countVehicleNo||"")+'</td>')
							.append('<td>'+(list[i].sumNetweight||"")+'</td>')												
        	                .appendTo('#RMgE');
        	            }       	
                }
            }
        });
	}
	
	//场内倒运
	function commonList(){
		$.ajax({
            url:URL.commonListUrl,
            async:false,
            cache:false,
            dataType:'json',
            type:'post',
            success:function(result){
                if(result.code == '000000'){
                console.log(result.data)           	
                	$('#RMgA').empty();
        	        var list = result.data||[];
        	            for(var i=0;i<list.length;i++){
        	            	var lightt,weightt;
        					if(list[i].lighttime){
        						lightt=new Date(list[i].lighttime).format("yyyy-MM-dd HH:mm:ss");
        					}else{
        						lightt="";
        					}
        					if(list[i].weighttime){
        						weightt=new Date(list[i].weighttime).format("yyyy-MM-dd HH:mm:ss");
        					}else{
        						weightt="";
        					}
        	            	$('<tr>').append('<td>'+(list[i].code||"")+'</td>')
							.append('<td>'+(list[i].noticecode||"")+'</td>')
							.append('<td>'+(list[i].enteryardname||"")+'</td>')
							.append('<td>'+(list[i].leaveyardname||"")+'</td>')						
							.append('<td>'+(list[i].materialname||"")+'</td>')
							.append('<td>'+(list[i].vehicleno||"")+'</td>')
							.append('<td>'+(list[i].grossweight||"")+'</td>')
							.append('<td>'+(list[i].tareweight||"")+'</td>')
							.append('<td>'+(list[i].netweight||"")+'</td>')
							.append('<td>'+(lightt)+'</td>')
							.append('<td>'+(weightt)+'</td>')
        	                .appendTo('#RMgA');
        	            }       	
                }
            }
        });
	}
	//调入堆场
	function commonList2(){
		$.ajax({
            url:URL.allotMaterListUrl,
            async:false,
            cache:false,
            dataType:'json',
            type:'post',
            success:function(result){
                if(result.code == '000000'){
                console.log(result.data)           	
                	$('#RMgD').empty();
        	        var list = result.data||[];
        	            for(var i=0;i<list.length;i++){
        	            	$('<tr>').append('<td>'+(list[i].materialname|"")+'</td>')
							.append('<td>'+(list[i].enteryardname||"")+'</td>')
							.append('<td>'+(list[i].countVehicleNo||"")+'</td>')
							.append('<td>'+(list[i].sumNetweight||"")+'</td>')												
        	                .appendTo('#RMgD');
        	            }       	
                }
            }
        });
	}
	//调出堆场
	function commonList3(){
		$.ajax({
            url:URL.allotMaterDCListUrl,
            async:false,
            cache:false,
            dataType:'json',
            type:'post',
            success:function(result){
                if(result.code == '000000'){
                console.log(result.data)           	
                	$('#RMgC').empty();
        	        var list = result.data||[];
        	            for(var i=0;i<list.length;i++){
        	            	
        	            	$('<tr>').append('<td>'+(list[i].materialname||"")+'</td>')
							.append('<td>'+(list[i].leaveyardname||"")+'</td>')
							.append('<td>'+(list[i].countVehicleNo||"")+'</td>')
							.append('<td>'+(list[i].sumNetweight||"")+'</td>')							
        	                .appendTo('#RMgC');
        	            }       	
                }
            }
        });
	}
	//物料车号
	function commonList4(){
		$.ajax({
            url:URL.allotMaterVeListUrl,
            async:false,
            cache:false,
            dataType:'json',
            type:'post',
            success:function(result){
                if(result.code == '000000'){
                console.log(result.data)           	
                	$('#RMg').empty();
        	        var list = result.data||[];
        	            for(var i=0;i<list.length;i++){      	            	
        	            	$('<tr>').append('<td>'+(list[i].materialname||"")+'</td>')
							.append('<td>'+(list[i].vehicleno||"")+'</td>')
							.append('<td>'+(list[i].countVehicleNo||"")+'</td>')
							.append('<td>'+(list[i].sumNetweight||"")+'</td>')								
        	                .appendTo('#RMgB');
        	            }       	
                }
            }
        });
	}
	
	function search(pageNo){
		var type=$(".tj_tab ul li.select").attr("data-type");
		if(type==0){
			queryData4(pageNo);
		}
		if(type==1){
			queryData3(pageNo);
		}
		if(type==2){
			queryData2(pageNo);
		}
		if(type==3){
			queryData(pageNo);
		}
		if(type==4){
			queryData5(pageNo);
		}
	}
	
	$('#searchBtn').off('click').on('click',function(){
		search(1);
		bbgClick();
		var clock1=document.getElementById("clock1").value;
		var clock2=document.getElementById("clock2").value;
		document.querySelector(".clock9").innerHTML=clock1.slice(0,10)+"至";
		document.querySelector(".clock10").innerHTML=clock2.slice(0,10);
		document.querySelector(".clock12").innerHTML=clock1.slice(0,10)+"至";
		document.querySelector(".clock13").innerHTML=clock2.slice(0,10);
		document.querySelector(".clock3").innerHTML=clock1.slice(0,10)+"至";
		document.querySelector(".clock4").innerHTML=clock2.slice(0,10);
		document.querySelector(".clock6").innerHTML=clock1.slice(0,10)+"至";
		document.querySelector(".clock7").innerHTML=clock2.slice(0,10);
		document.querySelector(".clock9").innerHTML=clock1.slice(0,10)+"至";
		document.querySelector(".clock10").innerHTML=clock2.slice(0,10);
		document.querySelector(".clock15").innerHTML=clock1.slice(0,10)+"至";
		document.querySelector(".clock16").innerHTML=clock2.slice(0,10);
		
	});
	$('#clean').off('click').on('click',function(){
		clean();
	});
	function bindEvent(){
		
		$('#jumpPageNoBtn').off('click').on('click',function(){
			var pageNo = $('input#jumpPageNo').val();pageNo = $.trim(pageNo);pageNo = parseInt(pageNo);
			var pageMaxNo = $('input#jumpPageNo').attr('maxpageno');pageMaxNo = $.trim(pageMaxNo);pageMaxNo = parseInt(pageMaxNo);
			if(!pageNo || !$.isNumeric(pageNo) || pageNo < 0 || pageNo > pageMaxNo){
				layer.msg('此处必须为1-'+pageMaxNo+'的数字');
				$('input#jumpPageNo').val('');
			}else{
				$('input#jumpPageNo').val(pageNo);
				search(pageNo);
				
			}
		});
		$('#pageSize').change(function(){
			search(1);
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
		var code = $('#guobangdanhao').val();code = $.trim(code);
		var noticecode = $('#tongzhidanhao').val();noticecode = $.trim(noticecode);
		var enteryardname = $('#diaoruduichang').val();enteryardname = $.trim(enteryardname);
		var leaveyardname= $('#diaoliduichang').val();leaveyardname = $.trim(leaveyardname);
		var status= $('#danjuzhuangtai').val();status= $.trim(status);	
		var cargo = $('#gys').val();cargo = $.trim(cargo);
		var drivername = $('#bbg_sjn').val();drivername = $.trim(drivername);
		var beginTime = $('#clock1').val();beginTime = $.trim(beginTime);
		var endTime = $('#clock2').val();endTime = $.trim(endTime);
		var vehicleno = $('#bbg_cph').val();vehicleno = $.trim(vehicleno);
		var pageSize = $('#pageSize').val() || 20;pageSize = $.trim(pageSize);
			return {
				 code: code,
				 noticecode:noticecode,
				 enteryardname:enteryardname,
				 leaveyardname:leaveyardname,
				 status: status,
				cargo:cargo,
				drivername:drivername,
				beginTime:str2Long(beginTime),
				endTime:str2Long(endTime),
				vehicleno:vehicleno,
				pageSize:pageSize
			};
		
	}
	function getTodayStr(){
		var myDate = new Date();
		var year = myDate.getFullYear();    //获取完整的年份(4位,1970-????)
		var month = myDate.getMonth()+1;       //获取当前月份(0-11,0代表1月)
		var day = myDate.getDate();        //获取当前日(1-31)
		var day1 = myDate.getDate()+1;        //获取当前日(1-31)
		var hours = myDate.getHours();       //获取当前小时数(0-23)
		var minutes = myDate.getMinutes();     //获取当前分钟数(0-59)
		var seconds = myDate.getSeconds();     //获取当前秒数(0-59)
		if(month<10){
			month = "0"+ month;
		}
		if(day<10){
			day = "0"+day;
		}
		if(day1<10){
			day1 = "0"+day1;
		}
		var array = new Array();
		array[0]=year+"-"+month+"-"+day+" "+"00:00:00";
		array[1]=year+"-"+month+"-"+day1+" "+"00:00:00";
		return array;
	}
	function clean(){
		 $('#bbg_gys').val("");	
		 $('#gys').val("");
		 $('#bbg_sjn').val("");
		 $('#clock1').val("");
		 $('#clock2').val("");
		 $('#bbg_cph').val("");
		 $('#guobangdanhao').val("");
		$('#tongzhidanhao').val("");
		$('#diaoruduichang').val("");
		$('#diaoliduichang').val("");
		$('#danjuzhuangtai').val("");
	}
//		//初始化页面
//		queryData(1);
		//	场内倒运
		function queryData(pageNo){
			var index = layer.load(2, {
			  shade: [0.3,'#fff'] //0.1透明度的白色背景
			});
			var params = getParams();
			params.pageNo = pageNo;
			$.ajax({
				url:URL.commonUrl,
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
		//过磅单号   通知单号   发货单位   收货单位     仓库  物料    车号   毛重    皮重    净重      轻车时间   重车时间  
		function renderHtml(data){
			$('#RMg1').empty();
			var list = data.list||[];
			if(list && list.length>0){
				var str1=0,str2=0,str3=0;
				for(var i=0;i<list.length;i++){
					str1+=list[i].grossweight;
					str2+=list[i].tareweight;
					str3+=list[i].netweight;
					var lightt,weightt;
					if(list[i].lighttime){
						lightt=new Date(list[i].lighttime).format("yyyy-MM-dd HH:mm:ss");
					}else{
						lightt="";
					}
					if(list[i].weighttime){
						weightt=new Date(list[i].weighttime).format("yyyy-MM-dd HH:mm:ss");
					}else{
						weightt="";
					}
					$('<tr>').append('<td>'+(list[i].code||"")+'</td>')
							.append('<td>'+(list[i].noticecode||"")+'</td>')
							.append('<td>'+(list[i].enteryardname||"")+'</td>')
							.append('<td>'+(list[i].leaveyardname||"")+'</td>')		
							.append('<td>'+(list[i].materialname||"")+'</td>')
							.append('<td>'+(list[i].vehicleno||"")+'</td>')
							.append('<td>'+(list[i].grossweight||"")+'</td>')
							.append('<td>'+(list[i].tareweight||"")+'</td>')
							.append('<td>'+(list[i].netweight||"")+'</td>')
							.append('<td>'+(lightt)+'</td>')
							.append('<td>'+(weightt)+'</td>')
							.appendTo('#RMg1');
				}
				$('<tr>').append('<td>'+("总计")+'</td>')
				.append('<td>'+("---")+'</td>')
				.append('<td>'+("---")+'</td>')
				.append('<td>'+("---")+'</td>')		
				.append('<td>'+("---")+'</td>')
				.append('<td>'+("---")+'</td>')
				.append('<td>'+(str1.toFixed(2))+'</td>')
				.append('<td>'+(str2.toFixed(2))+'</td>')
				.append('<td>'+(str3.toFixed(2))+'</td>')
				.append('<td>'+("---")+'</td>')
				.append('<td>'+("---")+'</td>')
				.appendTo('#RMg1');
			}else if(list.length<=0){
				layer.msg('暂无数据');
//				$('#dataMore').hide();
			}
		}
//		调入堆场
		function queryData2(pageNo){
			var index = layer.load(2, {
			  shade: [0.3,'#fff'] //0.1透明度的白色背景
			});
			var params = getParams();
			params.pageNo = pageNo;
			$.ajax({
				url:URL.allotMaterUrl,
				data:params,
				async:true,
				cache:false,
				dataType:'json',
				type:'post',
				success:function(result){
					if(result.code == '000000'){
						renderHtml2(result.data);
						var total = result.data.total;
						var pageNo = result.data.pageNo;
						var pageSize = result.data.pageSize;
						$('#total').html(total);
						$('#jumpPageNo').attr('maxPageNo',parseInt((total+pageSize-1)/pageSize));
						$("#pagination").pagination(total, {
						    callback: function(pageNo){
								queryData2(pageNo+1);
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
		//过磅单号   通知单号   发货单位   收货单位     仓库  物料    车号   毛重    皮重    净重      轻车时间   重车时间  
		function renderHtml2(data){
			$('#RMg4').empty();
			var list = data.list||[];
			if(list && list.length>0){
				var str1=0,str2=0;
				for(var i=0;i<list.length;i++){
					if(Number(list[i].countVehicleNo)!=NaN){
						str1+=list[i].countVehicleNo;
					}else{
						str1+="";
					}
					if(Number(list[i].sumNetweight)!=NaN){
						str2+=list[i].sumNetweight;
					}else{
						str2+="";
					}
					
					$('<tr>').append('<td>'+(list[i].materialname||"")+'</td>')
					.append('<td>'+(list[i].enteryardname||"")+'</td>')
					.append('<td>'+(list[i].countVehicleNo||"")+'</td>')
					.append('<td>'+(list[i].sumNetweight||"")+'</td>')	
							.appendTo('#RMg4');
				}
				$('<tr>').append('<td>总计</td>')
				.append('<td>'+("---")+'</td>')
				.append('<td>'+(str1.toFixed(2))+'</td>')
				.append('<td>'+(str2.toFixed(2))+'</td>')	
				.appendTo('#RMg4');
			}else if(list.length<=0){
				layer.msg('暂无数据');
//				$('#dataMore').hide();
			}
		}
//		调出堆场
		function queryData3(pageNo){
			var index = layer.load(2, {
			  shade: [0.3,'#fff'] //0.1透明度的白色背景
			});
			var params = getParams();
			params.pageNo = pageNo;
			$.ajax({
				url:URL.allotMaterDCUrl,
				data:params,
				async:true,
				cache:false,
				dataType:'json',
				type:'post',
				success:function(result){
					if(result.code == '000000'){
						renderHtml3(result.data);
						var total = result.data.total;
						var pageNo = result.data.pageNo;
						var pageSize = result.data.pageSize;
						$('#total').html(total);
						$('#jumpPageNo').attr('maxPageNo',parseInt((total+pageSize-1)/pageSize));
						$("#pagination").pagination(total, {
						    callback: function(pageNo){
								queryData3(pageNo+1);
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
		//过磅单号   通知单号   发货单位   收货单位     仓库  物料    车号   毛重    皮重    净重      轻车时间   重车时间  
		function renderHtml3(data){
			$('#RMg3').empty();
			var list = data.list||[];
			if(list && list.length>0){
				var str1=0,str2=0;
				for(var i=0;i<list.length;i++){
				
					if(Number(list[i].countVehicleNo)!=NaN){
						str1+=list[i].countVehicleNo;
					}else{
						str1+="";
					}
					if(Number(list[i].sumNetweight)!=NaN){
						str2+=list[i].sumNetweight;
					}else{
						str2+="";
					}
					$('<tr>').append('<td>'+(list[i].materialname||"")+'</td>')
					.append('<td>'+(list[i].leaveyardname||"")+'</td>')
					.append('<td>'+(list[i].countVehicleNo||"")+'</td>')
					.append('<td>'+(list[i].sumNetweight||"")+'</td>')	
							.appendTo('#RMg3');
				}
				$('<tr>').append('<td>总计</td>')
				.append('<td>'+("---")+'</td>')
				.append('<td>'+(str1.toFixed(2))+'</td>')
				.append('<td>'+(str2.toFixed(2))+'</td>')
				.appendTo('#RMg3');
			}else if(list.length<=0){
				layer.msg('暂无数据');
//				$('#dataMore').hide();
			}
		}
//		物料车号
		function queryData4(pageNo){
			var index = layer.load(2, {
			  shade: [0.3,'#fff'] //0.1透明度的白色背景
			});
			var params = getParams();
			params.pageNo = pageNo;
			$.ajax({
				url:URL.allotMaterVeUrl,
				data:params,
				async:true,
				cache:false,
				dataType:'json',
				type:'post',
				success:function(result){
					if(result.code == '000000'){
						renderHtml4(result.data);
						var total = result.data.total;
						var pageNo = result.data.pageNo;
						var pageSize = result.data.pageSize;
						$('#total').html(total);
						$('#jumpPageNo').attr('maxPageNo',parseInt((total+pageSize-1)/pageSize));
						$("#pagination").pagination(total, {
						    callback: function(pageNo){
								queryData4(pageNo+1);
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
		//过磅单号   通知单号   发货单位   收货单位     仓库  物料    车号   毛重    皮重    净重      轻车时间   重车时间  
		function renderHtml4(data){
			$('#RMg2').empty();
			var list = data.list||[];
			if(list && list.length>0){
				var str1=0,str2=0;
				for(var i=0;i<list.length;i++){
					
					if(Number(list[i].countVehicleNo)!=NaN){
						str1+=list[i].countVehicleNo;
					}else{
						str1+="";
					}
					if(Number(list[i].sumNetweight)!=NaN){
						str2+=list[i].sumNetweight;
					}else{
						str2+="";
					}
					$('<tr>').append('<td>'+(list[i].materialname||"")+'</td>')
					.append('<td>'+(list[i].vehicleno||"")+'</td>')
					.append('<td>'+(list[i].countVehicleNo||"")+'</td>')
					.append('<td>'+(list[i].sumNetweight||"")+'</td>')		
							.appendTo('#RMg2');
				}
				$('<tr>').append('<td>总计</td>')
				.append('<td>'+("---")+'</td>')
				.append('<td>'+(str1.toFixed(2))+'</td>')
				.append('<td>'+(str2.toFixed(2))+'</td>')
				.appendTo('#RMg2');
				
			}else if(list.length<=0){
				layer.msg('暂无数据');
//				$('#dataMore').hide();
			}
		}
//		调入调出堆场
		function queryData5(pageNo){
			var index = layer.load(2, {
			  shade: [0.3,'#fff'] //0.1透明度的白色背景
			});
			var params = getParams();
			params.pageNo = pageNo;
			$.ajax({
				url:URL.allotMaterDrDcUrl,
				data:params,
				async:true,
				cache:false,
				dataType:'json',
				type:'post',
				success:function(result){
					if(result.code == '000000'){
						renderHtml5(result.data);
						var total = result.data.total;
						var pageNo = result.data.pageNo;
						var pageSize = result.data.pageSize;
						$('#total').html(total);
						$('#jumpPageNo').attr('maxPageNo',parseInt((total+pageSize-1)/pageSize));
						$("#pagination").pagination(total, {
						    callback: function(pageNo){
								queryData5(pageNo+1);
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
		//过磅单号   通知单号   发货单位   收货单位     仓库  物料    车号   毛重    皮重    净重      轻车时间   重车时间  
		function renderHtml5(data){
			$('#RMg5').empty();
			var list = data.list||[];
			if(list && list.length>0){
				var str1=0,str2=0;
				for(var i=0;i<list.length;i++){
					if(Number(list[i].countVehicleNo)!=NaN){
						str1+=list[i].countVehicleNo;
					}else{
						str1+="";
					}
					if(Number(list[i].sumNetweight)!=NaN){
						str2+=list[i].sumNetweight;
					}else{
						str2+="";
					}
					
					$('<tr>').append('<td>'+(list[i].materialname||"")+'</td>')
					.append('<td>'+(list[i].enteryardname||"")+'</td>')
					.append('<td>'+(list[i].leaveyardname||"")+'</td>')
					.append('<td>'+(list[i].countVehicleNo||"")+'</td>')
					.append('<td>'+(list[i].sumNetweight||"")+'</td>')	
							.appendTo('#RMg5');
				}
				$('<tr>').append('<td>总计</td>')
				.append('<td>'+("---")+'</td>')
				.append('<td>'+("---")+'</td>')
				.append('<td>'+(str1.toFixed(2))+'</td>')
				.append('<td>'+(str2.toFixed(2))+'</td>')	
				.appendTo('#RMg5');
			}else if(list.length<=0){
				layer.msg('暂无数据');
//				$('#dataMore').hide();
			}
		}
})(jQuery, window);