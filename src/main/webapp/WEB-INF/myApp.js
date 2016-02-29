/**
 * Created by aedpf on 2/29/16.
 */
$(function(){
    var data = {
        labells:[years],
        datasets:[
            {
                data:[100, 34, 59]
            }
        ]
    }
    var option = {};

    var ctx = document.getElementsByClassId("myChart").getContext('2d');      // spot
    var myLineChar = new Chart(ctx).Line(data, option);
}