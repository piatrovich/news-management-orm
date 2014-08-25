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

function deleteArticlesFromPage(){
    var template = $(document).find("#article-block-template").clone();
    var articles = $(document).find(".articles").remove();
    $(document).find("#news-body").append(template);
}

$(document).ready(function(){
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
});

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
        },
        error: function (jqXHR, textStatus, errorThrown) {
            $("#article-title").text(jqXHR.status + " - " + errorThrown);
        },
        dataType: "json"
    });
}

/**
 * Loads single news from API and fills page.
 */
function loadNewsForEdit() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/news-management-orm/api/get/" + getIDFromCurrentPageUrl(),
        success: function(data){
            $("#inputTitle").attr("value", data["title"]);
            $("#inputShort").text(data["description"]);
            $("#inputLong").text(data["text"]);
            addIdToHref($("#sidebar"), ["#article-delete"], data["id"]);
            $(document).find("#article-delete").click(function(event){
                deleteArticle(event, this);
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
    var id = element.href.substring(45, element.href.length);
    event.preventDefault();
    if(confirm($(document).find("#deleteDialog").text())){
        $.ajax({
            type: "DELETE",
            url: "http://localhost:8080/news-management-orm/api/delete/" + id
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