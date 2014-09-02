/**
 * This function loads list of all news using application REST API
 */
function loadAllNews() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/news-management-orm/api/news/page?page=1&size=1",
        success: function (data) {
            $.each(data["items"], function (key, value) {
                var article = $("#article-block-template").clone();
                $(article).attr("id", function (i, val) {
                    return val + value["id"];
                });
                addIdToHref(article, ["#article-view", "#article-edit", "#article-delete"], value["id"]);
                $(article).find("#article-delete").click(function(event){
                    deleteArticle(event, this);
                });
                $(article).find("#article-title").text(value["title"]);
                changeId(article, "#article-title", value["id"]);
                $(article).find("#article-date").text(new Date(value["creationDate"]).toLocaleDateString());
                changeId(article, "#article-date", value["id"]);
                $(article).find("#article-description").text(value["shortText"]);
                changeId(article, "#article-description", value["id"]);
                $("#news-body").append(article);
            });
            paginationHandler(data["page"]);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(jqXHR.status + " Error has occurred");
        },
        dataType: "json"
    });
}

function loadNewsPage(page){
    var size = 1;
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/news-management-orm/api/news/page?page=" + page + "&size=" + size,
        success: function (data) {
            $.each(data["items"], function (key, value) {
                var article = $("#article-block-template").clone();
                $(article).attr("id", function (i, val) {
                    return val + value["id"];
                });
                addIdToHref(article, ["#article-view", "#article-edit", "#article-delete"], value["id"]);
                $(article).find("#article-delete").click(function(event){
                    deleteArticle(event, this);
                });
                $(article).find("#article-title").text(value["title"]);
                changeId(article, "#article-title", value["id"]);
                $(article).find("#article-date").text(new Date(value["creationDate"]).toLocaleDateString());
                changeId(article, "#article-date", value["id"]);
                $(article).find("#article-description").text(value["shortText"]);
                changeId(article, "#article-description", value["id"]);
                $("#news-body").append(article);
            });
            $(document).find("#current-page-badge").text(data["page"]["current"]);
            paginationHandler(data["page"]);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(jqXHR.status + " Error has occurred");
        },
        dataType: "json"
    });
}

$(document).ready(function(){
    /* News pagination arrows handlers */
    $(document).find("#previous-page").click(function(event){
        event.preventDefault();
        deleteArticlesFromPage();
        var currentPage = $(document).find("#current-page-badge").text();
        loadNewsPage(parseInt(currentPage) - 1);
    });
    $(document).find("#next-page").click(function(event){
        event.preventDefault();
        deleteArticlesFromPage();
        var currentPage = $(document).find("#current-page-badge").text();
        loadNewsPage(parseInt(currentPage) + 1);
    });
    /* Comment pagination arrows handlers */
    $(document).find("#previous-comment-page").click(function(event){
        event.preventDefault();
        deleteCommentsFromPage();
        var currentPage = $(document).find("#current-comment-page-badge").text();
        loadCommentPage(parseInt(currentPage) - 1);
    });
    $(document).find("#next-comment-page").click(function(event){
        event.preventDefault();
        deleteCommentsFromPage();
        var currentPage = $(document).find("#current-comment-page-badge").text();
        loadCommentPage(parseInt(currentPage) + 1);
    });
    /* Tags pagination arrows handlers */
    $(document).find("#previous-tag-page").click(function(event){
        event.preventDefault();
        deleteTagsFromMenu();
        var currentPage = $(document).find("#current-tag-page-badge").text();
        loadMenuTagPage(parseInt(currentPage) - 1);
    });
    $(document).find("#next-tag-page").click(function(event){
        event.preventDefault();
        deleteTagsFromMenu();
        var currentPage = $(document).find("#current-tag-page-badge").text();
        loadMenuTagPage(parseInt(currentPage) + 1);
    });
});

function deleteArticlesFromPage(){
    var template = $(document).find("#article-block-template").clone();
    var articles = $(document).find(".articles").remove();
    $(document).find("#news-body").append(template);
}

function deleteCommentsFromPage(){
    var template = $(document).find("#comment").clone();
    var articles = $(document).find(".single-comment").remove();
    $(document).find("#comments-block").append(template);
}

