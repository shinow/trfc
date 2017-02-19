$(function(){

	//加载物料和用户下拉框
	materialSelect();
	userSelect();
	//获取url地址
	var reg = new RegExp("id=");
	var href = window.location.href;
	//判断是 新增还是复制
	if(reg.test(href)){
		//加载复制的数据
		loadOld();
	}else{
		//加载select2
		$('.material_select2').select2();
		//加载新增数据
		loadNew();
	}

	//监听下拉框
	$('#material tr select').change(fillData);
	//保存按钮绑定时间
	$('#save').click(saveAction);
	//刷新按钮绑定事件
	$('#fresh').click(function(){window.location.reload();});
	
	//----------------------------------------------------------------

	//添加一列
	function addTR(index){
		
		var tbody = $('#material');
		var trs = tbody.find('tr');
		//只有最后一行的改变事件 才能触发添加
		if(trs.length>=index){
			return;
		}
		var tr = '<tr>'
			+'<td>'+index+'</td>'
			+'<td width="200px"><select type="text" class="material_select2"'
			+'style="border: none; width: 100%; height: 100%">'
			+'</select></td>'
			+'<td><input type="text"'
			+'	style="border: none; width: 100%;" readonly="true"></td>'
			+'	<td><input readonly="true" type="text"'
			+'		style="border: none; width: 100%;"></td>'
			+'		<td><input type="text" readonly="true"'
			+'			onfocus="WdatePicker({dateFmt:\'yyyy-MM-dd\'})" class="Wdate"'
			+'				style="border: none; width: 100%;"></td>'
			+'				<td><input type="text" readonly="true"'
			+'					onfocus="WdatePicker({dateFmt:\'yyyy-MM-dd\'})" class="Wdate"'
			+'						style="border: none; width: 100%;"></td>'
			+'						<td><input readonly="true" type="text"'
			+'							style="border: none; width: 100%;"></td>'
			+'							</tr>';
		//将tr追加到tbody中
		tbody.append(tr);	
		//重新加载下拉框并绑定事件
		materialSelect();
		//加载select样式等
		$('.material_select2').select2();
		$('#material tr select').change(fillData);
	}
	//检测
	function checkFC(factorycode){

		var reg = new RegExp('\\D');
		var bl = false;
		//判断批号不能为空
		if(!factorycode){
			alert('批号不能为空');
			return bl;
			//设置批号的规范
		}else if(factorycode.length<5 || factorycode.length>32 || reg.test(factorycode.substr(-5))){
			alert('批号必须是5~32位且后5位必须是数字')
			return bl;
		}else{
			//判断批号是否重复
			var param = {factorycode:factorycode};
			$.ajax({url:URL.checkUrl,
				data:param,
				async:false,
				cache:false,
				dataType:'json',
				type:'post',
				success:function(result){
					if(result.code=='000000'){
						//true为不重复
						if(result.data){
							bl=true;
						}else{
							alert("批号已存在!");
						}
					}else{
						layer.msg(result.error,{icon:5});
					}
				}});
			return bl;
		}
	}




//加载需要复制的数据
	function loadOld(){
		//获取id
		var strs = href.split('id=');
		var materialId = strs[1];
		//通过id从服务器获取数据
		$.post(URL.copyUrl,{id:materialId},function(result){
			if(result.code=='000000'){
				var obj = result.data;

				//制单日期
				$('#add_createtime').val(getNowFormatDate(true,obj.createtime));
				//制单人
				$('#add_creator').val(obj.creator);
				$('#add_assaytime').val(getNowFormatDate(false,obj.assaytime));
				$('#add_starttime').val(getNowFormatDate(false,obj.starttime));
				$('#user_select').val(obj.assayer);
				$('#add_endtime').val(getNowFormatDate(false,obj.endtime));
				$('#add_assayorg').val(obj.assayorg);
				//设置ajax执行完成后,执行
				$.when(post1,post2).done(function(){
					$('#material tr:first select').val(obj.material);
					$('#user_select').val(obj.assayer);
					$('.material_select2').select2();
				});
				//获取第一行的td元素 并根据下标赋值
				var tds = $('#material tr:first td');
				tds.eq(2).find('input').val(obj.factorycode);
				tds.eq(3).find('input').val(obj.count);
				tds.eq(4).find('input').val(getNowFormatDate(false,obj.producedtime));
				tds.eq(5).find('input').val(getNowFormatDate(false,obj.testtime));
				tds.eq(6).find('input').val(obj.remark);
				//添加一行新的表格
				addTR(2);

			}else{
				layer.msg(result.error, {icon:5});
			}
		});


	}
	//加载新增的数据
	function loadNew(){
		//制单日期
		$('#add_createtime').val(getNowFormatDate(true));
		//制单人
		var user = $('.user label').html();
		//获取当前日期字符串
		var nowDate = getNowFormatDate(false);
		$('#add_creator').val(user);
		$('#add_assaytime').val(nowDate);
		$('#add_starttime').val(nowDate);
		//获取当月最后一天,并复制
		$('#add_endtime').val(getLastMonthDay(nowDate));
		$('#add_assayorg').val("卫辉市天瑞水泥有限公司");

	}
	//提交保存数据
	function saveAction(){
		var param = getData();
		//判断 param有没有值,无值则不处理
		if(param){
			if(confirm('确定要保存吗?')){
				//数据存到服务器
				$.post(URL.saveUrl,param,function(result){
					if(result.code=='000000'){
						//跳转到列表页面
						window.location.assign("/trfc/quality/sales/batchnum/main");
					}else{
						layer.msg(result.error,{icon:5});
					}
				});
			}
		}
	}
	//获取需保存的参数
	function getData(){
		//将 字符串--> Date类型 -->getTime()转换为Number类型
		var assaytime =new Date($('#add_assaytime').val());
		var assayer = $('#user_select').val();
		var assayorg = $('#add_assayorg').val();
		var starttime = new Date($('#add_starttime').val());
		var endtime = new Date($('#add_endtime').val());
		var createtime = new Date($('#add_createtime').val());
		//获取user的id
		var user = $('.user').attr("userid");
		var data = {
				assaytime:assaytime.getTime(),
				assayer:assayer,
				assayorg:assayorg,
				starttime:starttime.getTime(),
				endtime:endtime.getTime(),
				createtime:createtime.getTime(),
				user:user
		};
		//获取表格数据
		var trs = $('#material tr[statu="true"]');
		var arr = new Array();
		//通过循环吧数据存到arr中
		for(var i=0;i<trs.length;i++){
			//获取子元素
			var tds = trs[i].children;
			var material = $(tds[1]).find('select').val();
			//物料名称不能为空
			if(!material){
				return null;
			}
			var factorycode = $(tds[2]).find('input').val();
			//检测 批号
			if(!checkFC(factorycode)){
				return null;
			}
			var count = $(tds[3]).find('input').val();
			var producedtime =new Date($(tds[4]).find('input').val());
			var testtime = new Date($(tds[5]).find('input').val());
			var remark = $(tds[6]).find('input').val();
			var mater = {
					material:material,
					factorycode:factorycode,
					count:count,
					producedtime:producedtime.getTime(),
					testtime:testtime.getTime(),
					remark:remark
			}
			//将结果放入数组
			arr[i]=mater;
		}
		//讲数组转换为JSON字符串
		data.arrStr = JSON.stringify(arr);
		//判断是否为空,为空则返回null
		if('[]'==data.arrStr){
			alert('物料详细不能为空!');
			data = null;
		}
		return data;
	}

	//当选择下拉框时,填充数据
	function fillData(){
		//当下拉框的值为不为空时
		if($(this).val()){
			var tr =  $(this).closest('tr');
			//添加一个属性,以便获取数据
			var tds = tr.attr("statu",true).find("input");
			//设置所有的input 的readonly属性
			$("#material tr[statu='true'] input").attr('readonly',false);
			//获取当前行号,并增1
			addTR(parseInt(tr.find('td:first').html())+1);
			
			tds[1].value = 5000;
			tds[2].value = getNowFormatDate(false);
			tds[3].value = getNowFormatDate(false);
		}
	}
	//下拉框
	function materialSelect(){
		//获取物料下拉框的数据
		post1 = $.post(URL.selectorUrl,{},function(result){
			if(result.code=='000000'){
				//填充下拉框
				fillContent(result.data);
			}else{
				layer.msg(result.error,{icon:5});
			}
		});
	}
	//填充下拉框
	function fillContent(list){
		//获取物料列表的所有下拉框
		var select = $('#material tr select');
		select.append("<option></option>");
		if(list){
			for(var i=0;i<list.length;i++){
				var obj = list[i];
				var msg = obj.name;
				//判断是否有型号
				if(obj.spec){
					msg = obj.name+' | '+obj.spec;
				}
				var option = '<option value='+obj.id+'>'+msg+'</option>';
				//将option追加到select后面
				select.append(option);
			}
		}
	}
	//获取当月最后一天
	function getLastMonthDay(date){
		var year = date.substr(0,4);
		var month = date.substr(5,2);
		var  day = new Date(year,month,0);   
		//获取当月最后一天日期    
		var lastdate = year + '-' + month + '-' + day.getDate();
		return lastdate;  
	}  

});