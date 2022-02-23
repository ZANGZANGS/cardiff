
function getAjax(endpoint, data, successFunc){
    ajax("GET", endpoint, data, successFunc);
}
function postAjax(endpoint, data, successFunc){
    ajax("POST", endpoint, data, successFunc);
}
function putAjax(endpoint, data, successFunc){
    ajax("PUT", endpoint, data, successFunc);
}
function deleteAjax(endpoint, data, successFunc){
    ajax("DELETE", endpoint, data, successFunc);
}


function ajax(method, endpoint, data, successFunc) {
    const base_url = location.origin

    $.ajax({
        method: method,
        contentType: 'application/json',
        dataType   : 'json',
        url: base_url+"/"+endpoint,
        data : JSON.stringify(data),

    })
        .done(function( res ) {
            if(typeof(successFunc) == 'function'){
                successFunc(res);
            }
        })
        .fail(function ( res ) {
            const msg = "알 수 없는 오류가 발생했습니다.\n잠시후 다시 시도해주십시오.";
            console.log(msg);
            alert(msg);
        });
}