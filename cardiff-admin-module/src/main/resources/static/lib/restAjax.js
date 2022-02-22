
function getAjax(endpoint, data, successFunc, failFunc){
    ajax("GET", endpoint, data, successFunc, failFunc);
}
function postAjax(endpoint, data, successFunc, failFunc){
    ajax("POST", endpoint, data, successFunc, failFunc);
}
function putAjax(endpoint, data, successFunc, failFunc){
    ajax("PUT", endpoint, data, successFunc, failFunc);
}
function deleteAjax(endpoint, data, successFunc, failFunc){
    ajax("DELETE", endpoint, data, successFunc, failFunc);
}


function ajax(method, endpoint, data, successFunc, failFunc) {
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
            if(typeof(failFunc) == 'function'){
                failFunc(res);
            }
        });
}