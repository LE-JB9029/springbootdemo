function message(content, status, flag) {
    $(".message").html(content);
    var color = status == 'success' ? "green" : status == "error" ? "red" : "orange";
    $(".message").css("color", color);
    $(".message").show();
    if (!flag) {
        $(".message").on("click", function () {
            $(this).hide();
        })
    } else {
        setTimeout(function () {
            $(".message").hide();
        }, 2000);
    }
}

function showBackground(clazz, flag) {
    if (flag) {
        if (clazz) {
            $("." + clazz).show();
        }
        $(".background").show();
    } else {
        if (clazz) {
            $("." + clazz).hide();
        }
        $(".background").hide();
    }
}