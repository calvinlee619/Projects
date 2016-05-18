/**
 * Created by kangw on 10/19/15.
 */
function populateBlog(){
    $.get("/get_blogs")
        .always(function(data){
            var blogarea = $("#blogarea");
            blogarea.data('max-entry',data['max-entry']);
            blogarea.html('');
            for (var i=0;i<data.blogs.length;i++){
                blog = data.blogs[i];
                var new_blog = $(blog.html);
                new_blog.attr('blog-id',blog.id)
                blogarea.append(new_blog);
            }
    });
}

function addBlog(){
    var postarea = $("#postarea");
    var text = postarea.val().replace(/\"/g,"'");
    $.post("/post_blog",{message:text})
        .done(function(data){
            getUpdates();
            postarea.val("").focus();
        });
}


function btntoggle(){
    var blog_id = parseInt($(this).attr('toggle-id'));
    console.log("**"+blog_id);
    $("div[commentarea-id="+blog_id+"]").fadeToggle();
}


function addComment(e){
    var id = $(this).attr('btn-id');
    if (id==undefined){
        id = $(event.target).attr('input-id');
    }
    var commentarea = $("input[input-id="+id+"]");
    if (commentarea.val()==undefined || commentarea.val().length == 0) return;
    $.post("/add_comment/"+id,{message:commentarea.val()})
        .done(function(data){
            getUpdates();
            commentarea.val("").focus;
    });
}

function getUpdates(){
    var blogarea = $("#blogarea");
    var max_entry = blogarea.data("max-entry");
    $.get("/get_changes/"+max_entry)
        .always(function(data){
            console.log("update")
           blogarea.data("max-entry",data['max-entry']);
            for(var i=0;i<data.blogs.length;i++){
                var blog = data.blogs[i];
                var id = blog.id;
                var path = "div[blog-id=" + id +"]";
                var exist_blog = $(path);
                var new_blog = $(blog.html);
                if (exist_blog==undefined || exist_blog.length == 0){
                    new_blog.attr('blog-id',blog.id)
                    blogarea.prepend(new_blog);
                }else{
                    exist_blog.html(new_blog);
                }
            }
        });
}

$(document).ready(function(){
    $("#post_btn").click(addBlog);
    //$("#postarea").keydown(function(e){
    //    if (e.which==13)
    //        addBlog();
    //});
    $(document).on("click", ".togglecomment-btn", btntoggle);
    $(document).on("click", ".addcomment-btn", addComment);
    $(document).on("keypress", ".commentInput", function(e){
        if (e.which==13){
            addComment(e);
        }
    });
    populateBlog();
    $("#postarea").focus();
    window.setInterval(getUpdates,5000);

    function getCookie(name){
        var cookieValue = null;
        if (document.cookie && document.cookie !=''){
            var cookies = document.cookie.split(';');
            for (var i=0;i<cookies.length;i++){
                var cookie = jQuery.trim(cookies[i]);
                // Does this cookie string begin with the name we want?
                if (cookie.substring(0,name.length+1)==(name+'=')){
                    cookieValue = decodeURIComponent(cookie.substring(name.length+1));
                    break;
                }
            }
        }
        return cookieValue;
    }

    var csrftoken = getCookie('csrftoken');
    $.ajaxSetup({
        beforeSend: function (xhr,setting) {
            xhr.setRequestHeader("X-CSRFToken",csrftoken)
        }
    });
});
