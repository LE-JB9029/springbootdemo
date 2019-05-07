function add__() {
    $(".slave1 input[name='id']").hide();
    $("#form__")[0].reset();
    showBackground("slave1", true);
}

function edit__(id) {
    if (id == undefined || id == '') {
        message("id不能为空！");
        return;
    }
    $.post(contextPath + "/view/getById_", {"id": id}, function (res) {
        if (res && res.status == 'success' && res.data) {
            showBackground("slave1", true);
            $(".slave1 input[name='id']").show();
            $(".slave1 input[name='id']").val(res.data.id);
            $(".slave1 input[name='name']").val(res.data.name);
        } else {
            message("获取对象失败！", "error", true);
        }
    })
}

function delete__(id) {
    if (id == undefined || id == '') {
        message("id不能为空！");
        return;
    }
    if (confirm("确认删除[id=" + id + "]")) {
        $.post(contextPath + "/view/deleteById_", {"id": id}, function (res) {
            if (res && res.status == 'success') {
                message("删除成功！", "success", true);
                reload__(id, "delete");
            } else {
                message("删除失败！", "error", true);
            }
        });
    }
}

function save__() {
    $.post(contextPath + "/view/save_", $("#form__").serialize(), function (res) {
        if (res && res.status == 'success') {
            message("保存成功！", "success", true);
            reload__(res.data, "add");
        } else {
            message("保存失败！", "error", true);
        }
    });
    showBackground("slave1");
}

function cancel__() {
    showBackground("slave1");
}

function reload__(obj, flag) {
    var bool = true;
    $(".main1 tbody tr").each(function () {
        var b = $(this).children()[0].innerHTML == (obj.id ? obj.id : obj);
        if (b) {
            bool = false;
            if (flag == "add") {
                $(this).children()[1].innerHTML = obj.name;
            } else {
                $(this).remove();
            }
            return;
        }
    })
    if (bool) {
        var html = "<tr>\n" +
            "    <td>" + obj.id + "</td>\n" +
            "    <td>" + obj.name + "</td>\n" +
            "    <td>\n" +
            "        <a href=\"javascript:void(0);\" onclick=\"edit__(" + obj.id + ")\">修改</a>\n" +
            "        &nbsp;|&nbsp;\n" +
            "        <a href=\"javascript:void(0);\" onclick=\"delete__(" + obj.id + ")\">删除</a>\n" +
            "    </td>\n" +
            "</tr>"
        $(".main1 tbody").append(html);
    }
}