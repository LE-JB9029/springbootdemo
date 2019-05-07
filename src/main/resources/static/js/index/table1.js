function add_() {
    $(".slave input[name='id']").hide();
    $("#form_")[0].reset();
    showBackground("slave", true);
}

function edit_(id) {
    if (id == undefined || id == '') {
        message("id不能为空！");
        return;
    }
    $.post(contextPath + "/view/getById", {"id": id}, function (res) {
        if (res && res.status == 'success' && res.data) {
            showBackground("slave", true);
            $(".slave input[name='id']").show();
            $(".slave input[name='id']").val(res.data.id);
            $(".slave input[name='code']").val(res.data.code);
            $(".slave input[name='name']").val(res.data.name);
        } else {
            message("获取对象失败！", "error", true);
        }
    })
}

function delete_(id) {
    if (id == undefined || id == '') {
        message("id不能为空！");
        return;
    }
    if (confirm("确认删除[id=" + id + "]")) {
        $.post(contextPath + "/view/deleteById", {"id": id}, function (res) {
            if (res && res.status == 'success') {
                message("删除成功！", "success", true);
                reload_(id, "delete");
            } else {
                message("删除失败！", "error", true);
            }
        });
    }
}

function save_() {
    $.post(contextPath + "/view/save", $("#form_").serialize(), function (res) {
        if (res && res.status == 'success') {
            message("保存成功！", "success", true);
            reload_(res.data, "add");
        } else {
            message("保存失败！", "error", true);
        }
    });
    showBackground("slave");
}

function cancel_() {
    showBackground("slave");
}

function reload_(obj, flag) {
    var bool = true;
    $(".main tbody tr").each(function () {
        var b = $(this).children()[0].innerHTML == (obj.id ? obj.id : obj);
        if (b) {
            bool = false;
            if (flag == "add") {
                $(this).children()[1].innerHTML = obj.code;
                $(this).children()[2].innerHTML = obj.name;
            } else {
                $(this).remove();
            }
            return;
        }
    })
    if (bool) {
        var html = "<tr>\n" +
            "    <td>" + obj.id + "</td>\n" +
            "    <td>" + obj.code + "</td>\n" +
            "    <td>" + obj.name + "</td>\n" +
            "    <td>\n" +
            "        <a href=\"javascript:void(0);\" onclick=\"edit_(" + obj.id + ")\">修改</a>\n" +
            "        &nbsp;|&nbsp;\n" +
            "        <a href=\"javascript:void(0);\" onclick=\"delete_(" + obj.id + ")\">删除</a>\n" +
            "    </td>\n" +
            "</tr>"
        $(".main tbody").append(html);
    }
}