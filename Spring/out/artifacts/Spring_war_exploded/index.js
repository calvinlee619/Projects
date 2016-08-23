/**
 * Created by kangw on 7/29/16.
 */

function searchRestaurant(){
    var name = "";
    $.post("/searchRestaurants",{name:name}).always(function(data){

    });
}