function deleteTagsFromMenu(){
    var tags = $(document).find("span[class='list-group-item']");
    $.each(tags, function(key, value){
        $(document).find(value).remove();
    });
}

function paginationHandler(page){
    if (page["current"] == 1){
        $(document).find("#previous-page").hide();
    } else {
        $(document).find("#previous-page").show();
    }
    if (page["current"] == page["total"]) {
        $(document).find("#next-page").hide();
    } else {
        $(document).find("#next-page").show();
    }
}

function commentPaginationHandler(page){
    if (page["current"] == 1){
        $(document).find("#previous-comment-page").hide();
    } else {
        $(document).find("#previous-comment-page").show();
    }
    if (page["current"] == page["total"]) {
        $(document).find("#next-comment-page").hide();
    } else {
        $(document).find("#next-comment-page").show();
    }
}

/**
 * This function loads single news for view from API
 */
function loadNewsForView() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/news-management-orm/api/news/" + getIDFromCurrentPageUrl(),
        success: function (data) {
            $("#article-title").text(data["title"]);
            $("#article-date").text(new Date(data["creationDate"]).toLocaleDateString());
            $("#article-description").text(data["shortText"]);
            $("#article-text").text(data["fullText"]);
            addIdToHref($("#sidebar"), ["#article-edit", "#article-delete"], data["id"]);
            $.each(data["tags"], function(key, value){
                var tagSpan = $("#tags-span").clone();
                tagSpan.text(value["name"]);
                tagSpan.attr("id", tagSpan.attr("id") + value["id"]);
                tagSpan.removeAttr("hidden");
                var tagsBlock = $("#tags-block");
                tagsBlock.append(tagSpan);
                if (key != data["tags"].length - 1){
                    var span = $('<span />').html(', ');
                    tagsBlock.append(span);
                }
            });
            $(document).find("#article-delete").click(function(event){
                deleteArticle(event, this);
            });
            loadComments();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            $("#article-title").text(jqXHR.status + " - " + errorThrown);
        },
        dataType: "json"
    });
}

$(document).ready(function(){
    $(document).find("#add-comment-btn").click(function(){
        var comment = new Object();
        comment.text = $(document).find("#comment-message").val();
        addComment(comment);
        $(document).find("#comment-message").val('');
        deleteCommentsFromPage();
        loadCommentPage(getTotalCommentPageCount());
        //var total = getTotalCommentPageCount();
        //var current = parseInt($(document).find("#current-comment-page-badge").text());
        //alert("total: " + total + "current: " + current);
    });
});

function loadComments() {
    $(document).ready(function () {
        loadCommentPage(1);
    });
}

function loadCommentPage(page){
    var size = 5;
    $.ajax({
        type:"GET",
        url: "http://localhost:8080/news-management-orm/api/comment/page?page=" + page
            + "&size=" + size + "&newsId=" + getIDFromCurrentPageUrl(),
        success: function(data){
            $.each(data["items"], function(key, value){
                var block = $(document).find("#comment").clone();
                $(block).find(".creation-date").text(new Date(value["creationDate"]).toLocaleDateString());
                $(block).find(".comment-text").text(value["text"]);
                //$(block).find(".author").text(value[""])
                $(block).attr("id", $(block).attr("id") + value["id"]);
                $(document).find("#comments-block").append(block);
            });
            $(document).find("#total-pages-count").val(data["page"]["total"]);
            $(document).find("#current-comment-page-badge").text(data["page"]["current"]);
            commentPaginationHandler(data["page"]);
        },
        dataType: "json"
    });
}

function addComment(comment){
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/news-management-orm/api/comment/" + getIDFromCurrentPageUrl(),
        contentType : 'application/json; charset=utf-8',
        data: JSON.stringify(comment)
    });
}

function getTotalCommentPageCount(){
    var size = 5;
    var response = "";
    $.ajax({
        type: "GET",
        async: false,
        url: "http://localhost:8080//news-management-orm/api/comment/page/count?size=" + size +
                    "&newsId=" + getIDFromCurrentPageUrl(),
        success: function (data){
           response = data;
        },
        dataType: "json"
    });
    return response;
}

