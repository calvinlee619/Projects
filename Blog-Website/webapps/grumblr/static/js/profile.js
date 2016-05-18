/**
 * Created by kangw on 10/23/15.
 */
function populateBlog(){
    // the user's id is stored in a div tag
    var id =$("#id-data").attr("profile-id");
    var blogarea = $("#blogarea");
    $.get("/get_profile_blogs/"+id)
        .always(function(data){
            for (var i=0;i<data.blogs.length;i++){
                blog = data.blogs[i];
                var new_blog = $(blog.html);
                new_blog.attr('blog-id',blog.id)
                blogarea.append(new_blog);
            }
        });


}

function btntoggle(){
    var blog_id = parseInt($(this).attr('toggle-id'));
    $("div[commentarea-id="+blog_id+"]").fadeToggle();
    $("input[input-id="+blog_id+"]").focus;
}


function addComment(e){
    var id = $(this).attr('btn-id');
    if (id==undefined){
        id = $(event.target).attr('input-id');
    }
    var commentarea = $("input[input-id="+id+"]");

    if (commentarea.val()==undefined || commentarea.val().length == 0) return;
    $.post("/add_comment/"+id,{message:commentarea.val()})
        .always(function(data){
            console.log(data.html)
            var new_comment = $(data.html);
            $("div[commentarea-id="+id+"]").after(new_comment);
            commentarea.val("").focus;

    });
}

$(document).ready(function(){
    $(document).on("click", ".togglecomment-btn", btntoggle);
    $(document).on("click", ".addcomment-btn", addComment);
    $(document).on("keypress", ".commentInput", function(e){
        if (e.which==13){
            addComment(e);
        }
    });

    populateBlog();
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