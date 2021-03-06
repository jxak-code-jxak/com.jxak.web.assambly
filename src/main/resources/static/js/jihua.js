layui.use(['element', 'laydate', 'table', 'form'], function () {
    var $ = layui.jquery
        , table = layui.table
        , form = layui.form
        , laydate = layui.laydate;
    ; //Tab的切换功能，切换事件监听等，需要依赖element模块
    var xqJH = {
        initJH: function () {
            table.render({
                elem: '#pxJH'  //绑定table id
                , url: '/getPlanList'  //数据请求路径
                , cellMinWidth: 80
                , height: 'full-150'
                , toolbar: '#jhToolBar'
                , cols: [[
                    {checkbox: true, fixed: 'left'},
                    {field: 'id', title: '计划编码', sort: true},
                    {field: 'planBeginTime', title: '开始时间', sort: true},
                    {field: 'planEndTime', title: '结束时间', sort: true},
                    {field: 'planName', title: '计划名称', sort: true},
                    {field: 'planObject', title: '培训对象', sort: true},
                    {field: 'planTypeName', title: '培训方式', sort: true},
                    {field: 'state', title: '状态', sort: true},
                    {title: '操作', width: 178, toolbar: '#jhBar', fixed: 'right', align: "center"}
                ]]
                , page: true   //开启分页
                , limit: 10   //默认十条数据一页
                , limits: [10, 20, 50, 100]  //数据分页条
                , done: function (res) {

                }
            });
            //监听表格中的工具
            table.on('tool(jhEdit)', function (obj) {
                var data = obj.data;
                if (obj.event === 'detail') {
                    layer.msg('ID：' + data.id + ' 的查看操作');
                } else if (obj.event === 'del') {
                    var ids = [];
                    ids.push(data.id)
                    xqJH.deleteJH(ids);
                } else if (obj.event === 'edit') {
                    var editData = {title: '编辑', btn: '保存', data: data, type: obj.event};
                    xqJH.opEditWin(editData);
                }
            });
            //监听表格头部工具
            table.on('toolbar(jhEdit)', function (obj) {
                //新增数据
                if (obj.event === 'add') {
                    var editData = {title: '新增', btn: '保存', data: '', type: 'add'};
                    xqJH.opEditWin(editData);
                }
                if (obj.event === 'deleteChecked') {
                    var ids = [];
                    //获取选中行的数据
                    var data = table.checkStatus(obj.config.id).data;
                    for (var i = 0; i < data.length; i++) {
                        ids.push(data[i].id);
                    }
                    xqJH.deleteJH(ids);
                }
            })
            //年选择器
            laydate.render({
                elem: '#yearSelect'
                , type: 'year'
            });

            //年月选择器
            laydate.render({
                elem: '#monthSelect'
                , type: 'month'
            });
            //年月日选择器
            laydate.render({
                elem: '#dateSelect'
            });
        },
        initBG: function () {
            table.render({
                elem: '#pxBG'  //绑定table id
                , url: '/getChangeList'  //数据请求路径
                , cellMinWidth: 80
                , height: 'full-150'
                , toolbar: '#bgToolBar'
                , cols: [[
                    {field: 'planId',title: '计划编码',templet: '<div >{{d.planId}}<span style="display: none">{{d.opCode}}</span></div>',},
                    {field: 'changeState', title: '变更状态', templet: '#changeStateBar'},
                    {field: 'planBeginTime', title: '开始时间'},
                    {field: 'planEndTime', title: '结束时间'},
                    {field: 'planName',title: '计划名称'},
                    {field: 'planObject',title: '培训对象'},
                    {field: 'planTypeName',title: '培训方式'},
                    {field: 'remark', title: '备注'}
                ]]
                , page: true   //开启分页
                , limit: 10  //默认十条数据一页
                , limits: [10, 20, 50, 100]  //数据分页条
                , done: function (res, curr, count) {
                    xqJH.bgTabCross(res);
                }
            });
        },
        /**
         *
         * @param obj 窗体需要的数据
         */
        opEditWin: function (obj) {
            layer.open({
                type: 2, //类型，解析url
                closeBtn: 1, //关闭按钮是否显示 1显示0不显示
                title: obj.title, //页面标题
                shadeClose: true, //点击遮罩区域是否关闭页面
                shade: 0.8,  //遮罩透明度
                area: ['100%', '100%'],  //弹出层页面比例
                content: '/main/opJiHuaEdit',
                success: function (layero, index) {
                    var body = layer.getChildFrame('body', index);
                    if (obj.type === 'edit') {
                        body.contents().find("#id").val(obj.data.id)
                        body.contents().find("#planCode").val(obj.data.planCode)
                        body.contents().find("#planName").val(obj.data.planName)
                        body.contents().find("#planType").val()
                        body.contents().find("#planBeginTime").val(obj.data.planBeginTime)
                        body.contents().find("#planEndTime").val(obj.data.planEndTime)
                        body.contents().find("#planContent").val(obj.data.planContent)
                        body.contents().find("#eduPurpose").val(obj.data.eduPurpose)
                        body.contents().find("#planObject").val(obj.data.planObject)
                        body.contents().find("#planTypeTemp").val(obj.data.planType);
                        body.contents().find("#type").val(obj.type);
                    }
                    body.contents().find("#saveJH").click(function () {
                        var jh = {
                            id: body.contents().find("#id").val(),
                            planCode: body.contents().find("#planCode").val(),
                            planName: body.contents().find("#planName").val(),
                            planType: body.contents().find("#planType").val(),
                            planTypeName: body.contents().find("#planType").attr("planTypeName"),
                            planBeginTime: body.contents().find("#planBeginTime").val(),
                            planEndTime: body.contents().find("#planEndTime").val(),
                            planContent: body.contents().find("#planContent").val(),
                            eduPurpose: body.contents().find("#eduPurpose").val(),
                            planObject: body.contents().find("#planObject").val(),
                            remark: body.contents().find("#remark").val()
                        };
                        xqJH.saveJH(jh, index);
                    });
                }
            });
        },
        /**
         *
         * @param data 保存的数据
         * @param index 窗体索引
         */
        saveJH: function (data, index) {
            $.ajax({
                url: '/savePlan'
                , type: 'post'
                , data: JSON.stringify(data)
                , contentType: 'application/json;charset=UTF-8' //contentType很重要
                , dataType: 'json'
                , success: function (ret) {
                    xqJH.initJH();//刷新计划
                    xqJH.initBG();//刷新变更记录
                    layer.close(index);
                }
                , error: function (ret) {
                    console.log(JSON.stringify(ret));
                }
            });
        },
        deleteJH: function (ids) {
            if (!ids.length > 0) {
                alert("你未选择任何数据");
                return;
            }
            layer.confirm('你确定删除这条记录吗？', function (index) {
                $.ajax({
                    url: '/deletePlanByIds'
                    , type: 'post'
                    , data: JSON.stringify(ids)
                    , contentType: 'application/json;charset=UTF-8' //contentType很重要
                    , dataType: 'json'
                    , success: function (ret) {
                        xqJH.initJH();
                        layer.close(index);
                    }
                    , error: function (ret) {
                        console.log(JSON.stringify(ret));
                    }
                });
            });
        },
        /**
         * 变更记录表合并单元格
         */
        bgTabCross: function (res) {
            var data = res.data;
            var mergeIndex = 0;//定位需要添加合并属性的行数
            var mark = 1; //这里涉及到简单的运算，mark是计算每次需要合并的格子数
            var columsName = ['planId'];//需要合并的列名称
            var columsIndex = [0];//需要合并的列索引值
            for (var k = 0; k < columsName.length; k++) { //这里循环所有要合并的列
                var trArr = $("#pxBG").next().find(".layui-table-body>.layui-table").find("tr");//所有行
                for (var i = 1; i < res.data.length; i++) { //这里循环表格当前的数据
                    var tdCurArr = trArr.eq(i).find("td").eq(columsIndex[k]);//获取当前行的当前列
                    var tdPreArr = trArr.eq(mergeIndex).find("td").eq(columsIndex[k]);//获取相同列的第一列
                    var upRow = tdCurArr.find("span").text();
                    var downRow = tdPreArr.find("span").text();
                    if (upRow === downRow) { //后一行的值与前一行的值做比较，相同就需要合并
                        mark += 1;
                        tdPreArr.each(function () {//相同列的第一列增加rowspan属性
                            $(this).attr("rowspan", mark);
                        });
                        tdCurArr.each(function () {//当前行隐藏
                            $(this).css("display", "none");
                        });
                    } else {
                        mergeIndex = i;
                        mark = 1;//一旦前后两行的值不一样了，那么需要合并的格子数mark就需要重新计算
                    }
                }
                mergeIndex = 0;
                mark = 1;
            }
        },
    };
    xqJH.initJH();
    xqJH.initBG();
});