/**
 * Loads single news from API and fills page.
 */
function loadNewsForEdit() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/news-management-orm/api/news/" + getIDFromCurrentPageUrl(),
        success: function(data){
            $("#inputTitle").attr("value", data["title"]);
            $("#inputShort").text(data["shortText"]);
            $("#inputLong").text(data["fullText"]);
            addIdToHref($("#sidebar"), ["#article-delete"], data["id"]);
            $(document).find("#article-delete").click(function(event){
                deleteArticle(event, this);
            });
            $.each(data["tags"], function(key, value){
                var val = $(document).find("#listTags").val();
                if (key == 0) {
                    $(document).find("#listTags").val(value["name"]);
                } else if (data["tags"].length != (key - 1)){
                    $(document).find("#listTags").val(val + ", " + value["name"]);
                }
            });
            editingArticle();
            warningsBehavior(data);
        },
        error: function(jqXHR, textStatus, errorThrown) {
            $("#edit-form").css("display", "none");
            $("#article-block").append("<div class='col-md-9'><h3 id='article-title'></h3></div>");
            $("#article-title").text(jqXHR.status + " - " + errorThrown);
        },
        dataType: "json"
    });
}

/**
 * Adds custom value to tag id
 *
 * @param article Html element
 * @param id {string} - tag id
 * @param value {string} - appending value
 */
function changeId(article, id, value) {
    $(article).find(id).attr("id", function (i, val) {
        return val + value;
    });
}

/**
 * This function searches number in current pathname
 *
 * @returns {number} - id at the pathname end
 */
function getIDFromCurrentPageUrl() {
    var re = /[0-9]+/;
    return re.exec(window.location.pathname)[0];
}

/**
 * Adds article id to different hrefs
 *
 * @param article Html element
 * @param items Array of tag id's
 * @param id {number} - article id
 */
function addIdToHref(article, items, id) {
    items.forEach(function (item) {
        $(article).find(item).attr("href", function (i, val) {
            return val + "/" + id;
        });
    });
}

function deleteArticle(event, element){
    var id = element.href.substring(49, element.href.length);
    event.preventDefault();
    if(confirm($(document).find("#deleteDialog").text())){
        $.ajax({
            type: "DELETE",
            url: "http://localhost:8080/news-management-orm/api/news/" + id
        }).done(function(){
            window.location.replace("http://localhost:8080/news-management-orm");
        });
    }
}

function addingArticle(){
    $(document).find("#createBtn").click(function(event){
        event.preventDefault();
        var article = new Object();
        article.title = $(document).find("#inputTitle").val();
        article.description = $(document).find("#inputShort").val();
        article.text = $(document).find("#inputLong").val();
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/news-management-orm/api/add",
            contentType : 'application/json; charset=utf-8',
            data: JSON.stringify(article)
        }).done(function(data){
            if(data["status"] === false) {
                setErrors(data);
            } else {
                window.location.href = "/news-management-orm";
            }
        });
    });
}

function editingArticle(){
    $(document).find("#saveBtn").click(function(event){
        event.preventDefault();
        var article = new Object();
        article.id = getIDFromCurrentPageUrl();
        article.title = $(document).find("#inputTitle").val();
        article.description = $(document).find("#inputShort").val();
        article.text = $(document).find("#inputLong").val();
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/news-management-orm/api/update",
            contentType : 'application/json; charset=utf-8',
            data: JSON.stringify(article)
        }).done(function(data){
            if(data["status"] === false) {
                setErrors(data);
            } else {
                window.location.href = "/news-management-orm";
            }
        });
    });
}

function setErrors(data){
    $.each(data["result"], function(k,v){
        if(k === "title.empty"){
            $(document).find("#title-danger").text(v);
        } else if (k === "description.empty"){
            $(document).find("#description-danger").text(v);
        } else if (k === "text.empty"){
            $(document).find("#text-danger").text(v);
        }
    });
}

