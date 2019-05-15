layui.use(['element', 'laydate', 'table', 'form'], function () {
    var $ = layui.jquery
        , table = layui.table
        , form = layui.form
        , laydate = layui.laydate;
    laydate.render({
        elem: "#planBeginTime",
        trigger: 'click'
    });
    laydate.render({
        elem: "#planEndTime",
        trigger: 'click'
    });
    form.on('select(planType)',function (obj) {
        var planTypeName = $("#planType").siblings("div.layui-form-select").find("dd[class='layui-this']").text();
        $("#planType").attr("planTypeName",planTypeName);
    });
    var jhEdit ={
        initPlanType:function () {
            $.get("/getDictionaryKeyType/PXFS",function (ret) {
                $("#planType").append(new Option("请选择培训方式", ""));
                $.each(ret.data, function(index, item) {
                    $("#planType").append(new Option(item.dataValue, item.dataCode));
                });
                form.render('select');
                if ($("#type").val()==='edit'){
                    var planType =$("#planTypeTemp").val()
                    var select = 'dd[lay-value=' + planType + ']';// 设置value
                    $('#planType').siblings("div.layui-form-select").find('dl').find(select).click();
                }
            });
        }
    }
    jhEdit.initPlanType();
});