function comment2Target(targetId, type, content) {
    console.log(content);
    if (content == null || content === "" || content.length === 0) {
        console.log("回复为空");
        alert("回复为空！");
    } else {
        axios.post('/comment', {
            parentId: targetId,
            content: content,
            type: type
        }).then(function (response) {
            console.log(response);
            if (response.data.code === 200) {
                location.reload();
            } else {
                if (response.data.code === 4010) {
                    if (confirm(response.data.message)) {
                        window.open("https://github.com/login/oauth/authorize?client_id=747e745d933477ad3303&redirect_uri=http://localhost:8887/callback&scope=user&state=1");
                        window.localStorage.setItem("closable", "true");
                    }
                } else {
                    alert(response.data.message);
                }
            }
        }).catch(function (error) {
            console.log(error);
        });
    }
}

function post() {
    let questionId = $("#questionId").val();
    let commentContent = $("#commentContent").val();
    comment2Target(questionId, 1, commentContent);
}

function comment(e) {
    const commentId = e.getAttribute("data-id");
    let content = $("#input-" + commentId).val();
    comment2Target(commentId, 2, content);
}

Date.prototype.format = function (fmt) {
    const o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for (const k in o) {
        if (new RegExp("(" + k + ")").test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ?
                (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        }
    }
    return fmt;
}

function commentList(avatarUrl, content, name, date) {
    return "<li class=\"list-group-item\">\n" +
        "                                        <div class=\"media\">\n" +
        "                                            <div class=\"media-left\">\n" +
        "                                                <img class=\"media-object img-rounded\"\n" +
        "                                                     src=\"" +
        avatarUrl
        + "\"/>\n" +
        "                                            </div>\n" +
        "                                            <div class=\"media-body\" style=\"padding-top: 10px\">\n" +
        "                                                <h6 class=\"media-heading\">\n" +
        "                                                    <span>" +
        name +
        "</span>\n" +
        "                                                </h6>\n" +
        "                                                <div>" + content + "</div>\n" +
        "                                                <div class=\"menu\">\n" +
        "                                                    <span class=\"glyphicon glyphicon-thumbs-up icon\"\n" +
        "                                                          aria-hidden=\"true\"></span>\n" +
        "                                                        <span class=\"glyphicon glyphicon-comment icon\"\n" +
        "                                                              aria-hidden=\"true\"></span>\n" +
        "                                                    <span class=\"pull-right\">" +
        date.format('yyyy-MM-dd')
        + "</span>\n" +
        "                                                </div>\n" +
        "                                            </div>\n" +
        "                                        </div>\n" +
        "                                    </li>";
}

function reply(e) {
    const commentId = e.getAttribute("data-id");
    if (commentId === undefined || commentId === 0 || commentId == null) {
        return;
    }
    axios.get('/comment/' + commentId).then(function (response) {
        let reply = $("#reply-" + commentId);
        reply.empty();
        if (response.data.code === 200) {
            if (response.data.data == null || response.data.data.length === 0) {
                reply.append("<li class=\"list-group-item\">没有回复</li>");
            } else {
                for (const data of response.data.data) {
                    console.log(data);
                    reply.append(commentList(data.user.avatarUrl, data.content, data.user.name, new Date(data.gmtCreated)));
                }
            }
        }
    });
}

$(function () {
    // 一言
    function yiyan() {
        axios.get('https://v1.hitokoto.cn')
            .then(({data}) => {
                $("#hitokoto #hitokoto_content").text(data.hitokoto);
                $("#hitokoto #hitokoto_from").text("--" + data.from);
            })
            .catch(console.error);
    }

    yiyan();
    setInterval(yiyan, 300000);
    // 鼠标移除页面 更改title
    const tit = $("title").text();
    $(document).mouseleave(function () {
        $("title").text("v(＃°Д°)");
    })
    $(document).mouseenter(function () {
        $("title").text(tit);
    })
});