function warningsBehavior(data){
    $(document).find("#backAction").click(function(event){
        if($(document).find("#inputTitle").val() != data["title"]
           || $(document).find("#inputShort").val() != data["description"]
            || $(document).find("#inputLong").val() != data["text"]){
            if(confirm($(document).find("#warningDialog").text())){
                window.history.back();
            }
        } else {
            window.history.back();
        }
    });
    $(document).find("#toNewsList").click(function(event){
        if($(document).find("#inputTitle").val() != data["title"]
            || $(document).find("#inputShort").val() != data["description"]
            || $(document).find("#inputLong").val() != data["text"]){
            if(!confirm($(document).find("#warningDialog").text())){
                event.preventDefault();
            }
        }
    });
}

$(document).ready(function(){
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/news-management-orm/api/news/count",
        success: function (data){
            $(document).find("#news-count").text(data);
        }
    });
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/news-management-orm/api/tag/count",
        success: function (data){
            $(document).find("#tags-count").text(data);
        }
    });
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/news-management-orm/api/comment/count",
        success: function (data){
            $(document).find("#comments-count").text(data);
        }
    });
});

function initMenuTags(){
    $(document).ready(function(){
        $(document).find("#add-tag-btn").click(function(){
            var newTag = $(document).find(".newItem").val();
            if (newTag.length > 0) {
                var tag = new Object();
                tag.name = newTag;
                tag.id = createTag(tag);
                addTagToNews(tag);
                deleteTagsFromMenu();
                loadMenuTagPage(1);
            } else {
                alert("Please, enter name of tag.")
            }
        });
    });
    $(document).ready(function(){
        loadMenuTagPage(1);
    });
}

function loadMenuTagPage(page){
    var size = 5;
    var pagination = $(document).find();
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/news-management-orm/api/tag/page?page=" + page + "&size=" + size,
        success: function(data){
            $.each(data["items"], function(key, tag){
                var tagItem = $(document).find("#menu-tag-template").clone();
                $(tagItem).find("a").text(tag["name"]);
                $(tagItem).find("a").attr("tagId", tag["id"]);
                $(tagItem).removeAttr("id");
                $(tagItem).removeAttr("hidden");
                $(tagItem).attr("class", "list-group-item");
                $(document).find("#menu-tags-block").prepend(tagItem);
            });
            $(document).find("#current-tag-page-badge").text(data["page"]["current"]);
            tagsPaginationHandler(data["page"]);
            setPreventDefaultToTags();
        },
        dataType: "json"
    });

}

function setPreventDefaultToTags(){
    var tags = $(document).find("a[class='tagItem']");
    $.each(tags, function(key, tag){
        $(tag).click(function(event){
            //alert($(this).text());
            event.preventDefault();
            var newTag = new Object();
            newTag.id = $(this).attr("tagId");
            newTag.name = $(this).text();
            addTagToNews(newTag);
        });
    });
}

function tagsPaginationHandler(page){
    if (page["current"] == 1){
        $(document).find("#previous-tag-page").hide();
    } else {
        $(document).find("#previous-tag-page").show();
    }
    if (page["current"] == page["total"]) {
        $(document).find("#next-tag-page").hide();
    } else {
        $(document).find("#next-tag-page").show();
    }
}

function addTagToNews(tag){
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/news-management-orm/api/news/" + getIDFromCurrentPageUrl() +
            "/tag",
        contentType : 'application/json; charset=utf-8',
        data: JSON.stringify(tag)
    }).done(function(){
        loadNewsForEdit();
    });
}

function initShowComments(){
    $(document).find("#comments-block").hide();
    $(document).find("#comment-pagination").hide();
    loadCommentPage(1);
    initShowCommentsEventHandler();
}

function initShowCommentsEventHandler() {
    $(document).ready(function(){
        $(document).find("#show-comments").click(function(event){
            event.preventDefault();
            if($("#comment-pagination").is(':visible')){
                $(document).find("#comments-block").hide();
                $(document).find("#comment-pagination").hide();
            } else {
                $(document).find("#comments-block").show();
                $(document).find("#comment-pagination").show();
            }
        });
    });
}

function createTag(tag){
    var result = "";
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/news-management-orm/api/tag",
        async: false,
        contentType : 'application/json; charset=utf-8',
        data: JSON.stringify(tag),
        success: function (data){
            result = data["id"];
        },
        dataType: "json"
    });
    return result;
}